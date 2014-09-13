package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.Coords;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.gen.GlitchTemple.TempleManager;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGlitchTempleCore extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		for(int xx = 0; xx < 9; xx++){
			for(int yy = 0; yy < 16; yy++){
				for(int zz = 0; zz < 9; zz++){
					if(((xx  == 1) || (xx == 7) || ((zz == 1) || (zz == 7)))){
						w.setBlock(x + xx, y + yy, z + zz, VoidMod.decor);
					}
					if(((xx  == 0) || (xx == 8) || ((zz == 0) || (zz == 8)))){
						w.setBlock(x + xx, y + yy, z + zz, VoidMod.chamberWall);
						w.setBlockMetadataWithNotify(x + xx, y + yy, z + zz, 3, 2);
					}
				}
			}
		}
		int mod = 0;
		while(mod < 5){
			for(int xx = -1 + mod; xx < 10 - mod; xx++){
				for(int zz = -1 + mod; zz < 10 - mod; zz++){
					if(((xx  == -1 + mod) || (xx == 9 - mod) || ((zz == -1 + mod) || (zz == 9 - mod)))){
						w.setBlock(x + xx, y + 16 + mod, z + zz, VoidMod.chamberWall);
						w.setBlockMetadataWithNotify(x + xx, y + 16 + mod, z + zz, 3, 2);
					}
				}
			}
			mod++;
		}
		w.setBlock(x + 4, y + 20, z + 4, Blocks.glowstone);
		for(int xx = 3; xx < 6; xx++){
			for(int zz = 3; zz < 6; zz++){
				w.setBlockToAir(xx, y, zz);
			}
		}
		TempleManager t = new TempleManager(new Coords(x + 4, y - 6, z + 4));
		t.populate(w);
		return true;
	}

}
