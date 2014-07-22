package aj.Java.Nullvoid.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSquid;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import aj.Java.Nullvoid.CommonProxy;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Entity.EntityBuilder;
import aj.Java.Nullvoid.Entity.EntityGlitch;
import aj.Java.Nullvoid.Entity.EntityNullFloater;
import aj.Java.Nullvoid.Entity.EntityVoidCloud;
import aj.Java.Nullvoid.Entity.EntityVoidMaster;
import aj.Java.Nullvoid.client.render.ModelNull;
import aj.Java.Nullvoid.client.render.ModelVoidCloud;
import aj.Java.Nullvoid.client.render.ModelVoidMaster;
import aj.Java.Nullvoid.client.render.RenderBuilder;
import aj.Java.Nullvoid.client.render.RenderGlitch;
import aj.Java.Nullvoid.client.render.RenderNullFloater;
import aj.Java.Nullvoid.client.render.RenderVoidCloud;
import aj.Java.Nullvoid.client.render.RenderVoidMaster;
import aj.Java.Nullvoid.client.render.TextureNullOre;
import aj.Java.Nullvoid.client.render.TileEntityReactorSpecialRender;
import aj.Java.Nullvoid.tileentity.TileEntityVoidReactor;

public class ClientProxy extends CommonProxy {
	@Override
    public void registerRenderers() {
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityNullFloater.class, new RenderNullFloater(new ModelSquid(), 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RenderBuilder(new ModelBiped(), 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlitch.class, new RenderGlitch(new ModelNull(), 0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidCloud.class, new RenderVoidCloud(new ModelVoidCloud(), 1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidMaster.class, new RenderVoidMaster(new ModelVoidMaster(), 1F));
		//Armor
		RenderingRegistry.addNewArmourRendererPrefix("NullArmor");
		//Blocks
		VoidMod.ReactorRender = RenderingRegistry.getNextAvailableRenderId();
		VoidMod.texNullOre = new TextureNullOre("nullvoid:nullOre");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidReactor.class, new TileEntityReactorSpecialRender());
    }
	@Override
	public int addArmor(String armor)
	{
	    return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}
