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

public class BlockDecor extends Block {
	public BlockDecor() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
		this.setHarvestLevel("pickaxe", 1, 0);
		this.setHardness(3F);
		this.setResistance(50F);
	}
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs,
			@SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 1; ++i) {
			list.add(new ItemStack(block, 1, i));
		}
	}
	private IIcon[] tex = new IIcon[1];
	@Override
	public void registerBlockIcons(IIconRegister i) {
		tex[0] = i.registerIcon("nullvoid:voidBrick");
	}
	@Override
	public IIcon getIcon(int side, int meta){
		return meta < tex.length ? tex[meta] : tex[0];
	}
}
