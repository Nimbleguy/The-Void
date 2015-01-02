package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCircuit extends Item {
	public ItemCircuit() {
		super();
		setMaxStackSize(1);
		setCreativeTab(VoidMod.ctab);
		setHasSubtypes(true);
		setMaxDamage(0);
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
	
	private String[] names = new String[] {"circuit_inert", "circuit_energized", "circuit_destablized", "circuit_fluxuating", "circuit_active"};
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getMetadata(), 0, 15);
	    return "item." + names[i];
	}
}
