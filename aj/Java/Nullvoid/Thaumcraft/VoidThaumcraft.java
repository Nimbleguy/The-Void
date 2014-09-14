package aj.Java.Nullvoid.Thaumcraft;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;

public class VoidThaumcraft {
	private String voidCategory;
	private String lensResearch;
	private ResearchItem lens;
	private ResearchPage[] lensPages = new ResearchPage[2];
	private InfusionRecipe lensInfusion;
	public VoidThaumcraft(){
		putAspects();
		initResearch();
	}
	private void putAspects(){
		ThaumcraftApi.registerObjectTag(new ItemStack(VoidMod.ingotNull), new AspectList().add(Aspect.VOID, 1).add(Aspect.CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(VoidMod.ingotVoid), new AspectList().add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 4));
	}
	private void initResearch(){
		voidCategory = "VOID";
		lensResearch = "ThaumicLens";
		ResearchCategories.registerCategory(voidCategory, new ResourceLocation("nullvoid", "textures/items/monocle"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchbackelderitch"));
		lens = new ResearchItem(lensResearch, 
				voidCategory, 
				new AspectList().add(Aspect.ELDRITCH, 1).add(Aspect.VOID, 1).add(Aspect.DARKNESS, 1).add(Aspect.AURA, 1),
				10,
				5,
				3,
				new ItemStack(VoidMod.lens, 1, 1));
		lens.setItemTriggers(new ItemStack(VoidMod.lens), new ItemStack(ConfigItems.itemGoggles));
		
		doRecipes();
		
		lensPages[0] = new ResearchPage("thaum.lens.page.1");
		lensPages[1] = new ResearchPage(lensInfusion);
	}
	private void doRecipes(){
		lensInfusion = ThaumcraftApi.addInfusionCraftingRecipe(lensResearch, 
				new ItemStack(VoidMod.lens, 1, 1), 
				3, 
				new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10).add(Aspect.ELDRITCH, 32).add(Aspect.VOID, 64), 
				new ItemStack(VoidMod.lens), 
				new ItemStack[] {new ItemStack(VoidMod.nullVoidAlloy), new ItemStack(VoidMod.nullVoidAlloy), new ItemStack(ConfigItems.itemGoggles), new ItemStack(ConfigItems.itemShard, 1, 6)});
	}
}
