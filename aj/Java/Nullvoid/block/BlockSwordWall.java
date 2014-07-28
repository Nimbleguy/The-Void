package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.tileentity.TileEntitySwordWall;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSwordWall extends BlockContainer {

	public BlockSwordWall() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
		this.setHardness(10F);
		this.setResistance(11F);
	}
	@Override
	public void registerBlockIcons(IIconRegister i){
		this.blockIcon = i.registerIcon("nullvoid:voidFabric");
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySwordWall();
	}
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float lx, float ly, float lz){
		((TileEntitySwordWall)w.getTileEntity(x, y, z)).onClick(p);
		return true;
	}
	@Override
	public void onBlockPreDestroy(World w, int x, int y, int z, int oldmeta) {
		if(((TileEntitySwordWall) w.getTileEntity(x, y, z)).sword != null){
			w.spawnEntityInWorld(new EntityItem(w, x, y, z, ((TileEntitySwordWall) w.getTileEntity(x, y, z)).sword));
		}
	}
}
