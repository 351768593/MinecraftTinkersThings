package firok.tiths.intergration.conarm.traits;

import firok.tiths.intergration.conarm.IAbstractArmorTrait;
import firok.tiths.traits.TraitDecoying;
import firok.tiths.util.Actions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static firok.tiths.util.Predicates.canTrigger;

/**
 * 诱食 - 护甲
 */
public class TraitArmorDecoying extends TraitDecoying implements IAbstractArmorTrait
{
	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt)
	{
		if(!player.world.isRemote && canTrigger(player.world,rate))
		{
			Actions.CauseSpawningPassives(player);
		}
		return newDamage;
	}
}
