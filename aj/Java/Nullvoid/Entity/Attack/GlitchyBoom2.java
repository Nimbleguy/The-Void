package aj.Java.Nullvoid.Entity.Attack;

import net.minecraft.entity.Entity;
import aj.Java.Nullvoid.Entity.EntityGlitch;

public class GlitchyBoom2 extends GlitchyBoom {

	public GlitchyBoom2(EntityGlitch g) {
		super(g);
	}
	@Override
	public void use(Entity target) {
		glitch.teleportTo(glitch.posX, glitch.posY, glitch.posZ);
		glitch.worldObj.createExplosion(glitch, glitch.posX, glitch.posY, glitch.posZ, 8F, false);
		isDone = true;
	}

	@Override
	public int getTier() {
		return 3;
	}
}
