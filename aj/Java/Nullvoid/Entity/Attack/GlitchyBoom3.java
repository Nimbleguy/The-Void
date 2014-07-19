package aj.Java.Nullvoid.Entity.Attack;

import net.minecraft.entity.Entity;
import aj.Java.Nullvoid.Entity.EntityGlitch;

public class GlitchyBoom3 extends GlitchyBoom2 {

	public GlitchyBoom3(EntityGlitch g) {
		super(g);
	}
	@Override
	public void use(Entity target) {
		warmuptime++;
		glitch.teleportTo(glitch.posX, glitch.posY, glitch.posZ, warmuptime);
		if(warmuptime >= 100){
			glitch.worldObj.createExplosion(glitch, glitch.posX, glitch.posY, glitch.posZ, 6F, true);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		return 4;
	}
}
