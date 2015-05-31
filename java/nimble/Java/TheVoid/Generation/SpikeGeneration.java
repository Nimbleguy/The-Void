package nimble.Java.TheVoid.Generation;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import nimble.Java.TheVoid.VoidMod;

public class SpikeGeneration {
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		/*
		OpenSimplexNoise noise = new OpenSimplexNoise(random.nextLong());
		for(int x = 16 * chunkX; x < 16 * chunkX + 16; x++){
			for(int z = 16 * chunkZ; z < 16 * chunkZ + 16; z++){
				for(int y = 64; y < 256; y++){
					double value = noise.eval(x, y, z);
					if(value >= 0){
						if(value > 0.5){
							world.setBlockState(new BlockPos(x, y, z), VoidMod.terrain.getStateFromMeta(1), 2);
						}
						else{
							world.setBlockState(new BlockPos(x, y, z), VoidMod.terrain.getStateFromMeta(0), 2);
						}
					}
				}
			}
		}
		*/
		if(random.nextBoolean()){
			return;
		}
		
		int sx = random.nextInt(16) + chunkX * 16;
		int sz = random.nextInt(16) + chunkZ * 16;
		
		int h = random.nextInt(256 - 63) + 63;
		int br = random.nextInt(8) + Math.round(h/64);
		
		int r = br;
		
		int cx = sx + br;
		int cz = sz + br;
		
		boolean done = false;
		while(!done){
			done = true;
			int y = 63;
			spike:
			for(; y < h; y++){
				for(int x = sx - r; x < sx + r; x++){
					for(int z = sz - r; z < sz + r; z++){
						int d = (int) (Math.pow(x - cx, 2) + Math.pow(z - cz, 2));
						if(d < Math.pow(r, 2)){
							world.setBlockState(new BlockPos(x, y, z), VoidMod.terrain.getStateFromMeta(1), 2);
						}
						else if(d == Math.pow(r, 2)){
							world.setBlockState(new BlockPos(x, y, z), VoidMod.terrain.getStateFromMeta(0), 2);
						}
					}
				}
				if((br - 2)/h >= random.nextInt(h)){
					r--;
					done = false;
					break spike;
				}
			}
		}
	}
}
