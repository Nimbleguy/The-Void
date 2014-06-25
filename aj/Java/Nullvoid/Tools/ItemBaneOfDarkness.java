package aj.Java.Nullvoid.Tools;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;

public class ItemBaneOfDarkness extends ItemSword {

	public ItemBaneOfDarkness(ToolMaterial material) {
		super(material);
		this.setCreativeTab(VoidMod.ctab);
	}
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:baneOfDark");
	}
	
}
