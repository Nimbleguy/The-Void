package aj.Java.Nullvoid.Packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import aj.Java.Nullvoid.client.fx.EntityGlitchFX;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketParticle implements IMessage, IMessageHandler<PacketParticle, IMessage> {
	
	double[] coords = new double[3];
	double[] accel = new double[3];
	int amount = 0;
	
	public PacketParticle(double x, double y, double z, double ax, double ay, double az, int amount){
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
		accel[0] = ax;
		accel[1] = ay;
		accel[2] = az;
		this.amount = amount;
	}
	
	@Override
	public IMessage onMessage(PacketParticle message, MessageContext ctx) {
		for(int i = 0; i < message.amount; i++){
			EntityFX glitch = new EntityGlitchFX(Minecraft.getMinecraft().theWorld, message.coords[0], message.coords[1], message.coords[2], message.accel[0], message.accel[1], message.accel[2]);
			Minecraft.getMinecraft().effectRenderer.addEffect(glitch);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		coords[0] = buf.readDouble();
		coords[1] = buf.readDouble();
		coords[2] = buf.readDouble();
		accel[0] = buf.readDouble();
		accel[1] = buf.readDouble();
		accel[2] = buf.readDouble();
		amount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(coords[0]);
		buf.writeDouble(coords[1]);
		buf.writeDouble(coords[2]);
		buf.writeDouble(accel[0]);
		buf.writeDouble(accel[1]);
		buf.writeDouble(accel[2]);
		buf.writeInt(amount);
	}

}
