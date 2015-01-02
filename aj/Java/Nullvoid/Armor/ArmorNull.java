package aj.Java.Nullvoid.Armor;

import org.lwjgl.input.Keyboard;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Packet.PacketFallDamage;
import aj.Java.Nullvoid.Packet.PacketGearBelt;
import aj.Java.Nullvoid.Packet.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorNull extends ItemArmor {
	public ArmorNull(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par2EnumArmorMaterial, par3, par4);
		this.setCreativeTab(VoidMod.ctab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister i) {
		if (armorType == 0) {
			this.itemIcon = i.registerIcon("nullvoid:nullGoggles");
		} else if (armorType == 1) {
			this.itemIcon = i.registerIcon("nullvoid:gear");
		}
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		return "nullvoid:textures/models/armor/Null_layer_1.png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player,
			ItemStack itemstack) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			if (new ItemStack(itemstack.getItem()).isItemEqual(new ItemStack(
					VoidMod.voidGear))) {
				//System.out.println("isGear");
				if(player.getDisplayName().equals(Minecraft.getMinecraft().thePlayer.getDisplayName())){
					//System.out.println("isPlayer");
					if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && FMLClientHandler.instance().getClient().inGameHasFocus) {
						//System.out.println("sending...");
						if (player.motionY < 0D) {
							player.setVelocity(player.motionX,
									player.motionY + 0.2F, player.motionZ);
							PacketHandler.INSTANCE.sendToServer(new PacketGearBelt(0.2F));
						} else {
							player.setVelocity(player.motionX,
									player.motionY + 0.07F, player.motionZ);
							PacketHandler.INSTANCE.sendToServer(new PacketGearBelt(0.07F));
						}
						if (player.motionY > -0.5D) {
							if(player.motionY < 0D){
								player.fallDistance = 0F;
								PacketHandler.INSTANCE.sendToServer(new PacketFallDamage(0F));
							}
						}
					}
				}
			}
		}
		super.onArmorTick(world, player, itemstack);
	}
}
