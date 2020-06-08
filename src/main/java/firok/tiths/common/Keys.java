package firok.tiths.common;

import firok.tiths.TinkersThings;
import firok.tiths.util.Colors;

import java.util.*;

@SuppressWarnings("all")
public interface Keys
{
	// 前缀
	String prefMolten = "molten_";
	String prefBlock = "block_";
	String prefOre = "ore_";
	String prefIngot = "ingot_";
	String prefNugget = "nugget_";
	String prefLog = "log_";
	String prefLeaf = "leaf_";
	String prefSapling = "sapling_";
	String prefLogic = "logic_";
	String prefDust = "dust_";
	String prefRL = TinkersThings.MOD_ID + ':';
	String suffArmor = "_armor";
	String prefAmulet = "amulet_";
	String prefBelt = "belt_";

	// 材料
	String nameRoyalAlloy = "royal_alloy";
	String nameStellarium = "stellarium";
	String nameHothium = "hothium";
	String nameTonium = "tonium";
	String nameInkPowder = "ink_powder";
	String nameCinnabar = "cinnabar";
	String nameSpiderLeg = "spider_leg";
	String nameHardSpiderLeg = "hard_spider_leg";
	String nameEnderDragonSquama = "ender_dragon_squama";
	String nameSkyCrystal = "sky_crystal";
	String nameStormCrystal = "storm_crystal";
	String namePhantomCrystal = "phantom_crystal";
	String nameAntiGravCrystal = "anti_grav_crystal";
	String nameConsolidatedGlass = "consolidated_glass";
	String nameBrokenIce = "broken_ice";
	String nameImmersedSilver = "immersed_silver";
	String nameMithril = "mithril";
	String nameAdamantine = "adamantine";
	String nameBlackrock = "blackrock";
	String nameInertWitherium = "inert_witherium";
	String nameWitherium = "witherium";
	String nameStellariumObsidian = "stellarium_obsidian";
	String nameShell = "shell";
	String nameShellCooked = nameShell + "_cooked";
	String nameRedins = "redins";
	String nameSpinel = "spinel";
	String nameTalcum = "talcum";
	String nameTourmaline = "tourmaline";
	String nameCorundum = "corundum";
	String nameNitre = "nitre";
	String namePyrophyllite = "pyrophyllite";
	String nameIcelandSpar = "iceland_spar";
	String nameRutile = "rutile";
	String nameTitanium = "titanium";
	String namePolarium = "polarium";
	String nameHalleium = "halleium";
	String nameAltairium = "altairium";
	String nameCocoa = "cocoa";
	String nameHotBread = "hot_bread";
	String nameHotFish = "hot_fish";
	String nameWeepingPumpkin = "weeping_pumpkin";
	String nameBloodPumpkin = "blood_pumpkin";
	String nameBloodSand = "blood_sand";
	String nameCoagulatedBloodSand = "coagulated_blood_sand";
	String nameInkySlime = "inky_slime";
	String nameGatewayGem = "gateway_gem";
	String nameRoyalPaper = "royal_paper";
	String nameRoyalBook = "royal_book";
	String nameAncientBookIrisia = "ancient_book_irisia";
	String nameMeteorolite = "meteorolite";
	String nameFulgurite = "fulgurite";
	String nameIrisia = "irisia";
	String nameCoal = "coal";
	String nameTreeRoot = "tree_root";
	String nameBrokenBedrock = "broken_bedrock";
	String nameSunStone = "sun_stone";
	String nameMoonStone = "moon_stone";
	String nameFlesh = "flesh";
	String nameFleshCooked = nameFlesh + "_cooked";
	String nameCloud = "cloud";
	String nameOpal = "opal";
	String nameTopaz = "topaz";
	String nameLizanite = "lizanite";
	String nameCordierite = "cordierite";
	String namePrehnite = "prehnite";
	String nameProustite = "proustite";
	String nameOraclium = "oraclium";
	String nameVibratingCrystal = "vibrating_crystal";
	String nameLavaCrystal = "lava_crystal";
	String nameSteamium = "steamium";
	String nameGrain = "grain";
	String nameEnderCreviceShard = "ender_crevice_shard";
	String nameMercurySulfide = "mercury_sulfide";
	String nameAventurine = "aventurine";
	String nameCinder = "cinder";
	String nameUlun = "ulun";
	String namePotos = "potos";
	String nameFurutorin = "furutorin";
	String nameHeavesand = "heavesand";
	String nameChloroplast = "chloroplast";
	String nameEnderTurbulence = "ender_turbulence";
	String nameWitheringEssence = "withering_essence";
	String nameDirtyWater = "dirty_water";
	String nameFormerCrystal = "former_crystal";
	String nameTorrentialCrystal = "torrential_crystal";
	String nameThanatonium = "thanatonium";
	String nameTorrentialThruster = "torrential_thruster";
	String nameTube = "tube";
	String nameColorfulGlass = "colorful_glass";
	String nameAirBubble = "air_bubble";
	String nameAirTank = "air_tank";
	String nameDisposableAirTank = "disposable_air_tank";
	String nameSoul = "soul";
	String nameAmuletSoulStone = prefAmulet + "soul_stone";
	String nameBeltStonePhasing = prefBelt + "stone_phasing";
	String nameBeltLeather = prefBelt + "leather";
	String nameAirBomb = "air_bomb";
	String nameChannelPackStone = "channel_pack_stone";
	String nameChannelPackWood = "channel_pack_wood";
	String nameSoulBeacon = "soul_beacon";
	String nameChloroplastDressing = "chloroplast_dressing";
	String nameAmuletIron = prefAmulet + "iron";
	String nameCreepSoil = "creep_soil";

	String nameHardener = "hardener";
	String namePolisher = "polisher";
	String nameDriller = "driller";
	String nameEnderGem = "ender_gem";
	String namePhasingGem = "phasing_gem";
	String nameMesh = "mesh";
	String nameBuoy = "buoy";

	// 植物
	String nameHura = "hura";
	String logHura = prefLog + nameHura;
	String leafHura = prefLeaf + nameHura;
	String saplingHura = prefSapling + nameHura;
	String nameHuraFruit = "hura_fruit";
	String nameBlood = "blood";
	String logBlood = prefLog + nameBlood;
	String leafBlood = prefLeaf + nameBlood;
	String saplingBlood = prefSapling + nameBlood;

	// 逻辑用方块
	String blockLogicSearing = prefLogic + "searing";

	// 矿块
	String blockStellarium = prefBlock + nameStellarium;
	String blockRoyalAlloy = prefBlock + nameRoyalAlloy;
	String blockIrisia = prefBlock + nameIrisia;
	String blockHothium = prefBlock + nameHothium;
	String blockTonium = prefBlock + nameTonium;
	String blockInkPowder = prefBlock + nameInkPowder;
	String blockCinnabar = prefBlock + nameCinnabar;
	String blockImmersedSilver = prefBlock + nameImmersedSilver;
	String blockMithril = prefBlock + nameMithril;
	String blockAdamantine = prefBlock + nameAdamantine;
	String blockBlackrock = prefBlock + nameBlackrock;
	String blockInertWitherium = prefBlock + nameInertWitherium;
	String blockWitherium = prefBlock + nameWitherium;
	String blockStellariumObsidian = prefBlock + nameStellariumObsidian;
	String blockRedins = prefBlock + nameRedins;
	String blockSpinel = prefBlock + nameSpinel;
	String blockTalcum = prefBlock + nameTalcum;
	String blockTourmaline = prefBlock + nameTourmaline;
	String blockCorundum = prefBlock + nameCorundum;
	String blockNitre = prefBlock + nameNitre;
	String blockPyrophyllite = prefBlock + namePyrophyllite;
	String blockIcelandSpar = prefBlock + nameIcelandSpar;
	String blockTitanium = prefBlock + nameTitanium;
	String blockPolarium = prefBlock + namePolarium;
	String blockHalleium = prefBlock + nameHalleium;
	String blockAltairium = prefBlock + nameAltairium;
	String blockCocoa = prefBlock + nameCocoa;
	String blockTreeRoot = prefBlock + nameTreeRoot;
	String blockBrokenBedrock = prefBlock + nameBrokenBedrock;
	String blockSunStone = prefBlock + nameSunStone;
	String blockMoonStone = prefBlock + nameMoonStone;
	String blockFlesh = prefBlock + nameFlesh;
	String blockCloud = prefBlock + nameCloud;
	String blockOpal = prefBlock + nameOpal;
	String blockTopaz = prefBlock + nameTopaz;
	String blockLizanite = prefBlock + nameLizanite;
	String blockCordierite = prefBlock + nameCordierite;
	String blockPrehnite = prefBlock + namePrehnite;
	String blockProustite = prefBlock + nameProustite;
	String blockOraclium = prefBlock + nameOraclium;
	String blockVibratingCrystal = prefBlock + nameVibratingCrystal;
	String blockLavaCrystal = prefBlock + nameLavaCrystal;
	String blockSteamium = prefBlock + nameSteamium;
	String blockAventurine = prefBlock + nameAventurine;
	String blockMercurySulfide = prefBlock + nameMercurySulfide;
	String blockChloroplast = prefBlock + nameChloroplast;
	String blockUlun = prefBlock + nameUlun;
	String blockPotos = prefBlock + namePotos;
	String blockFurutorin = prefBlock + nameFurutorin;
	String blockHeavesand = prefBlock + nameHeavesand;
	String blockTorrentialCrystal = prefBlock + nameTorrentialCrystal;
	String blockColorfulGlass = prefBlock + nameColorfulGlass;
	String blockCreepSoil = prefBlock + nameCreepSoil;

	String blockFulgurite = prefBlock + nameFulgurite;
	String blockMeteorolite = prefBlock + nameMeteorolite;
	String blockBloodPumpkin = prefBlock + nameBloodPumpkin;
	String blockWeepingPumpkin = prefBlock + nameWeepingPumpkin;
	String blockBloodSand = prefBlock + nameBloodSand;
	String blockConsolidatedGlass = prefBlock + nameConsolidatedGlass;
	String blockCoagulatedBloodSand = prefBlock + nameCoagulatedBloodSand;
	String blockBloodyChiseledQuartz = prefBlock + "bloody_chiseled_quartz";
	String blockBloodyPillarQuartz = prefBlock + "bloody_pillar_quartz";
	String blockBloodyQuartz = prefBlock + "bloody_quartz";
	String blockSearingVent = prefBlock + "searing_vent";
	String blockTinkerDisintegrator = prefBlock + "tinker_disintegrator";
	String blockAirPump = prefBlock + "air_pump";

	String blockRoyalEnchantmentTable = prefBlock + "royal_enchantment_table";

	// 原矿
	String oreCinnabar = prefOre + nameCinnabar;
	String oreHothium = prefOre + nameHothium;
	String oreTonium = prefOre + nameTonium;
	String oreStellarium = prefOre + nameStellarium;
	String oreInkPowder = prefOre + nameInkPowder;
	String oreImmersedSilver = prefOre + nameImmersedSilver;
	String oreMithril = prefOre + nameMithril;
	String oreAdamantine = prefOre + nameAdamantine;
	String oreBlackrock = prefOre + nameBlackrock;
	String oreInertWitherium = prefOre + nameInertWitherium;
	String oreWitherium = prefOre + nameWitherium;
	//	String oreShell=prefOre+nameShell;
	String oreRedins = prefOre + nameRedins;
	String oreSpinel = prefOre + nameSpinel;
	String oreTalcum = prefOre + nameTalcum;
	String oreTourmaline = prefOre + nameTourmaline;
	String oreCorundum = prefOre + nameCorundum;
	String oreNitre = prefOre + nameNitre;
	String orePyrophyllite = prefOre + namePyrophyllite;
	String oreIcelandSpar = prefOre + nameIcelandSpar;
	String oreRutile = prefOre + nameRutile;
	String orePolarium = prefOre + namePolarium;
	String oreHalleium = prefOre + nameHalleium;
	String oreAltairium = prefOre + nameAltairium;
	String oreSunStone = prefOre + nameSunStone;
	String oreMoonStone = prefOre + nameMoonStone;
	String oreTreeRoot = prefOre + nameTreeRoot;
	String oreBrokenBedrock = prefOre + nameBrokenBedrock;
	String oreOpal = prefOre + nameOpal;
	String oreTopaz = prefOre + nameTopaz;
	String oreLizanite = prefOre + nameLizanite;
	String oreCordierite = prefOre + nameCordierite;
	String orePrehnite = prefOre + namePrehnite;
	String oreProustite = prefOre + nameProustite;
	String oreVibratingCrystal = prefOre + nameVibratingCrystal;
	String oreLavaCrystal = prefOre + nameLavaCrystal;
	String oreSteamium = prefOre + nameSteamium;
	String oreAventurine = prefOre + nameAventurine;
	String oreChloroplast = prefOre + nameChloroplast;
	String oreUlun = prefOre + nameUlun;
	String orePotos = prefOre + namePotos;
	String oreFurutorin = prefOre + nameFurutorin;
	String oreHeavesand = prefOre + nameHeavesand;
	String oreTorrentialCrystal = prefOre + nameTorrentialCrystal;
	String oreSolidDirt = prefOre + "solid_dirt";
	String oreSolidSand = prefOre + "solid_sand";
	String oreSolidStone = prefOre + "solid_stone";

	// 矿锭
	String ingotStellarium = prefIngot + nameStellarium;
	String ingotHothium = prefIngot + nameHothium;
	String ingotTonium = prefIngot + nameTonium;
	String ingotRoyalAlloy = prefIngot + nameRoyalAlloy;
	String ingotImmersedSilver = prefIngot + nameImmersedSilver;
	String ingotMithril = prefIngot + nameMithril;
	String ingotAdamantine = prefIngot + nameAdamantine;
	String ingotInertWitherium = prefIngot + nameInertWitherium;
	String ingotWitherium = prefIngot + nameWitherium;
	String ingotTitanium = prefIngot + nameTitanium;
	String ingotPolarium = prefIngot + namePolarium;
	String ingotHalleium = prefIngot + nameHalleium;
	String ingotAltairium = prefIngot + nameAltairium;
	String ingotCocoa = prefIngot + nameCocoa;
	String ingotIrisia = prefIngot + nameIrisia;
	String ingotOraclium = prefIngot + nameOraclium;
	String ingotSteamium = prefIngot + nameSteamium;
	String ingotGrain = prefIngot + nameGrain;
	String ingotChloroplast = prefIngot + nameChloroplast;

	// 矿粒
	String nuggetStellarium = prefNugget + nameStellarium;
	String nuggetHothium = prefNugget + nameHothium;
	String nuggetTonium = prefNugget + nameTonium;
	String nuggetRoyalAlloy = prefNugget + nameRoyalAlloy;
	String nuggetImmersedSilver = prefNugget + nameImmersedSilver;
	String nuggetMithril = prefNugget + nameMithril;
	String nuggetAdamantine = prefNugget + nameAdamantine;
	String nuggetInertWitherium = prefNugget + nameInertWitherium;
	String nuggetWitherium = prefNugget + nameWitherium;
	String nuggetTitanium = prefNugget + nameTitanium;
	String nuggetPolarium = prefNugget + namePolarium;
	String nuggetHalleium = prefNugget + nameHalleium;
	String nuggetAltairium = prefNugget + nameAltairium;
	String nuggetCocoa = prefNugget + nameCocoa;
	String nuggetIrisia = prefNugget + nameIrisia;
	String nuggetOraclium = prefNugget + nameOraclium;
	String nuggetSteamium = prefNugget + nameSteamium;
	String nuggetChloroplast = prefNugget + nameChloroplast;

	// 粉末
	String dustBlackrock = prefDust + nameBlackrock;
	String dustChloroplast = prefDust + nameChloroplast;
	String dustStone = prefDust + "stone";
	String dustStoneCoal = prefDust + "stone_coal";

	// 材料颜色
	int colorEnderDragonSquama = 0x180c33;
	int colorFulgurite = 0x5c2999;
	int colorIrisia = 0xf1ff99;
	int colorMeteorolite = Colors.OliveDrab;
	int colorCoal = 0x2b2b2b;
	int colorTreeRoot = 0x4a3b31;
	int colorStellarium = Colors.Tomato;
	int colorStellariumObsidian = Colors.FireBrick;
	int colorHothium = 0x041a51;
	int colorTonium = 0x115205;
	int colorRoyalAlloy = 0xf5ea8d;
	int colorSpiderLeg = 0x3d0006;
	int colorHardSpiderLeg = 0x1a0003;
	int colorCinnabar = Colors.Silver;
	int colorConsolidatedGlass = Colors.LightBlue;
	int colorBrokenIce = Colors.LightSkyBlue;
	int colorImmersedSilver = 0x1e4575;
	int colorMithril = 0xa0d9eb;
	int colorAdamantine = 0xd4c6e5;
	int colorBlackrock = 0x2c1c0c;
	int colorInertWitherium = Colors.DarkGray;
	int colorWitherium = Colors.DimGray;
	int colorShell = Colors.LightCoral;
	int colorRedins = 0xeb041f;
	int colorSpinel = 0xbc061a;
	int colorTalcum = 0xc6ceb8;
	int colorTourmaline = Colors.DarkBlue;
	int colorCorundum = 0x85323c;
	int colorNitre = Colors.LightGray;
	int colorPyrophyllite = Colors.LightGreen;
	int colorIcelandSpar = Colors.LightSteelBlue;
	int colorTitanium = 0xaaceef; //
	int colorPolarium = 0x6aa7d9; //
	int colorHalleium = 0x966b2a; //
	int colorAltairium = 0x3f1564;
	int colorCocoa = Colors.Chocolate; //
	int colorCoagulatedBloodSand = Colors.DarkRed;
	int colorSunStone = 0xff8557;
	int colorMoonStone = 0x5ad3dc;
	int colorFlesh = 0xb54264;
	int colorBrokenBedrock = 0x323240;
	int colorCloud = 0xb6caea;
	int colorOpal = 0x0a7dd6;
	int colorTopaz = 0x72a6f5;
	int colorLizanite = 0x6607b3;
	int colorCordierite = 0x536f9e;
	int colorPrehnite = 0xb8e986;
	int colorProustite = 0x5e252b;
	int colorOraclium = 0x5dffb1;
	int colorVibratingCrystal = 0xab9e05;
	int colorLavaCrystal = 0xa9174f;
	int colorSteamium = 0xb7d3f5;
	int colorGrain = 0xea9b32;
	int colorAventurine = 0x4e8d41;
	int colorChloroplast = 0xb1fa2a;
	int colorEnderTurbulence = 0x5c1491;
	int colorTorrentialCrystal = 0x3e62ba;

	int colorDirtyWater = 0x613d1d;
	int colorAcid = 0x0e8c39;

	// 匠魂自带属性
	String nameTraitLightweight = "lightweight";
	String nameTraitInsatiable = "insatiable";
	String nameTraitUnnatural = "unnatural";
	String nameTraitStonebound = "stonebound";
	String nameTraitCheap = "cheap";
	String nameTraitCheapskate = "cheapskate";
	String nameTraitMagnetic = "magnetic";
	String nameTraitMagnetic2 = "magnetic2";
	String nameTraitSharp = "sharp";
	String nameTraitArsenicPoisonous = "arsenic_poisonous";
	String nameTraitEstablished = "established";
	String nameTraitDense = "dense";
	String nameTraitJagged = "jagged";
	String nameTraitTasty = "tasty";
	String nameTraitEcological = "ecological";
	String nameTraitWritable1 = "writable1";
	String nameTraitAlien = "alien";
	String nameTraitFlammable = "flammable";
	String nameTraitEnderference = "enderference";
	String nameTraitDuritos = "duritos";
	String nameTraitSqueaky = "squeaky";
	String nameTraitHeavy = "heavy";
	String nameTraitAquaspeed = "aquaspeed";
	String nameTraitSkeletal = "skeletal";
	String nameTraitBouncy = "bouncy";
	String nameTraitRough = "rough";
	String nameTraitAmbitious = "ambitious";
	String nameTraitSteady = "steady";
	String nameTraitCombustible = "combustible";
	String nameTraitIndomitable = "indomitable";
	String nameTraitVoltaic = "voltaic";
	String nameTraitMundane = "mundane";
	String nameTraitInvigorating = "invigorating";

	// 属性
	String nameTraitGluttonic = "gluttonic";
	String nameTraitMaiming = "maiming";
	String nameTraitCarbonizing = "carbonizing";
	String nameTraitSunPower = "sun_power";
	String nameTraitMoonPower = "moon_power";
	String nameTraitMoonlight = "moonlight";
	String nameTraitNatureBlessing = "nature_blessing";
	String nameTraitWitherFlowing = "wither_flowing";
	String nameTraitInky = "inky";
	String nameTraitRetrospective = "retrospective";
	String nameTraitIcy = "icy";
	String nameTraitLuxurious = "luxurious";
	String nameTraitRadiant = "radiant";
	String nameTraitExtremeFreezing = "extreme_freezing";
	String nameTraitSwitching = "switching";
	String nameTraitClustering = "clustering";
	String nameTraitHardened = "hardened";
	String nameTraitPolished = "polished";
	String nameTraitBrittle = "brittle";
	String nameTraitStarDashing = "star_dashing";
	String nameTraitSoluble = "soluble";
	String nameTraitBirefringent = "birefringent";
	String nameTraitPyroelectric = "pyroelectric";
	String nameTraitShaking = "shaking";
	String nameTraitAntiGrav = "anti_grav";
	String nameTraitStonePhasing = "stone_phasing";
	String nameTraitTreasureDetecting = "treasure_detecting";
	String nameTraitCreaky = "creaky";
	String nameTraitUndeadCalling = "undead_calling";
	String nameTraitRepressing = "repressing";
	String nameTraitDrilled = "drilled";
	String nameTraitWrapping = "wrapping";
	String nameTraitPhantasmic = "phantasmic";
	String nameTraitHemolytic = "hemolytic";
	String nameTraitLionheart = "lionheart";
	String nameTraitTerrifying = "terrifying";
	String nameTraitThunderWaving = "thunder_waving";
	String nameTraitAntiPoisonous = "anti_poisonous";
	String nameTraitChemicalInstable = "chemical_instable";
	String nameTraitInfernalBlazing = "infernal_blazing";
	String nameTraitDragonKiller = "dragon_killer";
	String nameTraitMidasDesiring = "midas_desiring";
	String nameTraitGorgeous = "gorgeous";
	String nameTraitPeaceEnergetic = "peace_energetic";
	String nameTraitHyper = "hyper";
	String nameTraitDichroic = "dichroic";
	String nameTraitLifeInspiring = "life_inspiring";
	String nameTraitOracular = "oracular";
	String nameTraitThermalGathering = "thermal_gathering";
	String nameTraitSteamy = "steamy";
	String nameTraitParroting = "parroting";
	String nameTraitDecoying = "decoying";
	String nameTraitWatery = "watery";
	String nameTraitStaminaFocusing = "stamina_focusing";
	String nameTraitArmorHiding = "hiding";
	String nameTraitUnsettled = "unsettled";
	String nameTraitLifting = "lifting";
	String nameTraitEndothermic = "endothermic";
	String nameTraitThresholdLimiting = "threshold_limiting";
	String nameTraitSliding = "sliding";
	String nameTraitOverHeavy = "over_heavy";
	String nameTraitChamping = "champing";
	String nameTraitFading = "fading";
	String nameTraitDeepParasitic = "deep_parasitic";
	String nameTraitPanicking = "panicking";
	String nameTraitDiffuseReflecting = "diffuse_reflecting";
	String nameTraitWidening = "widening";
	String nameTraitWarmSoft = "warm_soft";
	String nameTraitVibrating = "vibrating";
	String nameTraitCombustionSupporting = "combustion_supporting";
	String nameTraitBlowing = "blowing";
	String nameTraitDeadening = "deadening";
	String nameTraitDevouring = "devouring";
	String nameTraitSmooth = "smooth";
	String nameTraitMeshing = "meshing";
	String nameTraitBuoyant = "buoyant";
	String nameTraitSovereign = "sovereign";
	String nameTraitTubeUpgraded = "tube_upgraded";
	String nameTraitTurbulent = "turbulent";
	String nameTraitBattleFocusing = "battle_focusing";
	String nameTraitTorrential = "torrential";
	String nameTraitHydrating = "hydrating";
	String nameTraitDegenerating = "degenerating";
	String nameTraitPhotosynthetic = "photosynthetic";
	String nameTraitEddying = "eddying";

	// 属性颜色
	int colorTraitGluttonic = 0x752b40;
	int colorTraitMaiming = Colors.Crimson;
	int colorTraitCarbonizing = 0x2b2b2b;
	int colorTraitSunPower = 0xff8557;
	int colorTraitMoonPower = 0x5ad3dc;
	int colorTraitMoonlight = 0x85e8de;
	int colorTraitNatureBlessing = 0x538915;
	int colorTraitWitherFlowing = Colors.DarkSlateGray;
	int colorTraitInky = Colors.Black;
	int colorTraitRetrospective = Colors.LightYellow;
	int colorTraitIcy = Colors.DeepSkyBlue;
	int colorTraitLuxurious = Colors.Gold;
	int colorTraitRadiant = Colors.FireBrick;
	int colorTraitExtremeFreezing = 0x2a61bc;
	int colorTraitSwitching = Colors.SeaGreen;
	int colorTraitClustering = Colors.DarkKhaki;
	int colorTraitHardened = Colors.Peru;
	int colorTraitPolished = Colors.YellowGreen;
	int colorTraitBrittle = Colors.PaleTurquoise;
	int colorTraitStarDashing = 0xea7632;
	int colorTraitSoluble = Colors.CadetBlue;
	int colorTraitBirefringent = Colors.Indigo;
	int colorTraitPyroelectric = Colors.Navy;
	int colorTraitShaking = Colors.Fuchsia;
	int colorTraitAntiGrav = Colors.LightGoldenRodYellow;
	int colorTraitStonePhasing = Colors.LimeGreen;
	int colorTraitTreasureDetecting = Colors.GoldenRod;
	int colorTraitCreaky = Colors.DarkSlateGray;
	int colorTraitUndeadCalling = Colors.Gray;
	int colorTraitRepressing = Colors.DarkOliveGreen;
	int colorTraitDrilled = Colors.PaleVioletRed;
	int colorTraitWrapping = 0x2d3d1c;
	int colorTraitPhantasmic = Colors.BlueViolet;
	int colorTraitHemolytic = Colors.DarkRed;
	int colorTraitLionheart = 0x87143e;
	int colorTraitTerrifying = 0x2a135a;
	int colorTraitThunderWaving = 0x2b5c99;
	int colorTraitAntiPoisonous = 0xc9215a;
	int colorTraitChemicalInstable = 0xedde31;
	int colorTraitInfernalBlazing = 0x76222d;
	int colorTraitDragonKiller = 0x9100f3;
	int colorTraitMidasDesiring = 0xdc9113;
	int colorTraitGorgeous = 0x0a7dd6;
	int colorTraitPeaceEnergetic = 0xfff501;
	int colorTraitHyper = 0xa022ff;
	int colorTraitDichroic = 0x536f9e;
	int colorTraitLifeInspiring = 0xf84b5d;
	int colorTraitOracular = 0x5dffb1;
	int colorTraitThermalGathering = 0xa9174f;
	int colorTraitSteamy = 0xb7d3f5;
	int colorTraitParroting = 0x69bd09;
	int colorTraitDecoying = 0xea9b32;
	int colorTraitWatery = 0x466389;
	int colorTraitStaminaFocusing = 0x4e8d41;
	int colorTraitUnsettled = 0x1d1d1d;
	int colorTraitLifting = 0xc7205a;
	int colorTraitEndothermic = 0x2a99a5;
	int colorTraitThresholdLimiting = 0x524131;
	int colorTraitSliding = 0xc6ceb8;
	int colorTraitOverHeavy = 0x2b2b2b;
	int colorTraitChamping = 0xffa919;
	int colorTraitFading = 0x686868;
	int colorTraitDeepParasitic = 0xb8132a;
	int colorTraitPanicking = 0x451d7f;
	int colorTraitDiffuseReflecting = 0x81dbc7;
	int colorTraitWidening = 0xba274c;
	int colorTraitWarmSoft = 0xfff14f;
	int colorTraitPoisonous = 0x467f01;
	int colorTraitVibrating = 0x4a90e2;
	int colorTraitCombustionSupporting = 0xa56228;
	int colorTraitBlowing = 0x86b0ed;
	int colorTraitDeadening = 0x59a373;
	int colorTraitDevouring = 0x390664;
	int colorTraitSmooth = 0xe8f7f7;
	int colorTraitMeshing = 0x072a5a;
	int colorTraitBuoyant = 0xe5d53a;
	int colorTraitSovereign = 0xf5d723;
	int colorTraitTubeUpgraded = 0xcedef0;
	int colorTraitTurbulent = 0x062147;
	int colorTraitBattleFocusing = 0x2c5203;
	int colorTraitTorrential = 0x254592;
	int colorTraitHydrating = colorImmersedSilver;
	int colorTraitDegenerating = 0xa0ca72;
	int colorTraitPhotosynthetic = colorChloroplast;
	int colorTraitEddying = 0x3f516b;

	// 状态效果颜色
	int colorPotionHeavy = Colors.DarkSeaGreen;
	int colorPotionDisappear = Colors.Black;
	int colorPotionPestilential = Colors.DarkGreen;
	int colorPotionEstrous = 0xEF94F5;
	int colorPotionAvatar = 0xffeb11;
	int colorPotionRooted = 0x644209;
	int colorPotionArmorSoftened = 0x4b6816;
	int colorPotionAcidWetted = 0x0e8c39;
	int colorPotionLionheart = 0xe3041f;
	int colorPotionIcy = 0x4194c0;
	int colorPotionSoluble = colorNitre;
	int colorPotionHyper = colorTraitHyper;
	int colorPotionBuoyant = colorTraitBuoyant;
	int colorPotionTurbulent = colorTraitTurbulent;
	int colorPotionForcibleFocused = 0x294011;
	int colorPotionEddying = colorTraitEddying;

	// 状态效果属性修饰符uuid
	String uuidPotionAvatarSpeed = "258A83A5-A3BD-4CFD-842C-B2DF39343C91";
	String uuidPotionAvatarMaxHealth = "F550AE43-453E-4A84-ADDB-353C5EA6E733";
	String uuidPotionAvatarArmor = "A65363CA-7A4E-4B93-A092-0AB852E2CD28";
	String uuidPotionAvatarArmorToughness = "6DF53F73-CE44-4F5B-9F96-164EB72BEBDE";
	String uuidPotionAvatarAttackDamage = "BCF6FF13-508E-4971-9E39-FB6A62F623C1";
	String uuidPotionRootedSpeed = "9F5D5738-D280-4577-8589-B1791B5B847D";
	String uuidPotionArmorSoftenedArmor = "425BCA0D-7A45-4246-9887-144F55725230";
	String uuidPotionArmorSoftenedArmorToughness = "BB676529-49C3-4887-AD64-911EDBE69F7E";
	String uuidPotionArmorVoidInfected = "9BA4BDC8-7E7F-437A-B526-B2D8CF3588F0";
	String uuidPotionSpeedHiddenHyper = "DF67E659-FB9D-4FAF-802C-8AA2F593A558";
	String uuidPotionArmorLionheart = "995F5833-14F9-4897-9ED7-2FA85EFF5281";

	String uuidTraitSolubleSwimSpeed = "E42481C5-6AA3-4A74-A992-22AD533C88CC";

	// 燃料热值
	int fuelTimeCoal = 1600;
	int fuelTimeCoalBlock = 16000;
	int fuelTimeFlameStick = 2400;
	int fuelTimeWood = 300;
	int fuelTimeWoodPlate = 300; // fixme
	int fuelTimeCinder = fuelTimeCoal / 8;
	int fuelTimeLavaCrystal = fuelTimeCoal * 4;
	int fuelTimeTreeRoot = fuelTimeCoal / 4;

	// 唱片
	String recordTinkersEfforts = "record_tinkers_efforts";
	String recordTinkersWill = "record_tinkers_will";
	String recordTinkersImagination = "record_tinkers_imagination";
	String recordTinkersEnemy = "record_tinkers_enemy";

	// 唱片资源键
	String soundTinkersEfforts = "tinkers_efforts";
	String soundTinkersWill = "tinkers_will";
	String soundTinkersImagination = "tinkers_imagination";
	String soundTinkersEnemy = "tinkers_enemy";
	String soundHealEffect = "heal_effect";
	String soundShakeEffect = "shake_effect";
	String soundFireEffect = "fire_effect";
	String soundSwingEffect = "swing_effect";
	String soundTransformingEffect = "transforming_effect";
	String soundForwardEffect = "forward_effect";

//	String configBase="config."+TinkersThings.MOD_ID+'.';
//	String cateGeneral=configBase+"general";
//	String cateGeneralBase=cateGeneral+'.';
//	String confEnableConarm=cateGeneralBase+"enable_conarm";
//	String confEnableMaterialCustomization=cateGeneralBase+"enable_material_customization";
//	String confEnableOreGenCustomization=cateGeneralBase+"enable_ore_gen_customization";
//
//	String cateTraits=configBase+"traits";
//	String cateTraitsBase=cateTraits+'.';
//	String confTraitRateBirefringent=cateTraitsBase+"rate_birefringent";

	// 原版生物群系id
	// 平原
	String biome_plains = "minecraft:plains";
	String biome_savanna_rock = "minecraft:savanna_rock";
	String biome_savanna = "minecraft:savanna";
	String biome_mutated_ice_flats = "minecraft:mutated_ice_flats";
	String biome_ice_flats = "minecraft:ice_flats";
	String biome_mutated_plains = "minecraft:mutated_plains";
	// 森林
	String biome_jungle = "minecraft:jungle";
	String biome_jungle_edge = "minecraft:jungle_edge";
	String biome_forest = "minecraft:forest";
	String biome_birch_forest = "minecraft:birch_forest";
	String biome_taiga_cold = "minecraft:taiga_cold";
	String biome_redwood_taiga = "minecraft:redwood_taiga";
	String biome_taiga = "minecraft:taiga";
	String biome_roofed_forest = "minecraft:roofed_forest";
	String biome_mutated_taiga_cold = "minecraft:mutated_taiga_cold";
	String biome_mutated_jungle_edge = "minecraft:mutated_jungle_edge";
	String biome_mutated_savanna_rock = "minecraft:mutated_savanna_rock";
	String biome_mutated_redwood_taiga = "minecraft:mutated_redwood_taiga";
	String biome_mutated_forest = "minecraft:mutated_forest";
	String biome_mutated_taiga = "minecraft:mutated_taiga";
	String biome_mutated_birch_forest = "minecraft:mutated_birch_forest";
	String biome_mutated_roofed_forest = "minecraft:mutated_roofed_forest";
	String biome_mutated_jungle = "minecraft:mutated_jungle";
	String biome_mutated_savanna = "minecraft:mutated_savanna";
	// 恶地
	String biome_mesa = "minecraft:mesa";
	String biome_mesa_rock = "minecraft:mesa_rock";
	String biome_mesa_clear_rock = "minecraft:mesa_clear_rock";
	String biome_mutated_mesa = "minecraft:mutated_mesa";
	String biome_mutated_mesa_clear_rock = "minecraft:mutated_mesa_clear_rock";
	String biome_mutated_mesa_rock = "minecraft:mutated_mesa_rock";
	// 山地
	String biome_taiga_cold_hills = "minecraft:taiga_cold_hills";
	String biome_forest_hills = "minecraft:forest_hills";
	String biome_ice_mountains = "minecraft:ice_mountains";
	String biome_jungle_hills = "minecraft:jungle_hills";
	String biome_taiga_hills = "minecraft:taiga_hills";
	String biome_redwood_taiga_hills = "minecraft:redwood_taiga_hills";
	String biome_extreme_hills_with_trees = "minecraft:extreme_hills_with_trees";
	String biome_extreme_hills = "minecraft:extreme_hills";
	String biome_smaller_extreme_hills = "minecraft:smaller_extreme_hills";
	String biome_mutated_extreme_hills = "minecraft:mutated_extreme_hills";
	String biome_mutated_redwood_taiga_hills = "minecraft:mutated_redwood_taiga_hills";
	String biome_mutated_extreme_hills_with_trees = "minecraft:mutated_extreme_hills_with_trees";
	String biome_mutated_birch_forest_hills = "minecraft:mutated_birch_forest_hills";
	String biome_birch_forest_hills = "minecraft:birch_forest_hills";
	// 沙漠
	String biome_desert = "minecraft:desert";
	String biome_desert_hills = "minecraft:desert_hills";
	String biome_mutated_desert = "minecraft:mutated_desert";
	// 水
	String biome_river = "minecraft:river";
	String biome_frozen_river = "minecraft:frozen_river";
	String biome_swampland = "minecraft:swampland";
	String biome_mutated_swampland = "minecraft:mutated_swampland";
	// 海洋
	String biome_frozen_ocean = "minecraft:frozen_ocean";
	String biome_ocean = "minecraft:ocean";
	String biome_deep_ocean = "minecraft:deep_ocean";
	String biome_cold_beach = "minecraft:cold_beach";
	String biome_stone_beach = "minecraft:stone_beach";
	String biome_beaches = "minecraft:beaches";
	// 特殊
	String biome_void = "minecraft:void";
	String biome_hell = "minecraft:hell";
	String biome_sky = "minecraft:sky";
	String biome_mushroom_island_shore = "minecraft:mushroom_island_shore";
	String biome_mushroom_island = "minecraft:mushroom_island";

	// 组合
	String[] biomes_plains = { biome_plains, biome_savanna_rock, biome_savanna, biome_mutated_ice_flats, biome_ice_flats, biome_mutated_plains };
	String[] biomes_forests = { biome_jungle, biome_jungle_edge, biome_forest, biome_birch_forest, biome_taiga_cold, biome_redwood_taiga, biome_taiga, biome_roofed_forest, biome_mutated_taiga_cold, biome_mutated_jungle_edge, biome_mutated_savanna_rock, biome_mutated_redwood_taiga, biome_mutated_forest, biome_mutated_taiga, biome_mutated_birch_forest, biome_mutated_roofed_forest, biome_mutated_jungle, biome_mutated_savanna };
	String[] biomes_mesas = {biome_mesa, biome_mesa_rock, biome_mesa_clear_rock, biome_mutated_mesa, biome_mutated_mesa_clear_rock, biome_mutated_mesa_rock };
	String[] biomes_hills = { biome_taiga_cold_hills, biome_forest_hills, biome_ice_mountains, biome_jungle_hills, biome_taiga_hills, biome_redwood_taiga_hills, biome_extreme_hills_with_trees, biome_extreme_hills, biome_smaller_extreme_hills, biome_mutated_extreme_hills, biome_mutated_redwood_taiga_hills, biome_mutated_extreme_hills_with_trees, biome_mutated_birch_forest_hills, biome_birch_forest_hills };
	String[] biomes_desert = { biome_desert, biome_desert_hills, biome_mutated_desert };
	String[] biomes_water = { biome_river, biome_frozen_river, biome_swampland, biome_mutated_swampland };
	String[] biomes_sea = { biome_frozen_ocean, biome_ocean, biome_deep_ocean, biome_cold_beach, biome_stone_beach, biome_beaches };
	String[] biomes_water_sea = { biome_river, biome_frozen_river, biome_swampland, biome_mutated_swampland, biome_frozen_ocean, biome_ocean, biome_deep_ocean, biome_cold_beach, biome_stone_beach, biome_beaches };
	String[] biomes_special = { biome_void, biome_hell, biome_sky, biome_mushroom_island_shore, biome_mushroom_island };
	public static String[] getBiomes(String...keys)
	{
		if(keys==null || keys.length==0) return new String[0];

		Set<String> set=new HashSet<>();
		FOR_KEY:for(String keyTemp:keys)
		{
			if(keyTemp==null) continue;
			String[] keysTemp;
			switch (keyTemp)
			{
				case "$plains": keysTemp=biomes_plains; break;
				case "$forests": keysTemp=biomes_forests; break;
				case "$mesas": keysTemp=biomes_mesas; break;
				case "$hills": keysTemp=biomes_hills; break;
				case "$desert": keysTemp=biomes_desert; break;
				case "$water": keysTemp=biomes_water; break;
				case "$sea": keysTemp=biomes_sea; break;
				case "$water_sea": keysTemp=biomes_water_sea; break;
				case "$special": keysTemp=biomes_special; break;
				default:
				{
					set.add(keyTemp);
					continue FOR_KEY;
				}
			}
			for(String kt:keysTemp) set.add(kt);
		}

		return set.toArray(new String[set.size()]);

	}
}
