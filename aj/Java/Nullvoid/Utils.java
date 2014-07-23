package aj.Java.Nullvoid;

import java.util.List;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.Effects.Effect;
import aj.Java.Nullvoid.Effects.Effects;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Utils {
	@SideOnly(Side.CLIENT)
	public static KeyBinding specialKey;
	static{
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			specialKey = new KeyBinding("key.nullvoid_special.desc", Keyboard.KEY_BACKSLASH, "key.nullvoid.category");
		}
	}
	public static MovingObjectPosition rayTraceLiquid(World w, EntityPlayer p){
		Vec3 vec3 = p.getPosition(5F);
        Vec3 vec31 = p.getLook(5F);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * 5D, vec31.yCoord * 5D, vec31.zCoord * 5D);
        return w.rayTraceBlocks(vec3, vec32, true);
	}
	public static NBTTagCompound getEntityTag(EntityPlayer p){
		NBTTagCompound entityData = p.getEntityData();
		NBTTagCompound persist;
		if (!entityData.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
		    entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persist = new NBTTagCompound()));
		} else {
		    persist = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}
		return persist;
	}
	public static void setInVoid(EntityPlayer p, boolean b){
		getEntityTag(p).setBoolean("NullInVoid", b);
	}
	public static boolean getInVoid(EntityPlayer p){
		return getEntityTag(p).getBoolean("NullInVoid");
	}
	public static void setBright(EntityPlayer p, float f){
		getEntityTag(p).setFloat("NullBright", f);
	}
	public static float getBright(EntityPlayer p){
		return getEntityTag(p).getFloat("NullBright");
	}
	public static void setEffects(EntityPlayer p, Effects e){
		StringBuilder s = new StringBuilder(e.getEffects().size());
		for(int i = 0; i < e.getEffects().size(); i++){
			s.append(e.getEffects().get(i).name());
			if(i != e.getEffects().size() - 1){
				s.append("|");
			}
		}
		getEntityTag(p).setString("NullEffects", s.toString());
	}
	public static Effects getEffects(EntityPlayer p){
		Effects e = new Effects();
		String[] s = getEntityTag(p).getString("NullEffcts").split("|");
		for(int i = 0; i < s.length; i++){
			e.addEffect(Effect.valueOf(s[i]));
		}
		return e;
	}
	@SuppressWarnings("unchecked")
	public static EntityPlayerMP getPlayerFromUUID(String s){
		if(MinecraftServer.getServer().getConfigurationManager().playerEntityList != null){
			for(EntityPlayerMP p : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList){
				if(s.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")){
					if(p.getUniqueID().compareTo(UUID.fromString(s)) == 0){
						return p;
					}
				}
			}
		}
		return null;
	}
}
