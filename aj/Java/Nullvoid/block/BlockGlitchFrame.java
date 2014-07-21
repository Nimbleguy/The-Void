package aj.Java.Nullvoid.block;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.item.ItemTablet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGlitchFrame extends Block {
	public BlockGlitchFrame() {
		super(Material.wood);
		this.setCreativeTab(VoidMod.ctab);
		this.setHardness(10F);
		this.setResistance(10F);
	}

	private IIcon[] icons = new IIcon[2];

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister i) {
		icons[0] = i.registerIcon("nullvoid:glitchFrameEmpty");
		icons[1] = i.registerIcon("nullvoid:glitchFrameFull");
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z,
			EntityPlayer p, int side, float lx, float ly, float lz) {
		if (p.inventory.getCurrentItem() != null) {
			if (p.inventory.getCurrentItem().getItem() == VoidMod.corruptAlloy) {
				if (w.getBlockMetadata(x, y, z) == 0) {
					if(p.inventory.getCurrentItem().stackSize == 1){
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, null);
					}
					else{
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, new ItemStack(VoidMod.corruptAlloy, p.inventory.getCurrentItem().stackSize - 1));
					}
					w.setBlockMetadataWithNotify(x, y, z, 1, 3);
					return true;
				}
			} else if (p.inventory.getCurrentItem().getItem() instanceof ItemTablet
					&& w.getBlockMetadata(x, y, z) == 1) {
				if (p.inventory.getCurrentItem().getItemDamage() == 3) {
					boolean b = true;
					for (int xx = x - 1; xx < x + 2; xx++) {
						for (int zz = z - 1; zz < z + 2; zz++) {
							if (xx != x || zz != z) {
								if (!(w.getBlock(xx, y, zz) instanceof BlockGeneric)) {
									b = false;
								} else {
									w.setBlock(xx, y, zz, Blocks.air);
								}
							}
						}
					}
					if (b) {
						for (int i = 0; i < 100; i++) {
							w.spawnEntityInWorld(new EntityLightningBolt(w,
									(double) x, (double) y, (double) z));
							w.createExplosion((Entity) null, x, y, z, 5F, true);
						}
						w.spawnEntityInWorld(new EntityItem(w, (double) x,
								200D, (double) z, new ItemStack(Blocks.sapling,
										32)));
						w.setBlock(x, y, z, this);
					}
				} else if (p.inventory.getCurrentItem().getItemDamage() == 4) {
					boolean b = true;
					for (int xx = x - 1; xx < x + 2; xx++) {
						for (int zz = z - 1; zz < z + 2; zz++) {
							if (xx != x && zz != z) {
								if (!(w.getBlock(xx, y, zz) instanceof BlockNetherrack)) {
									b = false;
								} else {
									w.setBlock(xx, y, zz, Blocks.air);
								}
							} else if (xx != x || zz != z) {
								if (!(w.getBlock(xx, y, zz) instanceof BlockGeneric)) {
									b = false;
								} else {
									w.setBlock(xx, y, zz, Blocks.air);
								}
							}
						}
					}
					if (b) {
						for (int i = 0; i < 100; i++) {
							w.spawnEntityInWorld(new EntityLightningBolt(w,
									(double) x, (double) y, (double) z));
							w.createExplosion((Entity) null, x, y, z, 5F, true);
						}
						w.spawnEntityInWorld(new EntityItem(w, (double) x,
								200D, (double) z, new ItemStack(
										Items.lava_bucket, 4)));
						w.setBlock(x, y, z, this);
					}
				} else if (p.inventory.getCurrentItem().getItemDamage() == 5) {
					boolean b = true;
					for (int xx = x - 2; xx < x; xx++) {
						for (int zz = z - 2; zz < z; zz++) {
							if (w.getBlock(xx, y, zz) instanceof BlockGeneric) {
								w.setBlock(xx, y, zz, Blocks.air);
							} else {
								b = false;
							}
						}
					}
					for (int xx = x + 2; xx > x; xx--) {
						for (int zz = z - 2; zz < z; zz++) {
							if (w.getBlock(xx, y, zz) instanceof BlockGeneric) {
								w.setBlock(xx, y, zz, Blocks.air);
							} else {
								b = false;
							}
						}
					}
					for (int xx = x + 2; xx > x; xx--) {
						for (int zz = z + 2; zz > z; zz--) {
							if (w.getBlock(xx, y, zz) instanceof BlockGeneric) {
								w.setBlock(xx, y, zz, Blocks.air);
							} else {
								b = false;
							}
						}
					}
					for (int xx = x - 2; xx < x; xx++) {
						for (int zz = z + 2; zz > z; zz--) {
							if (w.getBlock(xx, y, zz) instanceof BlockGeneric) {
								w.setBlock(xx, y, zz, Blocks.air);
							} else {
								b = false;
							}
						}
					}
					if (b) {
						for (int i = 0; i < 100; i++) {
							w.spawnEntityInWorld(new EntityLightningBolt(w,
									(double) x, (double) y, (double) z));
							w.createExplosion((Entity) null, x, y, z, 5F, true);
						}
						w.spawnEntityInWorld(new EntityItem(w, (double) x,
								200D, (double) z, new ItemStack(Items.diamond,
										2)));
						w.setBlock(x, y, z, this);
					}
				} else if (p.inventory.getCurrentItem().getItemDamage() == 6) {
					int[] values = new int[8];
					boolean b = true;
					if(!genericCircle(x, y - 1, z, w)){
						b = false;
					}
					if(!genericCircle(x, y, z, w)){
						b = false;
					}
					if(!genericCircle(x, y + 1, z, w)){
						b = false;
					}
					if(!genericCircle(x, y + 2, z, w)){
						b = false;
					}
					if(!genericCircle(x, y + 3, z, w)){
						b = false;
					}
					if(!genericCircle(x, y + 4, z, w)){
						b = false;
					}
					for (int xx = x - 1; xx < x + 2; xx++) {
						for (int zz = z - 1; zz < z + 2; zz++) {
							if (xx != x || zz != z) {
								if (w.getBlock(xx, y, zz).equals(
										Blocks.netherrack)) {
									w.setBlock(xx, y, zz, Blocks.air);
									values[0]++;
								} else if (w.getBlock(xx, y, zz).equals(
										Blocks.ice)) {
									w.setBlock(xx, y, zz, Blocks.air);
									values[1]++;
								} else if (w.getBlock(xx, y, zz).equals(
										Blocks.obsidian)) {
									w.setBlock(xx, y, zz, Blocks.air);
									values[2]++;
								} else if (w.getBlock(xx, y, zz).equals(
										Blocks.air)) {
									values[3]++;
								} else if (w.getBlock(xx, y, zz).equals(
										Blocks.quartz_block)) {
									w.setBlock(xx, y, zz, Blocks.air);
									values[4]++;
								} else if (w.getBlock(xx, y, zz).equals(
										Blocks.cobblestone)) {
									values[5]++;
								} else if (w.getBlock(xx, y, zz) instanceof BlockStorage) {
									if (w.getBlockMetadata(xx, y, zz) == 0) {
										w.setBlock(xx, y, zz, Blocks.air);
										values[6]++;
									} else if (w.getBlockMetadata(xx, y, zz) == 1) {
										w.setBlock(xx, y, zz, Blocks.air);
										values[7]++;
									}
								} else {
									b = false;
								}
							}
						}
					}
					if (b) {
						for (int i = 0; i < 100; i++) {
							w.spawnEntityInWorld(new EntityLightningBolt(w,
									(double) x, (double) y, (double) z));
							w.createExplosion((Entity) null, x, y, z, 5F, true);
						}
						ItemStack i = new ItemStack(VoidMod.elementalHammer);
						((ItemElementalHammer) i.getItem()).setElements(
								values[0], values[1], values[2], values[3],
								values[4], values[5], values[6], values[7], i);
						;
						w.spawnEntityInWorld(new EntityItem(w, (double) x,
								200D, (double) z, i));
					}
				}
			}
		} else {
			if (w.getBlockMetadata(x, y, z) == 1) {
				p.inventory.setInventorySlotContents(p.inventory.currentItem,
						new ItemStack(VoidMod.corruptAlloy));
				w.setBlockMetadataWithNotify(x, y, z, 0, 3);
				return true;
			}
		}
		return false;
	}

	public IIcon getIcon(int side, int meta) {
		return meta == 0 ? icons[0] : icons[1];
	}
	private boolean genericCircle(int x, int y, int z, World w){
		// Center-Center
		if (w.getBlock(x - 3, y, z) instanceof BlockGeneric) {
			if (w.getBlock(x, y, z - 3) instanceof BlockGeneric) {
				if (w.getBlock(x + 3, y, z) instanceof BlockGeneric) {
					if (w.getBlock(x, y, z + 3) instanceof BlockGeneric) {
						// Sides-Center
						if (w.getBlock(x - 3, y, z - 1) instanceof BlockGeneric) {
							if (w.getBlock(x - 3, y, z + 1) instanceof BlockGeneric) {
								if (w.getBlock(x + 3, y, z - 1) instanceof BlockGeneric) {
									if (w.getBlock(x + 3, y, z + 1) instanceof BlockGeneric) {
										if (w.getBlock(x - 1, y, z - 3) instanceof BlockGeneric) {
											if (w.getBlock(x + 1, y, z - 3) instanceof BlockGeneric) {
												if (w.getBlock(x - 1, y, z + 3) instanceof BlockGeneric) {
													if (w.getBlock(x + 1, y,
															z + 3) instanceof BlockGeneric) {
														// Connections
														if (w.getBlock(x - 2,
																y, z - 2) instanceof BlockGeneric) {
															if (w.getBlock(
																	x + 2, y,
																	z + 2) instanceof BlockGeneric) {
																if (w.getBlock(
																		x - 2,
																		y,
																		z + 2) instanceof BlockGeneric) {
																	if (w.getBlock(
																			x + 2,
																			y,
																			z - 2) instanceof BlockGeneric) {
																		return true;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
