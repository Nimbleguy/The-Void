package aj.Java.Nullvoid.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import aj.Java.Nullvoid.VoidWorldData;
import aj.Java.Nullvoid.Entity.Attack.GlitchCorrupt;
import aj.Java.Nullvoid.Entity.Attack.GlitchCorrupt2;
import aj.Java.Nullvoid.Entity.Attack.GlitchCorrupt3;
import aj.Java.Nullvoid.Entity.Attack.GlitchReach;
import aj.Java.Nullvoid.Entity.Attack.GlitchTeleport;
import aj.Java.Nullvoid.Entity.Attack.GlitchyBoom;
import aj.Java.Nullvoid.Entity.Attack.GlitchyBoom2;
import aj.Java.Nullvoid.Entity.Attack.GlitchyBoom3;
import aj.Java.Nullvoid.Entity.Attack.IGlitchAttack;
import aj.Java.Nullvoid.Entity.Attack.SpawnEntity;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketParticle;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityGlitch extends EntityMob implements IBossDisplayData, IVoidWalker {
	private Entity target;
	private IGlitchAttack currentAttack;
	private float scale = 1F;
	private int tier = 1;
	public HashMap<Integer, List<Class<? extends IGlitchAttack>>> attacks = new HashMap<Integer, List<Class<? extends IGlitchAttack>>>();
	public EntityGlitch(World par1World) {
		super(par1World);
		setSize(0.6F, 1.8F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new EntityAISwimming(this));
		isImmuneToFire = true;
		experienceValue = 900;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
		.setBaseValue(4242.413F * 2);
		this.setHealth(4242.413F * 2);
		VoidWorldData v = VoidWorldData.get(worldObj);
		if(v.hasGlitch){
			this.setDead();
		}
		else{
			v.hasGlitch = true;
		}
		currentAttack =  null;
		attacks.put(1, new ArrayList<Class<? extends IGlitchAttack>>());
		attacks.put(2, new ArrayList<Class<? extends IGlitchAttack>>());
		attacks.put(3, new ArrayList<Class<? extends IGlitchAttack>>());
		attacks.put(4, new ArrayList<Class<? extends IGlitchAttack>>());
		attacks.put(5, new ArrayList<Class<? extends IGlitchAttack>>());
		attacks.put(6, new ArrayList<Class<? extends IGlitchAttack>>());
		addAttacks();
		/*
		 * Hello. I am the Glitch. This is the only place we can safely talk.
		 * Well. You know about the basic structure, right?
		 * Well here is how to make the rest of it:
		 * http://imgur.com/p8BJDjM
		 * ...
		 * ....
		 * h--p
		 * -e
		 */
	}
	public EntityGlitch(World worldObj, double x, double y, double z) {
		this(worldObj);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.setLocationAndAngles(x, y, z, 0F, 0F);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean allowLeashing() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onLivingUpdate() {
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			BossStatus.setBossStatus(this, true);
		}
		else{
			//Getting the Target
			if(target == null){
				EntityPlayer p = worldObj.getClosestPlayer(posX, posY, posZ, 0);
				if(p != null){
					target = p;
				}
			}
			if(target == null || !(target instanceof EntityBuilder)){
				loop:
				for(Entity e : (List<Entity>)worldObj.getEntitiesWithinAABB(EntityBuilder.class, AxisAlignedBB.getBoundingBox(posX - 10, posY - 50,
							posZ - 10, posX + 10, posY + 50, posZ + 10))){
					if(e != null){
						target = e;
						break loop;
					}
				}
			}
			tier = 1;
			scale = 1F;
			if(this.getHealth() < ((4242.413F * 2) * (5 / 6))){
				tier = 2;
				scale = 2F;
			}
			if(this.getHealth() < ((4242.413F * 2) * (4 / 6))){
				tier = 3;
				scale = 4F;
			}
			if(this.getHealth() < ((4242.413F * 2) * (3 / 6))){
				tier = 4;
				scale = 5.50F;
			}
			if(this.getHealth() < ((4242.413F * 2) * (2 / 6))){
				tier = 5;
				scale = 7F;
			}
			if(this.getHealth() < ((4242.413F * 2) * (1 / 6))){
				tier = 6;
				scale = 10F;
			}
			setSize(0.6F * scale, 1.8F * scale);

			if(currentAttack != null && currentAttack.isDone()){
				currentAttack = null;
			}
			else if(currentAttack != null && target != null){
				currentAttack.use(target);
			}
			if(currentAttack == null && rand.nextInt(5000 - (tier * 100)) == 42 && target != null){
				try {
					currentAttack = attacks.get(tier).get(rand.nextInt(attacks.get(tier).size())).getConstructor(EntityGlitch.class).newInstance(this);
					currentAttack.use(target);
				}
				catch (Exception e) {
					System.err.println("There has been an error with the Void Mod. Please report this to Nimbleguy.");
					e.printStackTrace(System.err);
				}
			}
			Random r = new Random();
			PacketHandler.INSTANCE.sendToDimension(new PacketParticle((posX - (0.5D * scale))
					+ r.nextDouble(), posY, (posZ - (0.5D * scale)) + r.nextDouble(),
					r.nextDouble() - (0.5D * scale), (2D * scale) + r.nextDouble(), r.nextDouble() - (0.5D * scale), 200), this.dimension);
		}
		super.onLivingUpdate();
	}

	@Override
	public boolean hitByEntity(Entity e) {
		target = e;
		if(currentAttack != null && currentAttack.isDone()){
			currentAttack = null;
		}
		else if(currentAttack != null){
			currentAttack.use(target);
		}
		if(currentAttack == null){
			try {
				currentAttack = attacks.get(tier)
						.get(rand.nextInt(attacks.get(tier).size()))
						.getConstructor(EntityGlitch.class)
						.newInstance(this);
				currentAttack.use(target);
			}
			catch (Exception err) {
				System.err.println("There has been an error with the Void Mod. Please report this to Nimbleguy.");
				err.printStackTrace(System.err);
			}
		}
		if (this.getHealth() > 4242.413) {
			if (e instanceof EntityPlayer) {
				if (((EntityPlayer) e).inventory.getCurrentItem() != null && ((EntityPlayer) e).inventory.getCurrentItem().getItem() instanceof ItemElementalHammer) {
					int[] values = new int[8];
					values[0] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.FIRE,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[1] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.ICE,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[2] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.EARTH,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[3] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.AIR,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[4] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.ORDER,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[5] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.ENTROPY,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[6] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.DARK,
									((EntityPlayer) e).inventory.getCurrentItem());
					values[7] = ((ItemElementalHammer) ((EntityPlayer) e).inventory
							.getCurrentItem().getItem()).getElement(
									EnumElement.LIGHT,
									((EntityPlayer) e).inventory.getCurrentItem());
					boolean check = false;
					for (int bla = 0; bla < 8; bla++) {
						for (int value = 0; value < 8; value++) {
							if (values[value] != values[bla]) {
								check = true;
							}
						}
					}
					if (!check) {
						return false;
					}
				}
			}
		} else {
			if (e instanceof EntityBuilder) {
				return false;
			}
		}
		return true;
	}
	@Override
	public IChatComponent func_145748_c_(){
		return new ChatComponentText(EnumChatFormatting.OBFUSCATED.toString() + "OOO" + 
				EnumChatFormatting.RESET.toString() + EnumChatFormatting.DARK_PURPLE.toString() + "The Glitch"
				+ EnumChatFormatting.WHITE.toString() + EnumChatFormatting.OBFUSCATED.toString() + "OOO");
	}
	@Override
	protected String getLivingSound(){
		return "nullvoid:mob.glitch.ambient";
	}
	@Override
	public void onDeath(DamageSource d){
		VoidWorldData.get(worldObj).hasGlitch = false;
		super.onDeath(d);
	}
	private void addAttacks(){
		//Tier 1
		attacks.get(1).add(SpawnEntity.class);
		attacks.get(1).add(GlitchReach.class);
		attacks.get(1).add(GlitchCorrupt.class);
		//Tier 2
		attacks.get(2).add(GlitchTeleport.class);
		attacks.get(2).add(SpawnEntity.class);
		attacks.get(2).add(GlitchReach.class);
		attacks.get(2).add(GlitchyBoom.class);
		attacks.get(1).add(GlitchCorrupt2.class);
		//Tier 3
		attacks.get(3).add(GlitchTeleport.class);
		attacks.get(3).add(SpawnEntity.class);
		attacks.get(3).add(GlitchReach.class);
		attacks.get(3).add(GlitchyBoom2.class);
		attacks.get(1).add(GlitchCorrupt3.class);
		//Tier 4
		attacks.get(4).add(GlitchTeleport.class);
		attacks.get(4).add(GlitchReach.class);
		attacks.get(4).add(GlitchyBoom3.class);
		attacks.get(4).add(GlitchyBoom2.class);
		attacks.get(4).add(GlitchyBoom.class);
		attacks.get(4).add(GlitchCorrupt.class);
		//Tier 5
		attacks.get(5).add(GlitchTeleport.class);
		attacks.get(5).add(GlitchReach.class);
		attacks.get(5).add(GlitchyBoom3.class);
		attacks.get(5).add(GlitchyBoom2.class);
		attacks.get(5).add(GlitchyBoom.class);
		attacks.get(5).add(GlitchCorrupt.class);
		attacks.get(5).add(GlitchCorrupt2.class);
		//Tier 6
		attacks.get(6).add(GlitchTeleport.class);
		attacks.get(6).add(GlitchReach.class);
		attacks.get(6).add(GlitchyBoom3.class);
		attacks.get(6).add(GlitchyBoom2.class);
		attacks.get(6).add(GlitchyBoom.class);
		attacks.get(6).add(GlitchCorrupt.class);
		attacks.get(6).add(GlitchCorrupt2.class);
		attacks.get(6).add(GlitchCorrupt3.class);
	}
	public boolean teleportRandomly(int warmuptime)
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 16.0D;
		double d1 = this.posY + (double)(this.rand.nextInt(64) - 8);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 16.0D;
		return this.teleportTo(d0, d1, d2, warmuptime);
	}
	public double[] getTeleportRandomly(){
		return new double[] {this.posX + (this.rand.nextDouble() - 0.5D) * 16.0D, this.posY + (double)(this.rand.nextInt(64) - 8), this.posZ + (this.rand.nextDouble() - 0.5D) * 16.0D};
	}
	public boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_, int warmuptime)
	{
		EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
		if (MinecraftForge.EVENT_BUS.post(event)){
			return false;
		}
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = event.targetX;
		this.posY = event.targetY;
		this.posZ = event.targetZ;
		boolean flag = false;
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if (this.worldObj.blockExists(i, j, k))
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--this.posY;
					--j;
				}
			}

			if (flag1)
			{
				this.setPosition(this.posX, this.posY, this.posZ);

				if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			if(warmuptime >= 100){
				this.setPosition(d3, d4, d5);
			}
			return false;
		}
		else
		{
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = (double)l / ((double)short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
				double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5D) * (double)this.width * 2.0D;
				PacketHandler.INSTANCE.sendToDimension(new PacketParticle(d7, d8, d9, (double)f, (double)f1, (double)f2, 1), this.dimension);
			}
			if(warmuptime >= 100){
				this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
				this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.scream", 1.0F, 1.0F);
				this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			}
			return true;
		}
	}
}
