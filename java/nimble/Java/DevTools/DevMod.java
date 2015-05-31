package nimble.Java.DevTools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "NulledDev", name = "Nulled Dev Tools", version = "Omega", dependencies = "required-after:TheVoid;")
public class DevMod {

	@Instance("NulledDev")
	public static DevMod instance;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new Events());
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event){
		
	}
}
