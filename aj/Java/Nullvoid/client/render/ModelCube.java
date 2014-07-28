package aj.Java.Nullvoid.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube extends ModelBase {
	// fields
	public ModelRenderer cube;

	public ModelCube() {
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.setTextureOffset("cube.cube", 0, 0);

		this.cube = new ModelRenderer(this, "cube");
		this.cube.setRotationPoint(0F, 0F, 0F);
		this.cube.addBox("cube", -8F, 8F, -8F, 16, 16, 16);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.cube.render(f5);

	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
