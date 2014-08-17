package aj.Java.Nullvoid.item;

import java.util.ConcurrentModificationException;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Dimention.TeleporterNullVoid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ItemVoidTome extends Item {
	public ItemVoidTome(){
		setCreativeTab(VoidMod.ctab);
		this.setMaxDamage(1);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p){
		if(stack.getItemDamage() == 0){
			@SuppressWarnings("unchecked")
			List<Entity> l = (List<Entity>) world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(p.posX - 5, p.posY - 5, p.posZ - 5, p.posX + 5, p.posY + 5, p.posZ + 5));
			try {
				for (Entity e : l) {
					if(!(e instanceof EntityPlayer)){
						int dim = e.dimension;
						Utils.setVoidwalking(e, 65536);
						e.dimension = VoidMod.NullVoidDimID;
						MinecraftServer.getServer()
							.getConfigurationManager()
								.transferEntityToWorld(e, dim, DimensionManager.getWorld(dim), DimensionManager.getWorld(VoidMod.NullVoidDimID), 
									new TeleporterNullVoid(
										MinecraftServer.getServer()
											.worldServerForDimension(VoidMod.NullVoidDimID)));
						e.setDead();
						return new ItemStack(stack.getItem(), stack.stackSize, 1);
					}
				}
			} catch (ConcurrentModificationException e) {
				return stack;
			}
		}
		return stack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister i){
		this.itemIcon = i.registerIcon("nullvoid:voidTome");
	}
}
