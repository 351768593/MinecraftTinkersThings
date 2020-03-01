package firok.tiths.traits;

import firok.tiths.util.Actions;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import static firok.tiths.common.Keys.*;
import static firok.tiths.util.Predicates.canTrigger;

// 诱食
public class TraitDecoying extends AbstractTrait
{
	public static final float rate=0.08f;
	public TraitDecoying()
	{
		super(nameTraitDecoying, colorTraitDecoying);
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
	{
		if(!world.isRemote && canTrigger(world, rate))
		{
			Actions.CauseSpawningPassives(player);
		}
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		if(!target.getEntityWorld().isRemote && canTrigger(player.world, rate))
		{
			Actions.CauseSpawningPassives(player);
		}
	}

}
