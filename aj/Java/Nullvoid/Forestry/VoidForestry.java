package aj.Java.Nullvoid.Forestry;

import aj.Java.Nullvoid.VoidMod;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.EnumHelper;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleRegistry;
import forestry.api.genetics.IClassification;
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
		IAlleleRegistry reg = AlleleManager.alleleRegistry;
		
		reg.getClassification("class.insecta").addMemberGroup(reg.createAndRegisterClassification(EnumClassLevel.GENUS, "nullvoid", "Fundatur Ciphra"));
		
		allNullB = new AlleleBeeSpecies("null", false, "Nulled", reg.getClassification("genus.nullvoid"), "Nihil Solutionibus", 0x6600FF, 0xFFFFFF);
		//allNullB.setHasEffect();
		allNullB.setHumidity(EnumHumidity.ARID);
		allNullB.setTemperature(EnumTemperature.ICY);
		
		
		allVoidB = new AlleleBeeSpecies("void", true, "Voided", reg.getClassification("genus.nullvoid"), "Vacuos Malum", 0xFFFFFF, 0x6600FF);
		
		nullBMut = new BeeMutationBiome(Allele.speciesEdenic, Allele.speciesEnded, getNullTemplate(), 1, VoidMod.NullVoidBioID);
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
		alleles[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowersEnd;
		alleles[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectAggressive;
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = allNullB;
		return alleles;
	}
}
