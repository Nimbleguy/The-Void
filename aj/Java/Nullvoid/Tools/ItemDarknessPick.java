package aj.Java.Nullvoid.Tools;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;

public class ItemDarknessPick extends ItemPickaxe {

	public ItemDarknessPick() {
		super(VoidMod.SpecialTool);
		this.setCreativeTab(VoidMod.ctab);
	}
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:darkPick");
	}
}
