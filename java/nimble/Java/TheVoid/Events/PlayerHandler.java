package nimble.Java.TheVoid.Events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityVoidwalker;
import nimble.Java.TheVoid.Packet.PacketGamma;

public class PlayerHandler {

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event){
		if(event.phase.equals(TickEvent.Phase.END)){
			if(event.side.isServer()){
				NBTTagCompound v = VoidMod.util.getVoidTag(event.player);
				if(v.getBoolean("InVoid")){
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
				else{
					if(event.player.dimension == VoidMod.config.dimid && event.player instanceof EntityPlayerMP){
						event.player.addChatMessage(new ChatComponentText("The world around you seems false..."));
						VoidMod.util.sendToDim((EntityPlayerMP)event.player, 0, true);
						VoidMod.packet.INSTANCE.sendTo(new PacketGamma(v.getFloat("Gamma"), true), (EntityPlayerMP)event.player);
					}
				}
				VoidMod.util.setVoidTag(event.player, v);
			}
		}
	}
}
