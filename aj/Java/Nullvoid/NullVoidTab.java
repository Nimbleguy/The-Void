package aj.Java.Nullvoid;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class NullVoidTab extends CreativeTabs {

	public NullVoidTab(int par1, String par2Str) {
		super(par1, par2Str);
	}
	@Override
	public Item getTabIconItem() {
		return VoidMod.circuits;
	}
}
