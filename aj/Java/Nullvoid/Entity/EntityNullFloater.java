package aj.Java.Nullvoid.Entity;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNullFloater extends EntitySquid implements IVoidWalker {
	private float randomMotionSpeed;
    /** change in squidRotation in radians. */
    private float rotationVelocity;
    private float field_70871_bB;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
	public EntityNullFloater(World par1World) {
		super(par1World);
		
	}
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
    }
	@Override
	public boolean isInWater()
    {
        return true;
    }
	@Override
	protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(3 + par2) + 1;

        for (int k = 0; k < j; ++k)
        {
            this.entityDropItem(new ItemStack(VoidMod.nullInk, 1, 0), 0.0F);
        }
    }
	@Override
	public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.dimension == VoidMod.NullVoidDimID && this.worldObj.getBlock((int)posX, (int)posY, (int)posZ) instanceof BlockMoltenFlux;
    }
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if (this.squidRotation > ((float)Math.PI * 2F))
        {
            this.squidRotation -= ((float)Math.PI * 2F);

            if (this.rand.nextInt(10) == 0)
            {
                this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.isInWater())
        {
            float f;

            if (this.squidRotation < (float)Math.PI)
            {
                f = this.squidRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.field_70871_bB = 1.0F;
                }
                else
                {
                    this.field_70871_bB *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.field_70871_bB *= 0.99F;
            }

            if (!this.worldObj.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw += (float)Math.PI * this.field_70871_bB * 1.5F;
            this.squidPitch += (-((float)Math.atan2((double)f, this.motionY)) * 180.0F / (float)Math.PI - this.squidPitch) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float)Math.PI * 0.25F;

            if (!this.worldObj.isRemote)
            {
                this.motionX = 0.0D;
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ = 0.0D;
            }

            this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
        }
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
    {
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }
	protected void updateEntityActionState()
    {
        if (this.rand.nextInt(50) == 0 || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F)
        {
            float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
            this.randomMotionVecX = MathHelper.cos(f) * 0.2F;
            this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
            this.randomMotionVecZ = MathHelper.sin(f) * 0.2F;
        }
    }
	@Override
	protected void fall(float par1) {}
	@Override
	protected void updateFallState(double par1, boolean par3) {}
}
