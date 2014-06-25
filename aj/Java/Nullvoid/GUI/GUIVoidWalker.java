package aj.Java.Nullvoid.GUI;

import org.lwjgl.opengl.GL11;

import aj.Java.Nullvoid.client.container.ContainerVoidWalker;
import aj.Java.Nullvoid.tileentity.TileEntityVoidWalker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIVoidWalker extends GuiContainer{
	public static final ResourceLocation texture = new ResourceLocation("nullvoid", "textures/gui/walker.png");
	public GUIVoidWalker(InventoryPlayer invPlayer, TileEntityVoidWalker entity) {
		super(new ContainerVoidWalker(invPlayer, entity));
		xSize = 176;
		ySize = 165;
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString(I18n.format("The Voidwalker", new Object[0]), 8, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("Inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(6.0F, 1.0F, 6.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = ((this.height - this.ySize) / 2) - 1;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
	}

	

	
}
