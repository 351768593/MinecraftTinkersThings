package firok.tiths.intergration.conarm;

import c4.conarm.common.armor.utils.ArmorHelper;
import c4.conarm.common.armor.utils.ArmorTagUtil;
import c4.conarm.lib.capabilities.ArmorAbilityHandler;
import firok.tiths.intergration.conarm.traits.TraitArmorWidening;
import firok.tiths.util.Actions;
import firok.tiths.util.InnerActions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.List;

public class ArmorEvents
{
	public static void onItemUsed(LivingEntityUseItemEvent.Finish event)
	{
		EntityLivingBase enlb=event.getEntityLiving();
		try
		{
			ItemStack stackUsed=event.getItem();
			Item itemUsed=stackUsed.getItem();
			if(itemUsed instanceof ItemPotion) // 广域化
			{
				Iterable<ItemStack> armorStacks=enlb.getArmorInventoryList();
 				FOR_WIDENING:for(ItemStack armorStack:armorStacks)
				{
					if(armorStack!=null && !armorStack.isEmpty())
					{
						List<ITrait> traits= ToolHelper.getTraits(armorStack);
						if(traits.contains(ArmorTraits.widening))
						{
							TraitArmorWidening.widen(enlb,stackUsed.copy());
							break FOR_WIDENING;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void onPlayerWaken(PlayerWakeUpEvent event)
	{
		World world=event.getEntityLiving().world;
		try
		{
			if(!event.wakeImmediately() &&  event.shouldSetSpawn())
			{
				EntityPlayer enlb=event.getEntityPlayer();
				int lvWS=0;

				Iterable<ItemStack> armorStacks=enlb.getArmorInventoryList();
				int i=0;
				FOR_WS:for(ItemStack armorStack:armorStacks)
				{
					i++;
					if(armorStack!=null && !armorStack.isEmpty())
					{
						System.out.println("i=="+i);
						System.out.println(armorStack.getTagCompound());
//						ModifierNBT data;
//						int lvTemp=0;
//						if((lvTemp=getLv(armorStack,ArmorTraits.warmSoft3)) > 0) lvWS=lvTemp;
//						else if((lvTemp=getLv(armorStack,ArmorTraits.warmSoft2)) > 0) lvWS=lvTemp;
//						else if((lvTemp=getLv(armorStack,ArmorTraits.warmSoft1)) > 0) lvWS=lvTemp;
						ArmorAbilityHandler.IArmorAbilities armorAbilities = ArmorAbilityHandler.getArmorAbilitiesData(enlb);
						int temp=armorAbilities.getAbilityLevel("warm_soft1_armor");
						if(temp>=0) lvWS=temp;
					}
				}

				System.out.println("isRemote?"+ world.isRemote +lvWS);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@Optional.Method(modid="conarm")
	private static int getLv(ItemStack stack,ITrait trait)
	{
		return new ModifierNBT(TinkerUtil.getModifierTag(stack, trait.getIdentifier())).level;
	}

	@SideOnly(Side.CLIENT)
	public static void onClientSoundPlay(PlaySoundEvent event)
	{
		try
		{
			EntityPlayerSP playerSP= Minecraft.getMinecraft().player;
			if(playerSP==null) return; // game has not started

			boolean hasDeadening=false; // 是否有消音

			ItemStack stackHead=playerSP.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if(stackHead.isEmpty()) return;

			List<ITrait> modifiers= ToolHelper.getTraits(stackHead);
			if(modifiers.contains(ArmorTraits.deadening))
			{
				hasDeadening=true;
			}

			ISound soundNew=null;

			if(hasDeadening)
			{
				ISound s=event.getSound();

				;

				float volume=(float) InnerActions.get(PositionedSound.class,"volume",s);
				InnerActions.set(PositionedSound.class,"volume",s,volume*0.15f);

				soundNew=s;
//				if(s instanceof PositionedSoundRecord)
//				{
//					PositionedSoundRecord s2=(PositionedSoundRecord)s;
//					soundNew=new PositionedSoundRecord(
//							s2.getSoundLocation(),s2.getCategory(),
//							s2.getVolume() * 0.2f,s2.getPitch(),
//							s2.canRepeat(),s2.getRepeatDelay(),s2.getAttenuationType(),
//							s2.getXPosF(),s2.getYPosF(),s2.getZPosF()
//					);
//				}
//				else if(s instanceof ElytraSound)
//				{
//					;
//				}

				event.setResultSound(soundNew);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
