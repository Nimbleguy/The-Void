package nimble.Java.TheVoid.Item.Block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Block.BlockNullOre;
import nimble.Java.TheVoid.Block.BlockNullOre.EnumNullOreType;
import nimble.Java.TheVoid.Block.BlockTerrain;
import nimble.Java.TheVoid.Block.BlockTerrain.EnumTerrainType;

public class ItemBlockNullOre extends ItemBlock {

	public ItemBlockNullOre(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack s){
		return super.getUnlocalizedName() + "." + ((EnumNullOreType)VoidMod.nullore.getStateFromMeta(s.getMetadata()).getValue(BlockNullOre.TYPE)).getName();
	}
}
