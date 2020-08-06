package firok.tiths.intergration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.utils.ToolHelper;

import static firok.tiths.common.Keys.colorTraitAquatic;
import static firok.tiths.common.Keys.nameTraitAquatic;
import static firok.tiths.util.Predicates.canTick;

/**
 * 水生 - 护甲
 */
public class TraitArmorAquatic extends AbstractArmorTrait
{
	public TraitArmorAquatic()
	{
		super(nameTraitAquatic,colorTraitAquatic);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if(!world.isRemote && canTick(world,100,0) && entity instanceof EntityLivingBase)
		{
			EntityLivingBase enlb=(EntityLivingBase)entity;
			if(entity.isInWater())
			{
				ToolHelper.healTool(tool,1,enlb);
			}
			else
			{
				ToolHelper.damageTool(tool,1,enlb);
			}
		}
	}
}
