package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngotVoid extends Item {
	public ItemIngotVoid() {
		super();
		setMaxStackSize(255);
		setCreativeTab(VoidMod.ctab);
	} 
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(VoidMod.MODID + ":ingotVoid");
	}
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.rare;
    }
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
}
