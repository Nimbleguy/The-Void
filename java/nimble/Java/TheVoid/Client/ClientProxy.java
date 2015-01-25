package nimble.Java.TheVoid.Client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import nimble.Java.TheVoid.CommonProxy;
import nimble.Java.TheVoid.VoidMod;
import nimble.Java.TheVoid.Utilities.ModInfo;
import nimble.Java.TheVoid.Utilities.Variant;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerTextures(){
		try{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			for(Field f : VoidMod.class.getFields()){
				if(f.getType().getSuperclass().getName().contains("Item")){
					Variant v = f.getAnnotation(Variant.class);
					if(v != null){
						for(int i = 0; i < v.value().length; i++){
							renderItem.getItemModelMesher().register((Item)f.get(null), i, new ModelResourceLocation(ModInfo.MODID + ":" + v.value()[i], "inventory"));
						}
					}
					else{
						renderItem.getItemModelMesher().register((Item)f.get(null), 0, new ModelResourceLocation(ModInfo.MODID + ":" + ((Item)f.get(null)).getUnlocalizedName().replace("item.", ""), "inventory"));
					}
				}
				else if(f.getType().getSuperclass().getName().contains("Block")){
					Variant v = f.getAnnotation(Variant.class);
					if(v != null){
						for(int i = 0; i < v.value().length; i++){
							renderItem.getItemModelMesher().register(Item.getItemFromBlock((Block)f.get(null)), i, new ModelResourceLocation(ModInfo.MODID + ":" + v.value()[i], "inventory"));
						}
					}
					else{
						renderItem.getItemModelMesher().register(Item.getItemFromBlock((Block)f.get(null)), 0, new ModelResourceLocation(ModInfo.MODID + ":" + ((Block)f.get(null)).getUnlocalizedName().replace("tile.", ""), "inventory"));
					}
				}
			}
		}
		catch(IllegalAccessException e){
			VoidMod.util.log("Could not register the texture of an item or block.", Level.ERROR);
		}
	}
	@Override
	public void registerVariants(){
		try{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			for(Field f : VoidMod.class.getFields()){
				if(f.getType().getSuperclass().getName().contains("Item")){
					Variant v = f.getAnnotation(Variant.class);
					if(v != null){
						String[] s = v.value();
						for(int i = 0; i < s.length; i++){
							s[i] = ModInfo.MODID + ":" + s[i];
						}
						ModelBakery.addVariantName((Item)f.get(null), s);
					}
				}
				else if(f.getType().getSuperclass().getName().contains("Block")){
					Variant v = f.getAnnotation(Variant.class);
					if(v != null){
						String[] s = v.value();
						for(int i = 0; i < s.length; i++){
							s[i] = ModInfo.MODID + ":" + s[i];
						}
						ModelBakery.addVariantName(Item.getItemFromBlock((Block)f.get(null)), s);
					}
				}
			}
		}
		catch(IllegalAccessException e){
			VoidMod.util.log("Could not register a variant of an item or block.", Level.ERROR);
		}
	}
	
}
