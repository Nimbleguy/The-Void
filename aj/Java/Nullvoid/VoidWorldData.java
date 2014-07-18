package aj.Java.Nullvoid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class VoidWorldData extends WorldSavedData {
	
	private final static String id = "void.mod.data";
	
	public boolean hasGlitch = false;
	private boolean spawnedAlpha = false;
	
	public VoidWorldData() {
		super(id);
		// TODO Auto-generated constructor stub
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
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		hasGlitch = nbt.getBoolean("hasGlitch");
		spawnedAlpha = nbt.getBoolean("spawnedAlpha");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("hasGlitch", hasGlitch);
		nbt.setBoolean("spawnedAlpha", spawnedAlpha);
	}

}
