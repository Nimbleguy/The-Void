package aj.Java.Nullvoid.block;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class BlockGeneric extends Block {

	public BlockGeneric() {
		super(Material.rock);
		this.setCreativeTab(VoidMod.ctab);
	}
	@Override
	public void registerBlockIcons(IIconRegister i){
		this.blockIcon = i.registerIcon("nullvoid:generic");
	}
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
}
