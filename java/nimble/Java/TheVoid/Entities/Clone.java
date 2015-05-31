package nimble.Java.TheVoid.Entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Clone extends EntityLivingBase{
	InventoryPlayer inv;
	public Clone(World worldIn, BlockPos pos) {
		super(worldIn);
		this.setPosition(pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public ItemStack getHeldItem() {
		return inv.getCurrentItem();
	}

	@Override
	public ItemStack getEquipmentInSlot(int slotIn) {
		return inv.getStackInSlot(slotIn);
	}

	@Override
	public ItemStack getCurrentArmor(int slotIn) {
		return inv.armorItemInSlot(slotIn);
	}

	@Override
	public void setCurrentItemOrArmor(int slotIn, ItemStack stack) {
		inv.setInventorySlotContents(slotIn, stack);
	}

	@Override
	public ItemStack[] getInventory() {
		ItemStack[] items = new ItemStack[inv.mainInventory.length + 4];//TODO add armor inv
		items = inv.mainInventory;
		int counter = 0;
		for (ItemStack item : inv.armorInventory){
			items[36+counter] = item;
			counter++;
		}
		return items;
	}

}
