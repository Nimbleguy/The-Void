package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class VoidModOreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		final int over = 0;
		final int end = 1;
		final int voidid = VoidMod.NullVoidDimID;
		final int cdim = world.provider.dimensionId;
		switch(cdim){
		case over:
		    genSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case end:
		    genEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		}
		if(cdim == voidid){
			genVoid(world, random, chunkX * 16, chunkZ * 16);
		}

	}

	private void genSurface(World world, Random random, int i, int j) {
		for(int k = 0; k < 2; k++) {
			int voidOreXCoord = i + random.nextInt(16);
			int voidOreYCoord = random.nextInt(5);
			int voidOreZCoord = j + random.nextInt(16);
			//System.out.println(voidOreXCoord + " " + voidOreYCoord + " " + voidOreZCoord);
			(new WorldGenMinable(VoidMod.NullOre, 2)).generate(world, random, voidOreXCoord, voidOreYCoord, voidOreZCoord);
		}
		
	}

	private void genEnd(World world, Random random, int i, int j) {
		for(int k = 0; k < 8; k++) {
			int voidOreXCoord = i + random.nextInt(16);
			int voidOreYCoord = random.nextInt(128);
			int voidOreZCoord = j + random.nextInt(16);
			//System.out.println(voidOreXCoord + " " + voidOreYCoord + " " + voidOreZCoord);
			(new WorldGenEndNullMinable(VoidMod.NullOre, 0, 10, Blocks.end_stone)).generate(world, random, voidOreXCoord, voidOreYCoord, voidOreZCoord);
		}
		
	}

	private void genVoid(World world, Random random, int i, int j) {
		for(int k = 0; k < 20; k++) {
			int voidOreXCoord = i + random.nextInt(16);
			int voidOreYCoord = random.nextInt(200);
			int voidOreZCoord = j + random.nextInt(16);
			//System.out.println(voidOreXCoord + " " + voidOreYCoord + " " + voidOreZCoord);
			(new WorldGenEndNullMinable(VoidMod.NullOre, 0, 50, VoidMod.VoidFabric)).generate(world, random, voidOreXCoord, voidOreYCoord, voidOreZCoord);
		}
		/**
		for(int k = 0; k < 3; k++) {
			int voidOreXCoord = i + random.nextInt(16);
			int voidOreYCoord = random.nextInt(22);
			int voidOreZCoord = j + random.nextInt(16);
			(new WorldGenEndNullMinable(VoidMod.VoidOre, 2, 2, VoidMod.VoidFabric)).generate(world, random, voidOreXCoord, voidOreYCoord, voidOreZCoord);
		}
		*/
		
	}

}
