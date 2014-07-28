package aj.Java.Nullvoid.Packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.Listners.TickListner;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketLighting implements IMessage, IMessageHandler<PacketLighting, IMessage> {
	private float brightness = 0F;
	private boolean send = false;
	public PacketLighting(float bright){
		brightness = bright;
	}
	public PacketLighting(float bright, boolean dontSendBack){
		send = dontSendBack;
		brightness = bright;
	}
	public PacketLighting(){
		
	}
	@Override
	public IMessage onMessage(PacketLighting message,
			MessageContext ctx) {
		if(ctx.side.isClient()){
			TickListner.brightness = message.brightness;
			if(!message.send){
				return new PacketLighting(Minecraft.getMinecraft().gameSettings.gammaSetting);
			}
		}
		else{
			Utils.setBright(ctx.getServerHandler().playerEntity, message.brightness);
		}
		return null;
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		brightness = buf.readFloat();
		send = buf.readBoolean();
	}
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(brightness);
		buf.writeBoolean(send);
	}

}
