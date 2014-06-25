package aj.Java.Nullvoid.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBuilder extends RenderBiped {
	private static final ResourceLocation textureLocation = new ResourceLocation("nullvoid:textures/entities/entityBuilder.png");
	public RenderBuilder(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return textureLocation;
	}
	@Override
	protected void preRenderCallback(EntityLivingBase entity, float f){
    	GL11.glScalef(10F, 10F, 10F);
    }
}
