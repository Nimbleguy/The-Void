package aj.Java.Nullvoid.Forestry;

import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.apiculture.genetics.BeeMutation;

public class BeeMutationBiome extends BeeMutation {
	private final int biomeid;

	public BeeMutationBiome(IAllele allele0, IAllele allele1, IAllele[] template, int chance, int biomeid) {
		super(allele0, allele1, template, chance);
		this.biomeid = biomeid;
	}

	@Override
	public float getChance(IBeeHousing housing, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1) {
		float chance = super.getChance(housing, allele0, allele1, genome0, genome1);
		
		if (chance <= 0){
			return 0;
		}

		if(housing.getBiomeId() == biomeid){
			return chance;
		}
		else{
			return 0;
		}
	}
}
