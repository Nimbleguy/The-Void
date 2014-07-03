package aj.Java.Nullvoid.Potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionDissolving extends Potion {
	public PotionDissolving(int id, boolean isBad, int color)
	{
		super(id, isBad, color);
		this.setIconIndex(1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nullvoid:textures/potions/Dissolving.png"));
		return 1;
	}

	@Override
	public boolean isReady(int duration, int amplifier)
	{
		return duration >= 1;
	}
}
