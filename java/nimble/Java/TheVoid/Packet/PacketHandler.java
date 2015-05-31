package nimble.Java.TheVoid.Packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MODID.toLowerCase());
	
	public void init(){
		INSTANCE.registerMessage(PacketGamma.class, PacketGamma.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(PacketGamma.class, PacketGamma.class, 0, Side.SERVER);
	}
}
