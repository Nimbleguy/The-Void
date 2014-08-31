package aj.Java.Nullvoid.block;

import java.util.Random;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Dimention.TeleporterNullVoid;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVoidDoor extends BlockDoor {
	public static boolean teleporting = false;
	@SideOnly(Side.CLIENT)
	private IIcon[] field_150017_a;
	@SideOnly(Side.CLIENT)
	private IIcon[] field_150016_b;

	public BlockVoidDoor() {
		super(Material.iron);
		float f = 0.5F;
		float f1 = 1.0F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		this.setCreativeTab(VoidMod.ctab);
		this.setHardness(10F);
		this.setResistance(15F);
		this.setHarvestLevel("pickaxe", 1);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return this.field_150016_b[0];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_,
			int p_149673_3_, int p_149673_4_, int p_149673_5_) {
		if (p_149673_5_ != 1 && p_149673_5_ != 0) {
			int i1 = this.func_150012_g(p_149673_1_, p_149673_2_, p_149673_3_,
					p_149673_4_);
			int j1 = i1 & 3;
			boolean flag = (i1 & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (i1 & 8) != 0;

			if (flag) {
				if (j1 == 0 && p_149673_5_ == 2) {
					flag1 = !flag1;
				} else if (j1 == 1 && p_149673_5_ == 5) {
					flag1 = !flag1;
				} else if (j1 == 2 && p_149673_5_ == 3) {
					flag1 = !flag1;
				} else if (j1 == 3 && p_149673_5_ == 4) {
					flag1 = !flag1;
				}
			} else {
				if (j1 == 0 && p_149673_5_ == 5) {
					flag1 = !flag1;
				} else if (j1 == 1 && p_149673_5_ == 3) {
					flag1 = !flag1;
				} else if (j1 == 2 && p_149673_5_ == 4) {
					flag1 = !flag1;
				} else if (j1 == 3 && p_149673_5_ == 2) {
					flag1 = !flag1;
				}

				if ((i1 & 16) != 0) {
					flag1 = !flag1;
				}
			}

			return flag2 ? this.field_150017_a[flag1 ? 1 : 0]
					: this.field_150016_b[flag1 ? 1 : 0];
		} else {
			return this.field_150016_b[0];
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.field_150017_a = new IIcon[2];
		this.field_150016_b = new IIcon[2];
		this.field_150017_a[0] = p_149651_1_.registerIcon(VoidMod.MODID
				+ ":voidDoor_upper");
		this.field_150016_b[0] = p_149651_1_.registerIcon(VoidMod.MODID
				+ ":voidDoor_lower");
		this.field_150017_a[1] = new IconFlipped(this.field_150017_a[0], true,
				false);
		this.field_150016_b[1] = new IconFlipped(this.field_150016_b[0], true,
				false);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World w, int p_149727_2_, int p_149727_3_,
			int p_149727_4_, EntityPlayer p, int p_149727_6_,
			float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			// p_149727_1_..getConfigurationManager().transferPlayerToDimension(p,
			// 0, new
			// TeleporterNullVoid(p.mcServer.worldServerForDimension(0)));
			// BlockVoidDoor.teleporting = true;
			// p.travelToDimension(0);
			p.addStat(VoidMod.useTardis, 1);
			MinecraftServer
					.getServer()
					.getConfigurationManager()
					.transferPlayerToDimension(
							(EntityPlayerMP)p,
							0,
							new TeleporterNullVoid(MinecraftServer.getServer()
									.worldServerForDimension(0)));
			// BlockVoidDoor.teleporting = false;
		}
		return true;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return (p_149650_1_ & 8) != 0 ? null : (VoidMod.ingotNull);
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_,
			int p_149694_4_) {
		return VoidMod.ingotNull;
	}

}
