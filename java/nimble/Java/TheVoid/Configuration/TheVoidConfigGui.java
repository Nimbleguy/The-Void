package nimble.Java.TheVoid.Configuration;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Configuration.Config.EnumConfigCategories;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class TheVoidConfigGui extends GuiConfig {
	public TheVoidConfigGui(GuiScreen gs){
		super(gs, getConfigElements(), ModInfo.MODID, false, false, VoidMod.config.getConfig().getConfigFile().getName());
	}
	
	
	private static List<IConfigElement> getConfigElements() {
    	List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        //Add categories to config GUI
        for(EnumConfigCategories ecc : EnumConfigCategories.values()){
        	list.add(categoryElement(ecc.getCategory(), ecc.getCategory().substring(0, 1).toUpperCase() + ecc.getCategory().substring(1), ModInfo.MODID.toLowerCase() + ".config.gui.category." + ecc.getCategory().toLowerCase()));
        }
        return list;
    }
    
    
    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key, new ConfigElement(VoidMod.config.getConfig().getCategory(category)).getChildElements());
    }
}
