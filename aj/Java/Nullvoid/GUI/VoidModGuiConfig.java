package aj.Java.Nullvoid.GUI;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;

public class VoidModGuiConfig extends GuiConfig {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VoidModGuiConfig(GuiScreen parent) {
        super(parent,
                new ConfigElement(VoidMod.config.getCategory("generation")).getChildElements(),
                "nullvoid", true, true, "The Void", "Generation");
    }
}
