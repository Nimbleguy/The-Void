package aj.Java.Nullvoid.Packet;

import net.minecraft.entity.projectile.EntityLargeFireball;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketFireball implements IMessage, IMessageHandler<PacketFireball, IMessage> {
	private double x = 0D;
	private double y = 0D;
	private double z = 0D;
	private double mX = 0D;
	private double mY = 0D;
	private double mZ = 0D;
	public PacketFireball(double x, double y, double z, double motX, double motY, double motZ){
		this.x = x;
		this.y = y;
		this.z = z;
		mX = motX;
		mY = motY;
		mZ = motZ;
	}
	public PacketFireball(){
		
	}
	@Override
	public IMessage onMessage(PacketFireball message, MessageContext ctx) {
		System.out.println("Packet");
		EntityLargeFireball e = new EntityLargeFireball(ctx.getServerHandler().playerEntity.worldObj, ctx.getServerHandler().playerEntity, 1, 1, 1);
		e.setPosition(x, y, z);
		e.accelerationX = mX;
		e.accelerationY = mY;
		e.accelerationZ = mZ;
		ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(e);
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		mX = buf.readDouble();
		mY = buf.readDouble();
		mZ = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeDouble(mX);
		buf.writeDouble(mY);
		buf.writeDouble(mZ);
	}

}
