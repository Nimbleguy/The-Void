package aj.Java.Nullvoid.item;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketIngotFrame;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemFrame extends Item {

	public ItemFrame() {
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setMaxStackSize(1);
	}

	public void registerIcons(IIconRegister i) {
		this.itemIcon = i.registerIcon("nullvoid:ingotFrame");
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
			// MovingObjectPosition pos = p.rayTrace(5D, 5F);
			pos = Utils.rayTraceLiquid(w, p);
			if (pos != null) {
				Block b = w.getBlock(pos.blockX, pos.blockY, pos.blockZ);
				if (b != null) {
					System.out.println("Not Null");
					System.out.println(b);
					if (b.equals(VoidMod.blockLiquidFlux)) {
						System.out.println("Is Block");
						if (w.getBlockMetadata(pos.blockX, pos.blockY,
								pos.blockZ) == 7) {
							System.out.println("Meta 7");
							boolean hasNull = false;
							boolean hasVoid = false;
							caseVoid = 0;
							caseNull = 0;
							e = (List<EntityItem>) w.getEntitiesWithinAABB(
									EntityItem.class, AxisAlignedBB
											.getBoundingBox(pos.blockX - 1D,
													pos.blockY - 1D,
													pos.blockZ - 1D,
													pos.blockX + 1D,
													pos.blockY + 1D,
													pos.blockZ + 1D));
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
								PacketHandler.INSTANCE.sendToServer(new PacketIngotFrame((double)pos.blockX, (double)pos.blockY, (double)pos.blockZ, e.get(caseVoid).getEntityId(), e.get(caseNull).getEntityId()));
							}
						}
					}
				}
			}
		}
		return i;
	}
}
