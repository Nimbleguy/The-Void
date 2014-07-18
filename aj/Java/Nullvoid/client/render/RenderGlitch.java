package aj.Java.Nullvoid.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGlitch extends RenderLiving {
	private static ModelBase[] models = new ModelBase[3];
	private static int model = 0;
	private static final ResourceLocation[] textureLocation = {new ResourceLocation("nullvoid:textures/blocks/null.png")};
	public RenderGlitch(ModelNull par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		models[0] = par1ModelBase;
		models[1] = new ModelSquid();
		models[2] = new ModelVoidCloud();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return textureLocation[model];
	}
	public void setModel(int ent){
		model = 0;
		this.mainModel = models[model];
		this.bindTexture(textureLocation[model]);
	}
	public int getModel(){
		return model;
	}
}
