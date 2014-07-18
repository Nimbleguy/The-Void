package aj.Java.Nullvoid.Entity;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import aj.Java.Nullvoid.Tools.ItemElementalHammer.EnumElement;
import aj.Java.Nullvoid.client.fx.EntityGlitchFX;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityGlitch extends EntityMob implements IBossDisplayData,
		IRangedAttackMob, IVoidWalker {
	public EntityGlitch(World par1World) {
		super(par1World);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this,
				EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(8, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1D, false));
		this.tasks.addTask(8, new EntityAIAttackOnCollide(this, EntityBuilder.class, 1D, false));
		this.tasks.addTask(9, new EntityAIMoveTowardsTarget(this, 1D, 30F));
		
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, false, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				EntityBuilder.class, 1, false, false));

		this.setSize(0.6F, 1.8F);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setBaseValue(4242.413F * 2);
		this.setHealth(4242.413F * 2);
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

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
		// worldObj.spawnEntityInWorld(new EntityGlitchists(this.worldObj));
	}

	@Override
	public void onLivingUpdate() {
		BossStatus.setBossStatus(this, true);
		Random r = new Random();
		if(r.nextInt(5000) == 42){
			//TODO: Morph.
			//if(this.re)
			//switch(r.nextInt())
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
}
