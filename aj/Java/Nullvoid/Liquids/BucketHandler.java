package aj.Java.Nullvoid.Liquids;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketHandler {
	public static BucketHandler INSTANCE = new BucketHandler();

	private BucketHandler() {
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event) {
		System.out.println("Bucket Fill");
		ItemStack result = fillCustomBucket(event.world, event.target);
		if (result == null)
			return;

		event.result = result;
		event.setResult(cpw.mods.fml.common.eventhandler.Event.Result.ALLOW);
		System.out.println("Done");
	}

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
		Item bucket = null;
		if(world.getBlock(pos.blockX, pos.blockY, pos.blockZ).getUnlocalizedName().equalsIgnoreCase(VoidMod.blockLiquidFlux.getUnlocalizedName())){
			bucket = VoidMod.bucket;
		}
		System.out.println(world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ));
		if (bucket != null
				&& world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) != 1) {
			// world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
			//System.out.println(world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ));
			world.setBlockMetadataWithNotify(
					pos.blockX,
					pos.blockY,
					pos.blockZ,
					world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) - 1,
					3);
			System.out.println("If");
			return new ItemStack(bucket);
		}
		else if (bucket != null) {
			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
			System.out.println("Else");
			return new ItemStack(bucket);
		}
		return null;
	}
}
