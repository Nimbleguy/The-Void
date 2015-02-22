package nimble.Java.TheVoid.Dimension;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;

public class WorldProviderVoid extends WorldProvider {
	
	public void registerWorldChunkManager(){
		worldChunkMgr = new WorldChunkManagerVoid();
		hasNoSky = true;
		dimensionId = VoidMod.config.dimid;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }
	
	public boolean canRespawnHere(){
		return false;
	}
	
	public boolean isSurfaceWorld(){
        return false;
    }
	
	@Override
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderVoid(worldObj, getSeed());
	}
	
	@Override
	public void generateLightBrightnessTable(){
		float f = 0.6F;
		for (int i = 0; i <= 15; ++i)
        {
        	float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
	}
	
	@Override
	public String getDimensionName() {
		return "The Void";
	}

	@Override
	public String getInternalNameSuffix() {
		return "_thevoid:void";
	}
	
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_){
        return 0.0F;
    }
	@SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks){
        return null;
    }
	@SideOnly(Side.CLIENT)
    public boolean isSkyColored(){
        return false;
    }
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_){
		return new Vec3(1, 1, 1);
    }
	

}
