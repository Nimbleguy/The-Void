package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.tileentity.TileEntityPhantom;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPhantom extends BlockContainer {
	public BlockPhantom() {
		super(Material.iron);
		this.setBlockUnbreakable();
		this.setResistance(99999999F);
	}
	
	@Override
	public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side){
		TileEntityPhantom t = (TileEntityPhantom) w.getTileEntity(x, y, z);
		System.out.println("meowmix");
		if(t.getPhantom() != null){
			System.out.println("icon");
			return t.getPhantom().getIcon(side, t.getMeta());
		}
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPhantom();
	}
	public static boolean setPhantom(World w, int x, int y, int z, Block b, int meta){
		if(b.isOpaqueCube()){
			w.setBlock(x, y, z, VoidMod.phantomB);
			((TileEntityPhantom) w.getTileEntity(x, y, z)).setPhantom(b, meta);
		}
		return true;
	}
	public static boolean isValid(Block b){
		if(b.isOpaqueCube()){
			return true;
		}
		return false;
	}

}
