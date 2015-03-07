package nimble.Java.TheVoid.Block;

import java.util.List;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityVoidwalker;
import nimble.Java.TheVoid.Item.ItemKeystone;
import nimble.Java.TheVoid.Item.ItemMaterial;
import nimble.Java.TheVoid.Packet.PacketGamma;

public class BlockVoidwalker extends Block implements ITileEntityProvider {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool KEYSTONE = PropertyBool.create("keystone");
	public static final PropertyBool NULLON = PropertyBool.create("nullon");
	
	public BlockVoidwalker() {
		super(Material.iron);
		
		setUnlocalizedName("voidwalker");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerBlock(this, getUnlocalizedName().replace("tile.", ""));
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		super.onBlockAdded(worldIn, pos, state);
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		TileEntity e = worldIn.getTileEntity(pos);
		if(e instanceof TileEntityVoidwalker){
			TileEntityVoidwalker v = (TileEntityVoidwalker)e;
			boolean keystone = v.getStackInSlot(0) != null;
			boolean nullon = v.getStackInSlot(1) != null;
			
			return state.withProperty(KEYSTONE, keystone).withProperty(NULLON, nullon);
		}
		return state;
    }
	
	@Override
	public boolean canConnectRedstone(IBlockAccess world, BlockPos pos, EnumFacing side){
		return true;
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock){
		if(!worldIn.isRemote){
			if(worldIn.isBlockPowered(pos)){
				TileEntity e = worldIn.getTileEntity(pos);
				if(e instanceof TileEntityVoidwalker){
					TileEntityVoidwalker v = (TileEntityVoidwalker)e;
					if(v.getStackInSlot(0) != null && v.getStackInSlot(1) != null){
						v.using = true;
						EntityPlayer player = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 5);
						if(player != null && player instanceof EntityPlayerMP && player.dimension == 0){
							NBTTagCompound nbt = VoidMod.util.getVoidTag(player);
							nbt.setInteger("VoidwalkerConsume", 600);
							NBTTagCompound posTag = new NBTTagCompound();
							posTag.setInteger("x", pos.getX());
							posTag.setInteger("y", pos.getY());
							posTag.setInteger("z", pos.getZ());
							posTag.setInteger("dim", player.dimension);
							nbt.setTag("VoidwalkerPos", posTag);
							VoidMod.packet.INSTANCE.sendTo(new PacketGamma(-4.2f, false), (EntityPlayerMP)player);
							nbt.setBoolean("InVoid", true);
							VoidMod.util.setVoidTag(player, nbt);
							
							v.setInventorySlotContents(0, null);
							
							VoidMod.util.sendToDim((EntityPlayerMP)player, VoidMod.config.dimid, false);
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
		TileEntity e = worldIn.getTileEntity(pos);
		if(e instanceof TileEntityVoidwalker){
			TileEntityVoidwalker v = (TileEntityVoidwalker)e;
			ItemStack i = playerIn.getHeldItem();
			if(i != null){
				if(side.equals(state.getValue(FACING)) || side.equals(((EnumFacing)(state.getValue(FACING))).getOpposite())){
					if(i.getItem() instanceof ItemKeystone && i.getMetadata() == 4 && v.getStackInSlot(0) == null){
						playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, 1);
						v.setInventorySlotContents(0, new ItemStack(VoidMod.keystone, 1, 4));
						return true;
					}
				}
				else{
					if(i.getItem() instanceof ItemMaterial && i.getMetadata() == 0 && (v.getStackInSlot(1) != null ? v.getStackInSlot(1).stackSize < v.getInventoryStackLimit() : true)){
						int n = v.getInventoryStackLimit() - (v.getStackInSlot(1) != null ? v.getStackInSlot(1).stackSize : 0);
						if(i.stackSize >= n){
							playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, n);
							v.setInventorySlotContents(1, new ItemStack(i.getItem(), v.getInventoryStackLimit(), i.getMetadata()));
						}
						else{
							playerIn.inventory.decrStackSize(playerIn.inventory.currentItem, i.stackSize);
							v.setInventorySlotContents(1, new ItemStack(i.getItem(), (v.getStackInSlot(1) != null ? v.getStackInSlot(1).stackSize : 0) + i.stackSize, i.getMetadata()));
						}
						return true;
					}
				}
			}
			else{
				if(side.equals(state.getValue(FACING)) || side.equals(((EnumFacing)(state.getValue(FACING))).getOpposite())){
					playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, v.getStackInSlot(0));
					v.setInventorySlotContents(0, null);
					return true;
				}
				else{
					playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, v.getStackInSlot(1));
					v.setInventorySlotContents(1, null);
					return true;
				}
			}
		}
		worldIn.notifyBlockOfStateChange(pos, this);
		return false;
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.values()[MathHelper.clamp_int(meta, 0, EnumFacing.values().length)]);
    }
	
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).ordinal();
    }
    
    public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING, KEYSTONE, NULLON});
    }
    
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.purpleColor;
    }
    
    public int getRenderType()
    {
        return 3;
    }
    
    public boolean isFullCube()
    {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidwalker();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityVoidwalker){
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityVoidwalker)tileentity);
		}
		super.breakBlock(worldIn, pos, state);
	}
}
