package aj.Java.Nullvoid.GUI;

import java.util.ArrayList;
import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class VoidModGuiConfig extends GuiConfig {
	public VoidModGuiConfig(GuiScreen parent) {
        super(parent, 
                getConfigElements(),
                "nullvoid", false, false, "The Void", "Config");
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyConfigElement.DummyCategoryElement("Generation", "config.Generation", new ConfigElement(VoidMod.config.getCategory("generation")).getChildElements()));
        list.add(new DummyConfigElement.DummyCategoryElement("Entities", "config.Entity", new ConfigElement(VoidMod.config.getCategory("entity")).getChildElements()));
        list.add(new DummyConfigElement.DummyCategoryElement("Potions", "config.Potion", new ConfigElement(VoidMod.config.getCategory("potion")).getChildElements()));
        return list;
    }
}
