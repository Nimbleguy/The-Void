package aj.Java.Nullvoid.Forestry;

import java.util.EnumSet;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import forestry.api.genetics.IFlowerProvider;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;
import forestry.core.utils.StringUtil;

public class FlowerProviderFlux implements IFlowerProvider {

	@Override
	public boolean isAcceptedFlower(World world, IIndividual individual, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return block instanceof BlockMoltenFlux;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		EnumSet<EnumPlantType> types = pollinatable.getPlantType();
		return types.size() > 1 || !types.contains(EnumPlantType.Nether);
	}

	@Override
	public boolean growFlower(World world, IIndividual individual, int x, int y, int z) {
		return false;
	}

	@Override
	public String getDescription() {
		return StringUtil.localize("flowers.flux");
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual individual, int x, int y, int z, ItemStack[] products) {
		return products;
	}

	@Override
	public ItemStack[] getItemStacks() {
		return new ItemStack[] { new ItemStack(VoidMod.blockLiquidFlux) };
	}

}
