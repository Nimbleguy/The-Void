package aj.Java.Nullvoid.Entity.Attack;

import net.minecraft.entity.Entity;

public interface IGlitchAttack {
	
	void use(Entity target);
	
	int getTier();
}
