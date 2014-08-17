package aj.Java.Nullvoid.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPhantom extends TileEntity {
	public Block phantom;
	public int metad = 0;
	@Override
	public boolean canUpdate(){
		return false;
	}
	public void setPhantom(Block b, int meta){
		phantom = b;
		metad = meta;
	}
	@Override
	public void writeToNBT(NBTTagCompound t){
		super.writeToNBT(t);
		if(phantom != null){
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
		phantom = Block.getBlockFromName(tag.getString("Block"));
		metad = tag.getInteger("Meta");
	}
	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		super.onDataPacket(manager, packet);
		worldObj.markBlockRangeForRenderUpdate(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
	}
}
