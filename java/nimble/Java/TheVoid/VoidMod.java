package nimble.Java.TheVoid;

import java.util.HashMap;

import org.apache.logging.log4j.Level;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.Biome.BiomeGenVoid;
import nimble.Java.TheVoid.Block.BlockTerrain;
import nimble.Java.TheVoid.Block.BlockVoidwalker;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityVoidwalker;
import nimble.Java.TheVoid.Configuration.Config;
import nimble.Java.TheVoid.Dimension.WorldProviderVoid;
import nimble.Java.TheVoid.Events.MiscHandler;
import nimble.Java.TheVoid.Events.PlayerHandler;
import nimble.Java.TheVoid.Item.ItemKeystone;
import nimble.Java.TheVoid.Item.ItemMaterial;
import nimble.Java.TheVoid.Packet.PacketHandler;
import nimble.Java.TheVoid.Utilities.ModInfo;
import nimble.Java.TheVoid.Utilities.Utils;
import nimble.Java.TheVoid.Utilities.Variant;


@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES, guiFactory = "nimble.Java.TheVoid.Client.Gui.TheVoidGuiFactory")
public class VoidMod {
	
	public static Utils util = new Utils();
	public static Config config;
	public static VoidTab tab;
	public static PacketHandler packet = new PacketHandler();
	
	public static HashMap<String, Integer> voidTime = new HashMap<String, Integer>();
	
	@SidedProxy(clientSide = "nimble.Java.TheVoid.Client.ClientProxy", serverSide = "nimble.Java.TheVoid.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.MODID)
	public static VoidMod instance;
	
	@Variant({"terrainFabric", "terrainRock"})
	public static BlockTerrain terrain;
	public static BlockVoidwalker voidwalker;
	
	@Variant({"materialUNull", "materialUVoid"})
	public static ItemMaterial material;
	@Variant({"keystoneInert", "keystoneEnergized", "keystoneDestablized", "keystoneFluxuating", "keystoneActive"})
	public static ItemKeystone keystone;
	
	public static BiomeGenBase biomeVoid;
	
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
		
		//Fluids
		
		
		//Blocks
		terrain = new BlockTerrain();
		voidwalker = new BlockVoidwalker();
		
		//TileEntities
		GameRegistry.registerTileEntity(TileEntityVoidwalker.class, "Voidwalker");
		
		//Items
		material = new ItemMaterial();
		keystone = new ItemKeystone();
		
		//Register Variants
		proxy.registerVariants();
		
		//Biomes
		biomeVoid = new BiomeGenVoid(config.biomeid);
		
		//Packets
		packet.init();
		
		//Dimension
		DimensionManager.registerProviderType(config.dimid, WorldProviderVoid.class, false);
		DimensionManager.registerDimension(config.dimid, config.dimid);
		
		//Events
		FMLCommonHandler.instance().bus().register(new PlayerHandler());
		FMLCommonHandler.instance().bus().register(new MiscHandler());
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		//Register models and textures for blocks and items.
		proxy.registerTextures();
		
		//Crafting Recipes
		ItemStack crudeNullon = new ItemStack(material, 1, 0);
		GameRegistry.addShapedRecipe(new ItemStack(keystone, 1, 0),
				"RCR", "CNC", "RCR",
				'R', Blocks.netherrack,
				'C', Blocks.cobblestone,
				'N', crudeNullon);
		GameRegistry.addShapedRecipe(new ItemStack(keystone, 1, 1),
				"GBG", "NKN", "GBG",
				'K', new ItemStack(keystone, 1, 0),
				'G', Items.glowstone_dust,
				'B', Blocks.glowstone,
				'N', crudeNullon);
		GameRegistry.addShapedRecipe(new ItemStack(keystone, 1, 2),
				"NNN", "NKN", "BRB",
				'K', new ItemStack(keystone, 1, 1),
				'G', Items.redstone,
				'B', Blocks.redstone_block,
				'N', crudeNullon);
		GameRegistry.addShapedRecipe(new ItemStack(keystone, 1, 3),
				"CGM", "NKN", "YRI",
				'K', new ItemStack(keystone, 1, 2),
				'R', Blocks.redstone_block,
				'G', Blocks.glowstone,
				'Y', new ItemStack(Items.dye, 1, 11),
				'C', new ItemStack(Items.dye, 1, 6),
				'M', new ItemStack(Items.dye, 1, 13),
				'I', new ItemStack(Items.dye, 1, 0),
				'N', crudeNullon);
		GameRegistry.addShapedRecipe(new ItemStack(keystone, 1, 4),
				"EDE", "NKN", "NQN",
				'K', new ItemStack(keystone, 1, 3),
				'Q', Blocks.quartz_block,
				'D', Items.diamond,
				'E', Blocks.end_stone,
				'N', crudeNullon);
		
		//Register Void Times
		voidTime.put(material.getUnlocalizedName(new ItemStack(material)), 300);
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event){
		//Logging fun
		util.log("Are you ready to Voidwalk?", Level.INFO);
    }
}
