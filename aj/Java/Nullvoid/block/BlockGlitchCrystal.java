package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.tileentity.TileEntityGlitchCrystal;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGlitchCrystal extends BlockContainer {

	public BlockGlitchCrystal() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
		this.setBlockUnbreakable();
		this.setResistance(9999999999F);
	}
	@Override
	public int getRenderType() {
		return VoidMod.CrystalRender;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityGlitchCrystal();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
