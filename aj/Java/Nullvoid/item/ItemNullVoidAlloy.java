package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemNullVoidAlloy extends Item {
	public ItemNullVoidAlloy(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:nullVoidAlloy");
	}
	@Override
	public boolean onEntityItemUpdate(EntityItem item){
		if (item.posY <= 0D) {
			if (item.getEntityItem().getItem()
					.equals(VoidMod.nullVoidAlloy)) {
				int i = item.getEntityItem().stackSize;
				item.worldObj.spawnEntityInWorld(new EntityItem(
						item.worldObj, item.posX, 200D, item.posZ,
						new ItemStack(VoidMod.corruptAlloy, i)));
				item.setDead();
			}
		}
		return false;
	}
}
