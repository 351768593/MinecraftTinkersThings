package firok.tiths.intergration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import firok.tiths.common.Configs;
import firok.tiths.common.Items;
import firok.tiths.util.Actions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static firok.tiths.common.Keys.colorTraitCarbonizing;
import static firok.tiths.common.Keys.nameTraitCarbonizing;
import static firok.tiths.util.Predicates.canDealWith;
import static firok.tiths.util.Predicates.canTrigger;

/**
 * 碳化 - 护甲
 */
public class TraitArmorCarbonizing extends AbstractArmorTrait
{
	public TraitArmorCarbonizing()
	{
		super(nameTraitCarbonizing,colorTraitCarbonizing);
	}

	@Override
	public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot)
	{
		return super.onArmorDamage(armor, source, damage, newDamage, player, slot);
	}

	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt)
	{
		if(canDealWith(source,true,null,null,null,null))
		{
			if(!player.world.isRemote && canTrigger(player.world, Configs.Traits.rate_carbonizing_drop))
			{
				Actions.CauseSpawnItem(player,new ItemStack(Items.cinder));
			}
			return newDamage * (source.isFireDamage()? 1.5f: 0.95f);
		}
		return newDamage;
	}
}
