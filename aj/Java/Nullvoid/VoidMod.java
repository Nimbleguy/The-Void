package aj.Java.Nullvoid;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aj.Java.Nullvoid.Armor.ArmorNull;
import aj.Java.Nullvoid.Armor.GlitchAmulet;
import aj.Java.Nullvoid.Armor.GravityBelt;
import aj.Java.Nullvoid.Armor.PhantomRing;
import aj.Java.Nullvoid.Biome.BiomeGenNull;
import aj.Java.Nullvoid.Dimention.WorldProviderNullVoid;
import aj.Java.Nullvoid.Entity.EntityBuilder;
import aj.Java.Nullvoid.Entity.EntityGlitch;
import aj.Java.Nullvoid.Entity.EntityNullFloater;
import aj.Java.Nullvoid.Entity.EntityVoidCloud;
import aj.Java.Nullvoid.Entity.EntityVoidMaster;
import aj.Java.Nullvoid.Liquids.BucketHandler;
import aj.Java.Nullvoid.Liquids.FluidMoltenFlux;
import aj.Java.Nullvoid.Listners.TickListner;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Potion.PotionDissolving;
import aj.Java.Nullvoid.Tools.ItemBaneOfDarkness;
import aj.Java.Nullvoid.Tools.ItemDarknessPick;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.block.BlockChamberWall;
import aj.Java.Nullvoid.block.BlockDecor;
import aj.Java.Nullvoid.block.BlockGeneric;
import aj.Java.Nullvoid.block.BlockGlitchCrystal;
import aj.Java.Nullvoid.block.BlockGlitchFrame;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import aj.Java.Nullvoid.block.BlockNullOre;
import aj.Java.Nullvoid.block.BlockPhantom;
import aj.Java.Nullvoid.block.BlockStorage;
import aj.Java.Nullvoid.block.BlockSwordWall;
import aj.Java.Nullvoid.block.BlockTransparent;
import aj.Java.Nullvoid.block.BlockVoidDoor;
import aj.Java.Nullvoid.block.BlockVoidFabric;
import aj.Java.Nullvoid.block.BlockVoidOre;
import aj.Java.Nullvoid.block.BlockVoidReactor;
import aj.Java.Nullvoid.block.BlockVoidWalker;
import aj.Java.Nullvoid.block.item.ItemBlockChamberWall;
import aj.Java.Nullvoid.block.item.ItemBlockDecor;
import aj.Java.Nullvoid.block.item.ItemBlockStorage;
import aj.Java.Nullvoid.client.ClientProxy;
import aj.Java.Nullvoid.client.GUIHandler;
import aj.Java.Nullvoid.client.HasStates;
import aj.Java.Nullvoid.client.MovingSoundPlayer;
import aj.Java.Nullvoid.client.render.TextureNullOre;
import aj.Java.Nullvoid.gen.VoidModOreGenerator;
import aj.Java.Nullvoid.gen.VoidModStructureGenerator;
import aj.Java.Nullvoid.gen.GlitchTemple.TempleManager;
import aj.Java.Nullvoid.item.ItemAntiGlitchCore;
import aj.Java.Nullvoid.item.ItemBucket;
import aj.Java.Nullvoid.item.ItemCircuit;
import aj.Java.Nullvoid.item.ItemDebug;
import aj.Java.Nullvoid.item.ItemEssenceDark;
import aj.Java.Nullvoid.item.ItemEssenceLight;
import aj.Java.Nullvoid.item.ItemFrame;
import aj.Java.Nullvoid.item.ItemGlitchyAlloy;
import aj.Java.Nullvoid.item.ItemIngotNull;
import aj.Java.Nullvoid.item.ItemIngotVoid;
import aj.Java.Nullvoid.item.ItemLens;
import aj.Java.Nullvoid.item.ItemNullInk;
import aj.Java.Nullvoid.item.ItemNullVoidAlloy;
import aj.Java.Nullvoid.item.ItemPureGlitch;
import aj.Java.Nullvoid.item.ItemTablet;
import aj.Java.Nullvoid.item.ItemVoidBook;
import aj.Java.Nullvoid.item.ItemVoidRecord;
import aj.Java.Nullvoid.item.ItemVoidTome;
import aj.Java.Nullvoid.item.ItemYingYang;
import aj.Java.Nullvoid.tileentity.TileEntityGlitchCrystal;
import aj.Java.Nullvoid.tileentity.TileEntityPhantom;
import aj.Java.Nullvoid.tileentity.TileEntitySwordWall;
import aj.Java.Nullvoid.tileentity.TileEntityVoidReactor;
import aj.Java.Nullvoid.tileentity.TileEntityVoidWalker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = VoidMod.MODID, name = "The Void", version = VoidMod.VERSION, guiFactory = "aj.Java.Nullvoid.client.VoidModGUIFactory", dependencies = "required-after:Forge@[1.8-11.14.0.1281-1.8,);"
		+ "after:Forestry;"
		+ "after:Thaumcraft;"
		+ "required-after:Baubles")
public class VoidMod implements LoadingCallback {
	public static List<Block> VoidReactorValidBlocks = new ArrayList<Block>(10);
	
	public static Utils util = new Utils();
	
	public static Achievement mineNull = null;
	public static Achievement enterNull = null;
	public static Achievement useTardis = null;
	public static Achievement fallVoid = null;
	public static Achievement craftGoggle = null;
	public static Achievement makeReactor = null;
	public static Achievement mineVoid = null;
	public static Achievement summonGlitch = null;
	public static Achievement scat = null;
	public static AchievementPage nullChievements = null;
	
	public static Configuration config;
	
	public static CreativeTabs ctab = new NullVoidTab(CreativeTabs.getNextID(),
			"The Null Void");
	
	@HasStates({"circuit_inert", "circuit_energized", "circuit_destablized", "circuit_fluxuating", "circuit_active"})
	public static Item circuits = null;
	public static Item ingotNull = null;
	public static Item ingotVoid = null;
	public static Item corruptAlloy = null;
	public static Item nullVoidAlloy = null;
	public static Item bucket = null;
	public static Item tablet = null;
	public static Item lightEssence = null;
	public static Item darkEssence = null;
	public static Item yingYang = null;
	@HasStates({"voidBook_myth", "voidBook_craft", "voidBook_craft2", "voidBook_myth2"})
	public static ItemVoidBook voidBook = null;
	public static Item scatman = null;
	public static Item pier = null;
	public static Item nullInk = null;
	public static Item pureGlitch = null;
	public static Item phantom = null;
	public static Item nullGoggles = null;
	public static Item voidGear = null;
	public static Item gravityBelt = null;
	public static Item glitchAmulet = null;
	public static Item ingotFrame = null;
	public static Item baneOfDark = null;
	public static Item darkPick = null;
	public static Item glitchCore = null;
	public static Item elementalHammer = null;
	public static Item voidTome = null;
	public static Item debug = null;
	@HasStates({"lens_normal", "lens_thaumic"})
	public static Item lens = null;
	
	public static Potion dissolving = null;
	
	public static Block nullOre = null;
	public static Block voidFabric = null;
	public static Block walker = null;
	public static Block voidDoor = null;
	public static Block voidReactor = null;
	public static Block swordWall = null;
	public static Block chamberWall = null;
	public static Block voidOre = null;
	public static Block generic = null;
	public static Block glitchFrame = null;
	public static Block transparent = null;
	public static Block storage = null;
	public static Block phantomB = null;
	public static Block blockLiquidFlux = null;
	public static Block decor = null;
	public static Block crystalGlitch = null;
	
	public static int NullVoidDimID;
	public static int NullVoidBioID;
	public static int EntIDBuild;
	public static int EntIDWalk;
	public static int EntIDCloud;
	public static int EntIDFloat;
	public static int EntIDGlitch;
	public static int PotIDDiss;
	public static int ReactorRender = -1;
	public static int CrystalRender = -1;
	
	public static String PotBitDiss;
	public static final String MODID = "nullvoid";
	public static final String VERSION = "1.7.10-4.0.0-BETA";
	
	public static boolean shouldRetro = false;
	public static boolean phantomRingE = true;
	public static boolean thaumcraft = false;
	
	public static MusicType voidMusic = null;
	
	public static Fluid liquidFlux = null;
	
	public static BiomeGenBase biomeNullVoid = null;
	
	@SideOnly(Side.CLIENT)
	public static TextureNullOre texNullOre;
	
	public static ArmorMaterial NullArmor = EnumHelper.addArmorMaterial(
			"NullArmor", "Null", 8, new int[] {2, 4, 3, 1}, 15);
	
	public static ToolMaterial SpecialTool = EnumHelper.addToolMaterial(
			"NullVoidSpecial", 100, 10000, 100F, 10F, 100);
	
	public static int NullArmorRender;
	
	@Instance(value = MODID)
	public static VoidMod me;
	
	@SidedProxy(clientSide = "aj.Java.Nullvoid.client.ClientProxy", serverSide = "aj.Java.Nullvoid.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Initing stuff
		config = new Configuration(event.getSuggestedConfigurationFile());
		config();
		NullArmorRender = proxy.addArmor("Null");
		blocks();
		items();
		potions();
		if(FMLCommonHandler.instance().getSide().isClient()){
			insertMusic();
		}
		biomeNullVoid = new BiomeGenNull(NullVoidBioID);

		// Fluids
		FluidRegistry.registerFluid(liquidFlux);

		// Blocks
		GameRegistry.registerBlock(walker, walker.getUnlocalizedName());
		GameRegistry.registerBlock(voidDoor, voidDoor.getUnlocalizedName());
		GameRegistry.registerBlock(voidFabric, voidFabric.getUnlocalizedName());
		GameRegistry.registerBlock(nullOre, nullOre.getUnlocalizedName());
		GameRegistry.registerBlock(blockLiquidFlux, blockLiquidFlux.getUnlocalizedName());
		GameRegistry.registerBlock(voidReactor, voidReactor.getUnlocalizedName());
		GameRegistry.registerBlock(swordWall, swordWall.getUnlocalizedName());
		GameRegistry.registerBlock(chamberWall, ItemBlockChamberWall.class, chamberWall.getUnlocalizedName());
		GameRegistry.registerBlock(voidOre, voidOre.getUnlocalizedName());
		GameRegistry.registerBlock(generic, generic.getUnlocalizedName());
		GameRegistry.registerBlock(glitchFrame, glitchFrame.getUnlocalizedName());
		GameRegistry.registerBlock(transparent, transparent.getUnlocalizedName());
		GameRegistry.registerBlock(storage, ItemBlockStorage.class, storage.getUnlocalizedName());
		GameRegistry.registerBlock(phantomB, phantomB.getUnlocalizedName());
		GameRegistry.registerBlock(decor, ItemBlockDecor.class, decor.getUnlocalizedName());
		GameRegistry.registerBlock(crystalGlitch, crystalGlitch.getUnlocalizedName());

		// Items
		GameRegistry.registerItem(bucket, bucket.getUnlocalizedName());
		GameRegistry.registerItem(circuits, circuits.getUnlocalizedName());
		GameRegistry.registerItem(ingotVoid, ingotVoid.getUnlocalizedName());
		GameRegistry.registerItem(ingotNull, ingotNull.getUnlocalizedName());
		GameRegistry.registerItem(nullGoggles, nullGoggles.getUnlocalizedName());
		GameRegistry.registerItem(voidGear, voidGear.getUnlocalizedName());
		GameRegistry.registerItem(gravityBelt, gravityBelt.getUnlocalizedName());
		GameRegistry.registerItem(glitchAmulet, glitchAmulet.getUnlocalizedName());
		GameRegistry.registerItem(corruptAlloy, corruptAlloy.getUnlocalizedName());
		GameRegistry.registerItem(nullVoidAlloy, nullVoidAlloy.getUnlocalizedName());
		GameRegistry.registerItem(ingotFrame, ingotFrame.getUnlocalizedName());
		GameRegistry.registerItem(baneOfDark, baneOfDark.getUnlocalizedName());
		GameRegistry.registerItem(tablet, tablet.getUnlocalizedName());
		GameRegistry.registerItem(lightEssence, lightEssence.getUnlocalizedName());
		GameRegistry.registerItem(darkEssence, darkEssence.getUnlocalizedName());
		GameRegistry.registerItem(darkPick, darkPick.getUnlocalizedName());
		GameRegistry.registerItem(elementalHammer, elementalHammer.getUnlocalizedName());
		GameRegistry.registerItem(yingYang, yingYang.getUnlocalizedName());
		GameRegistry.registerItem(glitchCore, glitchCore.getUnlocalizedName());
		GameRegistry.registerItem(voidBook, voidBook.getUnlocalizedName());
		GameRegistry.registerItem(scatman, "record_scatman");
		GameRegistry.registerItem(pier, "record_pier");
		GameRegistry.registerItem(nullInk, nullInk.getUnlocalizedName());
		GameRegistry.registerItem(pureGlitch, pureGlitch.getUnlocalizedName());
		GameRegistry.registerItem(phantom, phantom.getUnlocalizedName());
		GameRegistry.registerItem(voidTome, voidTome.getUnlocalizedName());
		GameRegistry.registerItem(debug, debug.getUnlocalizedName());
		GameRegistry.registerItem(lens, lens.getUnlocalizedName());

		// Tile entities
		GameRegistry.registerTileEntity(TileEntityVoidWalker.class,
				"NullVoidWalker");
		GameRegistry.registerTileEntity(TileEntitySwordWall.class,
				"NullSwordWall");
		GameRegistry.registerTileEntity(TileEntityVoidReactor.class,
				"NullVoidReactor");
		GameRegistry.registerTileEntity(TileEntityPhantom.class,
				"NullVoidPhantom");
		GameRegistry.registerTileEntity(TileEntityGlitchCrystal.class,
				"NullVoidCrystalGlitch");

		// Oredict
		OreDictionary.registerOre("crystalNull", ingotNull);
		OreDictionary.registerOre("oreNull", nullOre);
		OreDictionary.registerOre("gemVoid", ingotVoid);
		OreDictionary.registerOre("oreVoid", voidOre);

		// GUI
		new GUIHandler();

		// Buckets
		FluidContainerRegistry.registerFluidContainer(new FluidStack(
				liquidFlux, (FluidContainerRegistry.BUCKET_VOLUME / 7)),
				new ItemStack(bucket), new ItemStack(Items.bucket));
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

		// Register Biomes
		BiomeDictionary
				.registerBiomeType(biomeNullVoid, Type.END, Type.MAGICAL);
		BiomeManager.removeSpawnBiome(biomeNullVoid);

		// Entities
		EntityRegistry.registerModEntity(EntityVoidMaster.class, "Void Master",
				EntIDWalk, this, 80, 10, true);
		EntityRegistry.registerModEntity(EntityNullFloater.class,
				"Null Floater", EntIDFloat, this, 80, 10, true);
		EntityRegistry.registerModEntity(EntityBuilder.class, "Builder",
				EntIDBuild, this, 80, 10, true);
		EntityRegistry.registerModEntity(EntityGlitch.class, "Glitch",
				EntIDGlitch, this, 80, 10, true);
		EntityRegistry.registerModEntity(EntityVoidCloud.class, "Void Cloud",
				EntIDCloud, this, 80, 10, true);
		// Entities: Spawn
		EntityRegistry.addSpawn(EntityNullFloater.class, 15, 3, 10,
				EnumCreatureType.WATER_CREATURE, biomeNullVoid);
		EntityRegistry.addSpawn(EntityVoidCloud.class, 10, 3, 5,
				EnumCreatureType.AMBIENT, biomeNullVoid);
		// EntityRegistry.addSpawn(EntityVoidMaster.class, 5, 1, 1,
		// EnumCreatureType.creature, biomeNullVoid);
		// Entities: Eggs
		EntityList.addMapping(EntityNullFloater.class, "Null_Floater",
				EntIDFloat, 0x8000FF, 0x0B0B61);
		EntityList.addMapping(EntityBuilder.class, "Builder", EntIDBuild,
				0x2EFE2E, 0x8000FF);
		EntityList.addMapping(EntityVoidCloud.class, "Void_Cloud", EntIDCloud,
				0x9966FF, 0x352B47);
		// EntityList.addMapping(EntityVoidMaster.class, "Void Master", 6,
		// 0x000000, 0x6600FF);
		// Entities: Register
		EntityList.addMapping(EntityGlitch.class, "Glitch", EntIDGlitch);

		// Chunkloading
		ForgeChunkManager.setForcedChunkLoadingCallback(VoidMod.me, null);

		// Register dims
		DimensionManager.registerProviderType(VoidMod.NullVoidDimID,
				WorldProviderNullVoid.class, true);
		DimensionManager.registerDimension(VoidMod.NullVoidDimID,
				VoidMod.NullVoidDimID);

		// Generation
		GameRegistry.registerWorldGenerator(new VoidModOreGenerator(), 100);
		GameRegistry.registerWorldGenerator(new VoidModStructureGenerator(),
				101);

		// Renderers
		proxy.registerRenderers();

		// Packets
		PacketHandler.init();

		// Keys
		if (FMLCommonHandler.instance().getSide().isClient()) {
			ClientRegistry.registerKeyBinding(Utils.specialKey);
		}

		// Events
		FMLCommonHandler.instance().bus().register(new TickListner());
		MinecraftForge.EVENT_BUS.register(new TickListner());
		
		// BEES
		if(Loader.isModLoaded("Forestry")){
			try {
				Class.forName("aj.Java.Nullvoid.Forestry.VoidForestry").newInstance();
			} catch (Exception e) {
			}
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// NullCheivements
		mineNull = (Achievement) new Achievement("achievement.MineNullOre", "MineNullOre", -2, 0, new ItemStack(ingotNull, 1, 0), (Achievement) null).initIndependentStat().registerStat();
		enterNull = (Achievement) new Achievement("achievement.GoToVoid", "GoToVoid", 0, 0, new ItemStack(walker, 1, 0), mineNull).setSpecial().registerStat();
		scat = (Achievement) new Achievement("achievement.scat", "scat", -1, -2, VoidMod.scatman, enterNull).registerStat();
		useTardis = (Achievement) new Achievement("achievement.UseTARDIS", "UseTARDIS", 2, 0, new ItemStack(Blocks.lapis_block, 1, 0), enterNull).registerStat();
		fallVoid = (Achievement) new Achievement("achievement.FallVoid", "FallVoid", -3, -1, new ItemStack(voidOre, 1, 0), enterNull).registerStat();
		craftGoggle = (Achievement) new Achievement("achievement.CraftGoggles", "CraftGoggles", 0, 2, new ItemStack(nullGoggles, 1, 0), enterNull).registerStat();
		mineVoid = (Achievement) new Achievement("achievement.MineVoid", "MineVoid", 3, 3, new ItemStack(ingotVoid, 1, 0), craftGoggle).registerStat();
		makeReactor = (Achievement) new Achievement("achievement.MakeReactor", "MakeReactor", 2, 4, new ItemStack(voidReactor, 1, 0), mineVoid).setSpecial().registerStat();
		summonGlitch = (Achievement) new Achievement("achievement.SummonGlitch", "SummonGlitch", 2, 6, new ItemStack(corruptAlloy), makeReactor).setSpecial().registerStat();
		nullChievements = new AchievementPage("The Null Void", mineNull, enterNull, craftGoggle, useTardis, fallVoid, mineVoid, makeReactor, summonGlitch, scat);

		// NullChievementPage
		AchievementPage.registerAchievementPage(nullChievements);

		// Crafting
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 0),
				"RPR", "NEN", "QNQ",
				'R', "dustRedstone",
				'P', new ItemStack(Items.pumpkin_pie),
				'N', "crystalNull",
				'E', "dyeLime",
				'Q', "gemQuartz"));
		if (OreDictionary.getOres("ingotCopper").size() != 0 && OreDictionary.getOres("ingotElectrum").size() != 0) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 1),
					"OGO", "NCN", "RRR",
					'R', "dustRedstone",
					'G', "ingotElectrum",
					'C', new ItemStack(circuits, 1, 0),
					'O', "ingotCopper",
					'N', "crystalNull"));
		} else if (OreDictionary.getOres("ingotCopper").size() != 0) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 1),
					"OGO", "NCN", "RRR",
					'R', "dustRedstone",
					'G', "dustGlowstone",
					'C', new ItemStack(circuits, 1, 0),
					'O', "ingotCopper",
					'N', "crystalNull"));
		} else {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 1),
					"GGG", "NCN", "RRR",
					'R', "dustRedstone",
					'G', "dustGlowstone",
					'C', new ItemStack(circuits, 1, 0),
					'N', "crystalNull"));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 2),
				"NNN", "NCN", "NRN",
				'R', "blockRedstone",
				'N', "crystalNull",
				'C', new ItemStack(circuits, 1, 1)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(circuits, 1, 3),
				"REB", "NCN", "YQP",
				'C', new ItemStack(circuits, 1, 2),
				'N', "crystalNull",
				'R', "dyeRed",
				'B', "dyeCyan",
				'Y', "dyeYellow",
				'P', "dyePurple",
				'Q', "gemQuartz",
				'E', "blockRedstone"));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(circuits, 1, 4),
				"NNN", "RCR", "IEI",
				'R', "blockRedstone",
				'C', new ItemStack(circuits, 1, 3),
				'N', "crystalNull",
				'D', "blockIron",
				'E', Blocks.end_stone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(walker),
				"NCN", "EDE", "NIN",
				'D', "blockDiamond",
				'C', new ItemStack(circuits, 1, 0),
				'I', new ItemStack(circuits, 1, 3),
				'N', "crystalNull",
				'E', Blocks.end_stone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(nullGoggles),
				"SCS", "GLG", "NGN",
				'S', new ItemStack(Items.string),
				'C', new ItemStack(circuits, 1, 4),
				'N', "crystalNull",
				'G', "paneGlass",
				'L', Items.leather));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(swordWall),
				"NVN", "GSG", "CVC",
				'V', new ItemStack(voidFabric),
				'C', new ItemStack(circuits, 1, 3),
				'N', "crystalNull",
				'G', "gemVoid",
				'S', baneOfDark));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ingotFrame),
				"SWS", "VNV", "SGS",
				'V', "gemVoid",
				'W', "plankWood",
				'N', "crystalNull",
				'G', "slimeball",
				'S', new ItemStack(Items.string)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glitchFrame),
				"NAN", "VGV", "FAF",
				'N', "crystalNull",
				'V', "gemVoid",
				'G', generic,
				'F', voidFabric,
				'A', glitchCore));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(baneOfDark),
				"ABA", "CBC", " G ",
				'A', nullVoidAlloy,
				'B', lightEssence,
				'G', glitchCore,
				'C', new ItemStack(circuits, 1, 4)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(darkPick),
				"DDD", "CGC", " A ",
				'D', darkEssence,
				'C', new ItemStack(circuits, 1, 4),
				'G', corruptAlloy,
				'A', glitchCore));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glitchAmulet),
				"SYS", "YCY", "YYY",
				'S', Items.string,
				'Y', yingYang,
				'C', glitchCore));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(decor, 1, 0),
				"VV", "VV",
				'V', voidFabric));
		
		//Tier 2 crafting
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(phantom),
				"YYY", "YPY", "YYY",
				'Y', yingYang,
				'P', pureGlitch));
		GameRegistry.addRecipe(new ShapelessOreRecipe(voidBook.getStack(2),
				voidBook.getStack(1),
				new ItemStack(pureGlitch)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(voidBook.getStack(3),
				voidBook.getStack(0),
				new ItemStack(pureGlitch)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(voidTome, 1, 1),
				"EWE",
				"WVW",
				"EWE",
				'V', voidBook.getStack(3),
				'E', new ItemStack(Blocks.end_stone),
				'W', new ItemStack(walker)));
		
		// Smelting
		GameRegistry.addSmelting(nullOre, new ItemStack(ingotNull), 0.5f);
		GameRegistry.addSmelting(voidOre, new ItemStack(ingotVoid), 1f);
		// Dungeon Loot
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 3), 1,
						40, 2));
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 4), 1,
						40, 2));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 0), 50,
						60, 51));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 7), 50,
						60, 51));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 2), 1,
						40, 2));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 1), 1,
						40, 2));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,
				new WeightedRandomChestContent(new ItemStack(tablet, 1, 5), 1,
						40, 2));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST,
				new WeightedRandomChestContent(new ItemStack(glitchCore, 8), 1,
						20, 19));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
				new WeightedRandomChestContent(VoidMod.voidBook.getStack(0),
						40, 60, 40));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
				new WeightedRandomChestContent(VoidMod.voidBook.getStack(1),
						20, 30, 20));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Upgrades
		VoidReactorValidBlocks.add(walker);
		VoidReactorValidBlocks.add(swordWall);
		//Glitch Temple
		TempleManager.initilize();
		//Sound
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			((ClientProxy)proxy).voidSound = new MovingSoundPlayer(Minecraft.getMinecraft().thePlayer);
		}
		// THAUMIC
		if(Loader.isModLoaded("Thaumcraft")){
			try {
				Class.forName("aj.Java.Nullvoid.Thaumcraft.VoidThaumcraft").newInstance();
			} catch (Exception e) {
				
			}
		}
		System.out.println("[NULLVOID]: Shall we traverse the void?");
	}

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		for (Ticket t : tickets) {
			if (t != null) {
				for (ChunkCoordIntPair chunk : t.getChunkList()) {
					if (chunk != null) {
						ForgeChunkManager.forceChunk(t, chunk);
					}
				}
			}
		}
	}

	public static void config() {
		config.load();
		config.getCategory("generation");
		config.getCategory("entity");
		config.getCategory("potion");
		NullVoidDimID = config.get("generation", "Null Void Dimention ID", 42)
				.getInt();
		NullVoidBioID = config.get("generation", "Null Void Biome ID", 34)
				.getInt();
		shouldRetro = config.get("generation", "Should Retrogen", false)
				.getBoolean();
		
		EntIDBuild = config.get("entity", "The Builder Entity ID", 1337)
				.getInt();
		EntIDWalk = config.get("entity", "Void Walker Entity ID", 1338)
				.getInt();
		EntIDCloud = config.get("entity", "Void Cloud Entity ID", 1339)
				.getInt();
		EntIDFloat = config.get("entity", "Null Floater Entity ID", 1340)
				.getInt();
		EntIDGlitch = config.get("entity", "Glitch Entity ID", 1341).getInt();
		
		PotIDDiss = config.get("potion", "Dissolving Potion ID", 42).getInt();
		PotBitDiss = config.get("potion", "Dissolving Potion Bit", "+0+1-2+3")
				.getString();
		
		phantomRingE = config.get(Configuration.CATEGORY_GENERAL, "Ring of the Phantom Enabled?", true).getBoolean();
		config.save();
	}

	private void items() {
		nullGoggles = new ArmorNull(NullArmor, NullArmorRender, 0)
				.setUnlocalizedName("nullGoggles");
		voidGear = new ArmorNull(NullArmor, NullArmorRender, 1)
				.setUnlocalizedName("voidGear");
		circuits = new ItemCircuit().setUnlocalizedName("circuits");
		ingotNull = new ItemIngotNull().setUnlocalizedName("ingotNull");
		ingotVoid = new ItemIngotVoid().setUnlocalizedName("ingotVoid");
		gravityBelt = new GravityBelt().setUnlocalizedName("gravBelt");
		glitchAmulet = new GlitchAmulet().setUnlocalizedName("glitchAmulet");
		ingotFrame = new ItemFrame().setUnlocalizedName("ingotFrame");
		bucket = new ItemBucket(blockLiquidFlux).setUnlocalizedName("bucketVoidMod");
		baneOfDark = new ItemBaneOfDarkness(SpecialTool)
				.setUnlocalizedName("darknessBane");
		darkPick = new ItemDarknessPick().setUnlocalizedName("darkPick");
		elementalHammer = new ItemElementalHammer(SpecialTool)
				.setUnlocalizedName("elementHammer");
		corruptAlloy = new ItemGlitchyAlloy()
				.setUnlocalizedName("glitchAlloy");
		nullVoidAlloy = new ItemNullVoidAlloy()
				.setUnlocalizedName("nullVoidAlloy");
		tablet = new ItemTablet().setUnlocalizedName("tablet");
		lightEssence = new ItemEssenceLight()
				.setUnlocalizedName("lightEssence");
		darkEssence = new ItemEssenceDark().setUnlocalizedName("darkEssence");
		yingYang = new ItemYingYang().setUnlocalizedName("yingYang");
		glitchCore = new ItemAntiGlitchCore()
				.setUnlocalizedName("antiGlitchCore");
		voidBook = (ItemVoidBook) new ItemVoidBook()
				.setUnlocalizedName("voidBook");
		scatman = new ItemVoidRecord("scatman").setUnlocalizedName("record");
		pier = new ItemVoidRecord("piertonowhere").setUnlocalizedName("record");
		nullInk = new ItemNullInk().setUnlocalizedName("nullInk");
		pureGlitch = new ItemPureGlitch().setUnlocalizedName("pureGlitch");
		phantom = new PhantomRing().setUnlocalizedName("phantomRing");
		voidTome = new ItemVoidTome().setUnlocalizedName("voidTome");
		debug = new ItemDebug().setUnlocalizedName("debug");
		lens = new ItemLens().setUnlocalizedName("lens");
	}

	private void blocks() {
		voidDoor = new BlockVoidDoor().setUnlocalizedName("voidDoor");
		nullOre = new BlockNullOre().setUnlocalizedName("nullOre");
		voidOre = new BlockVoidOre().setUnlocalizedName("voidOre");
		generic = new BlockGeneric().setUnlocalizedName("generic");
		voidFabric = new BlockVoidFabric().setUnlocalizedName("voidFabric");
		walker = new BlockVoidWalker().setUnlocalizedName("voidWalker");
		swordWall = new BlockSwordWall().setUnlocalizedName("swordWall");
		chamberWall = new BlockChamberWall().setUnlocalizedName("chamberWall");
		glitchFrame = new BlockGlitchFrame().setUnlocalizedName("glitchFrame");
		transparent = new BlockTransparent().setUnlocalizedName("transparent");
		storage = new BlockStorage().setUnlocalizedName("storage");
		blockLiquidFlux = new BlockMoltenFlux(liquidFlux).setUnlocalizedName("moltenFlux");
		liquidFlux.setBlock(blockLiquidFlux);
		voidReactor = new BlockVoidReactor().setUnlocalizedName("voidReactor");
		phantomB = new BlockPhantom().setUnlocalizedName("phantomBlock");
		decor = new BlockDecor().setUnlocalizedName("decor");
		crystalGlitch = new BlockGlitchCrystal().setUnlocalizedName("crystalGlitch");
		
		liquidFlux = new FluidMoltenFlux("Molten Flux");
	}

	@SuppressWarnings("unchecked")
	private void potions() {
		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("potionTypes")
						|| f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[]) f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0,
							potionTypes.length);
					f.set(null, newPotionTypes);
				}
			} catch (Exception e) {
				System.err
						.println("Severe error, please report this to the mod author:");
				e.printStackTrace(System.err);
			}
		}
		for (Field f : PotionHelper.class.getDeclaredFields()) {
			try {
				if (f.getName().equals("potionRequirements") || f.getName().equals("field_77927_l")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					f.setAccessible(true);
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.PRIVATE);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
					char[] c = PotBitDiss.toCharArray();
					int firstPlus = -1;
					StringBuilder b = new StringBuilder();
					for (int where = 0; where < c.length; where++) {
						switch (Character.valueOf(c[where])) {
						case '-':
							if (where == 0) {
								b.append('!');
							} else {
								b.append(" !");
							}
							break;
						case '+':
							if (where != 0) {
								b.append(" ");
							}
							if (firstPlus == -1) {
								firstPlus = Integer.valueOf(c[where + 1]);
							}
							break;
						default:
							b.append(c[where] + " &");
							break;
						}
					}
					b.append(" " + firstPlus + "+6");
					((HashMap<Integer, String>) f.get(null)).put(PotIDDiss,
							b.toString());
				}
			} catch (Exception e) {
				System.err
						.println("Severe error, please report this to the mod author:");
				e.printStackTrace(System.err);
			}
		}
		dissolving = new PotionDissolving(PotIDDiss, true, 0x260060)
				.setPotionName("potion.Dissolving");
	}
	@SideOnly(Side.CLIENT)
	private void insertMusic(){
		voidMusic = EnumHelper.addEnum(MusicType.class, "VOID", 
				new ResourceLocation("nullvoid:sounds.ambient.piertonowhere"), 
				Integer.MAX_VALUE, Integer.MAX_VALUE);
		/*
		try{
			for(Field f : MusicTicker.class.getDeclaredFields()){
				if(f.getName().equalsIgnoreCase("field_147677_b")){
					Field modfield = Field.class.getDeclaredField("modifiers");
					f.setAccessible(true);
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.PRIVATE);
					f.set(null, this);
				}
			}
		}
		catch(Exception e){
			System.err
				.println("Severe error, please report this to the mod author:");
			e.printStackTrace(System.err);
		}
		*/
	}
	/*
	public MusicTicker.MusicType func_147109_W()
    {
        return Minecraft.getMinecraft().currentScreen instanceof GuiWinGame ? MusicTicker.MusicType.CREDITS : (Minecraft.getMinecraft().thePlayer != null ? (Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderHell ? MusicTicker.MusicType.NETHER : (Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderNullVoid ? VoidMod.voidMusic : (Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderEnd ? (BossStatus.bossName != null && BossStatus.statusBarTime > 0 ? MusicTicker.MusicType.END_BOSS : MusicTicker.MusicType.END) : (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode && Minecraft.getMinecraft().thePlayer.capabilities.allowFlying ? MusicTicker.MusicType.CREATIVE : MusicTicker.MusicType.GAME)))) : MusicTicker.MusicType.MENU);
    }
    */
}
