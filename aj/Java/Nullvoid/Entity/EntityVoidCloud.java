package aj.Java.Nullvoid.Entity;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVoidCloud extends EntityFlying {
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	public int courseChangeCooldown;

	public EntityVoidCloud(World par1World) {
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIWatchClosest(this,
				EntityPlayer.class, 8.0F));
		this.tasks.addTask(1, new EntityAILookIdle(this));
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled() {
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		super.onUpdate();
		this.motionY *= 0.6000000238418579D;
	}

	protected void updateAITasks() {
		super.updateAITasks();
		EntityPlayer e = worldObj.getClosestPlayerToEntity(this, 40);
		if (e != null) {
			double d0 = (double) e.posX + 0.5D - this.posX;
			double d1 = (double) e.posY + 0.1D - this.posY;
			double d2 = (double) e.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
			this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
			this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
			float f = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;
		} else {
			System.out.println("NULL");
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return false;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float p_70069_1_) {
	}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on
	 * the ground to update the fall distance and deal fall damage if landing on
	 * the ground. Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {
	}

	/**
	 * Return whether this entity should NOT trigger a pressure plate or a
	 * tripwire.
	 */
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
}
