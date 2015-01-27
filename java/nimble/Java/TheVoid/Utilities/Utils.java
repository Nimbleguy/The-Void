package nimble.Java.TheVoid.Utilities;

import java.io.Serializable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private final Logger log = LogManager.getLogger("TheVoid");
	
	public <T extends Comparable<T>> void log(T message, Level l){
		log.log(l, message);
	}
	
	public NBTTagCompound getEntityTag(EntityPlayer e){
		NBTTagCompound entityData = e.getEntityData();
		NBTTagCompound persist;
		if (!entityData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
		    entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persist = new NBTTagCompound()));
		} else {
		    persist = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}
		return persist;
	}
	
	public NBTTagCompound getVoidTag(EntityPlayer e){
		NBTTagCompound persist = getEntityTag(e);
		NBTTagCompound voidTag;
		if (!persist.hasKey("Void")) {
		    persist.setTag("Void", (voidTag = new NBTTagCompound()));
		} else {
		    persist = persist.getCompoundTag("Void");
		}
		return persist;
	}
	
	public void setInVoid(EntityPlayer p, int ticksToConsume, BlockPos pos){
		NBTTagCompound v = getVoidTag(p);
		v.setIntArray("VoidwalkerPos", new int[] {pos.getX(), pos.getY(), pos.getZ()});
		v.setInteger("VoidwalkerConsume", ticksToConsume);
		v.setBoolean("InVoid", true);
		//TODO: Transport player to void.
	}
}
