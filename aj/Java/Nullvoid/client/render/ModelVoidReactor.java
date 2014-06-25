package aj.Java.Nullvoid.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoidReactor extends ModelBase {
	public ModelRenderer base;

	public ModelVoidReactor() {
		this.textureWidth = 192;
		this.textureHeight = 96;

		this.setTextureOffset("base.base", 0, 0);

		this.base = new ModelRenderer(this, "base");
		this.base.setRotationPoint(0F, 0F, 0F);
		this.base.addBox("base", -24F, 8F, -24F, 48, 48, 48);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.base.render(f5);

	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
