package aj.Java.Nullvoid.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockStorage extends Block {

	public BlockStorage() {
		super(Material.iron);
		this.setCreativeTab(VoidMod.ctab);
	}
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs,
			@SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 2; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}
	private IIcon[] tex = new IIcon[2];
	@Override
	public void registerBlockIcons(IIconRegister i) {
		tex[0] = i.registerIcon("nullvoid:lightStorage");
		tex[1] = i.registerIcon("nullvoid:darkStorage");
	}
	@Override
	public IIcon getIcon(int side, int meta){
		return meta < tex.length ? tex[meta] : null;
	}
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z){
    	if(world.getBlockMetadata(x, y, z) == 0){
    		return 15;
    	}
    	else if(world.getBlockMetadata(x, y, z) == 1){
    		return -15;
    	}
    	return 0;
    }
}
