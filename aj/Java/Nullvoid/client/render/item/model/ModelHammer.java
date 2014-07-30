package aj.Java.Nullvoid.client.render.item.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHammer extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer handle;

	public ModelHammer() {
		this.textureWidth = 48;
		this.textureHeight = 21;

		this.setTextureOffset("head.head", 0, 13);
		this.setTextureOffset("handle.handle", 40, 0);

		this.head = new ModelRenderer(this, "head");
		this.head.setRotationPoint(0F, 0F, 0F);
		this.head.addBox("head", 0F, 12F, 6F, 16, 4, 4);
		
		this.handle = new ModelRenderer(this, "handle");
		this.handle.setRotationPoint(0F, 0F, 0F);
		this.handle.addBox("handle", 7F, 0F, 7F, 2, 12, 2);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.render(f5);
		this.handle.render(f5);

	}
	public void render(float f5){
		this.head.render(f5);
		this.handle.render(f5);
	}
}
