package nimble.Java.TheVoid.Generation;

import java.util.Random;

import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TemplateGeneration {
	
	public void generate(Random random, int x, int y, int z, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider, String struct) {
		try {
			NBTTagCompound tag = JsonToNBT.func_180713_a(struct);
			NBTTagList list = tag.getTagList("Blocks", 10);
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound block = list.getCompoundTagAt(i);
				world.setBlockState(new BlockPos(x + block.getInteger("x"), y + block.getInteger("y"), z + block.getInteger("z")), GameRegistry.findBlock(block.getString("modid"), block.getString("name")).getStateFromMeta(block.getInteger("meta")));
			}
		} catch (NBTException e) {
			//TODO: Proper errors
			e.printStackTrace();
		}
		
	}
}
