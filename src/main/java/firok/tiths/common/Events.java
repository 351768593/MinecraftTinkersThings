package firok.tiths.common;


import baubles.api.BaublesApi;
import firok.tiths.TinkersThings;
import firok.tiths.entity.special.EnderBeacon;
import firok.tiths.intergration.conarm.ArmorEvents;
import firok.tiths.item.IFluid;
import firok.tiths.item.ISoulGather;
import firok.tiths.item.ISoulStore;
import firok.tiths.item.bauble.ItemCharmLapsing;
import firok.tiths.traits.IHitBlockProjectile;
import firok.tiths.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.EntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.events.ProjectileEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.smeltery.events.TinkerSmelteryEvent;

import java.util.*;

import static firok.tiths.common.Configs.Traits.enable_gluttonic_clear;
import static firok.tiths.common.Traits.thermalGathering;
import static firok.tiths.item.ISoulStore.Common;
import static firok.tiths.util.Predicates.*;
import static net.minecraftforge.fml.common.eventhandler.EventPriority.HIGHEST;

@Mod.EventBusSubscriber(modid=TinkersThings.MOD_ID)
public class Events
{
	@SubscribeEvent
	public static void onBlockBroken(BlockEvent.BreakEvent event)
	{
		World world=event.getWorld();
		if(world.isRemote) return; // 只在服务端执行

		BlockPos pos=event.getPos();
		Block block=world.getBlockState(pos).getBlock();

		// 碎冰
		TRY_DROP_BROKEN_ICE:if(block== Blocks.ICE)
		{
			int amount=1+world.rand.nextInt(3);
			ItemStack stack=new ItemStack(Items.brokenIce,amount);
			EntityItem ei=new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),stack);

			world.spawnEntity(ei); // spawn broken ice
			break TRY_DROP_BROKEN_ICE;
		}
		else TRY_DROP_BLACKROCK: if(block == Blocks.COAL_ORE)
		{
			if(!canTrigger(world,0.25f)) break TRY_DROP_BLACKROCK;

			int amount = 1 + (world.rand.nextBoolean() ? 1 : 0);
			ItemStack stack=new ItemStack(Items.blackrock,amount);
			EntityItem ei = new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),stack);

			world.spawnEntity(ei);
			break TRY_DROP_BLACKROCK;
		}
		// 贝壳
		else TRY_DROP_SHELL:if(block== Blocks.SAND)
		{
			if(!canTrigger(world,0.14f)) break TRY_DROP_SHELL;

			Biome biome=world.getBiome(pos);
			if(biome== Biomes.BEACH ||
					biome==Biomes.COLD_BEACH ||
					biome==Biomes.OCEAN ||
					biome==Biomes.DEEP_OCEAN
			){
				int amount= 1+ world.rand.nextInt(2);
				ItemStack stack=new ItemStack(Items.shell,amount);
				EntityItem ei=new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),stack);
				world.spawnEntity(ei);
			}
			break TRY_DROP_SHELL;
		}
	}

	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent event)
	{
		World world=event.getWorld();
		Random rand=world.rand;
		if(world.isRemote || !canTrigger(rand, Configs.Potion.rate_midas_vision_drop)) return;

		EntityPlayer player = event.getPlayer();
		PotionEffect peMidasVision=player.getActivePotionEffect(Potions.midas_vision);
		int maxGoldenNugget = peMidasVision!=null ? (peMidasVision.getAmplifier()+1) : 0;
		if(maxGoldenNugget>0) maxGoldenNugget=rand.nextInt(maxGoldenNugget);
		if(maxGoldenNugget>0)
		{
			Actions.CauseSpawnItem(player,new ItemStack(net.minecraft.init.Items.GOLD_NUGGET,maxGoldenNugget));
		}
	}

	@SubscribeEvent
	public static void onEntityItemPickup(EntityItemPickupEvent event)
	{
		EntityItem ei=event.getItem();
		ItemStack stack=ei.getItem();
		EntityPlayer player=event.getEntityPlayer();
		if(stack.getItem()==Items.soul)
		{
			int point=stack.getCount() * 1000;
			SoulUtil.chargeSoul(event.getEntityPlayer(),point);
			ei.setDead();
			event.setCanceled(true);
		}
		else if(isAnyStone(stack) && BaublesApi.isBaubleEquipped(player,Items.beltStonePhasing)>=0)
		{
			ei.setDead();
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public static void onEntityDead(LivingDeathEvent event)
	{
		EntityLivingBase livingDead=event.getEntityLiving();
		World world=livingDead.world;
		if(world.isRemote) return;
		Random rand=world.rand;

		List<ItemStack> stack2drops=new ArrayList<>();

		if(canTrigger(rand,0.005))
		{
			int temp=rand.nextInt(100);
			ItemStack record2drop;
			if(temp<33)
			{
				record2drop=new ItemStack(Items.recordTinkersEfforts);
			}
			else if(temp<66)
			{
				record2drop=new ItemStack(Items.recordTinkersImagination);
			}
			else
			{
				record2drop=new ItemStack(Items.recordTinkersWill);
			}
			stack2drops.add(record2drop);
		}

		EntityLivingBase attacker=livingDead.getRevengeTarget();
		if(attacker!=null && attacker.isEntityAlive())
		{
			Map<ItemStack,ISoulGather> stackSoulGathers=SoulUtil.getAllSoulGathers(attacker);

			int dropBase=0,dropExtra=0,dropTotal=0;

			for(Map.Entry<ItemStack,ISoulGather> entry:stackSoulGathers.entrySet())
			{
				ItemStack stackSoulGather=entry.getKey();
				ISoulGather iSoulGather=entry.getValue();

				dropBase+=iSoulGather.soulDropBase(stackSoulGather);
				dropExtra+=iSoulGather.soulDropExtra(stackSoulGather,attacker,livingDead);
			}

			if(dropBase>0) dropTotal+=dropBase;
			if(dropExtra>0) dropTotal+=rand.nextInt(dropExtra+1);

			stack2drops.add(new ItemStack(Items.soul,dropTotal));
		}

		if(livingDead instanceof EntityPlayer) // 玩家死亡
		{
			EntityPlayer player=(EntityPlayer)livingDead;
			List<ItemStack> soulStacks=SoulUtil.getAllSoulStores(player);
			Map<ItemStack,Integer> mapDeathDrainPriorities=new HashMap<>();
			for(ItemStack soulStack:soulStacks)
				mapDeathDrainPriorities.put(soulStack,((ISoulStore)soulStack.getItem()).deathDrainPriority(soulStack));

			soulStacks.sort(Comparator.comparing(is -> mapDeathDrainPriorities.getOrDefault(is, Common)));

			DEATH_DRAIN:for(ItemStack soulStack:soulStacks) // 死亡时的灵魂逸散
			{
				ISoulStore iSoulStore =(ISoulStore)soulStack.getItem();

				if(!iSoulStore.canDeathDrain(soulStack,world)) continue; // 如果不逸散就直接跳过

				int drain= iSoulStore.countDeathDrain(soulStack,world);
				if(drain<=0) continue;
				iSoulStore.costSoul(soulStack,player,drain,true); // 先计算逸散

				boolean interrupted=iSoulStore.onDeathDrain(soulStack,player,drain,event); // 再触发事件
				if(interrupted) break DEATH_DRAIN;
			}
		}
		else if(livingDead instanceof EntityCaveSpider) // 洞穴蜘蛛
		{
			if(canTrigger(rand,0.35f))
			{
				stack2drops.add(new ItemStack(Items.spiderLeg,1+rand.nextInt(2)));
			}
		}
		else if(livingDead instanceof EntitySpider) // 蜘蛛
		{
			if(canTrigger(rand,0.45f))
			{
				stack2drops.add(new ItemStack(canTrigger(rand,0.3f)?Items.hardSpiderLeg:Items.spiderLeg,1+rand.nextInt(1)));
			}
		}
		else if(livingDead instanceof EntityWitherSkeleton && canTrigger(rand,0.3)) // 凋灵骷髅
		{
			stack2drops.add(new ItemStack(Items.witheringEssence,1));
		}
		else if(livingDead instanceof EntityWither) // 凋灵
		{
			stack2drops.add(new ItemStack(Items.witheringEssence,2+rand.nextInt(3)));
		}
		else if(livingDead instanceof EntityDragon) // 末影龙
		{
			stack2drops.add(new ItemStack(Items.enderDragonSquama));
		}

		// 掉落物品
		for(ItemStack stack2drop:stack2drops)
		{
			Actions.CauseSpawnItem(livingDead,stack2drop);
		}


	}

	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		World world=event.getWorld();
		if(world.isRemote) return;

//		try
//		{
//			// 石之相变
//			Entity entity=event.getEntity();
//			if(entity instanceof EntityPlayer)
//			{
//				BlockPos posClicked=event.getPos();
//				BlockPos posReplace=posClicked.offset(event.getFace());
//				boolean canReplace=world.getBlockState(posReplace).getBlock().isReplaceable(world,posReplace);
//
//				if(canReplace)
//				{
//					EntityPlayer player=(EntityPlayer)entity;
//					ItemStack stackMain=player.getHeldItem(EnumHand.MAIN_HAND);
//					ItemStack stackSub=player.getHeldItem(EnumHand.OFF_HAND);
//
//
//					if(costStone(stackMain) || costStone(stackSub))
//					{
//						world.setBlockState(posReplace,Blocks.COBBLESTONE.getDefaultState());
//					}
//				}
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}

	@SubscribeEvent
	public static void onEntityJoin(net.minecraftforge.event.entity.EntityJoinWorldEvent event)
	{
		World world=event.getWorld();
		Random rand=world.rand;
		if(world.isRemote) return; // 只在服务端进行

		Entity en=event.getEntity();
		if(en instanceof EntityLightningBolt && Configs.Gameplay.enable_fulgurite_generation)
		{
			BlockPos pos=en.getPosition(); // 中心位置

			IBlockState stateFulgurite=firok.tiths.common.Blocks.blockFulgurite.getDefaultState(); // 闪电熔岩

			final short depth=(short)(-5-rand.nextInt(4));
			for(int ox=-1;ox<=1;ox++)
			{
				for(int oz=-1;oz<=1;oz++)
				{
					for(int oy=0;oy>=depth;oy--)
					{
						BlockPos posTemp=pos.add(ox,oy,oz);
						Block block=world.getBlockState(posTemp).getBlock();

						if(isAnyStone(block) || isDirt(block) || isEndStone(block))
							world.setBlockState(posTemp,stateFulgurite);

//						if(ox==oz && ox==0 && oy!=depth)
//						{
//							if(block==Blocks.STONE ||
//								block==Blocks.COBBLESTONE ||
//								block==Blocks.DIRT ||
//								block==Blocks.SAND ||
//								block instanceof BlockFluidBase ||
//								block instanceof BlockLeaves ||
//								block instanceof BlockLog
//							)
//								world.setBlockToAir(posTemp);
//						}
//						else
//						{
//							if(isAnyStone(block))
//								world.setBlockState(posTemp,stateFulgurite);
//							else
//							{
//								if(block==Blocks.DIRT || block==Blocks.SAND || block==Blocks.GRASS || block instanceof BlockLeaves || block instanceof BlockLog)
//									world.setBlockToAir(posTemp);
//							}
//						}
					}
				}
			}
		}
		else if(en instanceof EntityFishHook)
		{
			try
			{
				EntityFishHook hook=(EntityFishHook)en;

				EntityPlayer player=hook.getAngler();
				if(player==null) return;

				InventoryPlayer inv=player.inventory;
				final int size=inv.getSizeInventory();
				for(int i=0;i<size;i++)
				{
					ItemStack stack=inv.getStackInSlot(i);
					if(stack.isEmpty()) continue;

					if(stack.getItem()!=Items.shell) continue;

					stack.shrink(1);

					hook.setLureSpeed(1);

//					int lureSpeed=(int) InnerActions.get(EntityFishHook.class,"lureSpeed",hook);
					final String field_name="field_191519_ax";
					int lureSpeed=ObfuscationReflectionHelper.getPrivateValue(EntityFishHook.class,hook,field_name);
					lureSpeed++;
//					InnerActions.set(EntityFishHook.class,"lureSpeed",hook,lureSpeed);
					ObfuscationReflectionHelper.setPrivateValue(EntityFishHook.class,hook,lureSpeed,field_name);

					break;
				}
			}
			catch (Exception ignored) { }
		}
	}

	@SubscribeEvent
	public static void onEntityStruckByLightning(EntityStruckByLightningEvent event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onEntityStruckByLightning(event);
		}
	}

	/**
	 * todo 后面可能要改成 ProjectileImpactEvent.Throwable
	 */
	@SubscribeEvent
	public static void onHitBlock(ProjectileEvent.OnHitBlock event)
	{
		try
		{
			if(event.projectile != null)
			{
				final BlockPos posHit=event.pos;
				final World world=event.projectile.world;
				final IBlockState stateHit=event.blockState;
				final Random rand=world.rand;
				final ItemStack stackAmmo = event.projectile.tinkerProjectile.getItemStack();
//					final NBTTagCompound nbtAmmo = TagUtil.getTagSafe(stackAmmo);
//					final NBTTagList tagAmmoTraits = TagUtil.getTraitsTagList(nbtAmmo);
//					final int sizeAmmoTraits = tagAmmoTraits.tagCount();
				final Entity shootingEntity = event.projectile.shootingEntity;


				List<ITrait> traits=ToolHelper.getTraits(stackAmmo);
				for(ITrait trait:traits)
				{
					if(!(trait instanceof IHitBlockProjectile)) continue;

					((IHitBlockProjectile) trait).hitBlock(
							event,posHit,world,stateHit,rand,stackAmmo,shootingEntity
					);

					if(event.isCanceled()) break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public static void onMobDrop(LivingDropsEvent event)
	{
		EntityLivingBase enlb=event.getEntityLiving();
		EntityLivingBase enlbRevTarget=enlb.getRevengeTarget();
		World world=enlb.world;
		if(enlbRevTarget instanceof EntityPlayer && enlbRevTarget.isEntityAlive())
		{
			EntityPlayer player=(EntityPlayer)enlbRevTarget;
			ItemStack stack=player.getHeldItemMainhand();
			final List<EntityItem> drops=event.getDrops();
			final List<ITrait> traits=ToolHelper.getTraits(stack);

			// 检查有没有各类属性
			boolean hasRancher=traits.contains(Traits.rancher);
			boolean hasMidasDesiring=traits.contains(Traits.midasDesiring);
			boolean hasGluttonic=enable_gluttonic_clear && traits.contains(Traits.gluttonic);

			if(hasRancher && !hasGluttonic && isAnimals(enlb) && canTrigger(world,0.4)) // 牧场主
			{
				for(EntityItem ei:drops)
				{
					ItemStack stackEi=ei.getItem();
					stackEi.setCount( stackEi.getCount() * 2 );
				}
			}
			if(hasMidasDesiring && !hasGluttonic) // 迈达斯之欲 转化物品 // 有暴食的话没必要执行了 // info 其实还能优化 但是懒得优化了
			{
				for(EntityItem ei:drops)
				{
					ItemStack stackEi=ei.getItem();
					if(stackEi.getItem()== Item.getItemFromBlock(Blocks.GOLD_ORE))
					{
						ei.setItem(new ItemStack(net.minecraft.init.Items.GOLD_INGOT,stackEi.getCount()*2));
					}
					else if(canTrigger(enlb.world,0.04f))
					{
						ei.setItem(new ItemStack(net.minecraft.init.Items.GOLD_INGOT,stackEi.getCount()));
					}
				}
			}
			if(hasGluttonic) drops.clear(); // 暴食 清空掉落物
		}

	}

	@SubscribeEvent(priority = HIGHEST)
	public static void onLivingHurt(LivingHurtEvent event)
	{
		final EntityLivingBase enlb=event.getEntityLiving();
		final DamageSource source=event.getSource();

		if(source.isFireDamage()) // 判断是不是火焰伤害
		{
			float originDamage=event.getAmount();
			List<ITrait> traits=new ArrayList<>();
			traits.addAll(ToolHelper.getTraits(enlb.getHeldItemMainhand()));
			traits.addAll(ToolHelper.getTraits(enlb.getHeldItemOffhand()));
			if(traits.contains(thermalGathering))
			{
				event.setAmount( originDamage / 2 );
			}

		}

		WHEN_PLAYER:if(enlb instanceof EntityPlayer) // 玩家相关的逻辑判断
		{
			EntityPlayer player=(EntityPlayer)enlb;

			if(TinkersThings.enableConarm())
			{
				ArmorEvents.onPlayerHurt(event,player);
				if(event.getAmount()<=0 || event.isCanceled()) return; // 如果在执行护甲效果时被取消则直接return
			}

			ItemCharmLapsing itemCharm=(ItemCharmLapsing)Items.lapsingCharm;

			ItemStack stackCharm=itemCharm.getCharmNoneCD(player);

			if(stackCharm==null) break WHEN_PLAYER;
			itemCharm.lapse(stackCharm,player);
		}

		EMPATHIC: if(!DamageSources.TypeEmpathic.equals(source.getDamageType())) // 共感伤害不会触发共感
		{
			List<Entity> entitiesSurroundings = EntityFinders.Nearby( enlb, 8, Selectors.livingBaseAlive );
			float damageOriginal = event.getAmount();
			for(Entity entitySurrounding:entitiesSurroundings)
			{
				EntityLivingBase enlbSurrounding = (EntityLivingBase) entitySurrounding;

				PotionEffect pe = enlbSurrounding.getActivePotionEffect(Potions.empathic);
				if(pe == null) continue;

				float hpNow = enlbSurrounding.getHealth(); // 当前血量
				float damagePer = damageOriginal * 0.2f; // 受到最大共感伤害
				float damageNow = Math.min( hpNow-1, damagePer ); // 最大只会削减hp到1
				if(damageNow <= 0) continue;

				enlbSurrounding.attackEntityFrom(DamageSources.Empathic(enlb),damageNow);
			}
		}
	}

	@SuppressWarnings("all")
	@SubscribeEvent
	public static void onEntityEnderTeleport(EnderTeleportEvent event)
	{

		EntityLivingBase enlb=event.getEntityLiving();

		// 掉落末影裂隙碎片
		if(!enlb.world.isRemote && canTrigger(enlb.world,0.15f))
		{
			Actions.CauseSpawnItem(enlb,new ItemStack(Items.enderCreviceShard));
		}

		// 寻找周围的末影信标
		List<EnderBeacon> beacons=(List<EnderBeacon>)(List)enlb.world.getEntitiesWithinAABB(EnderBeacon.class,
				Ranges.Neighbour(enlb,16)
		);

		if(beacons.size()<=0) return;

		// 找一个最近的
		float minDistance=Float.MAX_VALUE;
		EnderBeacon beacon=null;

		for(EnderBeacon beaconTemp:beacons)
		{
			float distanceTemp=beaconTemp.getDistanceToEntity(enlb);
			if(distanceTemp<minDistance)
			{
				minDistance=distanceTemp;
				beacon=beaconTemp;
			}
		}

		// 传送过去
		event.setTargetX(beacon.posX);
		event.setTargetY(beacon.posY);
		event.setTargetZ(beacon.posZ);
	}

	// 允许在游戏内菜单更改配置文件
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(TinkersThings.MOD_ID))
		{
			ConfigManager.sync(TinkersThings.MOD_ID, Config.Type.INSTANCE);
//			TinkersThings.log("config changed : "+event.getConfigID());
			// 重载世界生成器
//			WorldGens.getInstance().reload();
		}
	}

	@SubscribeEvent
	public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(Configs.General.isShowLoginWarning())
		{
			EntityPlayer player=event.player;
			String name=TinkersThings.version.name().toLowerCase();
			player.sendMessage( new TextComponentTranslation("warning.tiths."+name) );
		}
	}

	@SubscribeEvent
	public static void onItemUsed(LivingEntityUseItemEvent.Finish event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onItemUsed(event);
			/*
			广域化 - 护甲
			* */
		}
	}

	@SubscribeEvent
	public static void onPlayerWaken(PlayerWakeUpEvent event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onPlayerWaken(event);
			/*
			相关内容:
			温软 - 护甲
			* */
		}
	}

//	@SubscribeEvent
//	public static void onLoot(LootTableLoadEvent event)
//	{
//		ResourceLocation name=event.getName();
//		LootTableManager manager=event.getLootTableManager();
//		LootTable table=event.getTable();
//
//		LootPool pool=new LootPool();
//	}

	private static boolean has(String[] strs,String str)
	{
		for(String temp:strs) if(str.equals(temp)) return true;
		return false;
	}

	@SubscribeEvent
	public static void onToolCrafting(TinkerCraftingEvent.ToolCraftingEvent event)
	{
		EntityPlayer player=event.getPlayer();
		ItemStack stack=event.getItemStack();
		Item item=stack.getItem();
		if(!player.world.isRemote)
		{
			try
			{
				// 输出部件列表信息
//				System.out.println("部件列表");
//				List<ItemStack> toolparts=event.getToolParts();
//				for(ItemStack toolpart:toolparts)
//				{
//					if(toolpart.isEmpty()) continue;
//					System.out.println(toolpart.getUnlocalizedName() + " : "+((IMaterialItem)toolpart.getItem()).getMaterialID(toolpart));
//				}

//				List<Material> materials = TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(stack));
//				List<PartMaterialType> component = itemToolCore.getRequiredComponents();

				// 获取输出材料和部件信息
//				System.out.println("\n材料:");
//				for(Material mat:materials)
//				{
//					System.out.print(mat.identifier);
//				}
//				System.out.println("\npmts :");
//				int i=0;
//				for(PartMaterialType pmt:component)
//				{
//					System.out.println("pmt "+i);
//					Set<IToolPart> parts=(Set<IToolPart>)Actions.get(PartMaterialType.class,"neededPart",pmt);
//					String[] neededTypes=(String[])Actions.get(PartMaterialType.class,"neededTypes",pmt);
//					System.out.println("parts :");
//					for(IToolPart part:parts)
//					{
//						System.out.println(part.toString());
//						if(part instanceof ToolPart)
//						{
//							ToolPart _part=(ToolPart)part;
//							System.out.println(_part.getUnlocalizedName());
//						}
//					}
//					System.out.println("needed types :");
//					System.out.println(Arrays.toString(neededTypes));
//					i++;
//				}

				// 工作台gui的代码
//				for(int i = 0; i < component.size(); i++) {
//					PartMaterialType pmt = component.get(i);
//					Material material = materials.get(i);
//
//					// get (one possible) toolpart used to craft the thing
//					Iterator<IToolPart> partIter = pmt.getPossibleParts().iterator();
//					if(!partIter.hasNext()) {
//						continue;
//					}
//
//					IToolPart part = partIter.next();
//					ItemStack partStack = part.getItemstackWithMaterial(material);
//					if(partStack != null) {
//						// we have the part, add it
//						tooltips.add(material.getTextColor() + TextFormatting.UNDERLINE + partStack.getDisplayName());
//
//						Set<ITrait> usedTraits = Sets.newHashSet();
//						// find out which stats and traits it contributes and add it to the tooltip
//						for(IMaterialStats stats : material.getAllStats()) {
//							if(pmt.usesStat(stats.getIdentifier())) {
//								tooltips.addAll(stats.getLocalizedInfo());
//								for(ITrait trait : pmt.getApplicableTraitsForMaterial(material)) {
//									if(!usedTraits.contains(trait)) {
//										tooltips.add(material.getTextColor() + trait.getLocalizedName());
//										usedTraits.add(trait);
//									}
//								}
//							}
//						}
//						tooltips.add("");
//					}
//				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onClientSoundPlay(PlaySoundEvent event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onClientSoundPlay(event);
		}
	}

	@SubscribeEvent
	public static void onMelting(TinkerSmelteryEvent.OnMelting event)
	{
		if(event.itemStack==null) return;
		Item item=event.itemStack.getItem();
		if(item instanceof IFluid)
		{
			IFluid itemFluid=(IFluid)item;
			FluidStack fluidStack=itemFluid.getFluidStack(event.itemStack);
			if(fluidStack==null) return;

			event.result=fluidStack;
		}

	}

	@SubscribeEvent
	public static void onMobGriefing(EntityMobGriefingEvent event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onMobGriefing(event);
		}
	}

	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event)
	{
		Datas.Server server=Datas.Server.instance();
		if(event.phase == TickEvent.Phase.END && server != null)
		{
			server.update();
		}
	}

//	/**
//	 * 区块加载事件
//	 * <ul>
//	 *     <li>维护{@code IChunkListener}</li>
//	 * </ul>
//	 */
//	@SubscribeEvent
//	public static void onChunkLoad(ChunkEvent.Load event)
//	{
//		if(event.getWorld().isRemote) return;
//
//		Chunk chunk=event.getChunk();
////		ChunkPos posChunk=chunk.getPos();
//		Map<BlockPos,TileEntity> mapTE=chunk.getTileEntityMap();
//		for(Map.Entry<BlockPos,TileEntity> entry:mapTE.entrySet())
//		{
////			BlockPos pos=entry.getKey();
//			TileEntity te=entry.getValue();
//			if(te instanceof IChunkListener)
//			{
//				((IChunkListener) te).beforeChunkLoaded();
//			}
//		}
//	}
//	/**
//	 * 区块卸载事件
//	 * <ul>
//	 *     <li>维护{@code IChunkListener}</li>
//	 * </ul>
//	 */
//	@SubscribeEvent
//	public static void onChunkUnload(ChunkEvent.Unload event)
//	{
//		if(event.getWorld().isRemote) return;
//
//		Chunk chunk=event.getChunk();
////		ChunkPos posChunk=chunk.getPos();
//		Map<BlockPos,TileEntity> mapTE=chunk.getTileEntityMap();
//		for(Map.Entry<BlockPos,TileEntity> entry:mapTE.entrySet())
//		{
////			BlockPos pos=entry.getKey();
//			TileEntity te=entry.getValue();
//			if(te instanceof IChunkListener)
//			{
//				((IChunkListener) te).beforeChunkUnloaded();
//			}
//		}
//	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event)
	{
		Datas.Client.init();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
	{
		Datas.Client.uninit();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event)
	{
		Datas.Client client=Datas.Client.instance();
		if(event.phase == TickEvent.Phase.END && client != null)
		{
			client.update();
		}
	}

	@SubscribeEvent
	public static void onTrample(BlockEvent.FarmlandTrampleEvent event)
	{
		if(TinkersThings.enableConarm())
		{
			ArmorEvents.onTrample(event);
		}

		Entity entity = event.getEntity();
		if(entity instanceof EntityLivingBase)
		{
			// 你踩老子田 你完了 你等死吧
			EntityLivingBase living=(EntityLivingBase)entity;
			living.addPotionEffect(new PotionEffect(Potions.farmland_trampler,300,0));
		}
	}

	/**
	 * <ul>
	 *     <li>特性 - 蔽叶</li>
	 * </ul>
	 * @param event 方块碰撞箱检测事件
	 */
	@SuppressWarnings("all")
	@SubscribeEvent
	public static void onCollideWith(GetCollisionBoxesEvent event)
	{
		try
		{
			Entity entity=event.getEntity();
			AxisAlignedBB aabb=event.getAabb();
			List<AxisAlignedBB> listCollect=event.getCollisionBoxesList();
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer player=(EntityPlayer)entity;

				CHECK_REMOVE:
				{
					if(player.getActivePotionEffect(Potions.leaves_hiding)==null) break CHECK_REMOVE;

					World world=player.world;
					BlockPos posPlayer=player.getPosition();
					BlockPos posTemp;
					FOR_X:for(int tempX=-1;tempX<=1;tempX++)
					{
						FOR_Z:for(int tempZ=-1;tempZ<=1;tempZ++)
						{
							FOR_Y:for(int tempY=-1;tempY<=2;tempY++)
							{
								posTemp=posPlayer.add(tempX,tempY,tempZ);
								IBlockState stateTemp=world.getBlockState(posTemp);
								Block blockTemp=stateTemp.getBlock();
								boolean shouldRemove=false;
								if( blockTemp instanceof IShearable)
								{
									shouldRemove=true;
								}

								if(shouldRemove)
								{
									List<AxisAlignedBB> listTemp=new ArrayList<>(2);
									// IBlockState state, World worldIn, BlockPos pos,
									// AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
									// @Nullable Entity entityIn, boolean p_185477_7_
									blockTemp.addCollisionBoxToList(
											stateTemp,world,posTemp,
											aabb,listTemp,player,false
									);
									for(AxisAlignedBB aabbTemp:listTemp)
									{
										if(aabbTemp==null) continue;
										Iterator<AxisAlignedBB> iterCollect=listCollect.iterator();
										while(iterCollect.hasNext())
										{
											AxisAlignedBB aabbCollect= iterCollect.next();
											if(aabbCollect==null) continue;
											if(aabbTemp.equals(aabbCollect))
											{
												iterCollect.remove();
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}
		catch (Exception ignored) { }
	}
}
