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
	
	public RenderHammer() {
        model = new ModelHammer();
    }
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) 
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		GL11.glRotatef(67.5F, 0F, 1F, 0F);
		if(type.compareTo(ItemRenderType.EQUIPPED_FIRST_PERSON) == 0){
			GL11.glTranslatef(0.25F, 0F, 0F);
		}
		else{
			GL11.glTranslatef(0F, 0F, -0.5F);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("nullvoid:textures/models/hammer/default"));
		if(data.length > 1){
			model.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		}
		else{
			model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		}
		GL11.glPopMatrix();
	}

}
