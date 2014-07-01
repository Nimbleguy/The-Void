package aj.Java.Nullvoid.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.tileentity.TileEntityVoidWalker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class BlockVoidWalker extends BlockContainer {
	public Ticket ticket = null;
	@SideOnly(Side.CLIENT)
    private IIcon field_149935_N;
    @SideOnly(Side.CLIENT)
    private IIcon field_149936_O;

	public BlockVoidWalker() {
		super(Material.iron);
		setCreativeTab(VoidMod.ctab);
		setHardness(20F);
		setResistance(40F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(VoidMod.me, 0, world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityVoidWalker();
	}

	@Override
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z,
			int side) {
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		if(ticket == null){
			this.ticket = ForgeChunkManager.requestTicket(VoidMod.me, world,
					Type.NORMAL);
		}
		if (this.ticket != null) {
			ForgeChunkManager.forceChunk(ticket,
					new ChunkCoordIntPair(
							world.getChunkFromBlockCoords(x, z).xPosition,
							world.getChunkFromBlockCoords(x, z).zPosition));
		}
		this.func_149930_e(world, x, y, z);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block,
			int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		if (this.ticket != null) {
			ForgeChunkManager.unforceChunk(ticket,
					new ChunkCoordIntPair(
							world.getChunkFromBlockCoords(x, z).xPosition,
							world.getChunkFromBlockCoords(x, z).zPosition));
		}
	}
    private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
    {
        if (!p_149930_1_.isRemote)
        {
            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.field_149935_N : (p_149691_1_ == 0 ? this.field_149935_N : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.field_149936_O));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("nullvoid:walker_side");
        this.field_149936_O = p_149651_1_.registerIcon("nullvoid:walker_front");
        this.field_149935_N = p_149651_1_.registerIcon("nullvoid:walker_top");
    }
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityFurnace)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }
    @Override
	public void onBlockPreDestroy(World w, int x, int y, int z, int oldmeta) {
		if (ticket != null) {
			ForgeChunkManager.unforceChunk(
					ticket,
					new ChunkCoordIntPair(
							w.getChunkFromBlockCoords(x, z).xPosition, w
									.getChunkFromBlockCoords(x, z).zPosition));
		}
	}
}
