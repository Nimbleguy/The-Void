package aj.Java.Nullvoid.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import aj.Java.Nullvoid.VoidMod;

public class ItemBucket extends net.minecraft.item.ItemBucket {

	public ItemBucket(Block par2) {
		super(par2);
		this.setCreativeTab(VoidMod.ctab);
		this.setUnlocalizedName("bucketVoidMod");
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(VoidMod.MODID + ":bucketNull");
	}
}
