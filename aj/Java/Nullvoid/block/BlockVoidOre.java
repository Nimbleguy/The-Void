package aj.Java.Nullvoid.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockVoidOre extends BlockOre {

	public BlockVoidOre() {
		super();
		setCreativeTab(VoidMod.ctab);
		this.setHarvestLevel("pickaxe", 3);
		setHardness(100F);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister i)
	{
		this.blockIcon = i.registerIcon("nullvoid:voidOre");
	}
	@Override
	public void onBlockHarvested(World w, int i1, int i2, int i3, int i4, EntityPlayer p){
		p.addStat(VoidMod.mineVoid, 1);
		super.onBlockHarvested(w, i1, i2, i3, i4, p);
	}
}
