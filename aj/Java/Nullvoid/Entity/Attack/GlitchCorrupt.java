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
		System.out.println(warmuptime);
		warmuptime++;
		glitch.teleportTo(target.posX, target.posY, target.posZ, warmuptime);
		if(warmuptime >= 100){
			System.out.println("CORRUPT");
			target.attackEntityFrom(new DamageSource("mob.glitch.damage.corrupt"), 3.5F);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}

}
