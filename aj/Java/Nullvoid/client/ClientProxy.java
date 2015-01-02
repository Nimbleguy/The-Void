package aj.Java.Nullvoid.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import aj.Java.Nullvoid.CommonProxy;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Entity.EntityBuilder;
import aj.Java.Nullvoid.Entity.EntityGlitch;
import aj.Java.Nullvoid.Entity.EntityNullFloater;
import aj.Java.Nullvoid.Entity.EntityVoidCloud;
import aj.Java.Nullvoid.Entity.EntityVoidMaster;
import aj.Java.Nullvoid.client.render.ModelNull;
import aj.Java.Nullvoid.client.render.TextureNullOre;
import aj.Java.Nullvoid.client.render.block.render.TileEntityCrystalSpecialRender;
import aj.Java.Nullvoid.client.render.block.render.TileEntityReactorSpecialRender;
import aj.Java.Nullvoid.client.render.entity.model.ModelVoidCloud;
import aj.Java.Nullvoid.client.render.entity.model.ModelVoidMaster;
import aj.Java.Nullvoid.client.render.entity.render.RenderBuilder;
import aj.Java.Nullvoid.client.render.entity.render.RenderGlitch;
import aj.Java.Nullvoid.client.render.entity.render.RenderNullFloater;
import aj.Java.Nullvoid.client.render.entity.render.RenderVoidCloud;
import aj.Java.Nullvoid.client.render.entity.render.RenderVoidMaster;
import aj.Java.Nullvoid.client.render.item.render.RenderHammer;
import aj.Java.Nullvoid.tileentity.TileEntityGlitchCrystal;
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
		
		//Items
		MinecraftForgeClient.registerItemRenderer(VoidMod.elementalHammer, new RenderHammer());
		
		//Blocks
		VoidMod.ReactorRender = RenderingRegistry.getNextAvaliableRenderId();
		VoidMod.CrystalRender = RenderingRegistry.getNextAvailableRenderId();
		VoidMod.texNullOre = new TextureNullOre("nullvoid:nullOre");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidReactor.class, new TileEntityReactorSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlitchCrystal.class, new TileEntityCrystalSpecialRender());
    }
	
	@Override
	public void registerIcons(){
		try{
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for(Field f : VoidMod.class.getFields()){
			if(f.getGenericType().toString().contains("Item")){
				renderItem.getItemModelMesher().register((Item) f.get(null), 0, new ModelResourceLocation(VoidMod.MODID + ":" + ((Item) (f.get(null))).getUnlocalizedName(), "inventory"));
				HasStates hs = f.getAnnotation(HasStates.class);
				if(hs != null){
					ModelBakery.addVariantName((Item)f.get(null), hs.value());
				}
			}
		}
		}
		catch(IllegalAccessException e){
			System.err.print("Unable to add icons.");
			e.printStackTrace();
		}
	}
}
