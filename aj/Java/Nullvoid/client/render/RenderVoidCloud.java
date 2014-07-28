package aj.Java.Nullvoid.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVoidCloud extends RenderLiving {
	private static final ResourceLocation textureLocation = new ResourceLocation("nullvoid:textures/entities/voidCloud.png");
	public RenderVoidCloud(ModelVoidCloud par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		return textureLocation;
	}

}
