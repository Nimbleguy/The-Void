package aj.Java.Nullvoid.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.item.ItemIngotVoid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockChamberWall extends Block {
	public BlockChamberWall() {
		super(Material.rock);
		this.setBlockUnbreakable();
		this.setResistance(99999F);
		this.setCreativeTab(VoidMod.ctab);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs,
			@SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}
	private IIcon[] tex = new IIcon[4];
	@Override
	public void registerBlockIcons(IIconRegister i) {
		tex[3] = i.registerIcon("nullvoid:chamberBlockade");
		tex[2] = i.registerIcon("nullvoid:voidFabric");
		tex[1] = i.registerIcon("nullvoid:chamberWall");
		tex[0] = i.registerIcon("nullvoid:chamberDoor");
	}
	@Override
	public IIcon getIcon(int side, int meta){
		if(meta == 0){
			return tex[0];
		}
		else if(meta == 1){
			return tex[2];
		}
		else if(meta == 2){
			return tex[1];
		}
		else{
			return tex[3];
		}
	}
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer p, int side, float lx, float ly, float lz) {
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
		else if(w.getBlockMetadata(x, y, z) == 3){
			ForgeDirection f = ForgeDirection.getOrientation(side);
			if(f.equals(ForgeDirection.UP)){
				p.setLocationAndAngles(x, y - 2, z, p.cameraPitch, p.cameraYaw);
			}
			else if(f.equals(ForgeDirection.DOWN)){
				p.setLocationAndAngles(x, y + 1, z, p.cameraPitch, p.cameraYaw);
			}
			else if(f.equals(ForgeDirection.EAST)){
				p.setLocationAndAngles(x + 1, y, z, p.cameraPitch, p.cameraYaw);
			}
			else if(f.equals(ForgeDirection.WEST)){
				p.setLocationAndAngles(x - 1, y, z, p.cameraPitch, p.cameraYaw);
			}
			else if(f.equals(ForgeDirection.NORTH)){
				p.setLocationAndAngles(x, y, z - 1, p.cameraPitch, p.cameraYaw);
			}
			else if(f.equals(ForgeDirection.SOUTH)){
				p.setLocationAndAngles(x, y, z + 1, p.cameraPitch, p.cameraYaw);
			}
		}
		return false;
	}
}
