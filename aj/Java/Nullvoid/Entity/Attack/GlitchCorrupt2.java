package aj.Java.Nullvoid.Entity.Attack;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import aj.Java.Nullvoid.Entity.EntityGlitch;

public class GlitchCorrupt2 extends GlitchCorrupt {
	
	public GlitchCorrupt2(EntityGlitch g) {
		super(g);
	}

	@Override
	public void use(Entity target) {
		warmuptime++;
		glitch.teleportTo(target.posX, target.posY, target.posZ, warmuptime);
		if(warmuptime >= 100){
			target.attackEntityFrom(new DamageSource("mob.glitch.damage.corrupt"), 7F);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		return 2;
	}
}
