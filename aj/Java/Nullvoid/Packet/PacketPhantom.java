package aj.Java.Nullvoid.Packet;

import aj.Java.Nullvoid.tileentity.TileEntityPhantom;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.DimensionManager;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketPhantom implements IMessage, IMessageHandler<PacketPhantom, IMessage> {
	private int x;
	private int y;
	private int z;
	private String name;
	private int meta;
	public PacketPhantom(){
		
	}
	public PacketPhantom(int xx, int yy, int zz, String nameb, int metad){
		x = xx;
		y = yy;
		z = zz;
		name = nameb;
		meta = metad;
	}
	@Override
	public IMessage onMessage(PacketPhantom m, MessageContext ctx) {
		if(ctx.side.isServer()){
			TileEntityPhantom t = (TileEntityPhantom)(DimensionManager.getWorld(0).getTileEntity(m.x, m.y, m.z));
			return new PacketPhantom(m.x, m.y, m.z, Block.blockRegistry.getNameForObject(t.phantom), t.metad);
		}
		else{
			TileEntityPhantom t = (TileEntityPhantom)(Minecraft.getMinecraft().theWorld.getTileEntity(m.x, m.y, m.z));
			t.phantom = Block.getBlockFromName(m.name);
			t.metad = m.meta;
			Minecraft.getMinecraft().theWorld.markBlockRangeForRenderUpdate(m.x, m.y, m.z, m.x, m.y, m.z);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		int length = buf.readInt();
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < length; i++){
			b.append(buf.readChar());
		}
		name = b.toString();
		meta = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		if(name != null){
			buf.writeInt(name.length());
			for(char c : name.toCharArray()){
				buf.writeChar(c);
			}
		}
		else{
			buf.writeInt(0);
		}
		buf.writeInt(meta);
	}
}
