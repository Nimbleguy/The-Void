package nimble.Java.TheVoid.Client.Gui;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import nimble.Java.TheVoid.Configuration.TheVoidConfigGui;

public class TheVoidGuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {
		//Empty for now.
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return TheVoidConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		//In-game help you say?
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		//Empty for now
		return null;
	}

}
