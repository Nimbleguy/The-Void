package nimble.Java.TheVoid.Bauble;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Entities.Clone;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;

public class ItemOmnipresentCharm extends Item implements IBauble {

	public HashMap <EntityPlayerMP, Clone> clones = new HashMap<EntityPlayerMP, Clone>();
	
	public ItemOmnipresentCharm(){
		super();
		this.setUnlocalizedName("omnicharm");
		this.setCreativeTab(VoidMod.tab);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
		GameRegistry.registerItem(this, getUnlocalizedName().replace("item.", ""));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(!par2World.isRemote) { 
			IInventory baubles = BaublesApi.getBaubles(par3EntityPlayer);
			for(int i = 0; i < baubles.getSizeInventory(); i++)
				if(baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, par1ItemStack)) {
					baubles.setInventorySlotContents(i, par1ItemStack.copy());
					if(!par3EntityPlayer.capabilities.isCreativeMode){
						par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, null);
					}
					onEquipped(par1ItemStack, par3EntityPlayer);
					break;
				}
		}

		return par1ItemStack;	
	}
	
	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.AMULET;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		
	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
		
	}
	
	int ticksSnuck = 0;
	@Override
	public void onWornTick(ItemStack item, EntityLivingBase entity) {
		if (entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			if (player.isSneaking()){
				ticksSnuck++;
				if (ticksSnuck >= 50){
					Clone clone = new Clone(player.worldObj);
					player.worldObj.spawnEntityInWorld(clone);//THIS IS A COMMENT
					clone.setLocationAndAngles(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ, player.rotationYaw, player.rotationPitch);
					clones.put(player, clone);
					ticksSnuck=0;
				}
			}
		}
		
		/*if(arg1 instanceof EntityPlayerMP){
			EntityPlayerMP p = (EntityPlayerMP)arg1;
			NBTTagCompound tag = VoidMod.util.getVoidTag(p);
			if(p.isSneaking()){
				if(tag.getInteger("OmnipresentCooldown") <= 0){
					//for(WorldServer ws : MinecraftServer.getServer().worldServers){*/
						//int dimid = /*ws.provider.getDimensionId()*/p.dimension;
						/*System.out.println(dimid);
						if(!p.worldObj.isRemote && !p.isDead){
							MinecraftServer minecraftserver = MinecraftServer.getServer();
		            		WorldServer nWorld = minecraftserver.worldServerForDimension(dimid);
		            		
		            		EntityPlayerMP entity = new EntityPlayerMP(p.mcServer, nWorld, p.getGameProfile(), p.theItemInWorldManager);
		            		entity.playerNetServerHandler = p.playerNetServerHandler;
		            		
		                	BlockPos blockpos = new BlockPos(p.getPosition().getX() + 1, p.getPosition().getY(), p.getPosition().getZ());
		              		
		                	NBTTagCompound etag = VoidMod.util.getVoidTag(entity);
		                	tag.setInteger("OmnipresentCooldown", 200);
		                	etag.setBoolean("Clone", true);
		               		VoidMod.util.setVoidTag(entity, etag);
		               		
		               		entity.copyDataFromOld(p);
		               		
		               		entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw, entity.rotationPitch);
		               		nWorld.spawnEntityInWorld(entity);
		               		
		               		List<EntityPlayerMP> clones;
		               		if((clones = omni.get(p)) == null){
		               			clones = new ArrayList<EntityPlayerMP>();
		               		}
		               		clones.add(entity);
		               		omni.put(p, clones);
						}
					//}
				}
			}
			else{
				if(tag.getInteger("OmnipresentCooldown") > 0){
					tag.setInteger("OmnipresentCooldown", tag.getInteger("OmnipresentCooldown") - 1);
				}
			}
			VoidMod.util.setVoidTag(p, tag);
		}*/
	}

}
