package aj.Java.Nullvoid.client.fx;

import java.util.Random;

import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.world.World;

public class EntityGlitchFX extends EntityEnchantmentTableParticleFX {

	public EntityGlitchFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		Random r = new Random();
		this.particleRed = r.nextFloat();
		this.particleBlue = r.nextFloat();
		this.particleGreen = r.nextFloat();
		this.particleAlpha = r.nextFloat();
	}

}
