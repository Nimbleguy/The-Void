package aj.Java.Nullvoid.item;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTablet extends Item {
	public ItemTablet(){
		super();
		setCreativeTab(VoidMod.ctab);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int x = 0; x < 9; x++)
	    {
	        par3List.add(new ItemStack(this, 1, x));
	    }
	}
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack i, EntityPlayer e, @SuppressWarnings("rawtypes") List l, boolean b){
		switch (i.getItemDamage()){
		case 0:
			l.add("⁂ = qui; ⁜ = vcgvinrmzyzg + HSABTA; _ = Ammoc;");
			l.add("⇆ = et; ∎ = ubqvr + ROT13; ↈ = victor;");
			l.add(" = BLANKcom; ∰ = Non; ⌨ = gratia; ⍥ = datur;");
			l.add("♖ = 18-5-13-1-14-19-9-20; ☔ = integra; ⌦ = vade;");
			l.add("⌛ = interfice; ☯ = kingdom");
			break;
		case 1:
			l.add("⁂⁜_⇆∎ↈ ∰⌨⍥ ∰♖☔ ⌦⇆_⌛☯ ");
			break;
		case 2:
			l.add("■ = Tcejbo; □ = !Strong Bug Container");
			l.add("▤ = (Void && Null) + Blank");
			l.add("▮☞■");
			break;
		case 3:
			l.add("■■■");
			l.add("■□■");
			l.add("■■■");
			break;
		case 4:
			l.add("▣■▣");
			l.add("■□■");
			l.add("▣■▣");
			l.add("▣ = Svoo Hglmv + ");
			l.add("(FYGFXM + ROT21");
			break;
		case 5:
			l.add("■■▤■■");
			l.add("■■▤■■");
			l.add("▤▤□▤▤");
			l.add("■■▤■■");
			l.add("■■▤■■");
			break;
		case 6:
			//Bygga, bygga, bygga. Felet måste förstöras. Kom, byggare av världar. Det är nödvändigt att ha hammaren. Du har inte det. Det har gått förlorad i den sista sammandrabbning av titaner. Hammaren, kan jag skapa det. Smida den från generika. Gör det nya. Kombinera mörker och ljus i ett. Eld och is. Jord och luft. Beställ och entropi. Denna magi kan rädda oss. Nu hör mig ringa.
			l.add("Bygga, bygga, bygga.");
			l.add("Felet måste förstöras.");
			l.add("Kom, byggare av världar.");
			l.add("Det är nödvändigt att ha hammaren.");
			l.add("Du har inte det.");
			l.add("Det har gått förlorad");
			l.add("i den sista sammandrabbning av titaner.");
			l.add("Hammaren, kan jag skapa det.");
			l.add("Smida den från generika.");
			l.add("Gör det nya.");
			l.add("Kombinera mörker och ljus i ett. Eld och is.");
			l.add("Jord och luft. Beställ och entropi.");
			l.add("Denna magi kan rädda oss.");
			l.add("Nu hör mig ringa.");
			break;
		case 7:
			l.add("▤▤■■■▤▤");
			l.add("▤■▤▤▤■▤");
			l.add("■▤◉◉◉▤■");
			l.add("■▤◉□◉▤■");
			l.add("■▤◉◉◉▤■");
			l.add("▤■▤▤▤■▤");
			l.add("▤▤■■■▤▤");
			l.add("+ (4x □ = ▤ ◉ = ▤)");
			l.add("◉ = 地風▣㓉亮㝜");
			break;
		case 8:
			l.add("■■■");
			l.add("■□■");
			l.add("■■■");
			l.add("x2 □ = ▤");
			break;
		}
	}
}
