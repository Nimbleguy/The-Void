package aj.Java.Nullvoid.client;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.GUI.GUIVoidBook;
import aj.Java.Nullvoid.GUI.GUIVoidWalker;
import aj.Java.Nullvoid.client.container.ContainerVoidWalker;
import aj.Java.Nullvoid.tileentity.TileEntityVoidWalker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GUIHandler implements IGuiHandler {
	public GUIHandler(){
		NetworkRegistry.INSTANCE.registerGuiHandler(VoidMod.me, this);
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		switch(ID) {
		case 0:
			if(entity != null && entity instanceof TileEntityVoidWalker) {
				return new ContainerVoidWalker(player.inventory, (TileEntityVoidWalker) entity);
			} else {
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		switch(ID) {
		case 0:
			if(entity != null && entity instanceof TileEntityVoidWalker) {
				return new GUIVoidWalker(player.inventory, (TileEntityVoidWalker) entity);
			} else {
				return null;
			}
		case 1:
			return new GUIVoidBook(player, player.inventory.getCurrentItem(), false);
		default:
			return null;
		}
	}

}
