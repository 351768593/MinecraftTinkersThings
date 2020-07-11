package firok.tiths.common;


import baubles.api.BaublesApi;
import firok.tiths.TinkersThings;
import firok.tiths.entity.special.EnderBeacon;
import firok.tiths.intergration.conarm.ArmorEvents;
import firok.tiths.item.IFluid;
import firok.tiths.item.ISoulGather;
import firok.tiths.item.ISoulStore;
import firok.tiths.item.bauble.ItemCharmLapsing;
import firok.tiths.util.Actions;
import firok.tiths.util.Ranges;
import firok.tiths.util.SoulUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.common.TinkerNetwork;
import slimeknights.tconstruct.library.events.ProjectileEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.smeltery.events.TinkerSmelteryEvent;

import java.util.*;

import static firok.tiths.common.Configs.Traits.enable_gluttonic_clear;
import static firok.tiths.common.Keys.*;
import static firok.tiths.common.Traits.thermalGathering;
import static firok.tiths.item.ISoulStore.Common;
import static firok.tiths.util.Predicates.*;

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
		if(en instanceof EntityLightningBolt)
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


						if(ox==oz && ox==0 && oy!=depth)
						{
							if(block==Blocks.STONE ||
								block==Blocks.COBBLESTONE ||
								block==Blocks.DIRT ||
								block==Blocks.SAND ||
								block instanceof BlockFluidBase ||
								block instanceof BlockLeaves ||
								block instanceof BlockLog
							)
								world.setBlockToAir(posTemp);
						}
						else
						{
							if(isAnyStone(block))
								world.setBlockState(posTemp,stateFulgurite);
							else
							{
								if(block==Blocks.DIRT || block==Blocks.SAND || block==Blocks.GRASS || block instanceof BlockLeaves || block instanceof BlockLog)
									world.setBlockToAir(posTemp);
							}
						}
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
	public void onHitBlock(ProjectileEvent.OnHitBlock event) {
		if(event.projectile != null) {

			BlockPos posHit=event.pos;
			World world=event.projectile.world;
			Random rand=world.rand;
			ItemStack stackAmmo = event.projectile.tinkerProjectile.getItemStack();
			NBTTagCompound nbtAmmo = TagUtil.getTagSafe(stackAmmo);
			NBTTagList tagAmmoTraits = TagUtil.getTraitsTagList(nbtAmmo);
			final int sizeAmmoTraits = tagAmmoTraits.tagCount();

			boolean hasPonderous = false;

			for(int i = 0; i < sizeAmmoTraits; i++) {
				String idTraits = tagAmmoTraits.getStringTagAt(i);
				switch (idTraits)
				{
					case nameTraitPonderous: hasPonderous = true; continue;
				}
			}

//			if(hasPonderous) // 重磅
//			{
//				for(int ox=-1;ox<=1;ox++)
//				{
//					for(int oy=-1;oy<=1;oy++)
//					{
//						for(int oz=-1;oz<=1;oz++)
//						{
//							BlockPos posTemp=posHit.add(ox,oy,oz);
//							IBlockState stateTemp=world.getBlockState(posTemp);
//							Block blockTemp=stateTemp.getBlock();
//							boolean hasTile=blockTemp.hasTileEntity(stateTemp);
//							float hardness=stateTemp.getBlockHardness(world,posTemp);
//							ItemStack stack=null;
//
//							if(!hasTile && hardness <= 1.55)
//							{
//								if(!world.isRemote) // server sided handling
//								{
//									// send the blockbreak event
//									int xp = ForgeHooks.onBlockBreakEvent(world, ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, posTemp);
//									if(xp == -1) {
//										return;
//									}
//
//									// serverside we reproduce ItemInWorldManager.tryHarvestBlock
//
//									TileEntity tileEntity = world.getTileEntity(posTemp);
//									// ItemInWorldManager.removeBlock
//									if(blockTemp.removedByPlayer(stateTemp, world, posTemp, player, true)) { // boolean is if block can be harvested, checked above
//										blockTemp.onBlockDestroyedByPlayer(world, posTemp, stateTemp);
//										blockTemp.harvestBlock(world, player, posTemp, stateTemp, tileEntity, stack);
//										blockTemp.dropXpOnBlockBreak(world, posTemp, xp);
//									}
//
//									// always send block update to client
//									TinkerNetwork.sendPacket(player, new SPacketBlockChange(world, posTemp));
//								}
//								else // client sided handling
//								{
//									// clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
//									// the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.
//
//									// following code can be found in PlayerControllerMP.onPlayerDestroyBlock
//									world.playBroadcastSound(2001, posTemp, Block.getStateId(stateTemp));
//									if(blockTemp.removedByPlayer(stateTemp, world, posTemp, player, true)) {
//										blockTemp.onBlockDestroyedByPlayer(world, posTemp, stateTemp);
//									}
//								}
//							}
//						}
//					}
//				}
//			}
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

	@SubscribeEvent
	public static void onEntityDamaged(LivingHurtEvent event)
	{
		EntityLivingBase enlb=event.getEntityLiving();
		DamageSource source=event.getSource();
		float originDamage=event.getAmount();
		if(source.isFireDamage()) // 判断是不是火焰伤害
		{
			List<ITrait> traits=new ArrayList<>();
			traits.addAll(ToolHelper.getTraits(enlb.getHeldItemMainhand()));
			traits.addAll(ToolHelper.getTraits(enlb.getHeldItemOffhand()));
			if(traits.contains(thermalGathering))
			{
				event.setAmount( originDamage / 2 );
			}

		}

		LAPSING:if(enlb instanceof EntityPlayer)
		{
			EntityPlayer player=(EntityPlayer)enlb;
			ItemCharmLapsing itemCharm=(ItemCharmLapsing)Items.lapsingCharm;

			ItemStack stackCharm=itemCharm.getCharmNoneCD(player);

			if(stackCharm==null) break LAPSING;
			itemCharm.lapse(stackCharm,player);
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

	@SideOnly(Side.SERVER)
	@SubscribeEvent
	public static void onServerTick(TickEvent.ServerTickEvent event)
	{
		Datas.Server server=Datas.Server.instance();
		if(event.phase == TickEvent.Phase.END && server != null)
		{
			server.update();
		}
	}

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
