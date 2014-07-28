package aj.Java.Nullvoid.Entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityVoidMaster extends EntityCreature implements IVoidWalker{

	public EntityVoidMaster(World par1World) {
		super(par1World);
		this.getNavigator().setCanSwim(true);
		this.tasks.addTask(0, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
	}
	@Override
	public boolean isAIEnabled(){
		return true;
	}
	public EnumCreatureAttribute getCreatureAttribute()
	{
	    return EnumCreatureAttribute.UNDEFINED;
	}

}
