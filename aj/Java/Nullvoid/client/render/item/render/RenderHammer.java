package aj.Java.Nullvoid.client.render.item.render;

import org.lwjgl.opengl.GL11;

import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
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
        rl[0] = new ResourceLocation("nullvoid", "textures/models/items/hammer/fire.png");
        rl[1] = new ResourceLocation("nullvoid", "textures/models/items/hammer/ice.png");
        rl[2] = new ResourceLocation("nullvoid", "textures/models/items/hammer/earth.png");
        rl[3] = new ResourceLocation("nullvoid", "textures/models/items/hammer/air.png");
        rl[4] = new ResourceLocation("nullvoid", "textures/models/items/hammer/order.png");
        rl[5] = new ResourceLocation("nullvoid", "textures/models/items/hammer/entropy.png");
        rl[6] = new ResourceLocation("nullvoid", "textures/models/items/hammer/dark.png");
        rl[7] = new ResourceLocation("nullvoid", "textures/models/items/hammer/light.png");
        rl[8] = new ResourceLocation("nullvoid", "textures/models/items/hammer/balance.png");
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
		int[] values = new int[8];
		values[0] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.FIRE, i);
		values[1] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ICE, i);
		values[2] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.EARTH, i);
		values[3] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.AIR, i);
		values[4] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ORDER, i);
		values[5] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.ENTROPY, i);
		values[6] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.DARK, i);
		values[7] = ((ItemElementalHammer) i.getItem()).getElement(EnumElement.LIGHT, i);
		boolean check = false;
		txt = rl[8];
		for(int bla = 0; bla < 8; bla++){
			check = false;
			for(int value = 0; value < 8; value++){
				if(values[value] != values[bla]){
					check = true;
				}
			}
			if(check){
				txt = rl[bla];
			}
		}
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		GL11.glRotatef(67.5F, 0F, 1F, 0F);
		if(type.compareTo(ItemRenderType.EQUIPPED_FIRST_PERSON) == 0){
			GL11.glTranslatef(0.25F, 0F, 0F);
		}
		else{
			GL11.glTranslatef(0F, 0F, -0.5F);
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
