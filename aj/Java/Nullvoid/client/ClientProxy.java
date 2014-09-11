package aj.Java.Nullvoid.client;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSquid;
import net.minecraftforge.client.MinecraftForgeClient;
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
import aj.Java.Nullvoid.client.render.TextureNullOre;
import aj.Java.Nullvoid.client.render.block.render.TileEntityReactorSpecialRender;
import aj.Java.Nullvoid.client.render.entity.model.ModelVoidCloud;
import aj.Java.Nullvoid.client.render.entity.model.ModelVoidMaster;
import aj.Java.Nullvoid.client.render.entity.render.RenderBuilder;
import aj.Java.Nullvoid.client.render.entity.render.RenderGlitch;
import aj.Java.Nullvoid.client.render.entity.render.RenderNullFloater;
import aj.Java.Nullvoid.client.render.entity.render.RenderVoidCloud;
import aj.Java.Nullvoid.client.render.entity.render.RenderVoidMaster;
import aj.Java.Nullvoid.client.render.item.render.RenderHammer;
import aj.Java.Nullvoid.tileentity.TileEntityVoidReactor;

public class ClientProxy extends CommonProxy {
	public ISound voidSound;
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
		//Items
		MinecraftForgeClient.registerItemRenderer(VoidMod.elementalHammer, new RenderHammer());
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
