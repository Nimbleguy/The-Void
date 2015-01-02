package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class ItemIngotNull extends Item {
	public ItemIngotNull() {
		super();
		setMaxStackSize(Loader.isModLoaded("NotEnoughItems") ? 64 : 255);
		setCreativeTab(VoidMod.ctab);
	}
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.UNCOMMON;
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
