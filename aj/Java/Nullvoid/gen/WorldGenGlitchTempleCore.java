package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGlitchTempleCore extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		for(int xx = 0; xx < 8; xx++){
			for(int yy = 0; yy < 15; yy++){
				for(int zz = 0; zz < 8; zz++){
					if(((xx  == 1) || (xx == 6) || ((zz == 1) || (zz == 6)))){
						w.setBlock(x + xx, y + yy, z + zz, VoidMod.decor);
					}
					if(((xx  == 0) || (xx == 7) || ((zz == 0) || (zz == 7)))){
						w.setBlock(x + xx, y + yy, z + zz, VoidMod.chamberWall);
						w.setBlockMetadataWithNotify(x + xx, y + yy, z + zz, 3, 2);
					}
				}
			}
		}
		int mod = 0;
		while(mod < 5){
			for(int xx = -1 + mod; xx < 9 - mod; xx++){
				for(int zz = -1 + mod; zz < 9 - mod; zz++){
					if(((xx  == -1 + mod) || (xx == 8 - mod) || ((zz == -1 + mod) || (zz == 8 - mod)))){
						w.setBlock(x + xx, y + 15 + mod, z + zz, VoidMod.chamberWall);
						w.setBlockMetadataWithNotify(x + xx, y + 15 + mod, z + zz, 3, 2);
					}
					if(mod < 4){
						if(((xx  == 0 + mod) || (xx == 7 - mod) || ((zz == 0 + mod) || (zz == 7 - mod)))){
							w.setBlock(x + xx, y + 15 + mod, z + zz, VoidMod.chamberWall);
							w.setBlockMetadataWithNotify(x + xx, y + 15 + mod, z + zz, 3, 2);
						}
					}
				}
			}
			mod++;
		}
		w.setBlock(x + 4, y + 19, z + 4, Blocks.glowstone);
		return true;
	}

}
