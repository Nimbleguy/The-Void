package nimble.Java.TheVoid.Biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;

public class BiomeGenVoid extends BiomeGenBase {
	
	public BiomeGenVoid(int id) {
		super(id);
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		
		topBlock = VoidMod.terrain.getStateFromMeta(0);
		fillerBlock = VoidMod.terrain.getStateFromMeta(0);
		enableRain = false;
		enableSnow = false;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float temp){
		return 0;
	}
}
