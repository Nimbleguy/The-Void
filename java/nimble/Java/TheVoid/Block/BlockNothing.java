package nimble.Java.TheVoid.Block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityNothing;
import nimble.Java.TheVoid.Item.ItemMaterial;

public class BlockNothing extends BlockContainer {

	public BlockNothing() {
		super(Material.dragonEgg);
		setUnlocalizedName("nothing");
		this.setBlockUnbreakable();
        this.setResistance(6000001.0F);
        this.disableStats();
        this.setLightLevel(1.0F);
		
		GameRegistry.registerBlock(this, getUnlocalizedName().replace("tile.", ""));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNothing();
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
    	if(!world.isRemote && entity instanceof EntityItem){
    		EntityItem item = (EntityItem)entity;
    		if(item.getEntityItem().getItem() instanceof ItemMaterial){
    			if(item.getEntityItem().getMetadata() == 0){
    				item.setEntityItemStack(new ItemStack(VoidMod.material, item.getEntityItem().stackSize, 2));
    			}
    			else if(item.getEntityItem().getMetadata() == 1){
    				item.setEntityItemStack(new ItemStack(VoidMod.material, item.getEntityItem().stackSize, 3));
    			}
    		}
    	}
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state){
        float f = 0.125F;
        return new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)((float)(pos.getY() + 1) - f), (double)(pos.getZ() + 1));
    }
}
