package nimble.Java.TheVoid.Item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nimble.Java.TheVoid.VoidMod;

public class ItemMaterial extends Item {
	
	public ItemMaterial() {
		super();
		setUnlocalizedName("material");
		setCreativeTab(VoidMod.tab);
		
		GameRegistry.registerItem(this, getUnlocalizedName().replace("item.", ""));
	}
	
	private String[] metanames = new String[] {"unull", "uvoid"};
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List list)
    {
		for(int i = 0; i < metanames.length; i++){
			list.add(new ItemStack(this, 1, i));
		}
    }
	
	@Override
	public int getMetadata(int meta){
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack s){
		return super.getUnlocalizedName() + "." + metanames[s.getMetadata()];
	}
}
