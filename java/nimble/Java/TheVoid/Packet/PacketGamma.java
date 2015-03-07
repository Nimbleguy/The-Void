package nimble.Java.TheVoid.Packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import nimble.Java.TheVoid.VoidMod;

public class PacketGamma implements IMessage, IMessageHandler<PacketGamma, IMessage> {

	public float gamma;
	public boolean set;
	
	public PacketGamma(float g, boolean s){
		gamma = g;
		set = s;
	}
	public PacketGamma(){
		
	}
	
	@Override
	public IMessage onMessage(PacketGamma message, MessageContext ctx) {
		if(ctx.side.isClient()){
			if(message.set){
				Minecraft.getMinecraft().gameSettings.gammaSetting = message.gamma;
			}
			else{
				return new PacketGamma(Minecraft.getMinecraft().gameSettings.gammaSetting, false);
			}
		}
		else{
			NBTTagCompound nbt = VoidMod.util.getVoidTag(ctx.getServerHandler().playerEntity);
			nbt.setFloat("Gamma", message.gamma);
			VoidMod.util.setVoidTag(ctx.getServerHandler().playerEntity, nbt);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		gamma = buf.readFloat();
		set = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(gamma);
		buf.writeBoolean(set);
	}

}
