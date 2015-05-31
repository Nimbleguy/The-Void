package nimble.Java.DevTools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import com.jcraft.jorbis.Block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Events {
	
	public HashMap<EntityPlayerMP, int[][]> map = new HashMap<EntityPlayerMP, int[][]>();
	public HashMap<EntityPlayerMP, Boolean> sha = new HashMap<EntityPlayerMP, Boolean>();

	@SubscribeEvent
	public void playerChat(ServerChatEvent event){
		if(map.get(event.player) == null){
			map.put(event.player, new int[2][3]);
		}
		if(sha.get(event.player) == null){
			sha.put(event.player, false);
		}
		
		if(event.message.equals("pos1")){
			int[][] i = map.get(event.player);
			i[0] = new int[] {(int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY), (int) Math.floor(event.player.posZ)};
			map.put(event.player, i);
		}
		else if(event.message.equals("pos2")){
			int[][] i = map.get(event.player);
			i[1] = new int[] {(int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY), (int) Math.floor(event.player.posZ)};
			map.put(event.player, i);
		}
		else if(event.message.equals("save")){
			NBTTagCompound nbt = new NBTTagCompound();
			NBTTagList list = new NBTTagList();
			int[][] i = map.get(event.player);
			for(int xx = i[0][0]; xx < i[1][0] + 1; xx++){
				for(int yy = i[0][1]; yy < i[1][1] + 1; yy++){
					label:
					for(int zz = i[0][2]; zz < i[1][2] + 1; zz++){
						int x = xx - i[0][0];
						int y = yy - i[0][1];
						int z = zz - i[0][2];
						World w = event.player.worldObj;
						NBTTagCompound block = new NBTTagCompound();
						IBlockState state = w.getBlockState(new BlockPos(xx, yy, zz));
						
						if(GameRegistry.findUniqueIdentifierFor(state.getBlock()).name.equalsIgnoreCase("air")){
							continue label;
						}
						
						block.setInteger("x", x);
						block.setInteger("y", y);
						block.setInteger("z", z);
						block.setString("modid", GameRegistry.findUniqueIdentifierFor(state.getBlock()).modId);
						block.setString("name", GameRegistry.findUniqueIdentifierFor(state.getBlock()).name);
						block.setInteger("meta", state.getBlock().getMetaFromState(state));
						
						list.appendTag(block);
					}
				}
			}
			nbt.setTag("Blocks", list);
			System.out.println(nbt.toString());
		}
		else if(event.message.equals("sha")){
			sha.put(event.player, true);
		}
		else if(sha.get(event.player)){
			sha.put(event.player, false);
			System.out.println(secureString(event.message));
		}
	}
	
	public String secureString(String s){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((s + s.hashCode()).getBytes("UTF-8"));
			return new String(md.digest());
		} catch (NoSuchAlgorithmException e) {
			//This should not happen.
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//This should not happen.
			e.printStackTrace();
		}
		return "";
	}
}
