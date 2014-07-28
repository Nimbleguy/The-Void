package aj.Java.Nullvoid.GUI.slot;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotNullCrystal extends Slot {
	public SlotNullCrystal(IInventory inventory, int par2, int par3, int par4) {
		super(inventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(itemstack.getItem().equals(VoidMod.ingotNull)) {
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int getSlotStackLimit() {
		return 256;
	}
}
