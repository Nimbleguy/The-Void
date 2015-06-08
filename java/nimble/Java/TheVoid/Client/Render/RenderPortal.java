package nimble.Java.TheVoid.Client.Render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * The Portal - Nimbleguy
 * Created using Tabula 5.0.0
 */
public class RenderPortal extends ModelBase {
    public ModelRenderer PortalBottom;
    public ModelRenderer PortalLeft;
    public ModelRenderer PortalRight;
    public ModelRenderer PortalTop;
    public ModelRenderer DialerBase;
    public ModelRenderer DialerTop;

    public RenderPortal() {
        this.textureWidth = 512;
        this.textureHeight = 128;
        this.PortalRight = new ModelRenderer(this, 338, 0);
        this.PortalRight.setRotationPoint(20.5F, -57.0F, -8.0F);
        this.PortalRight.addBox(0.0F, 0.0F, 0.0F, 32, 41, 16, 0.0F);
        this.PortalBottom = new ModelRenderer(this, 0, 0);
        this.PortalBottom.setRotationPoint(-26.25F, 21.0F, 0.0F);
        this.PortalBottom.addBox(-52.5F, -16.0F, -8.0F, 105, 32, 16, 0.0F);
        this.setRotateAngle(PortalBottom, 0.0F, 0.0F, 0.7853981633974483F);
        this.PortalLeft = new ModelRenderer(this, 242, 0);
        this.PortalLeft.setRotationPoint(-52.5F, -57.0F, -8.0F);
        this.PortalLeft.addBox(0.0F, 0.0F, 0.0F, 32, 41, 16, 0.0F);
        this.PortalTop = new ModelRenderer(this, 0, 48);
        this.PortalTop.setRotationPoint(-52.5F, -89.0F, -8.0F);
        this.PortalTop.addBox(0.0F, 0.0F, 0.0F, 105, 32, 16, 0.0F);
        this.DialerBase = new ModelRenderer(this, 434, 0);
        this.DialerBase.setRotationPoint(0.0F, 18.0F, -64.0F);
        this.DialerBase.addBox(-8.0F, -6.0F, -8.0F, 16, 12, 16, 0.0F);
        this.DialerTop = new ModelRenderer(this, 414, 37);
        this.DialerTop.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.DialerTop.addBox(-10.0F, -2.0F, -10.0F, 20, 4, 20, 0.0F);
        this.setRotateAngle(DialerTop, 0.27314402793711257F, 0.0F, 0.0F);
        this.PortalBottom.addChild(this.PortalRight);
        this.PortalBottom.addChild(this.PortalLeft);
        this.PortalBottom.addChild(this.PortalTop);
        this.DialerBase.addChild(this.DialerTop);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.PortalBottom.render(f5);
        this.DialerBase.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
