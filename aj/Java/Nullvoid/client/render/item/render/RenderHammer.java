package aj.Java.Nullvoid.client.render.item.render;

import org.lwjgl.opengl.GL11;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.client.render.item.model.ModelHammer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderHammer implements IItemRenderer {
	private ModelHammer model;
	private ResourceLocation[] rl = new ResourceLocation[9];
	
	public RenderHammer() {
        model = new ModelHammer();
        rl[0] = new ResourceLocation("nullvoid", "textures/models/item/hammer/fire.png");
        rl[1] = new ResourceLocation("nullvoid", "textures/models/item/hammer/ice.png");
        rl[2] = new ResourceLocation("nullvoid", "textures/models/item/hammer/earth.png");
        rl[3] = new ResourceLocation("nullvoid", "textures/models/item/hammer/air.png");
        rl[4] = new ResourceLocation("nullvoid", "textures/models/item/hammer/order.png");
        rl[5] = new ResourceLocation("nullvoid", "textures/models/item/hammer/entropy.png");
        rl[6] = new ResourceLocation("nullvoid", "textures/models/item/hammer/dark.png");
        rl[7] = new ResourceLocation("nullvoid", "textures/models/item/hammer/light.png");
        rl[8] = new ResourceLocation("nullvoid", "textures/models/item/hammer/balance.png");
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
	public void renderItem(ItemRenderType type, ItemStack i, Object... data) 
	{
		ResourceLocation txt = null;
		int typeh = VoidMod.util.hammerBalance(i);
		txt = rl[0];
		if(typeh != -1){
			txt = rl[typeh];
		}
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		if(type.compareTo(ItemRenderType.EQUIPPED_FIRST_PERSON) == 0){
			GL11.glRotatef(90F, 0F, 1F, 0F);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(txt);
		if(data.length > 1){
			model.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		}
		else{
			model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		}
		GL11.glPopMatrix();
	}

}
