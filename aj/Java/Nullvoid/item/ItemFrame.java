package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketIngotFrame;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ItemFrame extends Item {

	public ItemFrame() {
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setMaxStackSize(1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
		List<EntityItem> e;
		MovingObjectPosition pos;
		int caseNull;
		int caseVoid;
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			System.out.println("Clicked");
			pos = p.rayTrace(5D, 5F);
			if (pos != null) {
				Block b = w.getBlockState(pos.getBlockPos()).getBlock();
				if (b != null) {
					System.out.println("Not Null");
					System.out.println(b);
					if (b.equals(VoidMod.blockLiquidFlux)) {
						System.out.println("Is Block");
						if (w.getBlockState(pos.getBlockPos()) == 7) {
							System.out.println("Meta 7");
							boolean hasNull = false;
							boolean hasVoid = false;
							caseVoid = 0;
							caseNull = 0;
							e = (List<EntityItem>) w.getEntitiesWithinAABB(
									EntityItem.class, AxisAlignedBB
											.fromBounds(pos.getBlockPos().getX() - 1D,
													pos.getBlockPos().getY() - 1D,
													pos.getBlockPos().getZ() - 1D,
													pos.getBlockPos().getX() + 1D,
													pos.getBlockPos().getY() + 1D,
													pos.getBlockPos().getZ() + 1D));
							System.out.println(e.size());
							for (int num = 0; num < e.size(); num++) {
								if (e.get(num).getEntityItem().getItem()
										.equals(VoidMod.ingotVoid)) {
									caseVoid = num;
									hasVoid = true;
									System.out.println("Has Void");
								} else if (e.get(num).getEntityItem().getItem()
										.equals(VoidMod.ingotNull)) {

									caseNull = num;
									hasNull = true;
									System.out.println("Has Null");
								}
							}
							if (hasNull && hasVoid) {
								PacketHandler.INSTANCE.sendToServer(new PacketIngotFrame((double)pos.getBlockPos().getX(), (double)pos.getBlockPos().getY(), (double)pos.getBlockPos().getZ(), e.get(caseVoid).getEntityId(), e.get(caseNull).getEntityId()));
							}
						}
					}
				}
			}
		}
		return i;
	}
}
