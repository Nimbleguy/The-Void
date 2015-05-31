package nimble.Java.TheVoid.Packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nimble.Java.TheVoid.VoidMod;

public class PacketMotion implements IMessage, IMessageHandler<PacketMotion, IMessage> {

	private double motionX;
	private double motionZ;
	
	public PacketMotion(double mX, double mZ){
		motionX = mX;
		motionZ = mZ;
	}
	
	public PacketMotion(){
	}
	
	@Override
	public IMessage onMessage(PacketMotion message, MessageContext ctx) {
		if(ctx.side.isClient()){
			return new PacketMotion(Minecraft.getMinecraft().thePlayer.motionX, Minecraft.getMinecraft().thePlayer.motionZ);
		}
		else{
			if(VoidMod.omnicharm.omni.get(ctx.getServerHandler().playerEntity) != null){
				for(EntityPlayerMP p : VoidMod.omnicharm.omni.get(ctx.getServerHandler().playerEntity)){
					p.motionX = message.motionX;
					p.motionZ = message.motionZ;
				}
			}
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		motionX = buf.readDouble();
		motionZ = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(motionX);
		buf.writeDouble(motionZ);
	}

}
