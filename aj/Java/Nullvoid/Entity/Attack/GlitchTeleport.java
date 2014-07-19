package aj.Java.Nullvoid.Entity.Attack;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;

public class GlitchTeleport implements IGlitchAttack {
	
	EntityGlitch glitch;
	boolean isDone;
	
	public GlitchTeleport(EntityGlitch e){
		glitch = e;
		isDone = false;
	}
	
	@Override
	public void use(Entity target) {
		glitch.teleportRandomly();
		isDone = true;
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
