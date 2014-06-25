package aj.Java.Nullvoid.Entity;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.block.BlockMoltenFlux;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;
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
	protected void updateEntityActionState() {}
	@Override
	protected void fall(float par1) {}
	@Override
	protected void updateFallState(double par1, boolean par3) {}
}
