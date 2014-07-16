package aj.Java.Nullvoid.client.container;

import aj.Java.Nullvoid.GUI.slot.SlotNullCircuit;
import aj.Java.Nullvoid.GUI.slot.SlotNullCrystal;
import aj.Java.Nullvoid.item.ItemIngotNull;
import aj.Java.Nullvoid.tileentity.TileEntityVoidWalker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVoidWalker extends Container {
	private TileEntityVoidWalker walker;

	public ContainerVoidWalker(InventoryPlayer invPlayer,
			TileEntityVoidWalker entity) {
		this.walker = entity;
		walker.slots[0] = addSlotToContainer(new SlotNullCrystal(entity,
				0 + 0 * 3, 62 + 0 * 18, 17 + 0 * 18));
		walker.slots[1] = addSlotToContainer(new SlotNullCrystal(entity,
				1 + 0 * 3, 62 + 1 * 18, 17 + 0 * 18));
		walker.slots[2] = addSlotToContainer(new SlotNullCrystal(entity,
				2 + 0 * 3, 62 + 2 * 18, 17 + 0 * 18));
		walker.slots[3] = addSlotToContainer(new SlotNullCircuit(entity,
				1 + 1 * 3, 62 + 1 * 18, 17 + 1 * 18));
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9,
						8 + x * 18, 84 + y * 18));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return walker.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int slotnum) {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotnum);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if ((slotnum < 0 || slotnum > 2) && slotnum != 3)
            {
                if (!this.walker.slots[3].getHasStack() && this.walker.slots[3].isItemValid(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 3, 4, false))
                    {
                        return null;
                    }
                }
                else if (itemstack.getItem() instanceof ItemIngotNull)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 3, false))
                    {
                        return null;
                    }
                }
                else if (slotnum >= 4 && slotnum < 31)
                {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false))
                    {
                        return null;
                    }
                }
                else if (slotnum >= 31 && slotnum < 40)
                {
                    if (!this.mergeItemStack(itemstack1, 4, 31, false))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(itemstack1, 4, 40, false))
                {
                    return null;
                }
            }
            else
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p, itemstack1);
        }

        return itemstack;
	}
}
