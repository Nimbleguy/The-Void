package aj.Java.Nullvoid.Armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class PhantomRing extends Item implements IBauble {
	
	public PhantomRing() {
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		Utils.getEntityTag(arg1).setBoolean("HasPhantom", true);
	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
		Utils.getEntityTag(arg1).setBoolean("HasPhantom", false);
	}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
		
	}

}
