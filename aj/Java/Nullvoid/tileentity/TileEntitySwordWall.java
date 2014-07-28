package aj.Java.Nullvoid.tileentity;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySwordWall extends TileEntity {
	public ItemStack sword;
	public void onClick(EntityPlayer p){
		System.out.println(sword);
		if(p.inventory.getCurrentItem() != null){
			if(p.inventory.getCurrentItem().getItem().equals(VoidMod.baneOfDark)){
				if(sword == null){
					sword = p.inventory.getCurrentItem();
					p.inventory.setInventorySlotContents(p.inventory.currentItem, null);
				}
			}
		}
		else{
			if(sword != null){
				if(p.inventory.getCurrentItem() == null){
					p.inventory.setInventorySlotContents(p.inventory.currentItem, sword);
					sword = null;
				}
			}
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		if(sword == null){
			data.setBoolean("hasSword", false);
		}
		else{
			data.setBoolean("hasSword", true);
			data.setInteger("swordDamage", sword.getItemDamage());
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		if(data.getBoolean("hasSword")){
			sword = new ItemStack(VoidMod.baneOfDark, 1, data.getInteger("swordDamage"));
		}
		else{
			sword = null;
		}
	}
}
