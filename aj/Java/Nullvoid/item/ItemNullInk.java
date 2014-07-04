package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemNullInk extends Item {
	public ItemNullInk(){
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setPotionEffect("-0+1+2+3&-4+5+6-7");
	}
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:nullInk");
	}
}
