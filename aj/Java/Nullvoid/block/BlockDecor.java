package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDecor extends Block {
	public BlockDecor() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
	}

}
