package nimble.Java.TheVoid.Block.TileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRuneRock extends TileEntity {
	public String type = "";
	
	public TileEntityRuneRock(String t){
		super();
		type = t;
	}
	public TileEntityRuneRock(){
		super();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		type = tag.getString("type");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setString("type", type);
	}
}
