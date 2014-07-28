package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemCircut extends Item {
	public ItemCircut() {
		super();
		setMaxStackSize(1);
		setCreativeTab(VoidMod.ctab);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	public static final String[] names = new String[] {"inert", "energized", "destablized", "fluxuating", "active"};
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
	       icons = new IIcon[5];
	             
	       for(int i = 0; i < icons.length; i++)
	       {
	              icons[i] = par1IconRegister.registerIcon(VoidMod.MODID + ":" + names[i] + "Circuit");
	       }
	}
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return icons[par1];
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int x = 0; x < 5; x++)
	    {
	        par3List.add(new ItemStack(this, 1, x));
	    }
	}
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return names[i] + "Circuit";
	}
}
