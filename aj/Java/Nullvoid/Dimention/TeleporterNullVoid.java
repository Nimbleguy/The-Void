package aj.Java.Nullvoid.Dimention;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterNullVoid extends Teleporter {

	 private final WorldServer worldServerInstance;

	    /** A private Random() function in Teleporter */
	    @SuppressWarnings("unused")
		private final Random random;

	    /** Stores successful portal placement locations for rapid lookup. */
	    private final LongHashMap destinationCoordinateCache = new LongHashMap();

	    /**
	     * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
	     * location.
	     */
	    @SuppressWarnings("rawtypes")
		private final List destinationCoordinateKeys = new ArrayList();

	    public TeleporterNullVoid(WorldServer par1WorldServer)
	    {
	    	super(par1WorldServer);
	        this.worldServerInstance = par1WorldServer;
	        this.random = new Random(par1WorldServer.getSeed());
	    }

	    /**
	     * Place an entity in a nearby portal, creating one if necessary.
	     */
	    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	    {
	        if (this.worldServerInstance.provider.dimensionId != 1)
	        {
	            if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
	            {
	                this.makePortal(par1Entity);
	                this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
	            }
	        }
	        else
	        {
	            int i = MathHelper.floor_double(par1Entity.posX);
	            int j = MathHelper.floor_double(par1Entity.posY) - 1;
	            int k = MathHelper.floor_double(par1Entity.posZ);
	            for (int l = -2; l <= 2; ++l)
	            {
	                for (int i1 = -2; i1 <= 2; ++i1)
	                {
	                    for (int j1 = -1; j1 < 3; ++j1)
	                    {
	                    }
	                }
	            }

	            par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
	            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	        }
	    }

	    /**
	     * Place an entity in a nearby portal which already exists.
	     */
	    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	    {
	    	par1Entity.setLocationAndAngles(par2, par4, par6, par1Entity.rotationYaw, par1Entity.rotationPitch);
            return true;
	    }

	    public boolean makePortal(Entity par1Entity)
	    {
	        return false;
	    }

	    /**
	     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
	     * WorldServer.getTotalWorldTime() value.
	     */
	    @SuppressWarnings("rawtypes")
		public void removeStalePortalLocations(long par1)
	    {
	        if (par1 % 100L == 0L)
	        {
	            Iterator iterator = this.destinationCoordinateKeys.iterator();
	            long j = par1 - 600L;

	            while (iterator.hasNext())
	            {
	                Long olong = (Long)iterator.next();
	                PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());

	                if (portalposition == null || portalposition.lastUpdateTime < j)
	                {
	                    iterator.remove();
	                    this.destinationCoordinateCache.remove(olong.longValue());
	                }
	            }
	        }
	    }

}
