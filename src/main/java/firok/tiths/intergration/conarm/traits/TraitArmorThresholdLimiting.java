package firok.tiths.intergration.conarm.traits;

import c4.conarm.common.armor.utils.ArmorHelper;
import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static firok.tiths.common.Keys.colorTraitThresholdLimiting;
import static firok.tiths.common.Keys.nameTraitThresholdLimiting;
import static firok.tiths.util.Predicates.canDealWith;

/**
 * 阈限 - 护甲
 */
public class TraitArmorThresholdLimiting extends AbstractArmorTrait
{
	public TraitArmorThresholdLimiting()
	{
		super(nameTraitThresholdLimiting,colorTraitThresholdLimiting);
	}

	@Override
	public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt)
	{
		if(canDealWith(source,true,null,null,null,null))
		{
			if(newDamage<=4) return newDamage;
			float damageMax = player.getHealth()/4;
			if(newDamage>damageMax)
			{
				ArmorHelper.damageArmor(armor,source,1,player);
				newDamage= damageMax<1?1:damageMax;
			}
//		if(player.isServerWorld())
//		{
//			System.out.println("newDamage: "+newDamage);
//		}

		}
		return newDamage;
	}
}
