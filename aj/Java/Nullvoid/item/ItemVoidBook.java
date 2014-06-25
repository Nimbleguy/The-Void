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
import net.minecraft.world.World;

public class ItemVoidBook extends ItemEditableBook {
	public ItemVoidBook(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	public ItemStack getStack(){
		ItemStack tomeStack = new ItemStack(this);
		NBTTagList bookPages = new NBTTagList();
		for(String s : getPages()){
			bookPages.appendTag(new NBTTagString(s));
		}
		tomeStack.setTagInfo("pages", bookPages);
		tomeStack.setTagInfo("author", new NBTTagString("Eglarbroad Vandelsnatch"));
		tomeStack.setTagInfo("title", new NBTTagString("Void Mythology"));
		return tomeStack;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item i, CreativeTabs t, @SuppressWarnings("rawtypes") List l){
		l.add(getStack());
	}
	private List<String> getPages(){
		List<String> l = new ArrayList<String>(10);
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
				+ "The Void is a mysterious place. None from my humble village of Elderdwarf have ever been in the void."
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
}
