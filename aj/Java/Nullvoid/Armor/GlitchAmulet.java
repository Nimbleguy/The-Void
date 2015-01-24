package aj.Java.Nullvoid.Armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import aj.Java.Nullvoid.VoidMod;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class GlitchAmulet extends Item implements IBauble {
	public GlitchAmulet(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.EPIC;
    }
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return BaubleType.AMULET;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:glitchAmulet");
	}

}
