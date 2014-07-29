package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class VoidModStructureGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.dimensionId == VoidMod.NullVoidDimID){
			generateNull(world, random, chunkX*16, chunkZ*16);
		}
		if(world.provider.dimensionId == 0){
			generateOver(world, random, chunkX * 16, chunkZ * 16);
		}
		
	}
	private void generateNull(World w, Random r, int x, int z){
		int Xcoord1 = x + r.nextInt(16);
		int Ycoord1 = r.nextInt(200);
		int Zcoord1 = z + r.nextInt(16);

		(new WorldGenTARDIS()).generate(w, r, Xcoord1, Ycoord1, Zcoord1);
	}
	private void generateOver(World w, Random r, int xx, int zz){
		int Xcoord1 = xx + r.nextInt(16);
		int Ycoord1 = r.nextInt(200);
		int Zcoord1 = zz + r.nextInt(16);
		if(r.nextInt(10000) == 413){
			(new WorldGenReactor()).generate(w, r, Xcoord1, Ycoord1, Zcoord1);
		}
		Utils.hasStruct.put(new ChunkCoordIntPair(xx, zz), true);
	}
}
