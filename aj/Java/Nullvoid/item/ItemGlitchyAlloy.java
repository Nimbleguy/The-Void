package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGlitchyAlloy extends Item {
	public ItemGlitchyAlloy(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public boolean onEntityItemUpdate(EntityItem item){
		if(item.posY >= 256){
			item.setFire(10);
			item.worldObj.spawnEntityInWorld(new EntityItem(item.worldObj, item.posX, item.posY, item.posZ, new ItemStack(VoidMod.lightEssence, item.getEntityItem().stackSize)));
			item.setDead();
		}
		else if(item.posY <= 0){
			EntityItem i = new EntityItem(item.worldObj, item.posX, 0.1D, item.posZ, new ItemStack(VoidMod.darkEssence));
			i.motionY = 999999D;
			i.motionX = 0D;
			i.motionZ = 0D;
			item.worldObj.spawnEntityInWorld(i);
			item.setDead();
		}
		return false;
	}
}
