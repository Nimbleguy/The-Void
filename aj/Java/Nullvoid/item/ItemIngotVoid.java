package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngotVoid extends Item {
	public ItemIngotVoid() {
		super();
		setMaxStackSize(255);
		setCreativeTab(VoidMod.ctab);
	} 
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.RARE;
    }
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
}
