package nimble.Java.TheVoid.Client.Gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nimble.Java.TheVoid.Block.TileEntity.TileEntityRuneRock;
import nimble.Java.TheVoid.Utilities.ModInfo;

public class GuiRuneRock extends GuiScreen {
	
	private BlockPos pos;
	private World world;
	private ResourceLocation image;
	
	public GuiRuneRock(World w, BlockPos p){
		pos = p;
		world = w;
		
		if(w.getTileEntity(p) != null && w.getTileEntity(p) instanceof TileEntityRuneRock){
			image = new ResourceLocation(ModInfo.MODID + ":" + "textures/gui/runerock" + ((TileEntityRuneRock)w.getTileEntity(p)).type + ".png");
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        this.mc.getTextureManager().bindTexture(image);
        int x = (this.width - 256) / 2;
        int y = (this.height - 256) / 2;
        drawTexturedModalRect(x, y, 0, 0, 256, 256);
    }
}
