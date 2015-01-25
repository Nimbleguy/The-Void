package nimble.Java.TheVoid.Events;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class MiscHandler {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(event.modID.equals(ModInfo.MODID)){
			VoidMod.config.loadConfig();
		}
	}
}
