package aj.Java.Nullvoid.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoidMaster extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer arm1;
	public ModelRenderer arm2;
	public ModelRenderer head;

	public ModelVoidMaster() {
		this.textureWidth = 144;
		this.textureHeight = 144;

		this.setTextureOffset("base.base", 0, 0);
		this.setTextureOffset("leg1.leg1", 0, 0);
		this.setTextureOffset("leg2.leg2", 0, 0);
		this.setTextureOffset("arm1.arm1", 0, 0);
		this.setTextureOffset("arm2.arm2", 0, 0);
		this.setTextureOffset("head.head", 128, 128);

		this.base = new ModelRenderer(this, "base");
		this.base.setRotationPoint(0F, 44F, 0F);
		this.base.addBox("base", -4F, -32F, -3F, 8, -56, 6);
		
		this.leg1 = new ModelRenderer(this, "leg1");
		this.leg1.setRotationPoint(-1F, 32F, 0F);
		this.leg1.addBox("leg1", -2F, -0F, -1F, 2, -32, 2);
		
		this.leg2 = new ModelRenderer(this, "leg2");
		this.leg2.setRotationPoint(1F, 32F, 0F);
		this.leg2.addBox("leg2", 2F, -0F, 1F, -2, -32, -2);
		
		this.arm1 = new ModelRenderer(this, "arm1");
		this.arm1.setRotationPoint(-4.5F, 44F, 0F);
		this.arm1.addBox("arm1", -5F, -44F, -0.5F, 1, -28, 1);
		
		this.arm2 = new ModelRenderer(this, "arm2");
		this.arm2.setRotationPoint(4.5F, 44F, 0F);
		this.arm2.addBox("arm2", 5F, -44F, 0.5F, -1, -28, -1);
		
		this.head = new ModelRenderer(this, "head");
		this.head.setRotationPoint(0F, 64F, 0F);
		this.head.addBox("head", -8F, -56F, -8F, 16, -16, 16);
		//this.head.addBox("hair1", -24F, 8F, -24F, 48, 48, 48);
		//this.head.addBox("hair2", -24F, 8F, -24F, 48, 48, 48);
		//this.head.addBox("hat", -24F, 8F, -24F, 48, 48, 48);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.base.render(f5);
		this.arm1.render(f5);
		this.arm2.render(f5);
		this.leg1.render(f5);
		this.leg2.render(f5);
		this.head.render(f5);

	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
