package nimble.Java.TheVoid.Textures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;

public class TextureNullOre extends TextureAtlasSprite {

	public TextureNullOre(String spriteName) {
		super(spriteName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateAnimation(){
		EntityPlayerSP p = Minecraft.getMinecraft().thePlayer;
		if(p != null){
			ItemStack item = p.inventory.armorInventory[3];
			this.frameCounter = getFrame(p.dimension, item != null && item.getItem().equals(VoidMod.monocle));
			TextureUtil.uploadTextureMipmap(
					(int[][]) this.framesTextureData.get(this.frameCounter),
					this.width, this.height, this.originX, this.originY, false,
					false);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private int getFrame(int dim, boolean goggle){
		if(!goggle){
			switch(dim){
			case 0:
				return 0;
			case -1:
				return 1;
			case 1:
				return 2;
			default:
				return dim == VoidMod.config.dimid ? 3 : 0;
			}
		}
		else{
			switch(dim){
			case 0:
				return 4;
			case -1:
				return 5;
			case 1:
				return 6;
			default:
				return dim == VoidMod.config.dimid ? 7 : 4;
			}
		}
	}
}
