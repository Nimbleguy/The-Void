package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockGeneric extends Block {

	public BlockGeneric() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public void registerBlockIcons(IIconRegister i){
		this.blockIcon = i.registerIcon("nullvoid:generic");
	}
}
