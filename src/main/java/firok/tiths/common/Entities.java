package firok.tiths.common;

import firok.tiths.entity.item.EntityItemSoul;
import firok.tiths.entity.projectile.ProjectileDashingStar;
import firok.tiths.entity.projectile.ProjectileLightBall;
import firok.tiths.entity.special.EnderBeacon;
import firok.tiths.entity.special.LogicSearing;
import firok.tiths.entity.trans.Alchemy;
import firok.tiths.util.reg.RegEntity;

@SuppressWarnings("all")
public final class Entities
{
	private Entities() {}

//	@RegEntity(network=250)
//	public static ProjectileJavelin projectile_javelin;
	@RegEntity(network=251)
	public static ProjectileDashingStar projectile_dashing_star;
//	@RegEntity(network=252)
//	public static ProjectileItemPotionTransform item_potion_transform;
	@RegEntity(network=253)
	public static ProjectileLightBall projectile_light_ball;
//
//	@RegEntity(network=301)
//	public static TransformingCloud transforming_cloud;
	@RegEntity(network = 302)
	public static Alchemy alchemy;

	@RegEntity(network = 350)
	public static EnderBeacon ender_beacon;
	@RegEntity(network = 351)
	public static LogicSearing logic_searing;

	@RegEntity(network = 400,tracker = 24,updateFrequency = 5)
	public static EntityItemSoul entity_item_soul;
}
