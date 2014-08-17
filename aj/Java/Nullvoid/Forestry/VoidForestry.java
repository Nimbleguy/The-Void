package aj.Java.Nullvoid.Forestry;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.EnumHelper;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification.EnumClassLevel;
import forestry.api.genetics.IMutation;
import forestry.apiculture.genetics.AlleleBeeSpecies;
import forestry.apiculture.genetics.BeeMutation;
import forestry.apiculture.genetics.BeeTemplates;
import forestry.core.genetics.Allele;
import forestry.core.genetics.AllelePlantType;
import forestry.core.genetics.AlleleSpecies;
import forestry.core.genetics.Classification;

public class VoidForestry {
	public static IMutation nullBMut;
	public static IMutation voidBMut;
	
	public static AlleleSpecies allVoidB;
	public static AlleleSpecies allNullB;
	
	public static AllelePlantType fluxFlower;
	public static EnumPlantType fluxPlant;
	
	public static Classification voidClass;
	
	public VoidForestry(){
		registerBees();
	}
	
	public static void registerBees(){
		Class<AllelePlantType> c = AllelePlantType.class;
		try {
			fluxFlower = c.getConstructor(String.class, EnumPlantType.class, Boolean.class).newInstance("flux", fluxPlant = EnumHelper.addEnum(EnumPlantType.class, "FLUX"), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		voidClass = new Classification(EnumClassLevel.GENUS, "nullvoid", "Nihil Solutionibus");
		voidClass.addMemberGroup(new Classification(EnumClassLevel.TRIBE, "basenullvoid", "Fundatur Ciphra"));
		
		allNullB = new AlleleBeeSpecies("null", false, "Nulled", voidClass, "Nullus", 0x6600FF, 0xFFFFFF);
		allNullB.setHasEffect();
		allNullB.setHumidity(EnumHumidity.ARID);
		allNullB.setTemperature(EnumTemperature.ICY);
		
		
		allVoidB = new AlleleBeeSpecies("void", true, "Voided", voidClass, "Vacuos", 0xFFFFFF, 0x6600FF);
		
		nullBMut = new BeeMutation(Allele.speciesEdenic, Allele.speciesEnded, getNullTemplate(), 1);
	}
	
	public static IAllele[] getNullTemplate() {
		IAllele[] alleles = BeeTemplates.getDefaultTemplate();
		alleles[EnumBeeChromosome.SPEED.ordinal()] = Allele.speedSlower;
		alleles[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.lifespanShortest;
		alleles[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.fertilityLow;
		alleles[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.floweringSlowest;
		alleles[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.toleranceNone;
		alleles[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.toleranceNone;
		alleles[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.boolTrue;
		alleles[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = fluxFlower;
		alleles[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectAggressive;
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = allNullB;
		return alleles;
	}
}
