package aj.Java.Nullvoid.client.render.block.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import aj.Java.Nullvoid.client.render.block.model.ModelGlitchCrystal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityCrystalSpecialRender extends TileEntitySpecialRenderer {
	private final ModelGlitchCrystal model;
	private final ResourceLocation texture;
	public TileEntityCrystalSpecialRender() {
		this.model = new ModelGlitchCrystal();
		this.texture = new ResourceLocation(
				"nullvoid:textures/blocks/crystal.png");
	}
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z,
			float scale) {
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslated(x + 0.5D, y + 1.485D, z + 0.5D);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
