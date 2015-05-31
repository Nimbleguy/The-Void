package nimble.Java.TheVoid.Block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityRuneRock;
import nimble.Java.TheVoid.Client.Gui.VoidGuiHandler.EnumGUI;

public class BlockRuneRock extends Block implements ITileEntityProvider {

	public BlockRuneRock() {
		super(Material.rock);
		setUnlocalizedName("runerock");
		this.setHardness(1.0F);
		this.setDefaultState(this.blockState.getBaseState());
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerBlock(this, getUnlocalizedName().replace("tile.", ""));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer p, EnumFacing side, float hitX, float hitY, float hitZ){
		if(world.isRemote) {
            if (world.getTileEntity(pos) != null){
                p.openGui(VoidMod.instance, EnumGUI.RUNEROCK.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            	return true;
            }
        }
        return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRuneRock("OmegaV");
	}

}
