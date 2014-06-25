package aj.Java.Nullvoid.Packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketGearBelt implements IMessage, IMessageHandler<PacketGearBelt, IMessage>{
	private float amount = 0F;
	public PacketGearBelt(float up){
		amount = up;
	}
	public PacketGearBelt(){
		
	}
	@Override
	public IMessage onMessage(PacketGearBelt message, MessageContext ctx) {
		if(ctx.side.isServer()){
			ctx.getServerHandler().playerEntity.motionY += amount;
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
