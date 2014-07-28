package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemEssenceDark extends Item {
	public ItemEssenceDark(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:darkEssence");
	}
}
