package nimble.Java.TheVoid.Client.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class VoidGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		
		//Runerock has no container, Client Side Only.
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		
		if(id == EnumGUI.RUNEROCK.ordinal()){
			return new GuiRuneRock(world, new BlockPos(x, y, z));
		}
		return null;
	}

	
	public enum EnumGUI{
		RUNEROCK;
	}
}
