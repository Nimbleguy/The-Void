package aj.Java.Nullvoid.Entity;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNullFloater extends EntitySquid implements IVoidWalker {
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
            this.entityDropItem(new ItemStack(VoidMod.bucket, 1, 0), 0.0F);
        }
    }
	@Override
	public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.dimension == VoidMod.NullVoidDimID && this.worldObj.getBlock((int)posX, (int)posY, (int)posZ) instanceof BlockMoltenFlux;
    }
	@Override
	public void moveEntityWithHeading(float par1, float par2)
    {
        if (this.handleLavaMovement())
        {
            this.moveFlying(par1, par2, 0.02F);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        } else
        {
            float f2 = 0.91F;

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.moveFlying(par1, par2, f3);
            f2 = 0.91F;

            if (this.onGround)
            {
                f2 = 0.54600006F;
                Block j = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

                if (j != null)
                {
                    f2 = j.slipperiness * 0.91F;
                }
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) f2;
            this.motionY *= (double) f2;
            this.motionZ *= (double) f2;
        }

        double d0 = this.posX - this.prevPosX;
        double d2 = this.posY - this.prevPosY;
        double d1 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }
    }
	@Override
	protected void updateEntityActionState() {}
	@Override
	protected void fall(float par1) {}
	@Override
	protected void updateFallState(double par1, boolean par3) {}
}
