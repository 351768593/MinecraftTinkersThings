package firok.mtim.common;

import firok.mtim.util.*;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTraits;

import static firok.mtim.common.Traits.*;
import static firok.mtim.util.Keys.*;
import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.tools.TinkerTraits.cheap;
import static slimeknights.tconstruct.tools.TinkerTraits.lightweight;

public class TCMaterials
{

	@Compo(name=nameRoyalAlloy,fluid=nameRoyalAlloy)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoHandle(modifier = 0.8f, durability = 100)
	@CompoExtra(extraDurability = 100)
	public static Material royalAlloy=new Material(nameRoyalAlloy, colorRoyalAlloy)
			.addTrait(luxurious)
			.addTrait(TinkerTraits.magnetic2);

	@Compo(name=nameStellar,fluid=nameStellar)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoHandle(modifier = 0.8f, durability = 100)
	@CompoExtra(extraDurability = 100)
	public static Material stellar=new Material(nameStellar, colorStellar)
			.addTrait(radiant);

	@Compo(name=nameSpiderLeg)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoExtra(extraDurability = 100)
	public static Material spiderLeg=new Material(nameSpiderLeg, colorSpiderLeg)
			.addTrait(TinkerTraits.sharp)
			.addTrait(TinkerTraits.poisonous);

	@Compo(name=nameHardSpiderLeg)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoExtra(extraDurability = 100)
	public static Material hardSpiderLeg=new Material(nameHardSpiderLeg, colorHardSpiderLeg)
			.addTrait(TinkerTraits.sharp);

	@Compo(name=nameCinnabar,fluid=nameCinnabar)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoHandle(modifier = 0.8f, durability = 100)
	@CompoExtra(extraDurability = 100)
	public static Material cinnabar=new Material(nameCinnabar,colorCinnabar)
			.addTrait(TinkerTraits.poisonous, HEAD);

//	@Compo(name= nameGlass, item="glass",fluid="glass")
//	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
//	@CompoHandle(modifier = 0.8f, durability = 100)
//	@CompoExtra(extraDurability = 100)
//	public static Material glass=new Material(nameGlass, colorGlass)
//			.addTrait(cheapskate)
//			.addTrait(lightweight);

	@Compo(name= nameGlass) // , item="consolidated_glass"
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoHandle(modifier = 0.8f, durability = 100)
	@CompoExtra(extraDurability = 100)
	public static Material consolidatedGlass=new Material(nameConsolidatedGlass, colorConsolidatedGlass)
			.addTrait(cheap)
			.addTrait(lightweight);

	@Compo(name= nameBrokenIce, item=nameBrokenIce,castatble = false, craftable = true)
	@CompoHead(durability = 100,miningspeed = 0.5f,attack = 1,harvestLevel = 1)
	@CompoHandle(modifier = 0.8f, durability = 100)
	@CompoExtra(extraDurability = 100)
	public static Material brokenIce=new Material(nameBrokenIce,colorBrokenIce)
			.addTrait(cheap)
			.addTrait(icy);

	public static Material cloud=new Material("cloud", Colors.Silver);
	public static Material cloudStorm=new Material("cloud_storm", Colors.DarkBlue);
	public static Material skyCrystal=new Material("sky_crystal", Colors.Aqua);
	public static Material stormCrystal=new Material("storm_crystal", Colors.DarkBlue);
	public static Material antiGraCrystal=new Material("anti_gra_crystal", Colors.DarkGreen);
	public static Material phantomCrystal=new Material("phantom_crystal", Colors.Gray);
	public static Material brokenBedrock=new Material("broken_bedrock", Colors.DarkGray);
	public static Material paradiseMetal=new Material("paradise_metal", Colors.Yellow);

}
