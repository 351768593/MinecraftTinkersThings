package firok.tiths.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import static firok.tiths.common.Keys.*;

// 斑斓
public class TraitGorgeous extends AbstractTrait
{
	public TraitGorgeous()
	{
		super(nameTraitGorgeous, colorTraitGorgeous);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical)
	{
		return newDamage * (player.world.rand.nextFloat() * 0.1f + 1.1f);
	}
}