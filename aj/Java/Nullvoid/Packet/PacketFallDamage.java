package aj.Java.Nullvoid.Packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketFallDamage implements IMessage,
		IMessageHandler<PacketFallDamage, IMessage> {
	private float amount = 0F;

	public PacketFallDamage(float up) {
		amount = up;
	}

	public PacketFallDamage() {

	}

	@Override
	public IMessage onMessage(PacketFallDamage message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			ctx.getServerHandler().playerEntity.fallDistance = amount;
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		amount = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(amount);
	}
}
