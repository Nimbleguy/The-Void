package nimble.Java.TheVoid.Events;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import nimble.Java.TheVoid.VoidMod;

public class PlayerHandler {

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event){
		if(event.phase.equals(TickEvent.Phase.END)){
			if(event.side.isServer()){
				NBTTagCompound v = VoidMod.util.getVoidTag(event.player);
				if(v.getBoolean("InVoid")){
					if(v.getInteger("VoidwalkerConsume") == 0){
						//TODO: Consume fuel
					}
					else{
						v.setInteger("VoidwalkerConsume", v.getInteger("VoidwalkerConsume") - 1);
					}
				}
			}
		}
	}
}
