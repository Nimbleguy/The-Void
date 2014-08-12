package aj.Java.Nullvoid.Forestry;

import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification.EnumClassLevel;
import forestry.api.genetics.IMutation;
import forestry.apiculture.genetics.AlleleBeeSpecies;
import forestry.apiculture.genetics.BeeMutation;
import forestry.core.genetics.Allele;
import forestry.core.genetics.AlleleSpecies;
import forestry.core.genetics.Classification;

public class VoidForestry {
	public static IMutation nullBMut;
	public static IMutation voidBMut;
	
	public static AlleleSpecies allVoidB;
	public static AlleleSpecies allNullB;
	
	public static Classification voidClass;
	
	public VoidForestry(){
		System.out.println("BEEEEE");
		registerBees();
	}
	
	public static void registerBees(){
		voidClass = new Classification(EnumClassLevel.GENUS, "nullvoid", "Nihil Solutionibus");
		
		allNullB = new AlleleBeeSpecies("null", true, "Null", voidClass, "Nullus", 0x6600FF, 0xFFFFFF);
		allNullB = new AlleleBeeSpecies("void", true, "Void", voidClass, "Vacuos", 0xFFFFFF, 0x6600FF);
		
		nullBMut = new BeeMutation(Allele.speciesEdenic, Allele.speciesEnded, new IAllele[] {Allele.speciesEdenic, Allele.speciesEnded}, 1);
	}
}
