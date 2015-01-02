package aj.Java.Nullvoid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import aj.Java.Nullvoid.Effects.Effect;
import aj.Java.Nullvoid.Effects.Effects;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Utils {
	@SideOnly(Side.CLIENT)
	public static KeyBinding specialKey;
	public Utils(){
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			specialKey = new KeyBinding("key.nullvoid_special.desc", Keyboard.KEY_BACKSLASH, "key.nullvoid.category");
		}
	}
	public HashMap<ChunkCoordIntPair, Boolean> hasGen = new HashMap<ChunkCoordIntPair, Boolean>(1000);
	public HashMap<ChunkCoordIntPair, Boolean> hasStruct = new HashMap<ChunkCoordIntPair, Boolean>(1000);
	public NBTTagCompound getEntityTag(Entity e){
		NBTTagCompound entityData = e.getEntityData();
		NBTTagCompound persist;
		if (!entityData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
		    entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persist = new NBTTagCompound()));
		} else {
		    persist = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}
		return persist;
	}
	public void setInVoid(EntityPlayer p, boolean b){
		getEntityTag(p).setBoolean("NullInVoid", b);
	}
	public boolean getInVoid(EntityPlayer p){
		return getEntityTag(p).getBoolean("NullInVoid");
	}
	public void setBright(EntityPlayer p, float f){
		getEntityTag(p).setFloat("NullBright", f);
	}
	public float getBright(EntityPlayer p){
		return getEntityTag(p).getFloat("NullBright");
	}
	public void setEffects(EntityPlayer p, Effects e){
		StringBuilder s = new StringBuilder(e.getEffects().size());
		for(int i = 0; i < e.getEffects().size(); i++){
			s.append(e.getEffects().get(i).name());
			if(i != e.getEffects().size() - 1){
				s.append("|");
			}
		}
		getEntityTag(p).setString("NullEffects", s.toString());
	}
	public Effects getEffects(EntityPlayer p){
		Effects e = new Effects();
		String[] s = getEntityTag(p).getString("NullEffcts").split("|");
		for(int i = 0; i < s.length; i++){
			e.addEffect(Effect.valueOf(s[i]));
		}
		return e;
	}
	public void setVoidwalking(Entity e, int i){
		getEntityTag(e).setInteger("NullVoidwalking", i);
	}
	public int getVoidwalking(Entity e){
		return getEntityTag(e).getInteger("NullVoidwalking");
	}
	public void setGlitchstone(EntityPlayer p, boolean b){
		getEntityTag(p).setBoolean("NullGlitchstone", b);
	}
	public boolean getGlitchstone(EntityPlayer p){
		return getEntityTag(p).getBoolean("NullGlitchstone");
	}
	@SuppressWarnings("unchecked")
	public EntityPlayerMP getPlayerFromUUID(String s){
		if(MinecraftServer.getServer().getConfigurationManager() != null && MinecraftServer.getServer().getConfigurationManager().playerEntityList != null){
			for(EntityPlayerMP p : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList){
				if(s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
					if(p.getPersistentID().compareTo(UUID.fromString(s)) == 0){
						return p;
					}
				}
			}
		}
		return null;
	}
	public int hammerBalance(ItemStack i){
		int[] values = new int[8];
		values[0] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.FIRE, i);
		values[1] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ICE, i);
		values[2] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.EARTH, i);
		values[3] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.AIR, i);
		values[4] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ORDER, i);
		values[5] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ENTROPY, i);
		values[6] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.DARK, i);
		values[7] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.LIGHT, i);
		int greatest = values[0];
		int index = -1;
		for(int place = 0; place < 8; place++){
			if(values[place] > greatest){
				greatest = values[place];
				index = place;
			}
		}
		return index;
	}
	/*
	public static List<ItemStack> getBlockDrops(WorldServer world, int i, int j, int k, EntityPlayer p) {
		Block block = world.getBlock(i, j, k);

		if (block == null) {
			return null;
		}

		if (block.isAir(world, i, j, k)) {
			return null;
		}

		int meta = world.getBlockMetadata(i, j, k);

		ArrayList<ItemStack> dropsList = block.getDrops(world, i, j, k, meta, 0);
		float dropChance = ForgeEventFactory.fireBlockHarvesting(dropsList, world, block, i, j, k, meta, 0, 1.0F,
				false, p);

		ArrayList<ItemStack> returnList = new ArrayList<ItemStack>();
		for (ItemStack s : dropsList) {
			if (world.rand.nextFloat() <= dropChance) {
				returnList.add(s);
			}
		}

		return returnList;
	}
	*/
}
