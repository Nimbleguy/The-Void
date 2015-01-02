package aj.Java.Nullvoid.Packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import aj.Java.Nullvoid.VoidMod;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(VoidMod.MODID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(PacketLighting.class, PacketLighting.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketLighting.class, PacketLighting.class, 1, Side.CLIENT);
        INSTANCE.registerMessage(PacketIngotFrame.class, PacketIngotFrame.class, 2, Side.SERVER);
        INSTANCE.registerMessage(PacketGearBelt.class, PacketGearBelt.class, 3, Side.SERVER);
        INSTANCE.registerMessage(PacketFallDamage.class, PacketFallDamage.class, 4, Side.SERVER);
        INSTANCE.registerMessage(PacketFireball.class, PacketFireball.class, 5, Side.SERVER);
        INSTANCE.registerMessage(PacketParticle.class, PacketParticle.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(PacketPhantom.class, PacketPhantom.class, 7, Side.SERVER);
        INSTANCE.registerMessage(PacketPhantom.class, PacketPhantom.class, 8, Side.CLIENT);
    }
}
