package aj.Java.Nullvoid.Dimention;

import java.util.Arrays;
import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerNull extends WorldChunkManagerHell {
	public BiomeGenBase biomeToUse = VoidMod.biomeNullVoid;
	public WorldChunkManagerNull(BiomeGenBase par1BiomeGenBase, float par2) {
		super(par1BiomeGenBase, par2);
		allowedBiomes.clear();
		this.biomeToUse = VoidMod.biomeNullVoid;
		allowedBiomes.add(VoidMod.biomeNullVoid);
	}
	@SuppressWarnings("rawtypes")
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        return par4List.contains(this.biomeToUse);
    }
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomeToUse;
    }
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeToUse);
        return par1ArrayOfBiomeGenBase;
    }
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        return this.loadBlockGeneratorData(par1ArrayOfBiomeGenBase, par2, par3, par4, par5);
    }

}
