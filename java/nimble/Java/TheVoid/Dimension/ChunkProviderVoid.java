package nimble.Java.TheVoid.Dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import nimble.Java.TheVoid.VoidMod;

public class ChunkProviderVoid implements IChunkProvider {
	
	private World world;
    private Random seed;
    private List<MapGenStructure> structures = new ArrayList<MapGenStructure>();
    
    public ChunkProviderVoid(World w, long nrSeed){
    	seed = new Random(nrSeed);
    	world = w;
    }

	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		ChunkPrimer p = new ChunkPrimer();
		for(int xc = 0; xc < 16; xc++){
			for(int yc = 0; yc < 64; yc++){
				for(int zc = 0; zc < 16; zc++){
					p.setBlockState(xc, yc, zc, VoidMod.terrain.getStateFromMeta(0));
				}
			}
		}
		
		for(MapGenBase mgb : structures){
			mgb.func_175792_a(this, world, x, z, p);
		}
		
		Chunk c = new Chunk(world, p, x, z);
		return c;
	}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
	}

	@Override
	public void populate(IChunkProvider c, int x, int z) {
		Random r = new Random();
		ChunkCoordIntPair cc = new ChunkCoordIntPair(x, z);
		
		for(MapGenStructure mgs : structures){
			mgs.func_175794_a(world, r, cc);
		}
		
		
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_,
			int p_177460_3_, int p_177460_4_) {
		return false;
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "thevoid:Void";
	}

	@Override
	public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		return world.getBiomeGenForCoords(p_177458_2_).getSpawnableList(p_177458_1_);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_,
			BlockPos p_180513_3_) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk p, int x, int z) {
		for(MapGenBase mgb : structures){
			mgb.func_175792_a(this, world, x, z, (ChunkPrimer)null);
		}
	}

	@Override
	public void saveExtraData() {
	}

}
