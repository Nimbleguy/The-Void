package nimble.Java.TheVoid.Events;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Textures.TextureNullOre;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class TextureHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void textureStitchPre(TextureStitchEvent.Pre event){
		if(VoidMod.proxy.texNOre != null){
			event.map.setTextureEntry(ModInfo.MODID + ":oreNullon", VoidMod.proxy.texNOre);
		}
		else{
			event.map.setTextureEntry(ModInfo.MODID + ":oreNullon", VoidMod.proxy.texNOre = new TextureNullOre(ModInfo.MODID + ":blocks/oreNullon"));
		}
	}
}
