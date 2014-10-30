package aj.Java.Nullvoid.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@SideOnly(Side.CLIENT)
public class TextureNullOre extends TextureAtlasSprite {

	public TextureNullOre(String par1Str) {
		super(par1Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateAnimation() {
		//System.out.println("updateAnimation");
		EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;
		if(p != null){
			if (p.inventory.armorInventory[3] != null) {
				if (p.inventory.armorInventory[3].isItemEqual(new ItemStack(
						VoidMod.nullGoggles, 1, OreDictionary.WILDCARD_VALUE))) {
					this.frameCounter = getFrameForDim(p.dimension, true);
				} else {
					this.frameCounter = getFrameForDim(p.dimension, false);
				}
			} else {
				this.frameCounter = getFrameForDim(p.dimension, false);
			}
			TextureUtil.uploadTextureMipmap(
					(int[][]) this.framesTextureData.get(this.frameCounter),
					this.width, this.height, this.originX, this.originY, false,
					false);
		}
	}
	@SideOnly(Side.CLIENT)
	private int getFrameForDim(int dim, boolean goggle) {
		if (!goggle) {
			if (dim == 0) {
				return 0;
			} else if (dim == -1) {
				return 1;
			} else if (dim == 1) {
				return 2;
			} else if (dim == VoidMod.NullVoidDimID) {
				return 3;
			}
		} else {
			if (dim == 0) {
				return 4;
			} else if (dim == -1) {
				return 5;
			} else if (dim == 1) {
				return 6;
			} else if (dim == VoidMod.NullVoidDimID) {
				return 7;
			}
		}
		return 0;
	}
	@Override
	public boolean hasAnimationMetadata(){
		return true;
	}
}
