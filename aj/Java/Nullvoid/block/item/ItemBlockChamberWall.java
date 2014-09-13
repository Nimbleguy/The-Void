package aj.Java.Nullvoid.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChamberWall extends ItemBlock {
	public final String[] subtypes = new String[] {"door", "doorExtension", "wall", "blockade", "brick"};
	public ItemBlockChamberWall(Block p_i45328_1_) {
		super(p_i45328_1_);
		setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack i){
		return "tile.chamberWall." + subtypes[i.getItemDamage()];
	}
	@Override
	public int getMetadata(int damageValue) {
		return damageValue;
	}
}
