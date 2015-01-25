package nimble.Java.TheVoid.Block;

import java.util.List;

import org.apache.logging.log4j.Level;

import sun.util.logging.resources.logging;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Item.Block.ItemBlockTerrain;

public class BlockTerrain extends Block {
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockTerrain.EnumTerrainType.class);
	
	public BlockTerrain() {
		super(Material.rock);
		setUnlocalizedName("terrain");
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumTerrainType.FABRIC));
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerBlock(this, ItemBlockTerrain.class, getUnlocalizedName().replace("tile.", ""));
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
		for(EnumTerrainType ett : EnumTerrainType.values()){
			list.add(new ItemStack(this, 1, ett.ordinal()));
		}
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		return state;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumTerrainType.values()[MathHelper.clamp_int(meta, 0, EnumTerrainType.values().length)]);
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumTerrainType)state.getValue(TYPE)).ordinal();
    }

    public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }

    public MapColor getMapColor(IBlockState state)
    {
        return state.getValue(TYPE) == EnumTerrainType.FABRIC ? MapColor.purpleColor : MapColor.obsidianColor;
    }
    
	public enum EnumTerrainType implements IStringSerializable{
		FABRIC("fabric"),
		ROCK("rock");

		private String name;
		
		private EnumTerrainType(String s){
			name = s;
		}
		
		@Override
		public String getName() {
			return name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
}
