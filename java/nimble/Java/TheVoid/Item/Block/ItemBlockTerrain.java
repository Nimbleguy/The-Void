package nimble.Java.TheVoid.Item.Block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.BlockTerrain;
import nimble.Java.TheVoid.Block.BlockTerrain.EnumTerrainType;

public class ItemBlockTerrain extends ItemBlock {
	public ItemBlockTerrain(Block b){
		super(b);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack s){
		return super.getUnlocalizedName() + "." + ((EnumTerrainType)VoidMod.terrain.getStateFromMeta(s.getMetadata()).getValue(BlockTerrain.TYPE)).getName();
	}
}
