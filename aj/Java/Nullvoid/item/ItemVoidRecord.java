package aj.Java.Nullvoid.item;

import aj.Java.Nullvoid.VoidMod;
import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hX, float hY, float hZ)
    {
        if (world.getBlockState(pos).getBlock() == Blocks.jukebox && !((Boolean)world.getBlockState(pos).getValue(BlockJukebox.HAS_RECORD)))
        {
            if (world.isRemote)
            {
                return true;
            }
            else
            {
                ((BlockJukebox)Blocks.jukebox).insertRecord(world, pos, world.getBlockState(pos), stack);
                world.playAuxSFXAtEntity((EntityPlayer)null, 1005, pos, Item.getIdFromItem(this));
                --stack.stackSize;
                if(this.recordName.equalsIgnoreCase("scatman")){
                	player.addStat(VoidMod.scat, 1);
                }
                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
