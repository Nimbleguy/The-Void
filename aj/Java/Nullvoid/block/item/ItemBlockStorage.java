package aj.Java.Nullvoid.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStorage extends ItemBlock {
	private String[] subtypes = {"light", "dark"};
	public ItemBlockStorage(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack i){
		return "tile.storage." + subtypes[i.getItemDamage()];
	}
	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
}
