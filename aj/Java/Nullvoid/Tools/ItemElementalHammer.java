package aj.Java.Nullvoid.Tools;

import java.util.List;
import java.util.Random;
import java.util.Set;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Entity.EntityBuilder;
import aj.Java.Nullvoid.Packet.PacketGearBelt;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.block.BlockGeneric;
import aj.Java.Nullvoid.block.BlockStorage;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemElementalHammer extends ItemTool {
	@SuppressWarnings("rawtypes")
	private static Set blocks = Sets.newHashSet(new Block[] {
			VoidMod.VoidFabric, VoidMod.NullOre, VoidMod.voidOre,
			VoidMod.voidReactor, VoidMod.walker, VoidMod.swordWall, VoidMod.generic});

	public ItemElementalHammer(ToolMaterial p_i45333_2_) {
		super(6F, p_i45333_2_, blocks);
		this.setCreativeTab(VoidMod.ctab);
	}
	private IIcon[] icons = new IIcon[9];
	@Override
	public void registerIcons(IIconRegister i){
		icons[0] = i.registerIcon("nullvoid:elementHammerB");
		icons[1] = i.registerIcon("nullvoid:elementHammerF");
		icons[2] = i.registerIcon("nullvoid:elementHammerI");
		icons[3] = i.registerIcon("nullvoid:elementHammerEarth");
		icons[4] = i.registerIcon("nullvoid:elementHammerA");
		icons[5] = i.registerIcon("nullvoid:elementHammerO");
		icons[6] = i.registerIcon("nullvoid:elementHammerE");
		icons[7] = i.registerIcon("nullvoid:elementHammerD");
		icons[8] = i.registerIcon("nullvoid:elementHammerL");
		this.itemIcon = icons[0];
	}
	@Override
	public IIcon getIcon(ItemStack i, int renderpass){
		int[] values = new int[8];
		values[0] = getElement(EnumElement.FIRE, i);
		values[1] = getElement(EnumElement.ICE, i);
		values[2] = getElement(EnumElement.EARTH, i);
		values[3] = getElement(EnumElement.AIR, i);
		values[4] = getElement(EnumElement.ORDER, i);
		values[5] = getElement(EnumElement.ENTROPY, i);
		values[6] = getElement(EnumElement.DARK, i);
		values[7] = getElement(EnumElement.LIGHT, i);
		boolean check = false;
		for(int bla = 0; bla < 8; bla++){
			check = false;
			for(int value = 0; value < 8; value++){
				if(values[value] != values[bla]){
					check = true;
				}
			}
			if(check){
				return icons[bla];
			}
		}
		return icons[0];
	}
	@Override
	public boolean onEntitySwing(EntityLivingBase e, ItemStack i){
		onClick(e, e.worldObj, i);
		return false;
	}
	private void onClick(EntityLivingBase p, World w, ItemStack i){
		int[] values = new int[8];
		values[0] = getElement(EnumElement.FIRE, i);
		values[1] = getElement(EnumElement.ICE, i);
		values[2] = getElement(EnumElement.EARTH, i);
		values[3] = getElement(EnumElement.AIR, i);
		values[4] = getElement(EnumElement.ORDER, i);
		values[5] = getElement(EnumElement.ENTROPY, i);
		values[6] = getElement(EnumElement.DARK, i);
		values[7] = getElement(EnumElement.LIGHT, i);
		boolean check = false;
		int on = -1;
		for(int bla = 0; bla < 8; bla++){
			for(int value = 0; value < 8; value++){
				if(values[value] != values[bla]){
					check = true;
					on = bla;
				}
			}
		}
		if(check){
			switch(on){
				case 0:
					p.setFire(10);
				return;
			case 1:
				for(int x = (int)p.posX - 2; x < (int)p.posX + 1; x++){
					for(int y = (int)p.posY; y < (int)p.posY + 2; y++){
						for(int z = (int)p.posZ - 2; z < (int)p.posZ + 1; z++){
							w.setBlock(x, y, z, Blocks.ice);
						}
					}
				}
				return;
			case 2:
				for(int x = (int)p.posX - 1; x < (int)p.posX + 2; x++){
					for(int y = (int)p.posY + 2; y < (int)p.posY + 4; y++){
						for(int z = (int)p.posZ - 1; z < (int)p.posZ + 2; z++){
							w.setBlock(x, y, z, Blocks.gravel);
						}
					}
				}
				return;
			case 3:
				PacketHandler.INSTANCE.sendToServer(new PacketGearBelt(999F));
				p.motionY = 999D;
				return;
			case 4:
				//TODO: ORDER EFFECT
				return;
			case 5:
				p.addPotionEffect(new PotionEffect(VoidMod.PotIDDiss, 100000, 100000, true));
				p.worldObj.createExplosion(p, p.posX, p.posY, p.posZ, 9999F, false);
				return;
			case 6:
				p.addPotionEffect(new PotionEffect(Potion.blindness.id, 100000, 100000, true));
				return;
			case 7:
				//TODO: LIGHT EFFECT
				return;
			}
		}
	}
	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p){
		onClick(p, w, i);
		return i;
	}
	public void setElements(int fire, int ice, int earth, int air, int order,
			int entropy, int dark, int light, ItemStack i) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("fire", fire);
		tag.setInteger("ice", ice);
		tag.setInteger("earth", earth);
		tag.setInteger("air", air);
		tag.setInteger("order", order);
		tag.setInteger("entropy", entropy);
		tag.setInteger("dark", dark);
		tag.setInteger("light", light);
		i.stackTagCompound = tag;
	}

	public int getElement(EnumElement e, ItemStack i) {
		NBTTagCompound tag = i.stackTagCompound;
		Random r = new Random();
		if (tag == null) {
			setElements(r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), i);
		}
		if (tag != null) {
			switch (e) {
			case FIRE:
				return tag.getInteger("fire");
			case ICE:
				return tag.getInteger("ice");
			case EARTH:
				return tag.getInteger("earth");
			case AIR:
				return tag.getInteger("air");
			case ORDER:
				return tag.getInteger("order");
			case ENTROPY:
				return tag.getInteger("entropy");
			case DARK:
				return tag.getInteger("dark");
			case LIGHT:
				return tag.getInteger("light");
			default:
				return r.nextInt();
			}
		}
		else{
			return r.nextInt();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack i, EntityPlayer e,
			@SuppressWarnings("rawtypes") List l, boolean b) {
		l.add(new ChatComponentText("FIRE LEVEL: "
				+ getElement(EnumElement.FIRE, i)).setChatStyle(new ChatStyle()
				.setColor(EnumChatFormatting.RED)).getFormattedText());
		l.add(new ChatComponentText("ICE LEVEL: "
				+ getElement(EnumElement.ICE, i)).setChatStyle(new ChatStyle()
				.setColor(EnumChatFormatting.AQUA)).getFormattedText());
		l.add(new ChatComponentText("EARTH LEVEL: "
				+ getElement(EnumElement.EARTH, i))
				.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).getFormattedText());
		l.add(new ChatComponentText("AIR LEVEL: "
				+ getElement(EnumElement.AIR, i)).setChatStyle(new ChatStyle()
				.setColor(EnumChatFormatting.YELLOW)).getFormattedText());
		l.add(new ChatComponentText("ORDER LEVEL: "
				+ getElement(EnumElement.ORDER, i))
				.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY)).getFormattedText());
		l.add(new ChatComponentText("ENTROPY LEVEL: "
				+ getElement(EnumElement.ENTROPY, i))
				.setChatStyle(new ChatStyle()
						.setColor(EnumChatFormatting.DARK_GRAY)).getFormattedText());
		l.add(new ChatComponentText("DARKNESS LEVEL: "
				+ getElement(EnumElement.DARK, i)).setChatStyle(new ChatStyle()
				.setBold(true)).getFormattedText());
		l.add(new ChatComponentText("BRIGHTNESS LEVEL: "
				+ getElement(EnumElement.LIGHT, i))
				.setChatStyle(new ChatStyle()
						.setColor(EnumChatFormatting.WHITE)).getFormattedText());
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem item) {
		World w = item.worldObj;
		int x = (int) item.posX;
		int y = (int) item.posY;
		int z = (int) item.posZ;
		if(w.getBlock(x, y - 1, z).equals(Blocks.lava)){
			boolean b = true;
			for(int i = x - 1; i < x + 2; x++){
				for(int ii = z - 1; ii < z + 2; z++){
					if(i != x && ii != z){
						if(!w.getBlock(i, y, ii).equals(Blocks.iron_block)){
							b = false;
						}
					}
				}
			}
			if(b){
				for(int i = x - 1; i < x + 2; x++){
					for(int ii = z - 1; ii < z + 2; z++){
						if(i != x && ii != z){
							if(!w.getBlock(i, y + 1, ii).equals(Blocks.iron_bars)){
								b = false;
							}
						}
					}
				}
				if(b){
					for(int i = x - 1; i < x + 2; x++){
						for(int ii = z - 1; ii < z + 2; z++){
							if(!w.getBlock(i, y - 1, ii).equals(Blocks.iron_block)){
								b = false;
							}
						}
					}
					if(b){
						//Thing 1
						if(w.getBlock(x - 2, y - 1, z) instanceof BlockGeneric){
							if(w.getBlock(x - 3, y - 1, z) instanceof BlockGeneric){
								if(w.getBlock(x - 3, y, z) instanceof BlockGeneric){
									if(w.getBlock(x - 3, y + 1, z) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 0){
										if(w.getBlock(x - 3, y + 2, z) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 1){
											if(w.getBlock(x - 2, y + 2, z) instanceof BlockGeneric){
												//Thing 2
												if(w.getBlock(x + 2, y - 1, z) instanceof BlockGeneric){
													if(w.getBlock(x + 3, y - 1, z) instanceof BlockGeneric){
														if(w.getBlock(x + 3, y, z) instanceof BlockGeneric){
															if(w.getBlock(x + 3, y + 1, z) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 0){
																if(w.getBlock(x + 3, y + 2, z) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 1){
																	if(w.getBlock(x + 2, y + 2, z) instanceof BlockGeneric){
																		//Thing 3
																		if(w.getBlock(x, y - 1, z - 2) instanceof BlockGeneric){
																			if(w.getBlock(x, y - 1, z - 3) instanceof BlockGeneric){
																				if(w.getBlock(x, y, z - 3) instanceof BlockGeneric){
																					if(w.getBlock(x, y + 1, z - 3) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 0){
																						if(w.getBlock(x, y + 2, z - 3) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 1){
																							if(w.getBlock(x, y + 2, z - 2) instanceof BlockGeneric){
																								//Thing 4
																								if(w.getBlock(x, y - 1, z + 2) instanceof BlockGeneric){
																									if(w.getBlock(x, y - 1, z + 3) instanceof BlockGeneric){
																										if(w.getBlock(x, y, z + 3) instanceof BlockGeneric){
																											if(w.getBlock(x, y + 1, z + 3) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 0){
																												if(w.getBlock(x, y + 2, z + 3) instanceof BlockStorage && w.getBlockMetadata(x - 3, y + 1, z) == 1){
																													if(w.getBlock(x, y + 2, z + 2) instanceof BlockGeneric){
																														item.worldObj
																															.spawnEntityInWorld(new EntityLightningBolt(
																																item.worldObj, item.posX, item.posY,
																																item.posZ));
																														item.worldObj.createExplosion((Entity) null, item.posX,
																																item.posY, item.posZ, 10F, true);
																														item.worldObj.spawnEntityInWorld(new EntityBuilder(
																																item.worldObj, item.posX, 200D, item.posZ));
																														item.setDead();
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

	public static enum EnumElement {
		FIRE(), ICE(), EARTH(), AIR(), ORDER(), ENTROPY(), DARK(), LIGHT();
	}
}
