package nimble.Java.TheVoid.Entities;

import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class Clone extends EntityLivingBase{

	public InventoryPlayer inv = null;
	public EntityPlayerMP player;

	public Clone(World worldIn, BlockPos pos, EntityPlayerMP player) {
		super(worldIn);
		inv = player.inventory;
		this.setPosition(pos.getX(), pos.getY(), pos.getZ());
	}
	public Clone(World worldIn){
		super(worldIn);
	}

	@Override
	public ItemStack getHeldItem() {
		return(null);
	}

	@Override
	public ItemStack getEquipmentInSlot(int slotIn) {
		return null;
	}

	@Override
	public ItemStack getCurrentArmor(int slotIn) {
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int slotIn, ItemStack stack) {
		inv.setInventorySlotContents(slotIn, stack);
	}

	@Override
	public ItemStack[] getInventory() {
		ItemStack[] items = new ItemStack[40];
		int counter = 0;
		for (ItemStack item : inv.mainInventory){
			items[counter] = item;
			counter++;
		}
		counter = 0;
		for (ItemStack item : inv.armorInventory){
			items[36+counter] = item;
			counter++;
		}
		return items;
	}

}
