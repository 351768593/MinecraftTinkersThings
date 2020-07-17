package firok.tiths.common;

import firok.tiths.util.Predicates;
import firok.tiths.util.reg.FieldStream;
import firok.tiths.util.reg.GenOre;
import firok.tiths.world.*;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WorldGens implements IWorldGenerator
{
	private static volatile WorldGens instance;
	public static WorldGens getInstance()
	{
		if(instance==null)
		{
			instance=new WorldGens();
			instance.reload();
		}
		return instance;
	}

	/**
	 * 生成器列表
	 */
	List<IChunkGen> generators=new ArrayList<>();

	private static int[] r(int[] ts)
	{
		return ts!=null && ts.length>0? ts : null;
	}
	private static String[] r(String[] ts)
	{
		return ts!=null && ts.length>0? ts : null;
	}
	private volatile boolean isLoading=false;
	public boolean isLoading()
	{
		return isLoading;
	}
	public void reload()
	{
		if(isLoading) return;

		isLoading=true;
		List<IChunkGen> list=new ArrayList<>();

		list.add(new WorldGenTreeRoot(defaultTreeRoot,"TREE_ROOT"));
		list.add(new WorldGenMinableBedrock(defaultBrokenBedrockInfo,"BROKEN_BEDROCK"));
		list.add(new WorldGenLavaCrystal(defaultLavaCrystal,"LAVA_CRYSTAL"));

		list.add(new WorldGenCloud(defaultCloudInfo,"CLOUD"));

		list.add(new WorldGenSeaGlass(defaultSeaGrassInfo,"SEA_GRASS"));

		FieldStream.of(Blocks.class,null,Block.class)
				.forEach((field, anno, block) -> {

					GenOre ga=field.getAnnotation(GenOre.class);
					Info infoAnno=ga!=null?Info.build(
							block.getDefaultState(),

							ga.dim(), r(ga.dimsWL()),r(ga.dimsBL()),
							ga.biome(), r(ga.biomeWL()),r(Keys.getBiomes(ga.biomeBL())),
							Predicates.getPredicateIBlockState(ga.selector(), Predicates::isStone),

							ga.minY(),ga.maxY(),
							ga.times(),(float)ga.timeRate(),
							ga.size()
					):null;
					Info infoJson= ConfigJson.getOre(block.getUnlocalizedName());
					Info info2add= null;

					if(infoAnno==null)
					{
						if(infoJson!=null && infoJson.complete())
						{
							info2add=infoJson;
						}
					}
					else
					{
						info2add=Info.build(infoAnno,infoJson);
					}

					if(info2add==null || !Info.enable(info2add,null,false))
					{
						return;
					}

					GenMinable gen=new GenMinable( info2add );
					list.add(gen);

				});

		generators=list;

		isLoading=false;
	}
	public static final Info defaultLavaCrystal=Info.build(
			Blocks.oreLavaCrystal.getDefaultState(),
			Strategy.ONLY_WHITELIST,new int[]{-1},null,
			Strategy.NONE_BLACKLIST,null,null,
			Predicates::isNetherrack,
			10,110,
			4,0.25f,1
	);
	public static final Info defaultTreeRoot=Info.build(
			Blocks.oreTreeRoot.getDefaultState(),
			Strategy.NONE_BLACKLIST,null,null,
			Strategy.NONE_BLACKLIST,null,null,
			Predicates::isDirt,
			0,255,
			1,1,1
	);
	public static final Info defaultBrokenBedrockInfo=Info.build(
			Blocks.oreBrokenBedrock.getDefaultState(),
			Strategy.NONE_BLACKLIST,null,null,
			Strategy.NONE_BLACKLIST,null,null,
			Predicates::isStone,
			0,5,
			3,0.6f,8
	);
	public static final Info defaultCloudInfo=Info.build(
			Blocks.oreBrumeJade.getDefaultState(),
			Strategy.ONLY_WHITELIST,new int[]{0},null,
			Strategy.NONE_BLACKLIST,null,null,
			Predicates::isAir,
			150,180,
			1,0.015f,7
	);
	public static final Info defaultSeaGrassInfo=Info.build(
			Blocks.blockSeaGrass.getDefaultState(),
			Strategy.ONLY_WHITELIST,new int[]{0},null,
			Strategy.ONLY_WHITELIST,Keys.biomes_sea,null,
			Predicates::isWater,
			120,80,
			4,0.8f,1
	);


	int preChunkX=Integer.MIN_VALUE, preChunkZ =Integer.MIN_VALUE;
	World preWorld=null;
	public void gen(World world, int chunkX, int chunkZ, Random rand) // note event传进来的是区块号
	{
		if(preWorld==world&&preChunkX==chunkX&&preChunkZ==chunkZ) return;

		preWorld=world;
		preChunkX=chunkX;
		preChunkZ=chunkZ;

		int chunkVX=chunkX*16,chunkVZ=chunkZ*16; // 区块顶点坐标

		int targetDimId=world.provider.getDimension();
		WorldProvider provider=world.provider;
		Biome biome=world.getBiome(new BlockPos(chunkVX+8,1,chunkVZ+8));
		String targetBiomeName=String.valueOf(biome.getRegistryName());

		for(IChunkGen genWorld:generators) // 检查能不能生成在指定世界
		{
			boolean canGen=genWorld.canGenAtDim(targetDimId,world,provider) &&
			               genWorld.canGenAtBiome(targetBiomeName, world, biome);
			if(!canGen) continue;

			genWorld.genAtChunk(world, chunkVX, chunkVZ, rand);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		gen(world,chunkX,chunkZ,random);
	}
}
