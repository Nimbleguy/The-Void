package aj.Java.Nullvoid.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPhantom extends TileEntity {
	private Block phantom = null;
	private int metad = 0;
	@Override
	public boolean canUpdate(){
		return false;
	}
	public void setPhantom(Block b, int meta){
		System.out.println(b);
		phantom = b;
		metad = meta;
	}
	public Block getPhantom(){
		return phantom;
	}
	public int getMeta(){
		return metad;
	}
	@Override
	public void writeToNBT(NBTTagCompound t){
		super.writeToNBT(t);
		if(phantom != null){
			System.out.println("write");
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("Block", Block.blockRegistry.getNameForObject(phantom));
			tag.setInteger("Meta", metad);
			t.setTag("Phantom", tag);
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound t){
		super.readFromNBT(t);
		NBTTagCompound tag = (NBTTagCompound) t.getTag("Phantom");
			System.out.println("read");
			phantom = Block.getBlockFromName(tag.getString("Block"));
			metad = tag.getInteger("Meta");
			System.out.println(phantom);
	}
	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		super.onDataPacket(manager, packet);
		worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
	}
}
