package nimble.Java.TheVoid.Events;

import java.lang.reflect.Field;

import baubles.api.BaublesApi;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityVoidwalker;
import nimble.Java.TheVoid.Packet.PacketGamma;

public class PlayerHandler {

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event){
		if(event.phase.equals(TickEvent.Phase.END)){
			if(event.side.isServer()){
				NBTTagCompound v = VoidMod.util.getVoidTag(event.player);
				if(v.getBoolean("InVoid") && !v.getBoolean("Clone")){
					if(event.player.dimension == VoidMod.config.dimid && event.player instanceof EntityPlayerMP){
						VoidMod.packet.INSTANCE.sendTo(new PacketGamma(-4.2f, true), (EntityPlayerMP)event.player);
						if(v.getInteger("VoidwalkerConsume") == 0){
							NBTTagCompound pos = v.getCompoundTag("VoidwalkerPos");
							BlockPos p = new BlockPos(pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z"));

							EntityPlayerMP player = (EntityPlayerMP) event.player;
							TileEntity e = player.mcServer.worldServerForDimension(pos.getInteger("dim")).getTileEntity(p);
							if(e != null && e instanceof TileEntityVoidwalker){
								TileEntityVoidwalker tev = (TileEntityVoidwalker)e;
								ItemStack item;
								if((item = tev.getStackInSlot(1)) != null){
									v.setInteger("VoidwalkerConsume", VoidMod.voidTime.get(VoidMod.material.getUnlocalizedName(new ItemStack(item.getItem(), 0, item.getMetadata()))));
									tev.decrStackSize(1, 1);
								}
								else{
									VoidMod.util.sendToDim(player, 0, false);
									player.addChatMessage(new ChatComponentText("Dull flickers of darkness fade from your vision..."));
									v.setBoolean("InVoid", false);
									VoidMod.packet.INSTANCE.sendTo(new PacketGamma(v.getFloat("Gamma"), true), (EntityPlayerMP)event.player);
								}
							}
							else{
								player.addChatMessage(new ChatComponentText("Your connection to The Void shatters, leaving wisps of your previous world."));
								v.setBoolean("InVoid", false);
								VoidMod.util.sendToDim(player, 0, true);
								VoidMod.packet.INSTANCE.sendTo(new PacketGamma(v.getFloat("Gamma"), true), (EntityPlayerMP)event.player);
							}
						}
						else if(v.getInteger("VoidwalkerConsume") > 0){
							v.setInteger("VoidwalkerConsume", v.getInteger("VoidwalkerConsume") - 1);
						}
					}
					else{
						v.setBoolean("InVoid", false);
						VoidMod.packet.INSTANCE.sendTo(new PacketGamma(v.getFloat("Gamma"), true), (EntityPlayerMP)event.player);
					}
				}
				else if(!v.getBoolean("Clone")){
					if(event.player.dimension == VoidMod.config.dimid && event.player instanceof EntityPlayerMP){
						event.player.addChatMessage(new ChatComponentText("The world around you seems false..."));
						VoidMod.util.sendToDim((EntityPlayerMP)event.player, 0, true);
						VoidMod.packet.INSTANCE.sendTo(new PacketGamma(v.getFloat("Gamma"), true), (EntityPlayerMP)event.player);
					}
				}
				VoidMod.util.setVoidTag(event.player, v);
				/*
				if(VoidMod.omnicharm.omni.get(event.player) != null){
					Field field = null;
					for(Field f : EntityLivingBase.class.getDeclaredFields()){
						if(f.getName().equalsIgnoreCase("field_70773_bE") || f.getName().equalsIgnoreCase("jumpTicks")){
							field = f;
						}
					}
					field.setAccessible(true);
					
					if(event.player instanceof EntityPlayerMP){
						VoidMod.packet.INSTANCE.sendTo(new PacketMotion(0, 0), (EntityPlayerMP)event.player);
					}
					
					for(Clone p : VoidMod.omnicharm.omni.get(event.player)){
						if(p.getHealth() > event.player.getHealth()){
							p.setHealth(event.player.getHealth());
						}
						else if(p.getHealth() < event.player.getHealth()){
							event.player.setHealth(p.getHealth());
						}
						p.inventory = event.player.inventory;
						p.setSneaking(event.player.isSneaking());
						p.setSprinting(event.player.isSprinting());
						
						p.rotationYaw = event.player.rotationYaw;
						p.rotationPitch = event.player.rotationPitch;
						p.onUpdateEntity();
						p.onUpdate();
					}
				}*/
			}
		}
	}
	/*
	@SubscribeEvent
	public void livingJump(LivingJumpEvent event){
		if(event.entityLiving instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
			NBTTagCompound tag = VoidMod.util.getVoidTag(player);
			if(VoidMod.omnicharm.omni.get(player) != null){
				Field field = null;
				for(Field f : EntityLivingBase.class.getDeclaredFields()){
					if(f.getName().equalsIgnoreCase("field_70773_bE") || f.getName().equalsIgnoreCase("jumpTicks")){
						field = f;
					}
				}
				field.setAccessible(true);
				for(EntityPlayerMP p : VoidMod.omnicharm.omni.get(player)){
					try {
						if(field.getInt(p) <= 0 && p.onGround){
							p.jump();
							field.setInt(p, 10);
						}
					}
					catch (Exception e) {
						//TODO: Proper errors
						e.printStackTrace();
					}
				}
			}
		}
	}*/
	
	@SubscribeEvent
	public void playerDrop(PlayerDropsEvent event){
		if(event.entityPlayer instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
			if(VoidMod.util.getVoidTag(player).getBoolean("Clone")){
				event.setCanceled(true);
			}
		}
	}
	
	/*
	@SubscribeEvent
	public void playerInteract(PlayerInteractEvent event){
		if(event.entityPlayer instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
			if(VoidMod.omnicharm.omni.get(event.entityPlayer) != null){
				for(EntityPlayerMP p : VoidMod.omnicharm.omni.get(player)){
					
				}
			}
		}
	}
	*/
	
	@SubscribeEvent
	public void playerChat(ServerChatEvent event){
		if(VoidMod.util.secureString(event.message).equals("i���>C<�s��� ��O��x�L����ȕw")){
			//OMEGA
			try {
				boolean matched = true;
				NBTTagCompound tag = JsonToNBT.getTagFromJson(VoidMod.assets.omegaGenerator);
				NBTTagList list = tag.getTagList("Blocks", 10);
				//++
				for(int i = 0; i < list.tagCount(); i++){
					NBTTagCompound block = list.getCompoundTagAt(i);
					IBlockState state = event.player.worldObj.getBlockState(new BlockPos(Math.floor(event.player.posX) + block.getInteger("x") - 3, Math.floor(event.player.posY) + block.getInteger("y") - 1, Math.floor(event.player.posZ) + block.getInteger("z") - 3));
					if(!(GameRegistry.findUniqueIdentifierFor(state.getBlock()).name.equals(block.getString("name")) && GameRegistry.findUniqueIdentifierFor(state.getBlock()).modId.equals(block.getString("modid")) && (state.getBlock().getMetaFromState(state) == block.getInteger("meta") || block.getInteger("meta") == -1))){
						matched = false;
						System.out.println(block.toString());
						System.out.println((Math.floor(event.player.posX) + block.getInteger("x") - 3) + " " + (Math.floor(event.player.posY) + block.getInteger("y") - 1) + " " + (Math.floor(event.player.posZ) + block.getInteger("z") - 3));
						System.out.println(state.toString());
						System.out.println();
					}
				}
				if(!matched){
					matched = true;
					//-+
					for(int i = 0; i < list.tagCount(); i++){
						NBTTagCompound block = list.getCompoundTagAt(i);
						IBlockState state = event.player.worldObj.getBlockState(new BlockPos(Math.floor(event.player.posX) + block.getInteger("x") + 3, Math.floor(event.player.posY) + block.getInteger("y") - 1, Math.floor(event.player.posZ) + block.getInteger("z") - 3));
						if(!(GameRegistry.findUniqueIdentifierFor(state.getBlock()).name.equals(block.getString("name")) && GameRegistry.findUniqueIdentifierFor(state.getBlock()).modId.equals(block.getString("modid")) && (state.getBlock().getMetaFromState(state) == block.getInteger("meta") || block.getInteger("meta") == -1))){
							matched = false;
						}
					}
					if(!matched){
						matched = true;
						//+-
						for(int i = 0; i < list.tagCount(); i++){
							NBTTagCompound block = list.getCompoundTagAt(i);
							IBlockState state = event.player.worldObj.getBlockState(new BlockPos(Math.floor(event.player.posX) + block.getInteger("x") - 3, Math.floor(event.player.posY) + block.getInteger("y") - 1, Math.floor(event.player.posZ) + block.getInteger("z") + 3));
							if(!(GameRegistry.findUniqueIdentifierFor(state.getBlock()).name.equals(block.getString("name")) && GameRegistry.findUniqueIdentifierFor(state.getBlock()).modId.equals(block.getString("modid")) && (state.getBlock().getMetaFromState(state) == block.getInteger("meta") || block.getInteger("meta") == -1))){
								matched = false;
							}
						}
						if(!matched){
							matched = true;
							//--
							for(int i = 0; i < list.tagCount(); i++){
								NBTTagCompound block = list.getCompoundTagAt(i);
								IBlockState state = event.player.worldObj.getBlockState(new BlockPos(Math.floor(event.player.posX) + block.getInteger("x") + 3, Math.floor(event.player.posY) + block.getInteger("y") - 1, Math.floor(event.player.posZ) + block.getInteger("z") + 3));
								if(!(GameRegistry.findUniqueIdentifierFor(state.getBlock()).name.equals(block.getString("name")) && GameRegistry.findUniqueIdentifierFor(state.getBlock()).modId.equals(block.getString("modid")) && (state.getBlock().getMetaFromState(state) == block.getInteger("meta") || block.getInteger("meta") == -1))){
									matched = false;
								}
							}
							if(matched){
								System.out.println("yay");
							}
						}
						else{
							System.out.println("yay");
						}
					}
					else{
						System.out.println("yay");
					}
				}
				else{
					System.out.println("yay");
				}
				
			} catch (NBTException e) {
				//TODO: Proper errors
				e.printStackTrace();
			}
		}
	}
}
