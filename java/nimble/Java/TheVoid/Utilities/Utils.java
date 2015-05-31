package nimble.Java.TheVoid.Utilities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import nimble.Java.TheVoid.Dimension.TeleporterVoid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private final Logger log = LogManager.getLogger("TheVoid");
	
	public <T extends Comparable<T>> void log(T message, Level l){
		log.log(l, message);
	}
	
	public NBTTagCompound getEntityTag(EntityPlayer e){
		if(e != null){
			NBTTagCompound entityData = e.getEntityData();
			NBTTagCompound persist;
			if (!entityData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
		    	entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persist = new NBTTagCompound()));
			} else {
		    	persist = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
			}
			return persist;
		}
		else{
			return new NBTTagCompound();
		}
	}
	public void setEntityTag(EntityPlayer e, NBTTagCompound nbt){
		if(e != null){
		    e.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, nbt);
		}
	}
	
	public NBTTagCompound getVoidTag(EntityPlayer e){
		NBTTagCompound persist = getEntityTag(e);
		NBTTagCompound voidTag;
		if (!persist.hasKey("Void")) {
		    persist.setTag("Void", (voidTag = new NBTTagCompound()));
		} else {
		    voidTag = persist.getCompoundTag("Void");
		}
		return voidTag;
	}
	public void setVoidTag(EntityPlayer e, NBTTagCompound nbt){
		NBTTagCompound ent = getEntityTag(e);
		ent.setTag("Void", nbt);
		setEntityTag(e, ent);
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
	
	public void sendToDim(EntityPlayerMP p, int dimid, boolean unsafe){
		if(unsafe){
			if(!p.worldObj.isRemote && !p.isDead){
            	MinecraftServer minecraftserver = MinecraftServer.getServer();
            	int cDim = p.dimension;
            	WorldServer cWorld = minecraftserver.worldServerForDimension(cDim);
            	WorldServer nWorld = minecraftserver.worldServerForDimension(dimid);
            	p.dimension = dimid;
            	
            	p.worldObj.removeEntity(p);
            	p.isDead = false;
            	Entity entity = EntityList.createEntityByName(EntityList.getEntityString(p), nWorld);
            	
            	if (entity != null)
            	{
                	entity.copyDataFromOld(p);
                	
                	BlockPos blockpos = p.worldObj.getTopSolidOrLiquidBlock(p.getPosition());
                	entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw, entity.rotationPitch);
                	nWorld.spawnEntityInWorld(entity);
                	entity.setWorld(nWorld);
            	}
            	
            	p.isDead = true;
            	cWorld.resetUpdateEntityTick();
            	nWorld.resetUpdateEntityTick();
			}
		}
		else{
			p.mcServer.getConfigurationManager().transferPlayerToDimension(p, dimid, new TeleporterVoid(p.mcServer.worldServerForDimension(dimid)));
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
