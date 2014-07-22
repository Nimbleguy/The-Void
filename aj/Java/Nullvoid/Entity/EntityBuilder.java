package aj.Java.Nullvoid.Entity;

import java.util.List;

import aj.Java.Nullvoid.VoidMod;
import aj.Java.Nullvoid.VoidWorldData;
import aj.Java.Nullvoid.Tools.ItemElementalHammer;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBuilder extends EntityCreature implements IVoidWalker {

	public EntityBuilder(World par1World) {
		super(par1World);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityGlitch.class, 1.2D, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityGlitch.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityGlitch.class, 0, true));
		
		this.setSize(6F, 12F);
	}
	@Override
	public boolean isAIEnabled()
	{
	         return true;
	}
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4000.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
    }
	@Override
	public ItemStack getHeldItem()
    {
		ItemStack i = new ItemStack(VoidMod.elementalHammer);
		((ItemElementalHammer)VoidMod.elementalHammer).setElements(10, 10, 10, 10, 10, 10, 10, 10, i);
        return i;
    }
	
	@SuppressWarnings("unchecked")
	public void onLivingUpdate(){
		if(VoidWorldData.get(worldObj).hasGlitch){
			loop:
			for(EntityGlitch e : (List<EntityGlitch>)worldObj.getEntitiesWithinAABB(EntityGlitch.class, AxisAlignedBB.getBoundingBox(posX - 10, posY - 50,
							posZ - 10, posX + 10, posY + 50, posZ + 10))){
				if(e != null){
					setAttackTarget(e);
					break loop;
				}
			}
		}
	}
}
