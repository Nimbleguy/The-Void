package aj.Java.Nullvoid.Entity.Attack;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;

public class GlitchTeleport implements IGlitchAttack {
	
	EntityGlitch glitch;
	boolean isDone;
	double[] d;
	int warmuptime = 0;
	
	public GlitchTeleport(EntityGlitch e){
		glitch = e;
		isDone = false;
	}
	
	@Override
	public void use(Entity target) {
		warmuptime++;
		if(d == null){
			d = glitch.getTeleportRandomly();
		}
		glitch.teleportTo(d[0], d[1], d[2], warmuptime);
		if(warmuptime >= 100){
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}

}
