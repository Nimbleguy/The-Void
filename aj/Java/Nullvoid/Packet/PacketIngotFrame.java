package aj.Java.Nullvoid.Packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import aj.Java.Nullvoid.VoidMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PacketIngotFrame implements IMessage,
		IMessageHandler<PacketIngotFrame, IMessage> {

	private double x;
	private double y;
	private double z;
	private int voidId;
	private int nullId;

	public PacketIngotFrame(double xc, double yc, double zc, int vid, int nid) {
		x = xc;
		y = yc;
		z = zc;
		voidId = vid;
		nullId = nid;
	}

	public PacketIngotFrame() {

	}

	@Override
	public IMessage onMessage(PacketIngotFrame message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			World w = ctx.getServerHandler().playerEntity.worldObj;
			if (w.getEntityByID(message.voidId) != null) {
				System.out.println("Got void!!");
				w.getEntityByID(message.voidId).setDead();
			}
			if (w.getEntityByID(message.nullId) != null) {
				System.out.println("Got null!!");
				w.getEntityByID(message.nullId).setDead();
			}
			ctx.getServerHandler().playerEntity.inventory
					.consumeInventoryItem(VoidMod.ingotFrame);
			EntityItem item = new EntityItem(w, message.x, message.y,
					message.z, new ItemStack(VoidMod.nullVoidAlloy));
			item.delayBeforeCanPickup = 0;
			w.spawnEntityInWorld(item);
			w.setBlock((int) message.x, (int) message.y, (int) message.z,
					Blocks.air);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		voidId = buf.readInt();
		nullId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeInt(voidId);
		buf.writeInt(nullId);
	}

}
