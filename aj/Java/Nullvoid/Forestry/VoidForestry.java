package aj.Java.Nullvoid.Forestry;

import aj.Java.Nullvoid.VoidMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.oredict.ShapedOreRecipe;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleRegistry;
import forestry.api.genetics.IClassification;
import forestry.api.genetics.IMutation;
import forestry.apiculture.genetics.AlleleBeeSpecies;
import forestry.apiculture.genetics.BeeMutation;
import forestry.apiculture.genetics.BeeTemplates;
import forestry.apiculture.genetics.BranchBees;
import forestry.core.config.ForestryItem;
import forestry.core.genetics.Allele;
import forestry.core.genetics.AllelePlantType;
import forestry.plugins.PluginApiculture;

public class VoidForestry {
	public static IMutation nullBMut;
	public static IMutation voidBMut;
	
	public static AlleleBeeSpecies allVoidB;
	public static AlleleBeeSpecies allNullB;
	
	public static AllelePlantType fluxFlower;
	public static EnumPlantType fluxPlant;
	
	public static IClassification baseBranch;
	
	public static Item shard = null;
	
	public VoidForestry(){
		registerItems();
		registerBees();
		registerCrafting();
	}
	
	private static void registerItems(){
		shard = new ItemShard().setUnlocalizedName("shard");
		
		GameRegistry.registerItem(shard, "shard");
	}
	
	private static void registerCrafting(){
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(VoidMod.ingotNull),
				"SSS",
				"SSS",
				"SSS",
				'S', new ItemStack(shard, 1, 0)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(VoidMod.ingotVoid),
				"SSS",
				"SSS",
				"SSS",
				'S', new ItemStack(shard, 1, 1)));
	}
	
	private static void registerBees(){
		IAlleleRegistry reg = AlleleManager.alleleRegistry;
		
		baseBranch = new BranchBees("basezero", "Fundatur Ciphra");
		reg.getClassification("family.apidae").addMemberGroup(baseBranch);
		
		allNullB = new AlleleBeeSpecies("null", false, "Nulled", baseBranch, "Nihil Solutionibus", 0x6600FF, 0xFFFFFF){
			@Override
			public String getAuthority(){
				return "Eglarbroad Vandelsnatch";
			}
			@Override
			public String getDescription(){
				return "In my bee breeding experiments, I have come across this particular bee. "
						+ "It is a feisty one, crazed by it's adaption to The Void."
						+ "|Void Exploration: Bee Edition";
			}
			@Override
			public boolean isNocturnal() {
				return true;
			}
		};
		allNullB.setHasEffect();
		allNullB.setHumidity(EnumHumidity.ARID);
		allNullB.setTemperature(EnumTemperature.ICY);
		allNullB.addProduct(ForestryItem.beeComb.getItemStack(1, 8), 30);
		allNullB.addSpecialty(new ItemStack(shard), 5);
		allNullB.setEntityTexture("endBee");
		allNullB.setName("Nulled");
		
		allVoidB = new AlleleBeeSpecies("void", true, "Voided", baseBranch, "Vacuos Malum", 0xFFFFFF, 0x6600FF){
			@Override
			public String getAuthority(){
				return "Eglarbroad Vandelsnatch";
			}
			@Override
			public String getDescription(){
				return "The Void has many crystalizations of gems made of pure void. "
						+ "These bees contain the energy of Pure Void."
						+ "|Void Exploration: Bee Edition";
			}
			@Override
			public boolean isNocturnal() {
				return true;
			}
		};
		allVoidB.setHasEffect();
		allVoidB.setHumidity(EnumHumidity.ARID);
		allVoidB.setTemperature(EnumTemperature.ICY);
		allVoidB.addProduct(ForestryItem.beeComb.getItemStack(1, 8), 35);
		allVoidB.addSpecialty(new ItemStack(shard, 1, 1), 5);
		allVoidB.setEntityTexture("endBee");
		allVoidB.setName("Voided");
		
		PluginApiculture.beeInterface.registerTemplate(allNullB.getUID(), getNullTemplate());
		PluginApiculture.beeInterface.registerTemplate(allVoidB.getUID(), getVoidTemplate());
		
		nullBMut = new BeeMutation(Allele.speciesEdenic, Allele.speciesEnded, getNullTemplate(), 6).restrictBiomeType(BiomeDictionary.Type.MAGICAL);
		nullBMut = new BeeMutation(allNullB, Allele.speciesDemonic, getVoidTemplate(), 3).restrictBiomeType(BiomeDictionary.Type.END);
		
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
		alleles[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.boolFalse;
		return alleles;
	}
	public static IAllele[] getVoidTemplate() {
		IAllele[] alleles = BeeTemplates.getDefaultTemplate();
		alleles[EnumBeeChromosome.SPEED.ordinal()] = Allele.speedSlowest;
		alleles[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.lifespanNormal;
		alleles[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.fertilityLow;
		alleles[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.floweringSlowest;
		alleles[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.toleranceNone;
		alleles[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.toleranceNone;
		alleles[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.boolTrue;
		alleles[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.flowersEnd;
		alleles[EnumBeeChromosome.EFFECT.ordinal()] = Allele.effectMisanthrope;
		alleles[EnumBeeChromosome.SPECIES.ordinal()] = allVoidB;
		alleles[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.boolFalse;
		return alleles;
	}
}
