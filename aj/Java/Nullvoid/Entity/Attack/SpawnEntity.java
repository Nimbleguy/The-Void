package aj.Java.Nullvoid.Entity.Attack;

import java.lang.reflect.Constructor;
import aj.Java.Nullvoid.Entity.EntityGlitch;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SpawnEntity implements IGlitchAttack {
	
	EntityGlitch glitch;
	Class<? extends Entity> spawn;
	
	public SpawnEntity(EntityGlitch master, Class<? extends Entity> toSpawn){
		glitch = master;
		spawn = toSpawn;
	}
	
	@Override
	public void use(Entity target) {
		try {
			Constructor<? extends Entity> c = spawn.getDeclaredConstructor(World.class);
			int[] coords = new int[3];
			for(int i = 0; i < 8; i++){
				coords[0] = (int) glitch.posX;
				coords[1] = (int) glitch.posY;
				coords[2] = (int) glitch.posZ;
				switch(i){
				case(0):
					coords[0] -= 3;
					break;
				case(1):
					coords[0] -= 2;
					coords[2] -= 2;
					break;
				case(2):
					coords[2] -= 3;
					break;
				case(3):
					coords[0] += 2;
					coords[2] -= 2;
					break;
				case(4):
					coords[2] += 3;
					break;
				case(5):
					coords[0] += 2;
					coords[2] += 2;
					break;
				case(6):
					coords[0] += 3;
					break;
				case(7):
					coords[0] -= 2;
					coords[2] += 2;
					break;
				}
				Entity e = c.newInstance(glitch.worldObj);
				e.setLocationAndAngles(coords[0], coords[1], coords[2], 0F, 0F);
				glitch.worldObj.spawnEntityInWorld(e);
			}
		} catch (Exception e) {
			System.err.println("There has been a fatal error with the Void Mod. Please report this to Nimbleguy.");
			e.printStackTrace(System.err);
		}
	}

	@Override
	public int getTier() {
		return 1;
	}

}
