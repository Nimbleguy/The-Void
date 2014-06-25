package aj.Java.Nullvoid.Armor;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Packet.PacketFallDamage;
import aj.Java.Nullvoid.Packet.PacketGearBelt;
import aj.Java.Nullvoid.Packet.PacketHandler;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GravityBelt extends Item implements IBauble {
	public GravityBelt() {
		super();
		this.setCreativeTab(VoidMod.ctab);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		// TODO Auto-generated method stub
		return BaubleType.BELT;
	}

	@Override
	public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			if(arg1 instanceof EntityPlayer){
				if(((EntityPlayer)arg1).getDisplayName().equals(Minecraft.getMinecraft().thePlayer.getDisplayName())){
					if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && FMLClientHandler.instance().getClient().inGameHasFocus) {
						arg1.setVelocity(arg1.motionX,
								arg1.motionY + 0.05F, arg1.motionZ);
						PacketHandler.INSTANCE.sendToServer(new PacketGearBelt(0.05F));
						if(arg1.motionY <= -1F){
							System.out.println("Step 1");
							PacketHandler.INSTANCE.sendToServer(new PacketFallDamage(arg1.fallDistance - 1.5F));
							arg1.fallDistance -= 1.5F;
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(VoidMod.MODID
				+ ":gravBelt");
	}
}
