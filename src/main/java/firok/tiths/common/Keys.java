package firok.tiths.common;

import firok.tiths.TinkersThings;
import firok.tiths.util.Colors;

public interface Keys
{
	// 前缀
	String prefMolten="molten_";
	String prefBlock="block_";
	String prefOre="ore_";
	String prefIngot="ingot_";
	String prefLog="log_";
	String prefLeaf="leaf_";
	String prefSapling="sapling_";
	String prefLogic="logic_";
	String prefRL= TinkersThings.MOD_ID+':';

	// 材料
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
	String nameGlass="glass";
	String nameConsolidatedGlass="consolidated_glass";
	String nameBrokenIce="broken_ice";
	String nameImmersedSilver="immersed_silver";
	String nameMithril="mithril";
	String nameAdamantine="adamantine";
	String nameBlackrock="blackrock";
	String nameInertWitherium="inert_witherium";
	String nameWitherium="witherium";
	String nameStellariumObsidian="stellarium_obsidian";
	String nameShell="shell";
	String nameRuby="ruby";
	String nameSpinel="spinel";
	String nameTalcum="talcum";
	String nameTourmaline="tourmaline";
	String nameCorundum="corundum";
	String nameNitre="nitre";
	String namePyrophyllite="pyrophyllite";
	String nameIcelandSpar="iceland_spar";
	String nameRutile="rutile";
	String nameTitanium="titanium";
	String namePolarium="polarium";
	String nameHalleium="halleium";
	String nameAltairium="altairium";
	String nameCocoa="cocoa";
	String nameWeepingPumpkin="weeping_pumpkin";
	String nameBloodPumpkin="blood_pumpkin";
	String nameBloodSand="blood_sand";

	String nameHardener="hardener";
	String namePolisher="polisher";

	// 植物
	String nameHura="hura";
	String logHura=prefLog+nameHura;
	String leafHura=prefLeaf+nameHura;
	String saplingHura=prefSapling+nameHura;
	String nameHuraFruit="hura_fruit";
	String nameBlood="blood";
	String logBlood=prefLog+nameBlood;
	String leafBlood=prefLeaf+nameBlood;
	String saplingBlood=prefSapling+nameBlood;


	// 液体
	String moltenRoyalAlloy=prefMolten+nameRoyalAlloy;
	String moltenStellarium=prefMolten+nameStellarium;
	String moltenCinnabar=prefMolten+nameCinnabar;
	String moltenImmersedSilver=prefMolten+nameImmersedSilver;
	String moltenMithril=prefMolten+nameMithril;
	String moltenAdamantine=prefMolten+nameAdamantine;
	String moltenInertWitherium=prefMolten+nameInertWitherium;
	String moltenWitherium=prefMolten+nameWitherium;
	String moltenTitanium=prefMolten+nameTitanium;
	String moltenPolarium=prefMolten+namePolarium;
	String moltenHalleium=prefMolten+nameHalleium;
	String moltenAltairium=prefMolten+nameAltairium;
	String moltenCocoa=prefMolten+nameCocoa;

	// 逻辑用方块
	String blockLogicSearing=prefLogic+"searing";

	// 矿块
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
	String blockRuby=prefBlock+nameRuby;
	String blockSpinel=prefBlock+nameSpinel;
	String blockTalcum=prefBlock+nameTalcum;
	String blockTourmaline=prefBlock+nameTourmaline;
	String blockCorundum=prefBlock+nameCorundum;
	String blockNitre=prefBlock+nameNitre;
	String blockPyrophyllite=prefBlock+namePyrophyllite;
	String blockIcelandSpar=prefBlock+nameIcelandSpar;
	String blockTitanium=prefBlock+nameTitanium;
	String blockPolarium=prefBlock+namePolarium;
	String blockHalleium=prefBlock+nameHalleium;
	String blockAltairium=prefBlock+nameAltairium;
	String blockCocoa=prefBlock+nameCocoa;

	String blockBloodPumpkin=prefBlock+nameBloodPumpkin;
	String blockWeepingPumpkin=prefBlock+nameWeepingPumpkin;
	String blockBloodSand=prefBlock+nameBloodSand;
	String blockConsolidatedGlass=prefBlock+nameConsolidatedGlass;

	// 原矿
	String oreCinnabar=prefOre+nameCinnabar;
	String oreInkPowder=prefOre+nameInkPowder;
	String oreImmersedSilver=prefOre+nameImmersedSilver;
	String oreMithril=prefOre+nameMithril;
	String oreAdamantine=prefOre+nameAdamantine;
	String oreBlackrock=prefOre+nameBlackrock;
	String oreInertWitherium=prefOre+nameInertWitherium;
	String oreWitherium=prefOre+nameWitherium;
	String oreShell=prefOre+nameShell;
	String oreRuby=prefOre+nameRuby;
	String oreSpinel=prefOre+nameSpinel;
	String oreTalcum=prefOre+nameTalcum;
	String oreTourmaline=prefOre+nameTourmaline;
	String oreCorundum=prefOre+nameCorundum;
	String oreNitre=prefOre+nameNitre;
	String orePyrophyllite=prefOre+namePyrophyllite;
	String oreIcelandSpar=prefOre+nameIcelandSpar;
	String oreRutile=prefOre+nameRutile;
	String orePolarium=prefOre+namePolarium;
	String oreHalleium=prefOre+nameHalleium;
	String oreAltairium=prefOre+nameAltairium;

	// 矿锭
	String ingotStellarium=prefIngot+nameStellarium;
	String ingotRoyalAlloy=prefIngot+nameRoyalAlloy;
	String ingotImmersedSilver=prefIngot+nameImmersedSilver;
	String ingotMithril=prefIngot+nameMithril;
	String ingotAdamantine=prefIngot+nameAdamantine;
	String ingotInertWitherium=prefIngot+nameInertWitherium;
	String ingotWitherium=prefIngot+nameWitherium;
	String ingotTitanium=prefIngot+nameTitanium;
	String ingotPolarium=prefIngot+namePolarium;
	String ingotHalleium=prefIngot+nameHalleium;
	String ingotAltairium=prefIngot+nameAltairium;
	String ingotCocoa=prefIngot+nameCocoa;

	// 材料颜色
	int colorStellarium= Colors.Tomato;
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
	int colorShell=Colors.Coral;
	int colorRuby=Colors.OrangeRed;
	int colorSpinel=Colors.OrangeRed;
	int colorTalcum=Colors.FloralWhite;
	int colorTourmaline=Colors.DarkBlue;
	int colorCorundum=Colors.DarkRed;
	int colorNitre=Colors.LightGray;
	int colorPyrophyllite=Colors.LimeGreen;
	int colorIcelandSpar=Colors.White;
	int colorTitanium=Colors.Silver;
	int colorPolarium=Colors.MidnightBlue;
	int colorHalleium=Colors.MediumPurple;
	int colorAltairium=Colors.FloralWhite;
	int colorCocoa=Colors.Chocolate;

	// 属性
	String nameTraitIcy="icy";
	String nameTraitLuxurious="luxurious";
	String nameTraitRadiant="radiant";
	String nameTraitSwitching="switching";
	String nameTraitClustering="clustering";
	String nameTraitHardened="hardened";
	String nameTraitPolished="polished";
	String nameTraitBrittle="brittle";
	String nameTraitStarDashing="star_dashing";
	String nameTraitSoluble="soluble";
	String nameTraitBirefringent="birefringent";
	String nameTraitPyroelectric="pyroelectric";
	String nameTraitShaking="shaking";

	// 属性颜色
	int colorTraitIcy=Colors.Snow;
	int colorTraitLuxurious=Colors.Gold;
	int colorTraitRadiant=Colors.Coral;
	int colorTraitSwitching=Colors.SeaGreen;
	int colorTraitClustering=Colors.Ivory;
	int colorTraitHardened=Colors.SlateGray;
	int colorTraitPolished=Colors.Gray;
	int colorTraitBrittle=Colors.Gray;
	int colorTraitStarDashing=Colors.LightYellow;
	int colorTraitSoluble=Colors.RoyalBlue;
	int colorTraitBirefringent=Colors.HoneyDew;
	int colorTraitPyroelectric=Colors.LightSkyBlue;
	int colorTraitShaking=Colors.Fuchsia;
}
