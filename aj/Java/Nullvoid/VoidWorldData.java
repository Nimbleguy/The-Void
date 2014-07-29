package aj.Java.Nullvoid;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants;

public class VoidWorldData extends WorldSavedData {
	
	private final static String id = "void.mod.data";
	
	public boolean hasGlitch = false;
	private boolean spawnedAlpha = false;
	
	public VoidWorldData() {
		super(id);
	}
	
	public static VoidWorldData get(World w){
		MapStorage s = w.perWorldStorage;
		VoidWorldData me = (VoidWorldData)s.loadData(VoidWorldData.class, id);
		if(me == null){
			me = new VoidWorldData();
			s.setData(id, me);
		}
		return me;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		hasGlitch = nbt.getBoolean("hasGlitch");
		spawnedAlpha = nbt.getBoolean("spawnedAlpha");
		NBTTagList list = nbt.getTagList("ChunkGen", Constants.NBT.TAG_COMPOUND);
		List<NBTTagCompound> l = null;
		for (Field f : list.getClass().getDeclaredFields()) {
			try {
				if (f.getName().equals("tagList") || f.getName().equals("field_74747_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					f.setAccessible(true);
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.PRIVATE);
					l = (List<NBTTagCompound>)f.get(null);
				}
			} catch (Exception e) {
				System.err
						.println("Severe error, please report this to the mod author:");
				e.printStackTrace(System.err);
			}
		}
		ChunkCoordIntPair coord = null;
		for(NBTTagCompound t : l){
			coord = new ChunkCoordIntPair(t.getInteger("X"), t.getInteger("Z"));
			Utils.hasGen.put(coord, t.getBoolean("hasGen"));
			Utils.hasStruct.put(coord, t.getBoolean("hasStruct"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("hasGlitch", hasGlitch);
		nbt.setBoolean("spawnedAlpha", spawnedAlpha);
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < Utils.hasGen.size(); i++){
			NBTTagCompound t = new NBTTagCompound();
			ChunkCoordIntPair coord = (ChunkCoordIntPair) Utils.hasGen.keySet().toArray()[i];
			t.setInteger("X", coord.chunkXPos);
			t.setInteger("Z", coord.chunkZPos);
			t.setBoolean("hasGen", Utils.hasGen.get(coord));
			t.setBoolean("hasStruct", Utils.hasStruct.get(coord));
			list.appendTag(t);
		}
		nbt.setTag("ChunkGen", list);
	}

}
