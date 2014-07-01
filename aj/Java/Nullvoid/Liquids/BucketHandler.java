package aj.Java.Nullvoid.Liquids;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
		ItemStack result = fillCustomBucket(event.world, event.target);
		if (result == null)
			return;

		event.result = result;
		event.setResult(cpw.mods.fml.common.eventhandler.Event.Result.ALLOW);
	}

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
		Item bucket = null;
		if(world.getBlock(pos.blockX, pos.blockY, pos.blockZ) instanceof BlockMoltenFlux){
			bucket = VoidMod.bucket;
		}
		if (bucket != null
				&& world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) != 1) {
			world.setBlockMetadataWithNotify(
					pos.blockX,
					pos.blockY,
					pos.blockZ,
					world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) - 1,
					3);
			return new ItemStack(bucket);
		}
		else if (bucket != null) {
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return new ItemStack(bucket);
		}
		return null;
	}
}
