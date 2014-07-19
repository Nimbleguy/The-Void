package aj.Java.Nullvoid.Entity.Attack;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;

public class SpawnEntity implements IGlitchAttack {
	
	EntityGlitch glitch;
	
	public SpawnEntity(EntityGlitch master, Entity toSpawn){
		glitch = master;
	}
	
	@Override
	public void use(Entity target) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTier() {
		// TODO Auto-generated method stub
		return 0;
	}

}
