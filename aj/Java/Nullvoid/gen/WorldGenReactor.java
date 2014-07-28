package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReactor extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, int x, int y, int z) {
		// Wall
		for (int i = x - 3; i < x + 4; i++) {
			for (int ii = y - 5; ii < y + 1; ii++) {
				for (int iii = z - 3; iii < z + 4; iii++) {
					w.setBlock(i, ii, iii, VoidMod.chamberWall, 2, 3);
				}
			}
		}
		// Clear Center
		for (int i = x - 2; i < x + 3; i++) {
			for (int ii = y - 4; ii < y; ii++) {
				for (int iii = z - 2; iii < z + 3; iii++) {
					w.setBlock(i, ii, iii, Blocks.air);
				}
			}
		}
		genReactor(w, r, x, y, z);
		// Door
		w.setBlock(x - 3, y - 2, z, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 3, y - 2, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 3, y - 2, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 2, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 1, y - 2, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 2, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 2, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 2, z, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 2, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 1, y - 2, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 2, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 2, z + 3, VoidMod.chamberWall, 1, 3);
		// Door
		w.setBlock(x - 3, y - 3, z, VoidMod.chamberWall, 0, 3);
		w.setBlock(x - 3, y - 3, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 3, y - 3, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 3, z - 3, VoidMod.chamberWall, 0, 3);
		w.setBlock(x + 1, y - 3, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 3, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 3, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 3, z, VoidMod.chamberWall, 0, 3);
		w.setBlock(x + 3, y - 3, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 1, y - 3, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 3, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 3, z + 3, VoidMod.chamberWall, 0, 3);
		// Door
		w.setBlock(x - 3, y - 4, z, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 3, y - 4, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 3, y - 4, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 4, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 1, y - 4, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 4, z - 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 4, z - 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 4, z, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 3, y - 4, z + 1, VoidMod.chamberWall, 1, 3);
		w.setBlock(x + 1, y - 4, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x - 1, y - 4, z + 3, VoidMod.chamberWall, 1, 3);
		w.setBlock(x, y - 4, z + 3, VoidMod.chamberWall, 1, 3);
		return true;
	}

	private void genReactor(World w, Random r, int x, int y, int z) {
		// Layer 1: VoidReactor
		w.setBlock(x, y - 2, z, VoidMod.voidReactor);
		// Layer 1: VoidFabric
		int bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 2, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 2, z, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 2, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 2, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 2, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 2, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 2, z, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 2, z + 1, VoidMod.VoidFabric);
		}
		// Layer 2: VoidFabric
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 3, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 3, z, VoidMod.walker);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 3, z - 1, VoidMod.swordWall);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 3, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 3, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 3, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 3, z, VoidMod.walker);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 3, z + 1, VoidMod.swordWall);
		}
		// Layer 3: VoidFabric
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 4, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 4, z, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 4, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 4, z - 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x - 1, y - 4, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 4, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x + 1, y - 4, z, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 4, z + 1, VoidMod.VoidFabric);
		}
		bool = r.nextInt(2);
		if (bool == 1) {
			w.setBlock(x, y - 4, z, VoidMod.VoidFabric);
		}
		bool = r.nextInt(6);
		if (bool == 1) {
			w.setBlock(x, y - 3, z, VoidMod.NullOre);
		}
		if (bool > 3) {
			w.setBlock(x, y - 3, z, Blocks.stone);
		}
	}
}
