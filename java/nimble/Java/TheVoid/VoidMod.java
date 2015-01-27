package nimble.Java.TheVoid;

import org.apache.logging.log4j.Level;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nimble.Java.TheVoid.Block.BlockTerrain;
import nimble.Java.TheVoid.Configuration.Config;
import nimble.Java.TheVoid.Events.MiscHandler;
import nimble.Java.TheVoid.Item.ItemMaterial;
import nimble.Java.TheVoid.Utilities.ModInfo;
import nimble.Java.TheVoid.Utilities.Utils;
import nimble.Java.TheVoid.Utilities.Variant;


@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES, guiFactory = "nimble.Java.TheVoid.Client.Gui.TheVoidGuiFactory")
public class VoidMod {
	
	public static Utils util = new Utils();
	public static Config config;
	public static VoidTab tab;
	
	@SidedProxy(clientSide = "nimble.Java.TheVoid.Client.ClientProxy", serverSide = "nimble.Java.TheVoid.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.MODID)
	public static VoidMod instance;
	
	@Variant({"terrainFabric", "terrainRock"})
	public static BlockTerrain terrain;
	
	@Variant({"materialUNull", "materialUVoid"})
	public static ItemMaterial material;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		//Dynamically inserts version for less work on my end
		event.getModMetadata().version = ModInfo.VERSION;
		
		//Load Config
		config = new Config(new Configuration(event.getSuggestedConfigurationFile()));
		config.getConfig().load();
		config.loadConfig();
		
		//Creative Tab
		tab = new VoidTab();
		
		//Blocks
		terrain = new BlockTerrain();
		
		//Items
		material = new ItemMaterial();
		
		//Register Variants
		proxy.registerVariants();
		
		//Events
		FMLCommonHandler.instance().bus().register(new MiscHandler());
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		//Register models and textures for blocks and items.
		proxy.registerTextures();
		
		//Logging fun
		util.log("Are you ready to Voidwalk?", Level.INFO);
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
}
