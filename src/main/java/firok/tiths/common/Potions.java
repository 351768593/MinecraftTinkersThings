package firok.tiths.common;

import firok.tiths.potion.*;
import net.minecraft.potion.Potion;

// 状态效果的注册 reg_name是直接读取field名称
public class Potions
{
	public static Potion heavy = new PotionHeavy(); // 沉重
	public static Potion pestilential = new PotionPestilential(); // 瘟疫

//	public static Potion disappear = new PotionDisappear(); // 消失
}
