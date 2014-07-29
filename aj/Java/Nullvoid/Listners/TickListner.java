package aj.Java.Nullvoid.Listners;

import java.awt.Color;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Dimention.TeleporterNullVoid;
import aj.Java.Nullvoid.Effects.Effects;
import aj.Java.Nullvoid.Entity.EntityGlitch;
import aj.Java.Nullvoid.Entity.IVoidWalker;
import aj.Java.Nullvoid.GUI.GUIDissolving;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketLighting;
import aj.Java.Nullvoid.Potion.DissolvingRender;
import aj.Java.Nullvoid.Tools.ItemBaneOfDarkness;
import aj.Java.Nullvoid.block.BlockGeneric;
import aj.Java.Nullvoid.block.BlockGlitchFrame;
import aj.Java.Nullvoid.block.BlockStorage;
import aj.Java.Nullvoid.block.BlockVoidFabric;
import aj.Java.Nullvoid.gen.VoidModOreGenerator;
import aj.Java.Nullvoid.gen.VoidModStructureGenerator;
import baubles.api.BaublesApi;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TickListner {
	@SideOnly(Side.CLIENT)
	private static Minecraft m;
	static {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			m = Minecraft.getMinecraft();
		}
	}
	private Random r = new Random();
	public static float brightness = 0F;
	DissolvingRender[] dissolve;
	GUIDissolving dissolving;
	int dissolvetimer = 0;
	/**
	@SubscribeEvent
	public void worldGen(OreGenEvent.Post event) {
		int[] xa = new int[16];
		int[] za = new int[16];
		for (int i = 0; i < 16; i++) {
			xa[i] = (event.worldX * 16) + i;
			za[i] = (event.worldZ * 16) + i;
		}
		for (int x : xa) {
			for (int z : za) {
				if (event.world.getBiomeGenForCoords(x, z) instanceof BiomeGenNull) {
					System.out.println(xa[0] + ", " + za[0]);
					for (int y = 1; y < 256; y++) {
						if (event.world.getBlock(x, y, z).equals(Blocks.stone)) {
							if (event.rand.nextInt(2) != 0) {
								event.world.setBlock(x, y, z,
										VoidMod.VoidFabric);
							}
						} else if (event.world.getBlock(x, y, z).equals(
								Blocks.bedrock)) {
							if (event.rand.nextInt(5) == 2) {
								event.world.setBlock(x, y, z,
										VoidMod.VoidFabric);
							}
						}
					}
				}
			}
		}
	}
	*/
	Integer i = null;
	
	public void chunkLoad(ChunkWatchEvent.Watch event){
		if(!Utils.hasGen.get(event.chunk) && VoidMod.shouldRetro && event.player.dimension == 0){
			new VoidModOreGenerator().generate(new Random(), event.chunk.chunkXPos, event.chunk.chunkZPos, event.player.worldObj, null, null);
		}
		if(!Utils.hasStruct.get(event.chunk) && VoidMod.shouldRetro && event.player.dimension == 0){
			new VoidModStructureGenerator().generate(new Random(), event.chunk.chunkXPos, event.chunk.chunkZPos, event.player.worldObj, null, null);
		}
	}
	
	@SubscribeEvent
	public void playerChat(ServerChatEvent event) {
		System.out.println(event.message);
		if (event.message
				.equals("Qui exterminabat, et hodie victor. Non gratia datur. Non remansit integra. Vade, et interfice regni.")) {
			boolean b = false;
			Integer[] coords = new Integer[3];
			for (int x = (int) (event.player.posX) - 3; x < (int) (event.player.posX) + 4; x++) {
				for (int y = (int) (event.player.posY) - 3; y < (int) (event.player.posY) + 4; y++) {
					for (int z = (int) (event.player.posZ) - 3; z < (int) (event.player.posZ) + 4; z++) {
						if (event.player.worldObj.getBlock(x, y, z) instanceof BlockGlitchFrame) {
							if (event.player.worldObj.getBlockMetadata(x, y, z) == 1) {
								coords[0] = x;
								coords[1] = y;
								coords[2] = z;
								b = true;
							}
						}
					}
				}
			}
			if (b) {
				if (hasCircle(coords[0], coords[1], coords[2],
						event.player.worldObj)) {
					if(hasShell(coords[0], coords[1], coords[2], event.player.worldObj)){
						if(event.player.dimension == VoidMod.NullVoidDimID && coords[0] == 0 && coords[2] == 0){
							event.component = new ChatComponentTranslation(
									"<"
											+ event.username
											+ "> .won uoy evas nac cigam oN .gnimoc si hctilG ehT");
							event.component.setChatStyle(new ChatStyle()
							.setColor(EnumChatFormatting.DARK_PURPLE));
							for (int i = 0; i < 10; i++) {
								event.player.worldObj
								.spawnEntityInWorld(new EntityLightningBolt(
										event.player.worldObj,
										(double) coords[0], (double) coords[1],
										(double) coords[2]));
							}
							event.player.worldObj.createExplosion((Entity) null,
									(double) coords[0], (double) coords[1],
									(double) coords[2], 4F, false);
							event.player.worldObj.setBlock(coords[0], coords[1],
									coords[2], Blocks.air);
							event.player.addStat(VoidMod.summonGlitch, 1);
							EntityGlitch ent = new EntityGlitch(
									event.player.worldObj, (double) coords[0],
									(double) coords[1], (double) coords[2]);
							ent.setHealth(ent.getMaxHealth());
							event.player.worldObj.spawnEntityInWorld(ent);
						}
						else{
							if(BaublesApi.getBaubles(event.player).getStackInSlot(0) != null && BaublesApi.getBaubles(event.player)
									.getStackInSlot(0).getItem()
									.equals(VoidMod.glitchAmulet)){
								event.player
								.addChatComponentMessage(new ChatComponentText(
										"<G-----> Glitchiest spot").setChatStyle(new ChatStyle()
										.setColor(EnumChatFormatting.DARK_PURPLE)));
							}
							else{
								event.player
								.addChatComponentMessage(new ChatComponentText(
										"<------> Glitchiest spot").setChatStyle(new ChatStyle()
										.setColor(EnumChatFormatting.DARK_PURPLE).setObfuscated(true)));
							}
						}
					}
					else{
						if(BaublesApi.getBaubles(event.player).getStackInSlot(0) != null && BaublesApi.getBaubles(event.player)
								.getStackInSlot(0).getItem()
								.equals(VoidMod.glitchAmulet)){
							event.player
							.addChatComponentMessage(new ChatComponentText(
									"<Gli--h> https://github.com/Nimbleguy/The-Void/blob/master/aj/Java/Nullvoid/Entity/EntityGlitch.java").setChatStyle(new ChatStyle()
									.setColor(EnumChatFormatting.DARK_PURPLE)));
						}
						else{
							event.player
							.addChatComponentMessage(new ChatComponentText(
									"<G----h> https://github.com/Nimbleguy/The-Void/blob/master/aj/Java/Nullvoid/Entity/EntityGlitch.java").setChatStyle(new ChatStyle()
									.setColor(EnumChatFormatting.DARK_PURPLE).setObfuscated(true)));
						}
					}

				} else {
					if (BaublesApi.getBaubles(event.player).getStackInSlot(0) != null) {
						if (BaublesApi.getBaubles(event.player)
								.getStackInSlot(0).getItem()
								.equals(VoidMod.glitchAmulet)) {
							event.player
							.addChatComponentMessage(new ChatComponentText(
									"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.").setChatStyle(new ChatStyle()
									.setColor(EnumChatFormatting.DARK_PURPLE)));
						} else {
							event.player
							.addChatComponentMessage(new ChatComponentText(
									"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.")
							.setChatStyle(new ChatStyle()
							.setObfuscated(true)
							.setColor(
									EnumChatFormatting.DARK_PURPLE)));
						}
					} else {
						event.player
						.addChatComponentMessage(new ChatComponentText(
								"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.")
						.setChatStyle(new ChatStyle()
						.setObfuscated(true)
						.setColor(
								EnumChatFormatting.DARK_PURPLE)));
					}
				}
			} else {
				if (BaublesApi.getBaubles(event.player).getStackInSlot(0) != null) {
					if (BaublesApi.getBaubles(event.player).getStackInSlot(0)
							.getItem().equals(VoidMod.glitchAmulet)) {
						event.player
						.addChatComponentMessage(new ChatComponentText(
								"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.").setChatStyle(new ChatStyle()
								.setColor(EnumChatFormatting.DARK_PURPLE)));
					} else {
						event.player
						.addChatComponentMessage(new ChatComponentText(
								"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.")
						.setChatStyle(new ChatStyle()
						.setObfuscated(true)
						.setColor(
								EnumChatFormatting.DARK_PURPLE)));
					}
				} else {
					event.player
					.addChatComponentMessage(new ChatComponentText(
							"<Gh> Circ--___3_Bl----_G------_A---y_In_Ce-ter_F--me.")
					.setChatStyle(new ChatStyle()
					.setObfuscated(true)
					.setColor(
							EnumChatFormatting.DARK_PURPLE)));
				}
			}
		}
	}
	private boolean hasShell(int x, int y, int z, World w){
		//Four corner generic objects
		if(w.getBlock(x - 1, y, z - 1) instanceof BlockGeneric){
			if(w.getBlock(x + 1, y, z - 1) instanceof BlockGeneric){
				if(w.getBlock(x - 1, y, z + 1) instanceof BlockGeneric){
					if(w.getBlock(x + 1, y, z + 1) instanceof BlockGeneric){
						System.out.println("corner");
						//Four lower generic objects
						if(w.getBlock(x - 1, y - 1, z) instanceof BlockGeneric){
							if(w.getBlock(x + 1, y - 1, z) instanceof BlockGeneric){
								if(w.getBlock(x, y - 1, z - 1) instanceof BlockGeneric){
									if(w.getBlock(x, y - 1, z + 1) instanceof BlockGeneric){
										System.out.println("lower");
										//Tiny ring of Void Fabric
										//First fourth
										if(w.getBlock(x - 2, y - 1, z) instanceof BlockVoidFabric){
											if(w.getBlock(x - 2, y - 1, z - 1) instanceof BlockVoidFabric){
												if(w.getBlock(x - 2, y - 1, z + 1) instanceof BlockVoidFabric){
													System.out.println("fab1");
													//Second fourth
													if(w.getBlock(x + 2, y - 1, z - 1) instanceof BlockVoidFabric){
														if(w.getBlock(x + 2, y - 1, z + 1) instanceof BlockVoidFabric){
															if(w.getBlock(x + 2, y - 1, z) instanceof BlockVoidFabric){
																System.out.println("fab2");
																//Third fourth
																if(w.getBlock(x, y - 1, z - 2) instanceof BlockVoidFabric){
																	if(w.getBlock(x - 1, y - 1, z - 2) instanceof BlockVoidFabric){
																		if(w.getBlock(x + 1, y - 1, z - 2) instanceof BlockVoidFabric){
																			System.out.println("fab3");
																			//Last fourth
																			if(w.getBlock(x - 1, y - 1, z + 2) instanceof BlockVoidFabric){
																				if(w.getBlock(x, y - 1, z + 2) instanceof BlockVoidFabric){
																					if(w.getBlock(x + 1, y - 1, z + 2) instanceof BlockVoidFabric){
																						System.out.println("fab4");
																						//Generic ring
																						// Center-Center
																						if (w.getBlock(x - 3, y - 1, z) instanceof BlockGeneric) {
																							if (w.getBlock(x, y - 1, z - 3) instanceof BlockGeneric) {
																								if (w.getBlock(x + 3, y - 1, z) instanceof BlockGeneric) {
																									if (w.getBlock(x, y - 1, z + 3) instanceof BlockGeneric) {
																										// Sides-Center
																										if (w.getBlock(x - 3, y - 1, z - 1) instanceof BlockGeneric) {
																											if (w.getBlock(x - 3, y - 1, z + 1) instanceof BlockGeneric) {
																												if (w.getBlock(x + 3, y - 1, z - 1) instanceof BlockGeneric) {
																													if (w.getBlock(x + 3, y - 1, z + 1) instanceof BlockGeneric) {
																														if (w.getBlock(x - 1, y - 1, z - 3) instanceof BlockGeneric) {
																															if (w.getBlock(x + 1, y - 1, z - 3) instanceof BlockGeneric) {
																																if (w.getBlock(x - 1, y - 1, z + 3) instanceof BlockGeneric) {
																																	if (w.getBlock(x + 1, y - 1,
																																			z + 3) instanceof BlockGeneric) {
																																		// Connections
																																		if (w.getBlock(x - 2,
																																				y - 1, z - 2) instanceof BlockGeneric) {
																																			if (w.getBlock(
																																					x + 2, y - 1,
																																					z + 2) instanceof BlockGeneric) {
																																				if (w.getBlock(
																																						x - 2,
																																						y - 1,
																																						z + 2) instanceof BlockGeneric) {
																																					if (w.getBlock(
																																							x + 2,
																																							y - 1,
																																							z - 2) instanceof BlockGeneric) {
																																						System.out.println("ring");
																																						//Pillars
																																						//Pillar 1
																																						if(w.getBlock(x - 4, y, z) instanceof BlockStorage){
																																							if(w.getBlockMetadata(x - 4, y, z) == 1){
																																								if(w.getBlock(x - 4, y + 1, z) instanceof BlockStorage){
																																									if(w.getBlockMetadata(x - 4, y + 1, z) == 0){
																																										if(w.getBlock(x - 4, y + 2, z) instanceof BlockGeneric){
																																											if(w.getBlock(x - 3, y + 2, z) instanceof BlockGeneric){
																																												if(w.getBlock(x - 3, y + 3, z) instanceof BlockGeneric){
																																													if(w.getBlock(x - 2, y + 3, z) instanceof BlockGeneric){
																																														//Pillar 2
																																														if(w.getBlock(x + 4, y, z) instanceof BlockStorage){
																																															if(w.getBlockMetadata(x + 4, y, z) == 1){
																																																if(w.getBlock(x + 4, y + 1, z) instanceof BlockStorage){
																																																	if(w.getBlockMetadata(x + 4, y + 1, z) == 0){
																																																		if(w.getBlock(x + 4, y + 2, z) instanceof BlockGeneric){
																																																			if(w.getBlock(x + 3, y + 2, z) instanceof BlockGeneric){
																																																				if(w.getBlock(x + 3, y + 3, z) instanceof BlockGeneric){
																																																					if(w.getBlock(x + 2, y + 3, z) instanceof BlockGeneric){
																																																						//Pillar 3
																																																						if(w.getBlock(x, y, z + 4) instanceof BlockStorage){
																																																							if(w.getBlockMetadata(x, y, z + 4) == 1){
																																																								if(w.getBlock(x, y + 1, z + 4) instanceof BlockStorage){
																																																									if(w.getBlockMetadata(x, y + 1, z + 4) == 0){
																																																										if(w.getBlock(x, y + 2, z + 4) instanceof BlockGeneric){
																																																											if(w.getBlock(x, y + 2, z + 3) instanceof BlockGeneric){
																																																												if(w.getBlock(x, y + 3, z + 3) instanceof BlockGeneric){
																																																													if(w.getBlock(x, y + 3, z + 2) instanceof BlockGeneric){
																																																														//Pillar 4
																																																														if(w.getBlock(x, y, z - 4) instanceof BlockStorage){
																																																															if(w.getBlockMetadata(x, y, z - 4) == 1){
																																																																if(w.getBlock(x, y + 1, z - 4) instanceof BlockStorage){
																																																																	if(w.getBlockMetadata(x, y + 1, z - 4) == 0){
																																																																		if(w.getBlock(x, y + 2, z - 4) instanceof BlockGeneric){
																																																																			if(w.getBlock(x, y + 2, z - 3) instanceof BlockGeneric){
																																																																				if(w.getBlock(x, y + 3, z - 3) instanceof BlockGeneric){
																																																																					if(w.getBlock(x, y + 3, z - 2) instanceof BlockGeneric){
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
	private boolean hasCircle(int x, int y, int z, World w) {
		// Center-Center
		if (w.getBlock(x - 3, y, z) instanceof BlockTorch) {
			if (w.getBlock(x, y, z - 3) instanceof BlockTorch) {
				if (w.getBlock(x + 3, y, z) instanceof BlockTorch) {
					if (w.getBlock(x, y, z + 3) instanceof BlockTorch) {
						// Sides-Center
						if (w.getBlock(x - 3, y, z - 1) instanceof BlockTorch) {
							if (w.getBlock(x - 3, y, z + 1) instanceof BlockTorch) {
								if (w.getBlock(x + 3, y, z - 1) instanceof BlockTorch) {
									if (w.getBlock(x + 3, y, z + 1) instanceof BlockTorch) {
										if (w.getBlock(x - 1, y, z - 3) instanceof BlockTorch) {
											if (w.getBlock(x + 1, y, z - 3) instanceof BlockTorch) {
												if (w.getBlock(x - 1, y, z + 3) instanceof BlockTorch) {
													if (w.getBlock(x + 1, y,
															z + 3) instanceof BlockTorch) {
														// Connections
														if (w.getBlock(x - 2,
																y, z - 2) instanceof BlockTorch) {
															if (w.getBlock(
																	x + 2, y,
																	z + 2) instanceof BlockTorch) {
																if (w.getBlock(
																		x - 2,
																		y,
																		z + 2) instanceof BlockTorch) {
																	if (w.getBlock(
																			x + 2,
																			y,
																			z - 2) instanceof BlockTorch) {
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

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (event.phase.equals(Phase.END)) {
			if (event.side.isServer()) {
				EntityPlayer p = event.player;
				if (p != null) {
					// System.out.println("Got a ticked player");
					// System.out.println(p.posY);
					if (p.posY < 0.01D) {
						System.out.println("In the void!");
						if (p.dimension == VoidMod.NullVoidDimID) {
							((EntityPlayerMP) p).playerNetServerHandler
							.setPlayerLocation(p.posX, 512D, p.posZ,
									p.rotationYaw, p.rotationPitch);
						}
						p.addStat(VoidMod.fallVoid, 1);
					}
					if (p.dimension == VoidMod.NullVoidDimID) {
						if (p.inventory.getCurrentItem() == null) {
							p.clearActivePotions();
						} else if (p.inventory.getCurrentItem().getItem() instanceof ItemBaneOfDarkness) {
							p.addPotionEffect(new PotionEffect(
									Potion.nightVision.id, 10));
						} else {
							p.clearActivePotions();
						}
						if (!Utils.getInVoid(p)) {
							MinecraftServer
							.getServer()
							.getConfigurationManager()
							.transferPlayerToDimension(
									(EntityPlayerMP) p,
									0,
									new TeleporterNullVoid(
											MinecraftServer
											.getServer()
											.worldServerForDimension(
													0)));
							p.addChatComponentMessage(new ChatComponentText(
									"The Void Does Not Like Your Cheats. GET. OUT. NOW."));
						}
					} else {
						if (Utils.getInVoid(event.player)) {
							PacketHandler.INSTANCE.sendTo(new PacketLighting(
									Utils.getBright(event.player), true),
									(EntityPlayerMP) event.player);
							brightness = Utils.getBright(event.player);
							Utils.setEffects(event.player, new Effects());
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void worldTick(TickEvent.WorldTickEvent event) {
		if (event.side.isServer()) {
			if (event.phase.equals(Phase.END)) {
				World w = event.world;
				@SuppressWarnings("unchecked")
				List<Entity> l = (List<Entity>) w.loadedEntityList;
				try {
					for (Entity e : l) {
						if (e.dimension == VoidMod.NullVoidDimID) {
							if (!(e instanceof IVoidWalker)
									&& (e instanceof EntityLiving && !(e instanceof EntityPlayer))) {
								e.setDead();
							}
						}
					}
				} catch (ConcurrentModificationException e) {
					return;
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase.equals(Phase.END)) {
			if (m.thePlayer != null) {
				if (brightness == -1F || brightness == 1F) {
					m.gameSettings.gammaSetting = brightness;
				} else if (brightness != 100000F) {
					m.gameSettings.gammaSetting = brightness;
					brightness = 100000F;
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void texturePreStitch(TextureStitchEvent.Pre event) {
		if (event.map.getTextureType() == 0) {
			event.map.setTextureEntry("nullvoid:nullOre", VoidMod.texNullOre);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		if (VoidMod.glitchAmulet != null && m.thePlayer != null) {
			if (BaublesApi.getBaubles(m.thePlayer).getStackInSlot(0) != null) {
				if (!BaublesApi.getBaubles(m.thePlayer).getStackInSlot(0)
						.isItemEqual(new ItemStack(VoidMod.glitchAmulet))) {
					glitch();
				}
			} else {
				glitch();
			}
		}
		if(m.thePlayer != null){
			if(m.thePlayer.getActivePotionEffect(VoidMod.dissolving) != null){
				if(dissolve == null){
					dissolve = new DissolvingRender[10];
				}
				if(dissolving == null){
					dissolving = new GUIDissolving();
				}
				if(dissolvetimer != 0){
					dissolvetimer--;
				}
				loop:
					for(int i = 0; i < 10; i++){
						if(dissolve[i] == null){
							if(dissolvetimer == 0){
								dissolvetimer = 4000;
								Random r = new Random();
								int w = r.nextInt(m.displayWidth - m.displayWidth/4);
								int h = r.nextInt(m.displayHeight - m.displayHeight/4);
								int wa = r.nextInt(m.displayWidth/4);
								int ha = r.nextInt(m.displayHeight/4);
								dissolve[i] = new DissolvingRender(w, h, wa, ha);
								break loop;
							}
						}
						else{
							dissolve[i].render(dissolving);
						}
					}
			}
			else{
				dissolving = null;
				dissolve = null;
			}
		}
	}

	@SubscribeEvent
	public void crafted(ItemCraftedEvent event) {
		if (event.crafting.getItem().equals(VoidMod.nullGoggles)) {
			event.player.addStat(VoidMod.craftGoggle, 1);
		}
	}

	@SubscribeEvent
	public void onConfigChanged(
			ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals("nullvoid"))
			VoidMod.config();
	}

	/*
	 * @SubscribeEvent public void onKeyInput(KeyInputEvent event) { if
	 * (FMLClientHandler.instance().getClient().inGameHasFocus) { if
	 * (Utils.specialKey.isPressed()) { EntityPlayer arg1 =
	 * Minecraft.getMinecraft().thePlayer; Vec3 look = arg1.getLookVec();
	 * PacketFireball b = new PacketFireball(arg1.posX + look.xCoord * 5,
	 * arg1.posY + look.yCoord * 5, arg1.posZ + look.zCoord * 5, look.xCoord *
	 * 0.1, look.yCoord * 0.1, look.zCoord * 0.1);
	 * PacketHandler.INSTANCE.sendToServer(b); EntityLargeFireball fireball2 =
	 * new EntityLargeFireball(arg1.worldObj, arg1, 1, 1, 1);
	 * fireball2.setPosition( arg1.posX + look.xCoord * 5, arg1.posY +
	 * look.yCoord * 5, arg1.posZ + look.zCoord * 5); fireball2.accelerationX =
	 * look.xCoord * 0.1; fireball2.accelerationY = look.yCoord * 0.1;
	 * fireball2.accelerationZ = look.zCoord * 0.1;
	 * arg1.worldObj.spawnEntityInWorld(fireball2); } } }
	 */
	private String getLetter() {
		return String.valueOf(Character.toChars(r.nextInt(93) + 33));
	}

	private void glitch() {
		if (m.thePlayer.dimension == VoidMod.NullVoidDimID) {
			if (m.thePlayer.posX > 0) {
				if (m.thePlayer.posZ > 0) {
					if (m.thePlayer.posX > m.thePlayer.posZ) {
						for (int i = 0; i < (10000 - (m.thePlayer.posX * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					} else {
						for (int i = 0; i < (10000 - (m.thePlayer.posZ * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					}
				} else {
					if ((m.thePlayer.posX) > Math.abs(m.thePlayer.posZ)) {
						for (int i = 0; i < (10000 - (m.thePlayer.posX * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					} else {
						for (int i = 0; i < (10000 - Math
								.abs(m.thePlayer.posZ * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					}
				}
			} else {
				if (m.thePlayer.posZ > 0) {
					if (Math.abs(m.thePlayer.posX) > m.thePlayer.posZ) {
						for (int i = 0; i < (10000 - Math
								.abs(m.thePlayer.posX * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					} else {
						for (int i = 0; i < (10000 - (m.thePlayer.posZ * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					}
				} else {
					if (Math.abs(m.thePlayer.posX) > Math.abs(m.thePlayer.posZ)) {
						for (int i = 0; i < (10000 - Math
								.abs(m.thePlayer.posX * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					} else {
						for (int i = 0; i < (10000 - Math
								.abs(m.thePlayer.posZ * 50)); i++) {
							m.fontRenderer.drawString(
									getLetter(),
									r.nextInt(m.displayWidth),
									r.nextInt(m.displayHeight),
									new Color(r.nextFloat(), r.nextFloat(), r
											.nextFloat()).getRGB());
						}
					}
				}
			}
		}
	}
}