package aj.Java.Nullvoid.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemLens extends Item {
	public ItemLens() {
		super();
		setMaxStackSize(1);
		setCreativeTab(VoidMod.ctab);
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	public static final String[] names = new String[] {"normal", "thaumic"};
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
	       this.itemIcon =  par1IconRegister.registerIcon("nullvoid:lens");
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int x = 0; x < 2; x++)
	    {
	        par3List.add(new ItemStack(this, 1, x));
	    }
	}
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return "item." + names[i] + "Lens";
	}
}
