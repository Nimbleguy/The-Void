package aj.Java.Nullvoid.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockVoidFabric extends Block {
	public BlockVoidFabric() {
		super(Material.iron);
		setHardness(5F);
		this.setResistance(1F);
		this.setHarvestLevel("pickaxe", 2);
		setCreativeTab(VoidMod.ctab);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(VoidMod.MODID + ":voidFabric");
	}
}
