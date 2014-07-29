package aj.Java.Nullvoid.client.render.entity.render;

import aj.Java.Nullvoid.client.render.entity.model.ModelNull;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGlitch extends RenderLiving {
	private static final ResourceLocation textureLocation = new ResourceLocation("nullvoid:textures/blocks/null.png");
	public RenderGlitch(ModelNull par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return textureLocation;
	}
}
