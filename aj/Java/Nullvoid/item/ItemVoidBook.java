package aj.Java.Nullvoid.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.FMLCommonHandler;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.GUI.GUIVoidBook;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemVoidBook extends ItemEditableBook {
	public ItemVoidBook(){
		super();
		this.setCreativeTab(VoidMod.ctab);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	public static final String[] names = new String[] {"myth", "craft", "craft2", "myth2"};
	public ItemStack getStack(int meta){
		ItemStack tomeStack = new ItemStack(this, 1, meta);
		if(meta == 0){
			NBTTagList bookPages = new NBTTagList();
			for(String s : getPagesMyth()){
				bookPages.appendTag(new NBTTagString(s));
			}
			tomeStack.setTagInfo("pages", bookPages);
			tomeStack.setTagInfo("author", new NBTTagString("Eglarbroad Vandelsnatch"));
			tomeStack.setTagInfo("title", new NBTTagString("Void Mythology"));
		}
		else if(meta == 1){
			NBTTagList bookPages = new NBTTagList();
			for(String s : getPagesCraft()){
				bookPages.appendTag(new NBTTagString(s));
			}
			tomeStack.setTagInfo("pages", bookPages);
			tomeStack.setTagInfo("author", new NBTTagString("Vandelgrot Shnelesky"));
			tomeStack.setTagInfo("title", new NBTTagString("Forging Void Instruments"));
		}
		else if(meta == 2){
			NBTTagList bookPages = new NBTTagList();
			for(String s : getPagesCraft2()){
				bookPages.appendTag(new NBTTagString(s));
			}
			tomeStack.setTagInfo("pages", bookPages);
			tomeStack.setTagInfo("author", new NBTTagString("The Prince"));
			tomeStack.setTagInfo("title", new NBTTagString("stnemurtsnI dioV gnigroF"));
		}
		else if(meta == 3){
			NBTTagList bookPages = new NBTTagList();
			for(String s : getPagesMyth2()){
				bookPages.appendTag(new NBTTagString(s));
			}
			tomeStack.setTagInfo("pages", bookPages);
			tomeStack.setTagInfo("author", new NBTTagString("The Prince"));
			tomeStack.setTagInfo("title", new NBTTagString("ygolohtyM dioV"));
		}
		return tomeStack;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item i, CreativeTabs t, @SuppressWarnings("rawtypes") List l){
		l.add(getStack(0));
		l.add(getStack(1));
		l.add(getStack(2));
		l.add(getStack(3));
	}
	private List<String> getPagesCraft(){
		List<String> l = new ArrayList<String>();
		l.add(
				setFormat(setFormat("    FORGING VOID\n"
						+ "     INSTRUMENTS", EnumChatFormatting.GRAY), EnumChatFormatting.BOLD)
				+ setFormat(setFormat("\n", EnumChatFormatting.RESET), EnumChatFormatting.BLACK)
				+ "A bit of background, I am Vandelgrot Shnelesky. I am a Void Master, able to traverse across the dimensions."
				+ " Unlike my cousins the Endermen, my kind can traverse the veil surrounding the Void."
				);
		l.add(
				"I wrote this tome to let any ambitious others who want to traverse the Void know how to"
				+ " forge the magical constructs that allow non-voidwalkers to enter."
				);
		l.add(setFormat(setFormat("TABLE OF CONTENTS:", EnumChatFormatting.BOLD), EnumChatFormatting.DARK_GREEN)
				+ setFormat(setFormat(setFormat("\nCircuits - Page 4", EnumChatFormatting.RESET), EnumChatFormatting.ITALIC), EnumChatFormatting.WHITE)
				+ "\nVoidwalker - Page 9"
				+ "\nNull Goggles - Page 10"
				+ "\nSword Wall - Page 11"
				+ "\nIngot Frame - Page 12"
				+ "\nWeak Glitch Frame - Page 13"
				+ "\nAnti-Glitch Amulet - Page 14"
				+ "\nBane of Darkness - Page 15"
				+ "\nPickaxe of Darkness - Page 16"
				+ "\nOther Notes - Page 17"
				);
		l.add(
				setFormat("INERT NULL CIRCUIT", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "RPR\n"
				+ "NEN\n"
				+ "QNQ\n"
				+ "R = Redstone Dust. "
				+ "P = Pumpkin Pie. "
				+ "N = Null Crystal. "
				+ "Q = Nether Quartz. "
				+ "E = Lime Dye."
				);
		if (OreDictionary.getOres("ingotCopper").size() != 0
				&& OreDictionary.getOres("ingotElectrum").size() != 0) {
			l.add(
					setFormat("ENERGIZED NULL CIRCUIT", EnumChatFormatting.BOLD)
					+ setFormat("\n", EnumChatFormatting.RESET)
					+ "CGC\n"
					+ "NIN\n"
					+ "RRR\n"
					+ "R = Redstone Dust. "
					+ "G = Electrum. "
					+ "N = Null Crystal. "
					+ "I = Inert Null Circuit. "
					+ "C = Copper."
					);
		}
		else if (OreDictionary.getOres("ingotCopper").size() != 0) {
			l.add(
					setFormat("ENERGIZED NULL CIRCUIT", EnumChatFormatting.BOLD)
					+ setFormat("\n", EnumChatFormatting.RESET)
					+ "CGC\n"
					+ "NIN\n"
					+ "RRR\n"
					+ "R = Redstone Dust. "
					+ "G = Glowstone Dust. "
					+ "N = Null Crystal. "
					+ "I = Inert Null Circuit. "
					+ "C = Copper."
					);
		}
		else{
			l.add(
					setFormat("ENERGIZED NULL CIRCUIT", EnumChatFormatting.BOLD)
					+ setFormat("\n", EnumChatFormatting.RESET)
					+ "GGG\n"
					+ "NIN\n"
					+ "RRR\n"
					+ "R = Redstone Dust. "
					+ "G = Glowstone Dust. "
					+ "N = Null Crystal. "
					+ "I = Inert Null Circuit. "
					);
		}
		l.add(
				setFormat("DESTABILIZED NULL CIRCUIT", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "NNN\n"
				+ "NCN\n"
				+ "NRN\n"
				+ "R = Redstone Block. "
				+ "N = Null Crystal. "
				+ "C = Energized Null Circuit. "
				);
		l.add(
				setFormat("FLUXUATING NULL CIRCUIT", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "REB\n"
				+ "NCN\n"
				+ "YQP\n"
				+ "R = Red Dye. "
				+ "B = Cyan Dye. "
				+ "Y = Yellow Dye. "
				+ "P = Purple Dye. "
				+ "E = Redstone Block. "
				+ "N = Null Crystal. "
				+ "Q = Nether Quartz. "
				+ "C = Destabilized Null Circuit. "
				);
		l.add(
				setFormat("ACTIVATED NULL CIRCUIT", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "NNN\n"
				+ "RCR\n"
				+ "IEI\n"
				+ "R = Redstone Block. "
				+ "N = Null Crystal. "
				+ "C = Fluxuating Null Circuit. "
				+ "I = Iron Block. "
				+ "E = End Stone. "
				);
		l.add(
				setFormat("     VOIDWALKER", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "NCN\n"
				+ "EDE\n"
				+ "NIN\n"
				+ "E = End Stone. "
				+ "N = Null Crystal. "
				+ "C = Inert Null Circuit. "
				+ "I = Fluxuating Null Circuit. "
				);
		l.add(
				setFormat("    NULL GOGGLES", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "SCS\n"
				+ "GLG\n"
				+ "NGN\n"
				+ "G = Glass Pane. "
				+ "N = Null Crystal. "
				+ "S = String. "
				+ "L = Leather. "
				+ "C = Activated Null Circuit. "
				);
		l.add(
				setFormat("     SWORD WALL", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "NVN\n"
				+ "GSG\n"
				+ "CVC\n"
				+ "V = Void Fabric. "
				+ "N = Null Crystal. "
				+ "C = Fluxuating Null Circuit. "
				+ "S = Bane of Darkness. "
				+ "G = Void Gem. "
				);
		l.add(
				setFormat("     INGOT FRAME", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "SWS\n"
				+ "VNV\n"
				+ "SGS\n"
				+ "V = Void Gem. "
				+ "N = Null Crystal. "
				+ "W = Wooden Plank. "
				+ "G = Slimeball. "
				+ "S = String. "
				);
		l.add(
				setFormat(" WEAK GLITCH FRAME", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "NAN\n"
				+ "VGV\n"
				+ "FAF\n"
				+ "V = Void Fabric. "
				+ "N = Null Crystal. "
				+ "G = Perfectly Generic Object. "
				+ "F = Void Fabric. "
				+ "A = Anti-Glitch Core. "
				);
		l.add(
				setFormat("  BANE OF DARKNESS", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "ABA\n"
				+ "CBC\n"
				+ " G \n"
				+ "A = Null-Void Alloy. "
				+ "B = Essence of Light. "
				+ "C = Activated Null Circuit. "
				+ "G = Anti-Glitch Core. "
				);
		l.add(
				setFormat("PICKAXE OF DARKNESS", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "DDD\n"
				+ "CGC\n"
				+ " A \n"
				+ "D = Essence of Darkness. "
				+ "G = Alloy of the Destroyer. "
				+ "C = Activated Null Circuit. "
				+ "A = Anti-Glitch Core. "
				);
		l.add(
				setFormat("ANTI-GLITCH AMULET", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "SYS\n"
				+ "YCY\n"
				+ "YYY\n"
				+ "Y = Ying-Yang Ingot. "
				+ "S = String. "
				+ "C = Anti-Glitch Core. "
				);
		l.add(
				setFormat("OTHER NOTES", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "Some items will react strangely when covered in Molten Flux, maybe even"
				+ " become moldable."
				+ "Other materials will react when taken to 2^10Y or the inhabitable void."
				);
		l.add(setFormat("HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO ", EnumChatFormatting.OBFUSCATED));
		return l;
	}
	private List<String> getPagesMyth(){
		List<String> l = new ArrayList<String>(15);
		l.add(
				setFormat(setFormat("    VOID MYTHOLOGY", EnumChatFormatting.GRAY), EnumChatFormatting.BOLD)
				+ setFormat(setFormat("\n", EnumChatFormatting.RESET), EnumChatFormatting.BLACK)
				+ "I wrote this book to help explain the mysterious legands of "
				+ "the Void that has been passed from generation to generation."
				);
		l.add(setFormat(setFormat("TABLE OF CONTENTS:", EnumChatFormatting.BOLD), EnumChatFormatting.DARK_GREEN)
				+ setFormat(setFormat(setFormat("\nVoid Info - Page 3", EnumChatFormatting.RESET), EnumChatFormatting.ITALIC), EnumChatFormatting.WHITE)
				+ "\nEntering the Void - Page 6"
				+ "\nDiagram of Void - Page 8"
				+"\nInhabitants - Page 10"
				+ "\nThe      r - Page 12"
				+ "\nThe H      - Page 13"
				+ "\nThe C   t   - Page 14"
				+ "\nThe " + setFormat("G-----", EnumChatFormatting.OBFUSCATED) + setFormat(" - Page 15", EnumChatFormatting.RESET)
				);
		l.add(setFormat("       VOID INFO       ", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "The Void is a mysterious place. No one from my humble village of Elderdwarf have ever been in the void."
				+ "It has actualy been rumored that only creatures that have evolved with the void are able to live there."
				+ " Though, other"
				);
		l.add("legands say of a device so powerful that it can imbude an entity with void magic so powerful that it could let them survive in the void."
				+ "But that has been said to need a power source of it's own that comes from the void. This device is the Voidwalker.");
		l.add("All these legands have one thing in common: They all were originaly told by a Void Master, in this case Vandelgrot Shnelesky.");
		l.add(setFormat("       ENTERING", EnumChatFormatting.BOLD) + setFormat("\n", EnumChatFormatting.RESET)
				+ "The powerful device legand is a very important one because, if it is true us minecraftians could enter the void. "
				+ "It is said that you must first mine an ore maNullPointerException"
				+ "Then make ciruits powerful enough to let you enter The Void."
				);
		l.add("The way to make The Voidwalker has been lost in time, as well as how to make it's upgrade."
				+ "But it is told a magic recipe overlay could show you how to make The Voidwalker."
				+ "After you have the Voidwalker, you can put in a circuit, some crystals, and apply power."
				);
		l.add(setFormat("        DIAGRAM        ", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "The Inhabitable Void is the Void under us, we cannot survive there. The Habitable Void is what this book is about."
				+ "The Habitable Void is under the Inhabitable Void under the Overworld."
				+ "The Inhabitable Void is on top and below ");
		l.add("the Nether, and the Habitable Void is over the top Inhabitable and under the bottom."
				+ "The End is surrounded by Inhabitable Void surrounded by Habitable Void.");
		l.add(setFormat("      INHABITANTS      ", EnumChatFormatting.BOLD)
				+ setFormat("\n", EnumChatFormatting.RESET)
				+ "It has been sais that the void is full of mystical creatures. One is the Null Floater."
				+ " The Null Floater looks sort of like a squid. The Null Floater gets it's name from flying into the sky. Everything else is unknown.");
		l.add("The Void Cloud spawns in the Void's heights. They float around like a could. It is said that they like gems of nothing."
				+ "All is unknown about the Void Masters."
				);
		l.add(setFormat("      T      D  R      ", EnumChatFormatting.BOLD));
		l.add("");
		l.add("Help... " + setFormat("HE", EnumChatFormatting.OBFUSCATED) + setFormat("is coming...", EnumChatFormatting.RESET)
				+ " He knows Elderdwarf's abitions of visiting the void..."
				+ " He knows our knowladge..."
				+ " He wants ot ruin it..."
				+ " Help us... defeat t" + setFormat("he gl----", EnumChatFormatting.OBFUSCATED)
				);
		l.add(setFormat("HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO ", EnumChatFormatting.OBFUSCATED));
		return l;
	}
	private List<String> getPagesCraft2(){
		List<String> l = getPagesCraft();
		l.add(setFormat("HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO ", EnumChatFormatting.RED));
		l.add(setFormat("Why, hello there. I am The Prince. I shall be your guide today. "
				+ "Don't worry, the original writer of this book is...\n"
				+ "Lets say trapped. "
				+ "Well, in the next few pages I will elaborate on various diffrent things. ", EnumChatFormatting.RED));
		l.add(setFormat("Well, it seems like I will have to continue on the theme of the book. "
				+ "So these recipies have been thaught to be Tier 2. "
				+ "These can be done in a regular crafting table.", EnumChatFormatting.RED));
		l.add(setFormat("RING OF THE PHANTOM\n"
				+ "YYY\n"
				+ "YPY\n"
				+ "YYY\n"
				+ "Y = Ying-Yang Ingot. P = Pure Glitch.", EnumChatFormatting.RED));
		l.add(setFormat("TOME OF VOIDWALKING\n"
				+ "EWE\n"
				+ "WVW\n"
				+ "EWE\n"
				+ "E = End Stone. W = Voidwalker. V = ygolohtyM dioV.", EnumChatFormatting.RED));
		return l;
	}
	private List<String> getPagesMyth2(){
		List<String> l = getPagesMyth();
		l.add(setFormat("HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO "
				+ "HA HA HA HE HE HE HO HO HO HA HA HA HE HE HE HO HO HO ", EnumChatFormatting.RED));
		l.add(setFormat("Why, hello there. I am The Prince. I shall be your guide today. "
				+ "Don't worry, the original writer of this book is...\n"
				+ "Lets say trapped. "
				+ "Well, in the next few pages I will elaborate on various diffrent things. ", EnumChatFormatting.RED));
		l.add(setFormat("Well, first I should talk about The Destroyer."
				+ "The Destroyer. A destroyer by name. A pure embodyment of something wrong.\n"
				+ "My minion.", EnumChatFormatting.RED));
		l.add(setFormat("Only a weapon forged by one of the Creation Trio can even hurt it."
				+ " Then the defences build. It gets stronger as it gets weaker."
				+ " It is a paradox in itself."
				+ " Watch out.", EnumChatFormatting.RED));
		l.add(setFormat("Well, now I shall give some more info on some of the things written by Eglarbroad. "
				+ "Well, here is some info on Void Clouds. They do NOT like Void Gems, that is just a myth. "
				+ "There is a way to summon them. Just recite this: Nihil, et mundus surga. Venite, et mittamus. Caecitas nihil reale. "
				+ "That is the only incantation I know.", EnumChatFormatting.RED));
		l.add(setFormat("Before we delve into some more cloudy things, I need to say this. "
				+ "I do not know everything. Some things are foggy to me. "
				+ "Especially things about the nature of Pure Glitch.", EnumChatFormatting.RED));
		l.add(setFormat("The Ring of the Phantom. It, basically, creates a phantom."
				+ " It seems to work best in The Void. It works by weakening spacetime.", EnumChatFormatting.RED));
		l.add(setFormat("Now for the Tome of Voidwalking. It is a portable voidwalker."
				+ " When you read this tome, it activates. Afterward, you will need to recharge it."
				+ " It is about half of efficiant as a Voidwalker and each charge acts like 256 Null Crystals.", EnumChatFormatting.RED));
		return l;
	}
	private String setFormat(String s, EnumChatFormatting e){
		ChatStyle style = new ChatStyle();
		switch(e){
		case OBFUSCATED:
			style.setObfuscated(true);
			break;
		case BOLD:
			style.setBold(true);
			break;
		case UNDERLINE:
			style.setUnderlined(true);
			break;
		case ITALIC:
			style.setItalic(true);
			break;
		default:
			style.setColor(e);
			break;
		}
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			return new ChatComponentText(s).setChatStyle(style).getFormattedText();
		}
		else{
			return s;
		}
	}
	@Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player)
    {
        if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
        	player.openGui(VoidMod.me, 1, world, 0, 0, 0);
        	FMLClientHandler.instance().displayGuiScreen(player, new GUIVoidBook(player, stack, false));
        }
        return stack;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:voidBook");
	}
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return "item.voidBook." + names[i];
	}
}
