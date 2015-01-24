package aj.Java.Nullvoid.block;

import java.util.List;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.item.ItemIngotVoid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockChamberWall extends Block {
	public static PropertyEnum TYPE = PropertyEnum.create("type", EnumChamberWall.class);
	public BlockChamberWall() {
		super(Material.rock);
		this.setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumChamberWall.VOIDBRICK));
		this.setBlockUnbreakable();
		this.setResistance(99999F);
		this.setCreativeTab(VoidMod.ctab);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs,
			@SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 5; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}
	private IIcon[] tex = new IIcon[5];
	@Override
	public void registerBlockIcons(IIconRegister i) {
		tex[4] = i.registerIcon("nullvoid:voidBrick");
		tex[3] = i.registerIcon("nullvoid:chamberBlockade");
		tex[2] = i.registerIcon("nullvoid:chamberWall");
		tex[1] = i.registerIcon("nullvoid:voidFabric");
		tex[0] = i.registerIcon("nullvoid:chamberDoor");
	}
	@Override
	public IIcon getIcon(int side, int meta){
		return tex[meta];
	}
	@Override
	public boolean onBlockActivated(World w, BlockPos pos,
			IBlockState s, EntityPlayer p, EnumFacing side, float hx, float hy, float hz) {
		if (w.getBlockMetadata(x, y, z) == 0) {
			if (p.inventory.getCurrentItem() != null) {
				if (p.inventory.getCurrentItem().getItem() instanceof ItemIngotVoid) {
					for (int i = x - 1; i < x + 2; i++) {
						for (int ii = y - 1; ii < y + 2; ii++) {
							for (int iii = z - 1; iii < z + 2; iii++) {
								if (w.getBlock(i, ii, iii) instanceof BlockChamberWall) {
									if (w.getBlockMetadata(i, ii, iii) == 1) {
										w.setBlock(i, ii, iii, Blocks.air);
									}
								}
							}
						}
					}
					if (p.inventory.getCurrentItem() != null) {
						if (p.inventory.getCurrentItem().stackSize == 1) {
							p.inventory.setInventorySlotContents(
									p.inventory.currentItem, null);
						} else {
							p.inventory.getCurrentItem().stackSize -= 1;
						}
					}
					w.setBlock(x, y, z, Blocks.air);
					return true;
				}
			}
		}
		else if(w.getBlockMetadata(x, y, z) == 3 && VoidMod.util.getGlitchstone(p)){
			if(side.equals(EnumFacing.UP)){
				p.setLocationAndAngles(x, y - 2, z, p.cameraYaw, p.cameraPitch);
			}
			else if(side.equals(EnumFacing.DOWN)){
				p.setLocationAndAngles(x, y + 1, z, p.cameraYaw, p.cameraPitch);
			}
			else if(side.equals(EnumFacing.WEST)){
				p.setLocationAndAngles(x + 1, y, z, p.cameraYaw, p.cameraPitch);
			}
			else if(side.equals(EnumFacing.EAST)){
				p.setLocationAndAngles(x - 1, y, z, p.cameraYaw, p.cameraPitch);
			}
			else if(side.equals(EnumFacing.SOUTH)){
				p.setLocationAndAngles(x, y, z - 1, p.cameraYaw, p.cameraPitch);
			}
			else if(side.equals(EnumFacing.NORTH)){
				p.setLocationAndAngles(x, y, z + 1, p.cameraYaw, p.cameraPitch);
			}
			return true;
		}
		return false;
	}
	
	public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }
	@Override
	public int damageDropped(IBlockState state)
    {
        return ((EnumChamberWall)state.getValue(TYPE)).ordinal();
    }
	
	public enum EnumChamberWall{
		VOIDBRICK,
		CHAMBERBLOCKADE,
		CHAMBERWALL,
		VOIDKEYHOLE,
		KEYHOLEEXP;
	}
}
