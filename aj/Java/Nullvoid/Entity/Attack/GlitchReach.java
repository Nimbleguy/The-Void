package aj.Java.Nullvoid.Entity.Attack;

import java.util.Random;

import aj.Java.Nullvoid.Entity.EntityGlitch;
import aj.Java.Nullvoid.Packet.PacketHandler;
import aj.Java.Nullvoid.Packet.PacketParticle;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class GlitchReach implements IGlitchAttack {
	EntityGlitch glitch;
	boolean isDone = false;
	int[] cc = new int[3];
	
	public GlitchReach(EntityGlitch glitch){
		this.glitch = glitch;
		cc[0] = (int) glitch.posX;
		cc[1] = (int) glitch.posY;
		cc[2] = (int) glitch.posZ;
	}
	
	
	@Override
	public void use(Entity target) {
		System.out.println("REACH");
		int[] tc = {(int) target.posX, (int) target.posY, (int) target.posZ};
		
		Random r = new Random();
		PacketHandler.INSTANCE.sendToDimension(new PacketParticle(
				cc[0], cc[1], cc[2],
				r.nextDouble() - (0.5D), (1D) + r.nextDouble(), r.nextDouble() - (0.5D),
				200
				), glitch.dimension);
		boolean check = false;
		if(cc[0] - tc[0] != 0){
			if(cc[0] < tc[0]){
				cc[0]++;
			}
			else{
				cc[0]--;
			}
			check = true;
		}
		if(cc[1] - tc[1] != 0){
			if(cc[1] < tc[1]){
				cc[1]++;
			}
			else{
				cc[1]--;
			}
			check = true;
		}
		if(cc[2] - tc[2] != 0){
			if(cc[2] < tc[2]){
				cc[2]++;
			}
			else{
				cc[2]--;
			}
			check = true;
		}
		if(!check){
			target.attackEntityFrom(new DamageSource("mob.glitch.damage.hand"), 5F);
			isDone = true;
		}
	}

	@Override
	public int getTier() {
		return 1;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
