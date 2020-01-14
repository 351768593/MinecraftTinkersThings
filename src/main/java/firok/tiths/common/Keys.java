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
	String prefNugget="nugget_";
	String prefLog="log_";
	String prefLeaf="leaf_";
	String prefSapling="sapling_";
	String prefLogic="logic_";
	String prefRL= TinkersThings.MOD_ID+':';

	// 材料
	String nameRoyalAlloy="royal_alloy";
	String nameStellarium ="stellarium";
	String nameHothium="hothium";
	String nameTonium="tonium";
	String nameInkPowder="ink_powder";
	String nameCinnabar="cinnabar";
	String nameSpiderLeg="spider_leg";
	String nameHardSpiderLeg="hard_spider_leg";
	String nameSkyCrystal="sky_crystal";
	String nameStormCrystal="storm_crystal";
	String namePhantomCrystal="phantom_crystal";
	String nameAntiGravCrystal="anti_grav_crystal";
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
	String nameHotBread="hot_bread";
	String nameHotFish="hot_fish";
	String nameWeepingPumpkin="weeping_pumpkin";
	String nameBloodPumpkin="blood_pumpkin";
	String nameBloodSand="blood_sand";
	String nameCoagulatedBloodSand="coagulated_blood_sand";
	String nameInkySlime="inky_slime";
	String nameGatewayGem="gateway_gem";
	String nameRoyalPaper="royal_paper";
	String nameRoyalBook="royal_book";
	String nameMeteorolite="meteorolite";

	String nameHardener="hardener";
	String namePolisher="polisher";
	String nameDriller="driller";
	String nameEnderGem="ender_gem";
	String namePhasingGem="phasing_gem";

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

	// 逻辑用方块
	String blockLogicSearing=prefLogic+"searing";

	// 矿块
	String blockStellarium=prefBlock+nameStellarium;
	String blockRoyalAlloy=prefBlock+nameRoyalAlloy;
	String blockHothium=prefBlock+nameHothium;
	String blockTonium=prefBlock+nameTonium;
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

	String blockMeteorolite=prefBlock+nameMeteorolite;
	String blockBloodPumpkin=prefBlock+nameBloodPumpkin;
	String blockWeepingPumpkin=prefBlock+nameWeepingPumpkin;
	String blockBloodSand=prefBlock+nameBloodSand;
	String blockConsolidatedGlass=prefBlock+nameConsolidatedGlass;
	String blockCoagulatedBloodSand=prefBlock+nameCoagulatedBloodSand;

	String blockRoyalEnchantmentTable=prefBlock+"royal_enchantment_table";

	// 原矿
	String oreCinnabar=prefOre+nameCinnabar;
	String oreHothium=prefOre+nameHothium;
	String oreTonium=prefOre+nameTonium;
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
	String ingotHothium=prefIngot+nameHothium;
	String ingotTonium=prefIngot+nameTonium;
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

	// 矿粒
	String nuggetStellarium=prefNugget+nameStellarium;
	String nuggetHothium=prefNugget+nameHothium;
	String nuggetTonium=prefNugget+nameTonium;
	String nuggetRoyalAlloy=prefNugget+nameRoyalAlloy;
	String nuggetImmersedSilver=prefNugget+nameImmersedSilver;
	String nuggetMithril=prefNugget+nameMithril;
	String nuggetAdamantine=prefNugget+nameAdamantine;
	String nuggetInertWitherium=prefNugget+nameInertWitherium;
	String nuggetWitherium=prefNugget+nameWitherium;
	String nuggetTitanium=prefNugget+nameTitanium;
	String nuggetPolarium=prefNugget+namePolarium;
	String nuggetHalleium=prefNugget+nameHalleium;
	String nuggetAltairium=prefNugget+nameAltairium;
	String nuggetCocoa=prefNugget+nameCocoa;

	// 材料颜色
	int colorMeteorolite=Colors.OliveDrab;
	int colorStellarium=Colors.Tomato;
	int colorStellariumObsidian=Colors.FireBrick;
	int colorHothium=0x041a51;
	int colorTonium=0x115205;
	int colorRoyalAlloy=0xf5ea8d;
	int colorSpiderLeg=0x3d0006;
	int colorHardSpiderLeg=0x1a0003;
	int colorCinnabar=Colors.Silver; //
	int colorConsolidatedGlass=Colors.LightBlue;
	int colorBrokenIce=Colors.LightSkyBlue;
	int colorImmersedSilver=0x1e4575;
	int colorMithril=0xa0d9eb;
	int colorAdamantine=0xd4c6e5;
	int colorBlackrock=0x2c1c0c;
	int colorInertWitherium=Colors.DarkGray;
	int colorWitherium=Colors.DimGray;
	int colorShell=Colors.LightCoral;
	int colorRuby=0xeb041f;
	int colorSpinel=0xbc061a;
	int colorTalcum=0xc6ceb8;
	int colorTourmaline=Colors.DarkBlue;
	int colorCorundum=0x85323c;
	int colorNitre=Colors.LightGray;
	int colorPyrophyllite=Colors.LightGreen; //
	int colorIcelandSpar=Colors.LightSteelBlue;
	int colorTitanium=0xaaceef; //
	int colorPolarium=0x6aa7d9; //
	int colorHalleium=0x966b2a; //
	int colorAltairium=0x3f1564;
	int colorCocoa=Colors.Chocolate; //
	int colorCoagulatedBloodSand=Colors.DarkRed;

	// 属性
	String nameTraitMaiming="maiming";
	String nameTraitWithering="withering";
	String nameTraitInky="inky";
	String nameTraitRetrospective="retrospective";
	String nameTraitIcy="icy";
	String nameTraitLuxurious="luxurious";
	String nameTraitRadiant="radiant";
	String nameTraitExtremeFreezing="extreme_freezing";
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
	String nameTraitAntiGrav="anti_grav";
	String nameTraitStonePhasing="stone_phasing";
	String nameTraitTreasureDetecting="treasure_detecting";
	String nameTraitCreaky="creaky";
	String nameTraitUndeadCalling="undead_calling";
	String nameTraitRepressing="repressing";
	String nameTraitDrilled="drilled";
	String nameTraitWrapping="wrapping";
	String nameTraitPhantasmic="phantasmic";
	String nameTraitHemolytic="Hemolytic";

	// 属性颜色
	int colorTraitMaiming=Colors.Crimson;
	int colorTraitWithering=Colors.DarkSlateGray;
	int colorTraitInky=Colors.Black;
	int colorTraitRetrospective=Colors.LightYellow;
	int colorTraitIcy=Colors.DeepSkyBlue;
	int colorTraitLuxurious=Colors.Gold;
	int colorTraitRadiant=Colors.FireBrick;
	int colorTraitExtremeFreezing=0x2a61bc;
	int colorTraitSwitching=Colors.SeaGreen;
	int colorTraitClustering=Colors.DarkKhaki;
	int colorTraitHardened=Colors.Peru;
	int colorTraitPolished=Colors.YellowGreen;
	int colorTraitBrittle=Colors.PaleTurquoise;
	int colorTraitStarDashing=0xea7632;
	int colorTraitSoluble=Colors.CadetBlue;
	int colorTraitBirefringent=Colors.Indigo;
	int colorTraitPyroelectric=Colors.Navy;
	int colorTraitShaking=Colors.Fuchsia;
	int colorTraitAntiGrav=Colors.LightGoldenRodYellow;
	int colorTraitStonePhasing=Colors.LimeGreen;
	int colorTraitTreasureDetecting=Colors.GoldenRod;
	int colorTraitCreaky=Colors.DarkSlateGray;
	int colorTraitUndeadCalling=Colors.Gray;
	int colorTraitRepressing=Colors.DarkOliveGreen;
	int colorTraitDrilled=Colors.PaleVioletRed;
	int colorTraitWrapping=0x2d3d1c;
	int colorTraitPhantasmic=Colors.BlueViolet;
	int colorTraitHemolytic=Colors.DarkRed;

	// 状态效果颜色
	int colorPotionHeavy=Colors.DarkSeaGreen;
	int colorPotionDisappear=Colors.Black;

	// 唱片
	String recordTinkersEfforts="record_tinkers_efforts";
	String recordTinkersWill="record_tinkers_will";

	// 唱片资源键
	String soundTinkersEfforts="tinkers_efforts";
	String soundTinkersWill="tinkers_will";
}
