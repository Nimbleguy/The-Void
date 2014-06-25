package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockTransparent extends BlockVoidFabric {

	public BlockTransparent() {
		super();
		setHardness(5F);
		this.setBlockUnbreakable();
		this.setResistance(99999F);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(VoidMod.MODID + ":null");
	}
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
}
