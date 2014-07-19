package aj.Java.Nullvoid.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import aj.Java.Nullvoid.VoidWorldData;
import aj.Java.Nullvoid.Entity.Attack.IGlitchAttack;
import aj.Java.Nullvoid.Entity.Attack.SpawnEntity;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketParticle;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityGlitch extends EntityMob implements IBossDisplayData, IVoidWalker {
	private Entity target;
	private IGlitchAttack currentAttack;
	private float scale = 1F;
	public HashMap<Integer, List<Class<? extends IGlitchAttack>>> attacks = new HashMap<Integer, List<Class<? extends IGlitchAttack>>>();
	public EntityGlitch(World par1World) {
		super(par1World);
		setSize(0.6F, 1.8F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
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
		BossStatus.setBossStatus(this, true);
		//Getting the Target
		EntityPlayer p = worldObj.getClosestPlayer(posX, posY, posZ, 0);
		if(p != null){
			target = p;
		}
		loop:
			for(Entity e : (List<Entity>)worldObj.getLoadedEntityList()){
				if(e instanceof EntityBuilder){
					target = e;
					break loop;
				}
			}

		int tier = 1;
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
		else if(currentAttack != null){
			currentAttack.use(target);
		}
		if(currentAttack == null && rand.nextInt(10000 - (tier * 100)) == 42){
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
		super.onLivingUpdate();
	}

	@Override
	public boolean hitByEntity(Entity e) {
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
	}
	private void addAttacks(){
		attacks.get(1).add(SpawnEntity.class);
	}
}
