package aj.Java.Nullvoid.Potion;

import aj.Java.Nullvoid.GUI.GUIDissolving;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class DissolvingRender {
	int xo;
	int yo;
	int xt;
	int yt;
	Minecraft m;

	public DissolvingRender(int x1, int y1, int x2, int y2) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			xo = x1;
			yo = y1;
			xt = x2;
			yt = y2;
			m = Minecraft.getMinecraft();
		}
	}
	public void render(){
		GUIDissolving.drawRect(xo, yo, xt, yt, 0);
	}
}
