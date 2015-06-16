package nimble.Java.TheVoid.Generation;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Configuration.Config;
import nimble.Java.TheVoid.World.VoidWorldData;

public class GenerationHandler implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//Ores
		int y = 0;
		int i = 0;
		WorldGenMinable wgm = null;
		boolean stop = false;
		
		switch(world.provider.getDimensionId()){
		case -1:
			int y1 = random.nextInt(8);
			int y2 = 255 - random.nextInt(8);
			y = random.nextInt(2) == 0 ? y1 : y2;
			wgm = new WorldGenMinable(VoidMod.nullore.getStateFromMeta(0), 2, BlockHelper.forBlock(Blocks.netherrack));
			i = 1;
			break;
		case 0:
			y = random.nextInt(8);
			wgm = new WorldGenMinable(VoidMod.nullore.getStateFromMeta(0), 2, BlockHelper.forBlock(Blocks.stone));
			i = 1;
			break;
		case 1:
			y = random.nextInt(256);
			wgm = new WorldGenMinable(VoidMod.nullore.getStateFromMeta(0), 3, BlockHelper.forBlock(Blocks.end_stone));
			i = 2;
			break;
		default:
			if(world.provider.getDimensionId() == VoidMod.config.dimid){
				y = random.nextInt(256);
				wgm = new WorldGenMinable(VoidMod.nullore.getStateFromMeta(0), 4, BlockHelper.forBlock(VoidMod.terrain));
				i = 3;
			}
			else{
				stop = true;
			}
		}
		
		if(!stop){
			for(int c = 0; c < i; c++){
				BlockPos start = new BlockPos(16 * chunkX + random.nextInt(16), y, 16 * chunkZ + random.nextInt(16));
				wgm.generate(world, random, start);
			}
		}
		
		stop = false;
		
		//Worldgen
		switch(world.provider.getDimensionId()){
		default:
			if(world.provider.getDimensionId() == VoidMod.config.dimid){
				//Omega Valley
				if(chunkX * 8 == 0 && chunkZ * 8 == 0){
					BlockPos pos = new BlockPos(chunkX * 8, 63, chunkZ * 8);
					new TemplateGeneration().generate(random, pos.getX(), pos.getY(), pos.getZ(), world, chunkGenerator, chunkProvider, VoidMod.assets.omegaValley);
				}
				
				//Spikes
				//new SpikeGeneration().generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				if(random.nextInt(20) == 1){
					BlockPos pos = new BlockPos(chunkX * 8, 63, chunkZ * 8);
					if(random.nextInt(20) == 1){
						//Omega
						new TemplateGeneration().generate(random, pos.getX(), pos.getY(), pos.getZ(), world, chunkGenerator, chunkProvider, VoidMod.assets.pillarO1);
					}
					else{
						//Vaculite
						new TemplateGeneration().generate(random, pos.getX(), pos.getY(), pos.getZ(), world, chunkGenerator, chunkProvider, VoidMod.assets.pillarV1);
					}
				}
			}
		}
	}

}
