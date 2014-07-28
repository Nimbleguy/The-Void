package aj.Java.Nullvoid.Dimention;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.client.render.SkyrenderNullVoid;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderNullVoid extends WorldProvider {
	public WorldProviderNullVoid(){
		super();
		hasNoSky = true;
	}
	@SideOnly(Side.CLIENT)
	@Override
    public boolean isSkyColored()
    {
        return false;
    }
	@Override
	public String getDepartMessage(){
		return "Traversing the Void...";
	}
	@Override
	public String getDimensionName() {
		// TODO Auto-generated method stub
		return "The Null Void";
	}
	@Override
	public float[] calcSunriseSunsetColors(float par1, float par2){
		return null;
		
	}
	@Override
	 public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.75F;
    }
	@Override
	public boolean isDaytime()
    {
        return false;
    }
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerNull(VoidMod.biomeNullVoid,
				0.8F);
		this.dimensionId = VoidMod.NullVoidDimID;
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderNullVoid(worldObj, worldObj.getSeed(), true);
	}
	@Override
	public String getWelcomeMessage() {
		return "Traversing the Void...";
	}
	@Override
	protected void generateLightBrightnessTable()
    {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
            this.lightBrightnessTable[i] *= 0.99F;
        }
    }
	@Override
	public double getVoidFogYFactor(){
		return 1D;
	}
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public boolean getWorldHasVoidParticles() {
		return true;
	}
	@SideOnly(Side.CLIENT)
	@Override
    public IRenderHandler getSkyRenderer()
    {
            return new SkyrenderNullVoid();
    }
}
