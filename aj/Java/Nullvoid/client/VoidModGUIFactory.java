package aj.Java.Nullvoid.client;

import java.util.Set;

import aj.Java.Nullvoid.GUI.VoidModGuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.IModGuiFactory;

public class VoidModGUIFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		// TODO Auto-generated method stub
		return VoidModGuiConfig.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(
			RuntimeOptionCategoryElement element) {
		// TODO Auto-generated method stub
		return null;
	}

}
