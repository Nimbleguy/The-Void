package nimble.Java.TheVoid.Dimension;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterVoid extends Teleporter {
	private final WorldServer world;
	
	public TeleporterVoid(WorldServer w){
		super(w);
		world = w;
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw){
		int i = MathHelper.floor_double(entityIn.posX);
        int j = MathHelper.floor_double(entityIn.posY);
        int k = MathHelper.floor_double(entityIn.posZ);

        entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float p_180620_2_){
		return false;
	}
	
	@Override
	public boolean makePortal(Entity p_85188_1_){
		return true;
	}
	
	@Override
	public void removeStalePortalLocations(long p_85189_1_){
		//Blank because no portal locations to be stale.
	}
	
}
