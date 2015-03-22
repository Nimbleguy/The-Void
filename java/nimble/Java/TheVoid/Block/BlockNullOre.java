package nimble.Java.TheVoid.Block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.BlockTerrain.EnumTerrainType;
import nimble.Java.TheVoid.Item.Block.ItemBlockNullOre;

public class BlockNullOre extends Block {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumNullOreType.class);
	
	public BlockNullOre() {
		super(Material.rock);
		setUnlocalizedName("nullore");
		this.setHardness(6.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNullOreType.NULLON));
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerBlock(this, ItemBlockNullOre.class, getUnlocalizedName().replace("tile.", ""));
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
		for(EnumNullOreType eno : EnumNullOreType.values()){
			list.add(new ItemStack(this, 1, eno.ordinal()));
		}
    }
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		return state;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumNullOreType.values()[MathHelper.clamp_int(meta, 0, EnumNullOreType.values().length)]);
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumNullOreType)state.getValue(TYPE)).ordinal();
    }

    public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }
    
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune){
    	return MathHelper.getRandomIntegerInRange(new Random(), 4, 8);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return VoidMod.material;
    }
    
    @Override
    public int damageDropped(IBlockState state){
    	return state.getValue(TYPE).equals(EnumNullOreType.NULLON) ? 0 : 1;
    }
	
	public enum EnumNullOreType implements IStringSerializable{
		NULLON("nullon"),
		VACULITE("vaculite");
		
		private String name;
		
		private EnumNullOreType(String s){
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
