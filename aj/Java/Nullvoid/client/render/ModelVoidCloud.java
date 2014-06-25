package aj.Java.Nullvoid.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoidCloud extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer center;

	public ModelVoidCloud() {
		this.textureWidth = 256;
		this.textureHeight = 48;
		this.setTextureOffset("base.base", 0, 0);
		
		this.setTextureOffset("center.center", 128, 24);

		this.base = new ModelRenderer(this, "base");
		this.base.setRotationPoint(8F, 6F, 8F);
		this.base.addBox("base", -16F, 1F, -16F, 16, 14, 16);
		
		this.center = new ModelRenderer(this, "center");
		this.center.setRotationPoint(6F, 8F, 6F);
		this.center.addBox("center", -11F, -2F, -11F, 11, 16, 11);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.base.render(f5);
		this.center.render(f5);

	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
