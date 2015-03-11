package nimble.Java.TheVoid.Block;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Item.Block.ItemBlockTerrain;

public class BlockCondensedVoid extends BlockFluidFinite {

	public BlockCondensedVoid(Fluid fluid) {
		super(fluid, Material.lava);
		this.setTickRandomly(true);
		needsRandomTick = true;
		setUnlocalizedName("condensedVoid");
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerBlock(this, getUnlocalizedName().replace("tile.", ""));
	}
}
