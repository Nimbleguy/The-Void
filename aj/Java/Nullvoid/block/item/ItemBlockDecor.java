package aj.Java.Nullvoid.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDecor extends ItemBlock {
	private String[] subtypes = {"brickVoid"};
	public ItemBlockDecor(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack i){
		return "tile.decor." + subtypes[i.getItemDamage()];
	}
	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
}
