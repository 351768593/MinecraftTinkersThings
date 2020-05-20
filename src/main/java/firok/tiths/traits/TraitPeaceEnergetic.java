package firok.tiths.traits;

import firok.tiths.common.Configs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import static firok.tiths.common.Keys.colorTraitPeaceEnergetic;
import static firok.tiths.common.Keys.nameTraitPeaceEnergetic;
import static firok.tiths.util.Predicates.canTick;

// 平和能量
public class TraitPeaceEnergetic extends AbstractTrait
{
	public TraitPeaceEnergetic()
	{
		super(nameTraitPeaceEnergetic, colorTraitPeaceEnergetic);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(isSelected && !world.isRemote &&
		   Math.abs(entity.motionX) < 0.15 &&
           Math.abs(entity.motionY) < 0.15 &&
           Math.abs(entity.motionZ) < 0.15 &&
           canTick(world,20,1) && entity instanceof EntityLivingBase)
		{
			EntityLivingBase enlb=(EntityLivingBase)entity;
			enlb.heal(Configs.Traits.factor_peace_energetic_heal);
		}
	}
}
