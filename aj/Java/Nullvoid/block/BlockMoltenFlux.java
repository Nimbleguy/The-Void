package aj.Java.Nullvoid.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockMoltenFlux extends BlockFluidFinite {

	public BlockMoltenFlux(Fluid fluid) {
		super(fluid, Material.water);
		this.setTickRandomly(true);
		needsRandomTick = true;
	}
	@Override
	public void registerBlockIcons(IIconRegister i){
		this.blockIcon = Blocks.water.getIcon(0, 0);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return Blocks.water.getIcon(side, meta);
	}
	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z)
	{
		return 0x66FF00; // HEX color code as indicated by the 0x infront. This is a greenish color.
	}

}
