package aj.Java.Nullvoid.Packet;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

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
    }
}
