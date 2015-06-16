package nimble.Java.TheVoid.World;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class VoidWorldData extends WorldSavedData {

	public VoidWorldData() {
		super(ModInfo.MODID);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	}
	
	public static VoidWorldData get(World world) {
		VoidWorldData data = (VoidWorldData)world.loadItemData(VoidWorldData.class, ModInfo.MODID);
		if (data == null) {
			data = new VoidWorldData();
			world.setItemData(ModInfo.MODID, data);
		}
		return data;
	}

}
