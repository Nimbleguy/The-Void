package aj.Java.Nullvoid.Biome;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenNull extends BiomeGenBase {

	public BiomeGenNull(int par1) {
		super(par1);
		this.rootHeight = 0.1F;
		this.heightVariation = 0.6F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.setDisableRain();
		this.topBlock = VoidMod.VoidFabric;
		this.fillerBlock = VoidMod.VoidFabric;
		this.setBiomeName("NullVoid");
		this.waterColorMultiplier = 0xffffff;
		this.temperature = -100.0F;
		this.spawnableCaveCreatureList.clear();
	}

}
