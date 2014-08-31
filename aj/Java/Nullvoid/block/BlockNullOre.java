package aj.Java.Nullvoid.block;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockNullOre extends BlockOre {
	public BlockNullOre() {
		super();
		setCreativeTab(VoidMod.ctab);
		setHardness(15F);
		this.setHarvestLevel("pickaxe", 2);
		setBlockTextureName("nullvoid:nullOre");
	}
	@Override
	public void onBlockHarvested(World w, int i1, int i2, int i3, int i4, EntityPlayer p){
		p.addStat(VoidMod.mineNull, 1);
		super.onBlockHarvested(w, i1, i2, i3, i4, p);
	}
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return VoidMod.ingotNull;
    }
	      
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = VoidMod.texNullOre;
	}
}
