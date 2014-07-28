package aj.Java.Nullvoid.Entity.Attack;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class GlitchCorrupt implements IGlitchAttack {
	
	EntityGlitch glitch;
	boolean isDone = false;
	int warmuptime = 0;
	
	public GlitchCorrupt(EntityGlitch g){
		glitch = g;
	}
	
	@Override
	public void use(Entity target) {
		warmuptime++;
		glitch.teleportTo(target.posX, target.posY, target.posZ, warmuptime);
		if(warmuptime >= 100){
			target.attackEntityFrom(new DamageSource("mob.glitch.damage.corrupt"), 3.5F);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		return 1;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
