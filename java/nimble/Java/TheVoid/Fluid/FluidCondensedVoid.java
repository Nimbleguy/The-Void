package nimble.Java.TheVoid.Fluid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class FluidCondensedVoid extends Fluid {

	public FluidCondensedVoid() {
		super("Condensed Void");
		this.setDensity(100);
		this.setTemperature(-9001);//.-.
		this.setRarity(EnumRarity.UNCOMMON);
		this.setLuminosity(2);
		
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			try {
				Method m = TextureAtlasSprite.class.getMethod("makeAtlasSprite", ResourceLocation.class);
				m.setAccessible(true);
				TextureAtlasSprite tas = (TextureAtlasSprite) m.invoke(null, new ResourceLocation("thevoid", "textures/blocks/condensedVoid"));
				this.setFlowingIcon(tas);
				this.setStillIcon(tas);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
