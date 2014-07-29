package aj.Java.Nullvoid.client.render.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderNullFloater extends RenderSquid {
	private static final ResourceLocation textureLocation = new ResourceLocation("nullvoid:textures/entities/entityFloater.png");
	public RenderNullFloater(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return textureLocation;
	}

}
