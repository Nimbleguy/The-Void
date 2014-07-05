package aj.Java.Nullvoid.tileentity;

import java.io.IOException;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import aj.Java.Nullvoid.Utils;
import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.Dimention.TeleporterNullVoid;
import aj.Java.Nullvoid.Effects.Effects;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketLighting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.IChunkLoader;

public class TileEntityVoidWalker extends TileEntity implements IInventory,
		IChunkLoader {
	private ItemStack[] inv;
	public Slot[] slots = new Slot[4];
	private String uuid = "";
	private int timer = 0;
	private int itimer = 0;
	private boolean doITimer = false;
	private boolean doTimer = false;

	public TileEntityVoidWalker() {
		inv = new ItemStack[4];
	}

	/**
	 * @Override public void onChunkUnload() { this.worldObj.getChunkProvider()
	 *           .loadChunk( this.worldObj.getChunkFromBlockCoords(this.xCoord,
	 *           this.zCoord).xPosition,
	 *           this.worldObj.getChunkFromBlockCoords(this.xCoord,
	 *           this.zCoord).zPosition); }
	 */
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack itemstack = getStackInSlot(i);
		if (itemstack != null) {
			if (itemstack.stackSize <= j) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(j);
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemstack = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 256;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return entityplayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
				zCoord + 0.5D) <= 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		switch (i) {
		case 0:
			if (itemstack.getItem().equals(VoidMod.ingotNull)) {
				return true;
			} else {
				return false;
			}
		case 1:
			if (itemstack.getItem().equals(VoidMod.ingotNull)) {
				return true;
			} else {
				return false;
			}
		case 2:
			if (itemstack.getItem().equals(VoidMod.ingotNull)) {
				return true;
			} else {
				return false;
			}
		case 3:
			if (itemstack.getItem().equals(VoidMod.circuts)) {
				if (itemstack.getItemDamage() == 4) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList list = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack itemstack = getStackInSlot(i);

			if (itemstack != null) {
				NBTTagCompound item = new NBTTagCompound();

				item.setByte("SlotVoidWalker", (byte) i);
				item = itemstack.writeToNBT(item);
				list.appendTag(item);
			}
		}
		compound.setTag("ItemsVoidWalker", list);
		compound.setString("PlayerInVoid", uuid);
		compound.setBoolean("doITimer", doITimer);
		compound.setBoolean("doTimer", doTimer);
		compound.setInteger("ITimer", itimer);
		compound.setInteger("Timer", timer);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		timer = compound.getInteger("Timer");
		itimer = compound.getInteger("ITimer");
		doTimer = compound.getBoolean("doTimer");
		doITimer = compound.getBoolean("doITimer");
		uuid = compound.getString("PlayerInVoid");
		NBTTagList list = compound.getTagList("ItemsVoidWalker", 10);

		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) list.getCompoundTagAt(i);
			int slot = item.getByte("SlotVoidWalker");

			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot,
						ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateEntity() {
		// System.out.println("UPDATED");
		if (this.worldObj.getBlockPowerInput(this.xCoord, this.yCoord,
				this.zCoord) > 0) {
			// System.out.println("REDSTONE");
			List e = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class,
					AxisAlignedBB.getBoundingBox(xCoord - 3, yCoord - 3,
							zCoord - 3, xCoord + 3, yCoord + 3, zCoord + 3));
			int pie = 0;
			if(e.size() > 0){
				pie = 1;
			}
			for (int i = 0; i < pie; i++) {
				// System.out.println("Found entity");
				if (e.get(i) instanceof EntityPlayerMP) {
					// System.out.println("Is a playermp");
					EntityPlayerMP player = (EntityPlayerMP) e.get(i);
					if (this.getStackInSlot(4) != null) {
						if (this.getStackInSlot(4).getItem()
								.equals(VoidMod.circuts)
								&& this.getStackInSlot(4).getItemDamage() == 4) {
							if (player.dimension == 0
									&& uuid.compareTo(player
											.getUniqueID().toString()) != 0
									&& uuid.compareTo("") != 0) {
								EntityPlayerMP p = Utils.getPlayerFromUUID(uuid);
								if (p != null) {
									if (p.dimension != VoidMod.NullVoidDimID) {
										Utils.setInVoid(p, false);
										Utils.setEffects(p, new Effects());
										PacketHandler.INSTANCE.sendTo(new PacketLighting(Utils.getBright(p), true), p);
										uuid = "";
										System.out.println("Exited");
									}
								}
							}
							if (player.dimension == 0
									&& uuid.compareTo("") == 0) {
								// player.travelToDimension(VoidMod.NullVoidDimID);
								// player.mcServer.getConfigurationManager().transferPlayerToDimension(player,
								// VoidMod.NullVoidDimID);
								if (player.timeUntilPortal > 0) {
									player.timeUntilPortal = 10;
								} else {
									player.mcServer
											.getConfigurationManager()
											.transferPlayerToDimension(
													player,
													VoidMod.NullVoidDimID,
													new TeleporterNullVoid(
															player.mcServer
																	.worldServerForDimension(VoidMod.NullVoidDimID)));
									player.timeUntilPortal = 10;
									player.addStat(VoidMod.enterNull, 1);
									uuid = player.getUniqueID().toString();
									Utils.setInVoid(player, true);
									Utils.setEffects(player, new Effects());
									PacketHandler.INSTANCE.sendTo(new PacketLighting(-1F), player);
									this.setInventorySlotContents(
											4,
											new ItemStack(VoidMod.circuts, 1, 0));
									doITimer = true;
								}
							} else if (player.dimension == 0
									&& (uuid.compareTo(player.getUniqueID().toString()) == 0)) {
								// player.travelToDimension(VoidMod.NullVoidDimID);
								// player.mcServer.getConfigurationManager().transferPlayerToDimension(player,
								// VoidMod.NullVoidDimID);
								if (player.timeUntilPortal > 0) {
									player.timeUntilPortal = 10;
								} else {
									player.mcServer
											.getConfigurationManager()
											.transferPlayerToDimension(
													player,
													VoidMod.NullVoidDimID,
													new TeleporterNullVoid(
															player.mcServer
																	.worldServerForDimension(VoidMod.NullVoidDimID)));
									player.timeUntilPortal = 10;
									player.addStat(VoidMod.enterNull, 1);
									uuid = player.getUniqueID().toString();
									Utils.setInVoid(player, true);
									Utils.setEffects(player, new Effects());
									PacketHandler.INSTANCE.sendTo(new PacketLighting(-1F), player);
									this.setInventorySlotContents(
											4,
											new ItemStack(VoidMod.circuts, 1, 0));
									doITimer = true;
								}
							} else if (player.dimension == VoidMod.NullVoidDimID) {
								System.out.println("Player " + Utils.getPlayerFromUUID(uuid).getCommandSenderName()
										+ " is in the nullvoid!");
								// player.travelToDimension(0);
								// player.mcServer.getConfigurationManager().transferPlayerToDimension(player,
								// VoidMod.NullVoidDimID);
								if (player.timeUntilPortal > 0) {
									player.timeUntilPortal = 10;
								} else {
									if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
										Minecraft.getMinecraft().gameSettings.gammaSetting = player
												.getEntityData().getFloat(
														"NullGamma");
									}
									player.mcServer
											.getConfigurationManager()
											.transferPlayerToDimension(
													player,
													0,
													new TeleporterNullVoid(
															player.mcServer
																	.worldServerForDimension(0)));
									player.timeUntilPortal = 10;
									this.setInventorySlotContents(
											4,
											new ItemStack(VoidMod.circuts, 1, 0));
									doITimer = true;
								}
							} else if (uuid.compareTo(player
									.getUniqueID().toString()) != 0
									&& uuid.compareTo("") != 0) {
								player.addChatMessage(new ChatComponentText(
										"Player "
												+ Utils.getPlayerFromUUID(uuid).getCommandSenderName()
												+ " is already traversing the void!"));
							}
						} else {
							player.addChatMessage(new ChatComponentText(
									"Null cirtuit is not active!"));
						}
					} else {
						player.addChatMessage(new ChatComponentText(
								"Null circuit missing!"));
					}
				}
			}
		}
		if ((timer >= 500 || itimer >= 250)) {
			System.out.println("Timed");
			doITimer = false;
			doTimer = true;
			timer = 0;
			if (uuid.compareTo("") != 0) {
				EntityPlayerMP p = Utils.getPlayerFromUUID(uuid);
				if (p != null) {
					if (p.dimension == VoidMod.NullVoidDimID) {
						boolean check = false;
						boolean check1 = false;
						boolean check2 = false;
						boolean check3 = false;
						if (getStackInSlot(0) != null) {
							if (getStackInSlot(0).isItemEqual(
									new ItemStack(VoidMod.ingotNull))) {
								check = true;
								check1 = true;
								check2 = false;
								check3 = false;
							}
						}
						if (getStackInSlot(1) != null) {
							if (getStackInSlot(1).isItemEqual(
									new ItemStack(VoidMod.ingotNull))) {
								check = true;
								check3 = false;
								check2 = true;
								check1 = false;
							}
						}
						if (getStackInSlot(2) != null) {
							if (getStackInSlot(2).isItemEqual(
									new ItemStack(VoidMod.ingotNull))) {
								check = true;
								check3 = true;
								check2 = false;
								check1 = false;
							}
						}
						if (check) {
							if (check3) {
								this.setInventorySlotContents(3,
										decrStackSize(2, 1));
							} else if (check2) {
								this.setInventorySlotContents(2,
										decrStackSize(1, 1));
							} else if (check1) {
								this.setInventorySlotContents(1,
										decrStackSize(0, 1));
							}
							doITimer = false;
							doTimer = true;
							timer = 0;
							itimer = 0;
							System.out.println("Decremented");
						} else if (!check) {
							if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
								Minecraft.getMinecraft().gameSettings.gammaSetting = p
										.getEntityData().getFloat("NullGamma");
							}
							p.addChatMessage(new ChatComponentText(
									"Out of Null Crystals! Ejecting from void to prevent harm..."));
							p.mcServer
									.getConfigurationManager()
									.transferPlayerToDimension(
											p,
											0,
											new TeleporterNullVoid(p.mcServer
													.worldServerForDimension(0)));
							p.timeUntilPortal = 10;
						}
					}
				}
			}
		} else {
			if (!doITimer && doTimer) {
				timer++;
				doITimer = false;
				itimer = 0;
				// System.out.println("Timer " + timer);
			}
			if (doITimer) {
				// System.out.println("ITimer: " + itimer);
				itimer++;
			}
		}
		if (uuid.compareTo("") == 0) {
			doITimer = false;
			doTimer = false;
			timer = 0;
			itimer = 0;
		}
		if (uuid.compareTo("") != 0) {
			EntityPlayerMP p = Utils.getPlayerFromUUID(uuid);
			if (p != null) {
				if (p.dimension != VoidMod.NullVoidDimID) {
					doITimer = false;
					doTimer = false;
					timer = 0;
					itimer = 0;
					Utils.setInVoid(p, false);
					Utils.setEffects(p, new Effects());
					PacketHandler.INSTANCE.sendTo(new PacketLighting(Utils.getBright(p), true), p);
					uuid = "";
				}
			}
		}

	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "The Voidwalker";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public Chunk loadChunk(World var1, int var2, int var3) throws IOException {
		return null;
	}

	@Override
	public void saveChunk(World var1, Chunk var2) throws MinecraftException,
			IOException {

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
