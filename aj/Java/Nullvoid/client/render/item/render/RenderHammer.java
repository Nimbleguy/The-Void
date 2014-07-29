package aj.Java.Nullvoid.client.render.item.render;

import org.lwjgl.opengl.GL11;

import aj.Java.Nullvoid.client.render.item.model.ModelHammer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderHammer implements IItemRenderer {
	private ModelHammer model;
	private Minecraft mc = Minecraft.getMinecraft();
	private float scale = 1F;
	
	public RenderHammer() {
        model = new ModelHammer();
    }
	
	@Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {

        case EQUIPPED:
            GL11.glPushMatrix();
            mc.renderEngine.bindTexture(new ResourceLocation(
                    "subaraki:weapons/Sword.png"));
            //GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-5F, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-150F, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.8F, 0.6F, -0.1F);
            model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
                    0.0625F);
            GL11.glPopMatrix();
            break;

        case EQUIPPED_FIRST_PERSON:
            GL11.glPushMatrix();
            mc.renderEngine.bindTexture(new ResourceLocation(
                    "subaraki:weapons/Sword.png"));
            //GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-5F, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-150F, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.8F, 0.9F, -0.1F);
            model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
                    0.0625F);
            GL11.glPopMatrix();
            break;

        case ENTITY:
            GL11.glPushMatrix();
            scale = 1.5F;
            GL11.glScalef(scale, scale, scale);
            mc.renderEngine.bindTexture(new ResourceLocation(
                    "subaraki:weapons/Sword.png"));
            GL11.glRotatef(90F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(45F, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.2F, 1F, 0F);
            model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
                    0.0625F);
            GL11.glPopMatrix();
            break;

        case INVENTORY:
            GL11.glPushMatrix();
            scale = 0.7F;
            GL11.glScalef(scale, scale, scale);
            mc.renderEngine.bindTexture(new ResourceLocation(
                    "subaraki:weapons/Sword.png"));

            GL11.glRotatef(200F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-80F, 0.0f, 1.0f, 0.0f);
            //GL11.glTranslatef(0.0F, 1.2F, 0F);
            model.render(0.0625F);
            GL11.glPopMatrix();
            break;

        default:
            break;
        }
    }
@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {

        switch (type) {
        case INVENTORY:
            return true;
        default:
            break;
        }
        return false;

    }

}
