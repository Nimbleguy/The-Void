package nimble.Java.TheVoid.Block.TileEntity;

import org.apache.logging.log4j.Level;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.BlockVoidwalker;
import nimble.Java.TheVoid.Item.ItemKeystone;
import nimble.Java.TheVoid.Item.ItemMaterial;

public class TileEntityVoidwalker extends TileEntityLockable implements ISidedInventory {

	private ItemStack[] inv;
	public boolean using = false;
	
	public TileEntityVoidwalker(){
		super();
		inv = new ItemStack[2];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		
		using = tag.getBoolean("Using");
		NBTTagList items = tag.getTagList("Items", 10);
		for(int i = 0; i < items.tagCount(); i++){
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if(0 <= slot && slot < 2){
				inv[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		tag.setBoolean("using", using);
		NBTTagList items = new NBTTagList();
		for(int i = 0; i < 2; i++){
			if(inv[i] != null){
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				inv[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		tag.setTag("Items", items);
	}
	
	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		int meta = VoidMod.voidwalker.getMetaFromState(worldObj.getBlockState(pos));
		return new S35PacketUpdateTileEntity(pos, meta, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager network, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public String getName() {
		return "Voidwalker";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inv[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = getStackInSlot(index);
		if (stack != null) {
			if (stack.stackSize <= count) {
				setInventorySlotContents(index, null);
				} else {
					stack = stack.splitStack(count);
					if (stack.stackSize == 0) {
						setInventorySlotContents(index, null);
					}
				}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		ItemStack i = inv[index].copy();
		inv[index] = null;
		return i;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inv[index] = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		//No inventories to open.
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		//No inventories to close.
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch(index){
		case 0:
			if(inv[index] == null || (inv[index].getItem() instanceof ItemKeystone && inv[index].getMetadata() == 4)){
				return true;
			}
			break;
		case 1:
			if(inv[index] == null || (inv[index].getItem() instanceof ItemMaterial && inv[index].getMetadata() == 0)){
				return true;
			}
			break;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		//No fields to get.
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		//No fields to set.
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < inv.length; i++){
			inv[i] = null;
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		//TODO: Container
		return new ContainerFurnace(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "thevoid:voidwalker";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		IBlockState state = worldObj.getBlockState(pos);
		return side.equals(state.getValue(BlockVoidwalker.FACING)) || side.equals(((EnumFacing)(state.getValue(BlockVoidwalker.FACING))).getOpposite()) ? new int[] {0} : new int[] {1};
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn,
			EnumFacing direction) {
		for(int i = 0; i < getSlotsForFace(direction).length; i++){
			if(getSlotsForFace(direction)[i] == index){
				if(isItemValidForSlot(index, itemStackIn)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack,
			EnumFacing direction) {
		for(int i = 0; i < getSlotsForFace(direction).length; i++){
			if(getSlotsForFace(direction)[i] == index){
				return true;
			}
		}
		return false;
	}

}
