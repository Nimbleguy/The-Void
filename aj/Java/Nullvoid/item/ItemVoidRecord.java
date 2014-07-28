package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemVoidRecord extends ItemRecord {

	public ItemVoidRecord(String p_i45350_1_) {
		super(p_i45350_1_);
		this.setCreativeTab(VoidMod.ctab);
	}
	
	@Override
	public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation("nullvoid", name);
    }
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.getBlock(par4, par5, par6) == Blocks.jukebox && par3World.getBlockMetadata(par4, par5, par6) == 0)
        {
            if (par3World.isRemote)
            {
                return true;
            }
            else
            {
                ((BlockJukebox)Blocks.jukebox).func_149926_b(par3World, par4, par5, par6, par1ItemStack);
                par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, Item.getIdFromItem(this));
                --par1ItemStack.stackSize;
                if(this.recordName.equalsIgnoreCase("scatman")){
                	par2EntityPlayer.addStat(VoidMod.scat, 1);
                }
                return true;
            }
        }
        else
        {
            return false;
        }
    }
	@Override
	public void registerIcons(IIconRegister i){
		if(recordName.equalsIgnoreCase("scatman")){
			this.itemIcon = i.registerIcon("nullvoid:record_scatman");
		}
		else if(recordName.equalsIgnoreCase("piertonowhere")){
			this.itemIcon = i.registerIcon("nullvoid:record_piertonowhere");
		}
	}
}
