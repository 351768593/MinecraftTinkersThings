package firok.tiths.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import static firok.tiths.common.Keys.*;
import static firok.tiths.util.Predicates.canTrigger;

// 化学不稳定
public class TraitChemicalInstable extends AbstractTrait
{
	public TraitChemicalInstable()
	{
		super(nameTraitChemicalInstable, colorTraitChemicalInstable);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		if(wasHit && !target.world.isRemote && canTrigger(target.world,0.15f))
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,1));
			target.world.createExplosion(null,target.posX,target.posY,target.posZ,1,true);
		}
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
	{
		if(!player.world.isRemote && canTrigger(player.world,0.06f))
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,10,1));
			player.world.createExplosion(null,pos.getX(),pos.getY(),pos.getZ(),1,true);
		}
	}
}