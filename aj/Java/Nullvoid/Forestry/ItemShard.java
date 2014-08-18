package aj.Java.Nullvoid.Forestry;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemShard extends Item {
	public ItemShard(){
		super();
		this.setCreativeTab(VoidMod.ctab);
	}
	public static final String[] names = new String[] {"null", "void"};
	public static IIcon[] icons = new IIcon[2];
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister i){
		icons[0] = i.registerIcon("nullvoid:nullShard");
		icons[1] = i.registerIcon("nullvoid:voidShard");
	}
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return "item.shard." + names[i];
	}
	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item i, CreativeTabs t, @SuppressWarnings("rawtypes") List l){
		l.add(new ItemStack(this, 1, 0));
		l.add(new ItemStack(this, 1, 1));
	}
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int meta){
		if(meta == 0){
			return icons[0];
		}
		else if(meta == 1){
			return icons[1];
		}
		return null;
	}
}
