package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemNullInk extends Item {
	public ItemNullInk(){
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setPotionEffect(VoidMod.PotBitDiss + "&4-4+5+6-7+13");
	}
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:nullInk");
	}
}
