package nimble.Java.TheVoid.Utilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

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
	
	public void spawnItemStack(World worldIn, double p_180173_1_, double p_180173_3_, double p_180173_5_, ItemStack p_180173_7_)
    {
		Random r = new Random();
        float f = r.nextFloat() * 0.8F + 0.1F;
        float f1 = r.nextFloat() * 0.8F + 0.1F;
        float f2 = r.nextFloat() * 0.8F + 0.1F;

        while (p_180173_7_.stackSize > 0)
        {
            int i = r.nextInt(21) + 10;

            if (i > p_180173_7_.stackSize)
            {
                i = p_180173_7_.stackSize;
            }

            p_180173_7_.stackSize -= i;
            EntityItem entityitem = new EntityItem(worldIn, p_180173_1_ + (double)f, p_180173_3_ + (double)f1, p_180173_5_ + (double)f2, new ItemStack(p_180173_7_.getItem(), i, p_180173_7_.getMetadata()));

            if (p_180173_7_.hasTagCompound())
            {
                entityitem.getEntityItem().setTagCompound((NBTTagCompound)p_180173_7_.getTagCompound().copy());
            }

            float f3 = 0.05F;
            entityitem.motionX = r.nextGaussian() * (double)f3;
            entityitem.motionY = r.nextGaussian() * (double)f3 + 0.20000000298023224D;
            entityitem.motionZ = r.nextGaussian() * (double)f3;
            worldIn.spawnEntityInWorld(entityitem);
        }
    }
}
