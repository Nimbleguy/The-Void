package aj.Java.Nullvoid.item;

import java.util.Random;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.gen.WorldGenGlitchTempleCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemDebug extends Item {
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
            return EnumRarity.epic;
    }
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
		MovingObjectPosition pos = Utils.rayTraceLiquid(w, p);
		(new WorldGenGlitchTempleCore()).generate(w, new Random(), pos.blockX, pos.blockY, pos.blockZ);
		return i;
	}
}
