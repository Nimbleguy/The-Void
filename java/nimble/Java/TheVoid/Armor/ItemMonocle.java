package nimble.Java.TheVoid.Armor;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;

public class ItemMonocle extends ItemArmor {

	public ItemMonocle() {
		super(VoidMod.armorMonocle, 0, 0);
		setUnlocalizedName("monocle");
		setCreativeTab(VoidMod.tab);
		setMaxStackSize(1);
		
		GameRegistry.registerItem(this, getUnlocalizedName().replace("item.", ""));
	}

}
