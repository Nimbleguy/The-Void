package nimble.Java.TheVoid.Generation;

import java.util.Random;

import net.minecraft.block.Block;
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
			System.out.println("test");
			NBTTagCompound tag = JsonToNBT.getTagFromJson(struct);
			NBTTagList list = tag.getTagList("Blocks", 10);
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound block = list.getCompoundTagAt(i);
				System.out.println(block.getString("modid"));
				System.out.println(block.getString("name"));
				if(block != null && Block.getBlockFromItem(GameRegistry.findItem(block.getString("modid"), block.getString("name"))) != null){
					world.setBlockState(new BlockPos(x + block.getInteger("x"), y + block.getInteger("y"), z + block.getInteger("z")), Block.getBlockFromItem(GameRegistry.findItem(block.getString("modid"), block.getString("name"))).getStateFromMeta(block.getInteger("meta")));
				}
			}
		} catch (NBTException e) {
			//TODO: Proper errors
			e.printStackTrace();
		}
		
	}
}
