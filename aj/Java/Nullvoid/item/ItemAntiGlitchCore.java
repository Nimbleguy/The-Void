package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemAntiGlitchCore extends Item {
	public ItemAntiGlitchCore(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public void registerIcons(IIconRegister i) {
		this.itemIcon = i.registerIcon("nullvoid:glitchCore");
	}
}
