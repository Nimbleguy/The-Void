package aj.Java.Nullvoid.Entity;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import aj.Java.Nullvoid.VoidWorldData;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
import aj.Java.Nullvoid.client.fx.EntityGlitchFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
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
	private String currentAttack;
	public EntityGlitch(World par1World) {
		super(par1World);
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
		currentAttack = "NONE";
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
		
		super.onLivingUpdate();
	}
	
	@Override
	public void onUpdate() {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			Random r = new Random();
			for (int i = 0; i < 20; i++) {
				EntityFX glitch = new EntityGlitchFX(worldObj, (posX - 0.5D)
						+ r.nextDouble(), posY, (posZ - 0.5D) + r.nextDouble(),
						r.nextDouble() - 0.5D, 2D + r.nextDouble(), r.nextDouble() - 0.5D);
				Minecraft.getMinecraft().effectRenderer.addEffect(glitch);
			}
		}
		super.onUpdate();
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
}
