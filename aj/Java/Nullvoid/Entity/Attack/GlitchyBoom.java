package aj.Java.Nullvoid.Entity.Attack;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;

public class GlitchyBoom implements IGlitchAttack {
	
	EntityGlitch glitch;
	boolean isDone;
	int warmuptime = 0;
	
	public GlitchyBoom(EntityGlitch g){
		glitch = g;
		isDone = false;
	}
	
	@Override
	public void use(Entity target) {
		warmuptime++;
		glitch.teleportTo(glitch.posX, glitch.posY, glitch.posZ, warmuptime);
		if(warmuptime >= 100){
			glitch.worldObj.createExplosion(glitch, glitch.posX, glitch.posY, glitch.posZ, 4F, false);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		return 2;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
