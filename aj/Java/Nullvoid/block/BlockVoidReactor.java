package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.tileentity.TileEntityVoidReactor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class BlockVoidReactor extends BlockContainer {
	public Ticket ticket = null;

	public BlockVoidReactor(Material p_i45386_1_) {
		super(p_i45386_1_);
		this.setCreativeTab(VoidMod.ctab);
	}

	@Override
	public void registerBlockIcons(IIconRegister i) {
		this.blockIcon = i.registerIcon("nullvoid:walker_top");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityVoidReactor();
	}

	@Override
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z,
			int side) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer p, int side, float lx, float ly, float lz) {
		((TileEntityVoidReactor) w.getTileEntity(x, y, z)).onClick(p);
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		if (ticket == null) {
			this.ticket = ForgeChunkManager.requestTicket(VoidMod.me, world,
					Type.NORMAL);
		}
		if (this.ticket != null) {
			ForgeChunkManager.forceChunk(ticket,
					new ChunkCoordIntPair(
							world.getChunkFromBlockCoords(x, z).xPosition,
							world.getChunkFromBlockCoords(x, z).zPosition));
		}
	}

	@Override
	public void onBlockPreDestroy(World w, int x, int y, int z, int oldmeta) {
		if (((TileEntityVoidReactor) w.getTileEntity(x, y, z)).isStructure) {
			for (int xx = x - 1; xx < x + 2; xx++) {
				for (int yy = y - 2; yy < y + 1; yy++) {
					for (int zz = z - 1; zz < z + 2; zz++) {
						if (w.getBlock(xx, yy, zz) instanceof BlockVoidFabric) {
							if (w.getBlockMetadata(xx, yy, zz) == 1) {
								w.setBlockMetadataWithNotify(xx, yy, zz, 0, 3);
							}
						}
					}
				}
			}
		}
		if (ticket != null) {
			ForgeChunkManager.unforceChunk(
					ticket,
					new ChunkCoordIntPair(
							w.getChunkFromBlockCoords(x, z).xPosition, w
									.getChunkFromBlockCoords(x, z).zPosition));
		}
	}

	@Override
	public int getRenderType() {
		return VoidMod.ReactorRender;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
