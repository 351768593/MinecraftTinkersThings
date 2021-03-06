package firok.tiths.potion;

import firok.tiths.TinkersThings;
import firok.tiths.common.Keys;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

// 沉重
public class PotionHeavy extends BasePotion
{
	public PotionHeavy()
	{
		super(icon("heavy"),true, Keys.colorPotionHeavy);
	}

	@Override
	public boolean isReady(int tick, int level)
	{
		return true;
	}

	@Override
	public void performEffect(EntityLivingBase entity, int level)
	{
		if(entity instanceof EntityPlayer && ((EntityPlayer) entity).isCreative()) return;
		level++;
		if(entity.motionY>-3*level) entity.motionY-=0.08f*level;
	}
}
