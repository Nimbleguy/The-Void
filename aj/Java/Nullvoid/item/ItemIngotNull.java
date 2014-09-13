package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngotNull extends Item {
	public ItemIngotNull() {
		super();
		setMaxStackSize(Loader.isModLoaded("NotEnoughItems") ? 64 : 256);
		setCreativeTab(VoidMod.ctab);
	}	      
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(VoidMod.MODID + ":ingotNull");
	}
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.uncommon;
    }
	@Override
	public boolean onEntityItemUpdate(EntityItem item){
		if (item.posY <= 0D) {
			if (item.getEntityItem().getItem()
					.equals(VoidMod.ingotNull)) {
				int i = item.getEntityItem().stackSize;
				item.worldObj.spawnEntityInWorld(new EntityItem(
						item.worldObj, item.posX, 200D, item.posZ,
						new ItemStack(VoidMod.generic, i * 64)));
				item.setDead();
			}
		}
		return false;
	}
}
