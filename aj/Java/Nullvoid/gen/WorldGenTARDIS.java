package aj.Java.Nullvoid.gen;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class WorldGenTARDIS extends WorldGenerator {
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		boolean color = false;
		boolean slab = false;
		boolean light = false;
		while (world.isAirBlock(i, j, k) && j > 2)
        {
            j--;
        }
		j++;
		
		int c = random.nextInt(15);
		if(c == 11){
			color = true;
		}
		genWool(world, i, j, k, c);
		
		ItemDoor.placeDoorBlock(world, i, j, k + 1, 3, VoidMod.voiddoor);
		
		int t = random.nextInt(2);
		if(t == 0){
			light = true;
			genLight(world, i, j, k, Blocks.torch);
		}
		if(t == 1){
			genLight(world, i, j, k, Blocks.glowstone);
		}
		if(t == 2){
			genLight(world, i, j, k, Blocks.redstone_lamp);
		}
		
		int s = random.nextInt(7);
		if(s == 0){
			slab = true;
		}
		genSlab(world, i, j, k, s);
		
		if(color && slab && light){
			world.setBlock(i, j, k, Blocks.chest);
			TileEntity tile = world.getTileEntity(i, j, k);
			if(tile != null){
				if(tile instanceof TileEntityChest){
					TileEntityChest chest = (TileEntityChest)tile;
					WeightedRandomChestContent[] cont = ChestGenHooks.getItems(ChestGenHooks.MINESHAFT_CORRIDOR, random);
					int hi = 0;
					for(int bla = 0; bla < cont.length; bla++){
						if(bla < random.nextInt(19)){
							chest.setInventorySlotContents(bla, cont[bla].theItemId);
							hi = bla + 1;
						}
					}
					chest.setInventorySlotContents(hi, new ItemStack(VoidMod.ingotNull, 64, 0));
					if(random.nextInt(3) == 1){
						chest.setInventorySlotContents(hi + 1, new ItemStack(VoidMod.tablet, 1, 8));
					}
				}
			}
		}
		
		return true;
	}
	private void genWool(World world, int x, int y, int z, int color){
		for(int h = 0; h < 3; h++){
			world.setBlock(x - 1, y + h, z, Blocks.wool, color, 2);
			world.setBlock(x + 1, y + h, z, Blocks.wool, color, 2);
			world.setBlock(x, y + h, z - 1, Blocks.wool, color, 2);
			world.setBlock(x - 1, y + h, z - 1, Blocks.wool, color, 2);
			world.setBlock(x - 1, y + h, z + 1, Blocks.wool, color, 2);
			world.setBlock(x + 1, y + h, z - 1, Blocks.wool, color, 2);
			world.setBlock(x + 1, y + h, z + 1, Blocks.wool, color, 2);
		}
		world.setBlock(x, y + 2, z, Blocks.wool, color, 2);
		world.setBlock(x, y + 2, z + 1, Blocks.wool, color, 2);
	}
	private void genLight(World world, int x, int y, int z, Block toSet){
		world.setBlock(x, y + 3, z, toSet);
	}
	private void genSlab(World world, int x, int y, int z, int data){
		world.setBlock(x - 1, y + 3, z, Blocks.stone_slab, data, 2);
		world.setBlock(x + 1, y + 3, z, Blocks.stone_slab, data, 2);
		world.setBlock(x, y + 3, z - 1, Blocks.stone_slab, data, 2);
		world.setBlock(x, y + 3, z + 1, Blocks.stone_slab, data, 2);
		world.setBlock(x - 1, y + 3, z - 1, Blocks.stone_slab, data, 2);
		world.setBlock(x - 1, y + 3, z + 1, Blocks.stone_slab, data, 2);
		world.setBlock(x + 1, y + 3, z - 1, Blocks.stone_slab, data, 2);
		world.setBlock(x + 1, y + 3, z + 1, Blocks.stone_slab, data, 2);
	}

}
