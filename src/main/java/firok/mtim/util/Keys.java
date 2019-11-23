package firok.mtim.util;

import firok.mtim.MoreTinkersMaterials;

public interface Keys
{
	String prefMolten="molten_";
	String prefBlock="block_";
	String prefOre="ore_";
	String prefIngot="ingot_";
	String prefRL= MoreTinkersMaterials.MOD_ID+':';

	String nameRoyalAlloy="royal_alloy";
	String nameStellarium ="stellarium";
	String nameInkPowder="ink_powder";
	String nameCinnabar="cinnabar";
	String nameSpiderLeg="spider_leg";
	String nameHardSpiderLeg="hard_spider_leg";
	String nameSkyCrystal="sky_crystal";
	String nameStormCrystal="storm_crystal";
	String namePhantomCrystal="phantom_crystal";
	String nameAntiGravCrystal="anti_grav_crystal";
	String nameGlass= "glass";
	String nameConsolidatedGlass= "consolidated_glass";
	String nameBrokenIce="broken_ice";
	String nameImmersedSilver="immersed_silver";
	String nameMithril="mithril";
	String nameAdamantine="adamantine";
	String nameBlackrock="blackrock";
	String nameInertWitherium="inert_witherium";
	String nameWitherium="witherium";
	String nameStellariumObsidian="stellarium_obsidian";

	String moltenRoyalAlloy=prefMolten+nameRoyalAlloy;
	String moltenStellarium=prefMolten+nameStellarium;
	String moltenCinnabar=prefMolten+nameCinnabar;
	String moltenImmersedSilver=prefMolten+nameImmersedSilver;
	String moltenMithril=prefMolten+nameMithril;
	String moltenAdamantine=prefMolten+nameAdamantine;
	String moltenInertWitherium=prefMolten+nameInertWitherium;
	String moltenWitherium=prefMolten+nameWitherium;

	String blockStellarium=prefBlock+ nameStellarium;
	String blockInkPowder=prefBlock+nameInkPowder;
	String blockCinnabar=prefBlock+nameCinnabar;
	String blockImmersedSilver=prefBlock+nameImmersedSilver;
	String blockMithril=prefBlock+nameMithril;
	String blockAdamantine=prefBlock+nameAdamantine;
	String blockBlackrock=prefBlock+nameBlackrock;
	String blockInertWitherium=prefBlock+nameInertWitherium;
	String blockWitherium=prefBlock+nameWitherium;
	String blockStellariumObsidian=prefBlock+nameStellariumObsidian;

	String oreCinnabar=prefOre+nameCinnabar;
	String oreInkPowder=prefOre+nameInkPowder;
	String oreImmersedSilver=prefOre+nameImmersedSilver;
	String oreMithril=prefOre+nameMithril;
	String oreAdamantine=prefOre+nameAdamantine;
	String oreBlackrock=prefOre+nameBlackrock;
	String oreInertWitherium=prefOre+nameInertWitherium;
	String oreWitherium=prefOre+nameWitherium;

	String ingotStellarium=prefIngot+nameStellarium;
	String ingotRoyalAlloy=prefIngot+nameRoyalAlloy;
	String ingotImmersedSilver=prefIngot+nameImmersedSilver;
	String ingotMithril=prefIngot+nameMithril;
	String ingotAdamantine=prefIngot+nameAdamantine;
	String ingotInertWitherium=prefIngot+nameInertWitherium;
	String ingotWitherium=prefIngot+nameWitherium;

	int colorStellarium=Colors.Tomato;
	int colorRoyalAlloy=Colors.Yellow;
	int colorSpiderLeg =Colors.IndianRed;
	int colorHardSpiderLeg=Colors.IndianRed;
	int colorCinnabar=Colors.Silver;
	int colorGlass=Colors.WhiteSmoke;
	int colorConsolidatedGlass=Colors.WhiteSmoke;
	int colorBrokenIce=Colors.Snow;
	int colorImmersedSilver=Colors.Silver;
	int colorMithril=Colors.Silver;
	int colorAdamantine=Colors.BlanchedAlmond;
	int colorBlackrock=Colors.DarkRed;
	int colorInertWitherium=Colors.DarkGray;
	int colorWitherium=Colors.DimGray;

	String nameTraitIcy="icy";
	String nameTraitLuxurious="luxurious";
	String nameTraitRadiant="radiant";
	String nameTraitSwitching="switching";

	int colorTraitIcy=Colors.Snow;
	int colorTraitLuxurious=Colors.Gold;
	int colorTraitRadiant=Colors.Coral;
	int colorTraitSwitching=Colors.SeaGreen;
}
