package nimble.Java.TheVoid;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class VoidTab extends CreativeTabs {

	public VoidTab() {
		super("tabVoid");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(VoidMod.terrain);
	}

}
