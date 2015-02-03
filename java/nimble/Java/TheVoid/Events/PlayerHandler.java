package nimble.Java.TheVoid.Events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityVoidwalker;

public class PlayerHandler {

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event){
		if(event.phase.equals(TickEvent.Phase.END)){
			if(event.side.isServer()){
				NBTTagCompound v = VoidMod.util.getVoidTag(event.player);
				if(v.getBoolean("InVoid")){
					if(v.getInteger("VoidwalkerConsume") == 0){
						NBTTagCompound pos = v.getCompoundTag("VoidwalkerPos");
						BlockPos p = new BlockPos(pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z"));
						
						EntityPlayer player = event.player;
						TileEntity e = player.worldObj.getTileEntity(p);
						if(e instanceof TileEntityVoidwalker){
							TileEntityVoidwalker tev = (TileEntityVoidwalker)e;
							
						}
						else{
							//TODO: K-k-k-k-ick 'em out-out-out
						}
					}
					else if(v.getInteger("VoidwalkerConsume") > 0){
						v.setInteger("VoidwalkerConsume", v.getInteger("VoidwalkerConsume") - 1);
					}
				}
			}
		}
	}
}
