package aj.Java.Nullvoid.tileentity;

import java.io.IOException;

import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Dimention.TeleporterNullVoid;
import aj.Java.Nullvoid.Effects.Effect;
import aj.Java.Nullvoid.Effects.Effects;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketLighting;
import aj.Java.Nullvoid.Tools.ItemBaneOfDarkness;
import aj.Java.Nullvoid.block.BlockTransparent;
import aj.Java.Nullvoid.block.BlockVoidFabric;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityVoidReactor extends TileEntity implements IFluidHandler, IChunkLoader {
	public boolean isStructure = false;
	TileEntity[] upgrades = new TileEntity[4];
	private int walkerUpgrades = 0;
	private int maxNull = 0;
	private ItemStack nullCrystals;
	private FluidTank tank;
	private String playername = "";
	private int itimer = 0;
	private int timer = 0;
	private boolean Timer = false;
	private boolean ITimer = false;

	public TileEntityVoidReactor() {
		tank = new FluidTank(1000);
	}

	@Override
	public void updateEntity() {
		if (nullCrystals != null) {
			if (nullCrystals.stackSize == 0) {
				nullCrystals = null;
			}
		}
		// System.out.println("Check");
		boolean before = isStructure;
		checkStructure();
		for(int x = xCoord - 1; x < xCoord + 2; x++){
			for(int y = yCoord - 2; y < yCoord + 1; y++){
				for(int z = zCoord - 1; z < zCoord + 2; z++){
					if(worldObj.getBlock(x, y, z) instanceof BlockVoidFabric && isStructure){
						worldObj.setBlock(x, y, z, VoidMod.transparent);
					}
					else if(worldObj.getBlock(x, y, z) instanceof BlockTransparent && !isStructure){
						worldObj.setBlock(x, y, z, VoidMod.VoidFabric);
					}
				}
			}
		}
		if (isStructure) {
			int i = 0;
			for (TileEntity t : upgrades) {
				if (t instanceof TileEntityVoidWalker) {
					i++;
				}
			}
			if (i > walkerUpgrades) {
				walkerUpgrades = i;
				maxNull = walkerUpgrades * 256 * 3;
			}
			// Teleporting code
			if (worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0) {
				if (playername.equals("")) {
					EntityPlayer player = worldObj.getClosestPlayer(xCoord,
							yCoord, zCoord, -1);
					if (player != null) {
						if (player instanceof EntityPlayerMP) {
							EntityPlayerMP p = (EntityPlayerMP) player;
							if (nullCrystals != null) {
								if (tank.getFluidAmount() == 1000
										&& nullCrystals.stackSize > 0) {
									tank.drain(1000, true);
									nullCrystals.stackSize--;
									playername = p.getCommandSenderName();
									ITimer = true;
									itimer = 0;
									timer = 0;
									Timer = true;
									// Upgrades
									boolean light = false;
									Effects e = new Effects();
									for (TileEntity t : upgrades) {
										if (t instanceof TileEntitySwordWall) {
												if(((TileEntitySwordWall)t).sword != null){
													if (((TileEntitySwordWall) t).sword
															.getItem() instanceof ItemBaneOfDarkness) {
														e.addEffect(Effect.LIGHT);
														light = true;
													}
												}
										}
									}
									if(light){
										PacketHandler.INSTANCE.sendTo(new PacketLighting(1F), p);
									}
									else{
										PacketHandler.INSTANCE.sendTo(new PacketLighting(-1F), p);
									}
									// Teleport
									p.mcServer
											.getConfigurationManager()
											.transferPlayerToDimension(
													p,
													VoidMod.NullVoidDimID,
													new TeleporterNullVoid(
															p.mcServer
																	.worldServerForDimension(VoidMod.NullVoidDimID)));
									//Set In Void
									Utils.setInVoid(player, true);
									Utils.setEffects(player, e);
								} else {
									p.addChatComponentMessage(new ChatComponentText(
											"Not Enough Molten Flux!"));
								}
							} else {
								p.addChatComponentMessage(new ChatComponentText(
										"No Null Crystals!"));
							}
						}
					}
				}  
			}
			// Checking code
			if ((Timer && timer >= 750) || (ITimer && itimer >= 500)) {
				if (ITimer) {
					// ITimer is over
					if (nullCrystals != null) {
						// Still crystals
						itimer = 0;
						timer = 0;
						ITimer = false;
						Timer = true;
					} else {
						// Teleport
						if (worldObj.getPlayerEntityByName(playername) instanceof EntityPlayerMP) {
							MinecraftServer
									.getServer()
									.getConfigurationManager()
									.transferPlayerToDimension(
											(EntityPlayerMP) worldObj
													.getPlayerEntityByName(playername),
											0,
											new TeleporterNullVoid(
													MinecraftServer
															.getServer()
															.worldServerForDimension(
																	0)));
							EntityPlayer p = worldObj.getPlayerEntityByName(playername);
							Utils.setEffects(p, new Effects());
							Utils.setInVoid(p, false);
							PacketHandler.INSTANCE.sendTo(new PacketLighting(Utils.getBright(p), true), (EntityPlayerMP)worldObj.getPlayerEntityByName(playername));
							worldObj.getPlayerEntityByName(playername)
									.addChatComponentMessage(
											new ChatComponentText(
													"Out of Null Crystals! Ejecting from Void to prevent harm..."));
						}
					}
				} else if (Timer) {
					if (nullCrystals != null) {
						// Still crystals
						itimer = 0;
						timer = 0;
						ITimer = false;
						Timer = true;
					} else {
						// Teleport
						if (worldObj.getPlayerEntityByName(playername) instanceof EntityPlayerMP) {
							MinecraftServer
									.getServer()
									.getConfigurationManager()
									.transferPlayerToDimension(
											(EntityPlayerMP) worldObj
													.getPlayerEntityByName(playername),
											0,
											new TeleporterNullVoid(
													MinecraftServer
															.getServer()
															.worldServerForDimension(
																	0)));
							worldObj.getPlayerEntityByName(playername)
									.addChatComponentMessage(
											new ChatComponentText(
													"Out of Null Crystals! Ejecting from Void to prevent harm..."));
						}
					}
				}
			}
			// Increce Timers
			if (Timer) {
				timer++;
			} else if (ITimer) {
				itimer++;
			}
			// Person is not in anymore
			if (worldObj.getPlayerEntityByName(playername) != null) {
				if (worldObj.getPlayerEntityByName(playername).dimension != VoidMod.NullVoidDimID) {
					itimer = 0;
					timer = 0;
					Timer = false;
					ITimer = false;
					EntityPlayer p = worldObj.getPlayerEntityByName(playername);
					Utils.setInVoid(p, false);
					Utils.setEffects(p, new Effects());
					PacketHandler.INSTANCE.sendTo(new PacketLighting(Utils.getBright(p), true), (EntityPlayerMP)worldObj.getPlayerEntityByName(playername)); 
					playername = "";
				}
			}
		}
		if (before != isStructure && isStructure == true) {
			System.out.println("Hai");
			EntityPlayer p = worldObj.getClosestPlayer(xCoord, yCoord, zCoord,
					-1);
			if (p != null) {
				p.addStat(VoidMod.makeReactor, 1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		if (nullCrystals != null) {
			data.setInteger("AmountNull", nullCrystals.stackSize);
		} else {
			data.setInteger("AmountNull", 0);
		}
		data.setInteger("AmountFlux", tank.getFluidAmount());
		data.setBoolean("isStructure", isStructure);
	}

	public void onClick(EntityPlayer p) {
		System.out.println(maxNull);
		if (p.inventory.getCurrentItem() != null) {
			if (p.inventory.getCurrentItem().getItem()
					.equals(VoidMod.ingotNull)) {
				if (nullCrystals == null) {
					if (p.inventory.getCurrentItem().stackSize <= maxNull) {
						nullCrystals = p.inventory.getCurrentItem();
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, null);
					}
				} else {
					if (p.inventory.getCurrentItem().stackSize
							+ nullCrystals.stackSize <= maxNull) {
						nullCrystals.stackSize = nullCrystals.stackSize
								+ p.inventory.getCurrentItem().stackSize;
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, null);
					}
				}
			} else if (p.inventory.getCurrentItem().getItem()
					.equals(VoidMod.bucket)) {
				if (tank.getFluidAmount() == 0) {
					tank.fill(new FluidStack(VoidMod.liquidFlux, 1000), true);
					p.inventory.setInventorySlotContents(
							p.inventory.currentItem,
							new ItemStack(Items.bucket));
				}
			} else if (p.inventory.getCurrentItem().getItem()
					.equals(Items.bucket)) {
				if (tank.getFluidAmount() == 1000) {
					tank.drain(1000, true);
					p.inventory.setInventorySlotContents(
							p.inventory.currentItem, new ItemStack(
									VoidMod.bucket));
				}
			}
		} else {
			if (nullCrystals != null) {
				if (nullCrystals.stackSize < 65) {
					if (p.inventory.getCurrentItem() == null) {
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, new ItemStack(
										VoidMod.ingotNull,
										nullCrystals.stackSize));
						nullCrystals = null;
					}
				} else {
					if (p.inventory.getCurrentItem() == null) {
						nullCrystals.stackSize -= 64;
						p.inventory.setInventorySlotContents(
								p.inventory.currentItem, new ItemStack(
										VoidMod.ingotNull, 64));
					}
				}
			}
		}
		if (nullCrystals != null) {
			System.out.println(nullCrystals.stackSize);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		int crystalAmount = data.getInteger("AmountNull");
		int fluxAmount = data.getInteger("AmountFlux");
		if (crystalAmount != 0) {
			nullCrystals = new ItemStack(VoidMod.ingotNull, crystalAmount);
		} else {
			nullCrystals = null;
		}
		if (fluxAmount > 0) {
			tank.setFluid(new FluidStack(VoidMod.liquidFlux, fluxAmount));
		}
		isStructure = data.getBoolean("isStructure");
	}

	private void checkStructure() {
		if (worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(
				VoidMod.NullOre)) {
			// VoidFab
			// First Level
			if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) instanceof BlockVoidFabric) {
				if (worldObj.getBlock(xCoord - 1, yCoord, zCoord - 1) instanceof BlockVoidFabric) {
					if (worldObj.getBlock(xCoord, yCoord, zCoord - 1) instanceof BlockVoidFabric) {
						if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) instanceof BlockVoidFabric) {
							if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) instanceof BlockVoidFabric) {
								if (worldObj.getBlock(xCoord + 1, yCoord,
										zCoord + 1) instanceof BlockVoidFabric) {
									if (worldObj.getBlock(xCoord - 1, yCoord,
											zCoord + 1) instanceof BlockVoidFabric) {
										if (worldObj.getBlock(xCoord + 1,
												yCoord, zCoord - 1) instanceof BlockVoidFabric) {
											// System.out.println("DoneFirst");
											// Second Layer
											if (worldObj.getBlock(xCoord - 1,
													yCoord - 1, zCoord - 1)
													 instanceof BlockVoidFabric) {
												if (worldObj.getBlock(
														xCoord - 1, yCoord - 1,
														zCoord + 1) instanceof BlockVoidFabric) {
													if (worldObj.getBlock(
															xCoord + 1,
															yCoord - 1,
															zCoord - 1) instanceof BlockVoidFabric) {
														if (worldObj
																.getBlock(
																		xCoord + 1,
																		yCoord - 1,
																		zCoord + 1) instanceof BlockVoidFabric) {
															// System.out.println("DoneSecond");
															// Third Layer
															if (worldObj
																	.getBlock(
																			xCoord - 1,
																			yCoord - 2,
																			zCoord) instanceof BlockVoidFabric) {
																if (worldObj
																		.getBlock(
																				xCoord - 1,
																				yCoord - 2,
																				zCoord - 1) instanceof BlockVoidFabric) {
																	if (worldObj
																			.getBlock(
																					xCoord,
																					yCoord - 2,
																					zCoord - 1) instanceof BlockVoidFabric) {
																		if (worldObj
																				.getBlock(
																						xCoord + 1,
																						yCoord - 2,
																						zCoord) instanceof BlockVoidFabric) {
																			if (worldObj
																					.getBlock(
																							xCoord,
																							yCoord - 2,
																							zCoord + 1) instanceof BlockVoidFabric) {
																				if (worldObj
																						.getBlock(
																								xCoord + 1,
																								yCoord - 2,
																								zCoord + 1) instanceof BlockVoidFabric) {
																					if (worldObj
																							.getBlock(
																									xCoord - 1,
																									yCoord - 2,
																									zCoord + 1) instanceof BlockVoidFabric) {
																						if (worldObj
																								.getBlock(
																										xCoord + 1,
																										yCoord - 2,
																										zCoord - 1) instanceof BlockVoidFabric) {
																							if (worldObj
																									.getBlock(
																											xCoord,
																											yCoord - 2,
																											zCoord) instanceof BlockVoidFabric) {
																								// System.out.println("FoundLast");
																								// Upgrade
																								// Slots
																								if (VoidMod.VoidReactorValidBlocks
																										.contains(worldObj
																												.getBlock(
																														xCoord - 1,
																														yCoord - 1,
																														zCoord))) {
																									upgrades[0] = worldObj
																											.getTileEntity(
																													xCoord - 1,
																													yCoord - 1,
																													zCoord);
																									if (VoidMod.VoidReactorValidBlocks
																											.contains(worldObj
																													.getBlock(
																															xCoord + 1,
																															yCoord - 1,
																															zCoord))) {
																										upgrades[1] = worldObj
																												.getTileEntity(
																														xCoord + 1,
																														yCoord - 1,
																														zCoord);
																										if (VoidMod.VoidReactorValidBlocks
																												.contains(worldObj
																														.getBlock(
																																xCoord,
																																yCoord - 1,
																																zCoord - 1))) {
																											upgrades[2] = worldObj
																													.getTileEntity(
																															xCoord,
																															yCoord - 1,
																															zCoord - 1);
																											if (VoidMod.VoidReactorValidBlocks
																													.contains(worldObj
																															.getBlock(
																																	xCoord,
																																	yCoord - 1,
																																	zCoord + 1))) {
																												upgrades[3] = worldObj
																														.getTileEntity(
																																xCoord,
																																yCoord - 1,
																																zCoord + 1);
																												// System.out.println("isStruct");
																												isStructure = true;
																												return;
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
		isStructure = false;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (fluid.getID() == VoidMod.liquidFlux.getID()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if (fluid.getID() == VoidMod.liquidFlux.getID()) {
			return true;
		}
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public Chunk loadChunk(World var1, int var2, int var3) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveChunk(World var1, Chunk var2) throws MinecraftException,
			IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveExtraChunkData(World var1, Chunk var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chunkTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveExtraData() {
		// TODO Auto-generated method stub
		
	}
}
