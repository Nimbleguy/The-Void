package nimble.Java.TheVoid.Client.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import nimble.Java.TheVoid.Entities.Clone;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class RenderClone extends RenderBiped{

	public RenderClone(ModelBiped model, float shadowSize) {
		super(Minecraft.getMinecraft().getRenderManager(), model, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e){
		if (e instanceof Clone){
			Clone clone = (Clone) e;
			AbstractClientPlayer c = (AbstractClientPlayer) ((EntityPlayer) clone.player);
			return c.getLocationSkin();
		}else{
			return null;
		}
	}
}
