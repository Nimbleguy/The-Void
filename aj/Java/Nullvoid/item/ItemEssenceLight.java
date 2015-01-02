package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class ItemEssenceLight extends Item {
	public ItemEssenceLight() {
		super();
		this.setCreativeTab(VoidMod.ctab);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem item) {
		@SuppressWarnings({ "unchecked" })
		List<EntityItem> l = item.worldObj.getEntitiesWithinAABB(
				EntityItem.class, AxisAlignedBB.fromBounds(item.posX - 1D,
						item.posY - 1D, item.posZ - 1D, item.posX + 1D,
						item.posY + 1D, item.posZ + 1D));
		loop: for (EntityItem i : l) {
			if (i.getEntityItem() != null) {
				if (i.getEntityItem().getItem() instanceof ItemEssenceDark) {
					if (item.dimension == 1) {
						item.worldObj
								.spawnEntityInWorld(new EntityLightningBolt(
										item.worldObj, item.posX, item.posY,
										item.posZ));
						item.worldObj.createExplosion((Entity) null, item.posX,
								item.posY, item.posZ, 5F, false);
						item.worldObj.spawnEntityInWorld(new EntityItem(
								item.worldObj, item.posX, 200D, item.posZ,
								new ItemStack(VoidMod.yingYang, 1)));
						item.setDead();
						i.setDead();
						break loop;
					}
				}
			}
		}
		return false;
	}
}
