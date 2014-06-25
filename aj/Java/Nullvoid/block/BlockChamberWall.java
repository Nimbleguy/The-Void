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
		for (int i = 0; i < 3; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}
	private IIcon[] tex = new IIcon[3];
	@Override
	public void registerBlockIcons(IIconRegister i) {
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
		else{
			return tex[1];
		}
	}
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer p, int side, float lx, float ly, float lz) {
		System.out.println(w.getBlockMetadata(x, y, z));
		if (w.getBlockMetadata(x, y, z) == 0) {
			if (p.inventory.getCurrentItem() != null) {
				if (p.inventory.getCurrentItem().getItem() instanceof ItemIngotVoid) {
					for (int i = x - 1; i < x + 2; i++) {
						for (int ii = y - 1; ii < y + 2; ii++) {
							for (int iii = z - 1; iii < z + 2; iii++) {
								if (w.getBlock(i, ii, iii) instanceof BlockChamberWall) {
									System.out.println("instance");
									if (w.getBlockMetadata(i, ii, iii) == 1) {
										System.out.println("meta");
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
		System.out.println("Hai");
		return false;
	}
}
