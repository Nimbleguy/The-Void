package aj.Java.Nullvoid.GUI.slot;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotNullCircuit extends Slot {
	public SlotNullCircuit(IInventory par1iInventory, int par2, int par3,
			int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if(itemstack.getItem().equals(VoidMod.circuts)) {
			if(itemstack.getItemDamage() == 4) {
				return true;
			} else {
				return false;
			}
		}
		else{
			return false;
		}
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
