package firok.tiths.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

public class BasePotion extends Potion
{
	ResourceLocation rl;
	public BasePotion(ResourceLocation rl,boolean isBadEffect, int color)
	{
		super(isBadEffect, color);
		this.rl=rl;
	}

	@Override
	public boolean isReady(int p_isReady_1_, int p_isReady_2_)
	{
		return false;
	}

	@Override
	public void performEffect(EntityLivingBase entity, int level)
	{
		;
	}

	@Override
	public boolean hasStatusIcon() {
		return false;
	}

	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		this.render(x+8, y+8, effect, mc, 1);
	}

	// 原版药水效果的图标是集中在一张纹理上的。
	// 原版默认的逻辑是根据 Potion 类下的 setIconIndex（func_76399_b）和 getStatusIconIndex（func_76392_e）来确定该用哪一个图标。
	// 其中，getStatusIconIndex 使用的 int 是 x + (y * 8) 的结果，
	// 其中 x 和 y 是纹理上的坐标（u、v），
	// 需要通过 index % 8 和 index / 8 转换回来。
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		this.render(x+4,y+4,effect,mc,1);
	}

	public void render(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		// 绘制逻辑
		mc.getTextureManager().bindTexture(rl);
		// x, y 为绘制的起点，u, v 为纹理的起点，w, h 为选取的纹理的宽和高，texW 和 texH 代表整张纹理的宽和高
		Gui.drawModalRectWithCustomSizedTexture(
				x, y,
				0, 0,
				16, 16,
				16, 16);
	}
}
