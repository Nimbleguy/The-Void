package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int x = 0; x < 2; x++)
	    {
	        par3List.add(new ItemStack(this, 1, x));
	    }
	}
	public static final String[] names = new String[] {"lens_normal", "lens_thaumic"};
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return "item." + names[i];
	}
}
