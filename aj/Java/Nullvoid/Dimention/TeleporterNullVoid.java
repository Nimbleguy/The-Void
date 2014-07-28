package aj.Java.Nullvoid.Dimention;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
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
	    @SuppressWarnings("unchecked")
		public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	    {
	        short short1 = 128;
	        double d3 = -1.0D;
	        int i = 0;
	        int j = 0;
	        int k = 0;
	        int l = MathHelper.floor_double(par1Entity.posX);
	        int i1 = MathHelper.floor_double(par1Entity.posZ);
	        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
	        boolean flag = true;
	        double d4;
	        int k1;

	        if (this.destinationCoordinateCache.containsItem(j1))
	        {
	            PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
	            d3 = 0.0D;
	            i = portalposition.posX;
	            j = portalposition.posY;
	            k = portalposition.posZ;
	            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
	            flag = false;
	        }
	        else
	        {
	            for (k1 = l - short1; k1 <= l + short1; ++k1)
	            {
	                double d5 = (double)k1 + 0.5D - par1Entity.posX;

	                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
	                {
	                    double d6 = (double)l1 + 0.5D - par1Entity.posZ;

	                    for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
	                    {
	                        if (this.worldServerInstance.getBlock(k1, i2, l1) == Blocks.portal)
	                        {
	                            while (this.worldServerInstance.getBlock(k1, i2 - 1, l1) == Blocks.portal)
	                            {
	                                --i2;
	                            }

	                            d4 = (double)i2 + 0.5D - par1Entity.posY;
	                            double d7 = d5 * d5 + d4 * d4 + d6 * d6;

	                            if (d3 < 0.0D || d7 < d3)
	                            {
	                                d3 = d7;
	                                i = k1;
	                                j = i2;
	                                k = l1;
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        if (d3 >= 0.0D)
	        {
	            if (flag)
	            {
	                this.destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
	                this.destinationCoordinateKeys.add(Long.valueOf(j1));
	            }

	            double d8 = (double)i + 0.5D;
	            double d9 = (double)j + 0.5D;
	            d4 = (double)k + 0.5D;
	            int j2 = 1;

	            int k2 = par1Entity.getTeleportDirection();

	            if (j2 > -1)
	            {
	                int l2 = Direction.rotateLeft[j2];
	                int i3 = Direction.offsetX[j2];
	                int j3 = Direction.offsetZ[j2];
	                int k3 = Direction.offsetX[l2];
	                int l3 = Direction.offsetZ[l2];
	                boolean flag1 = !this.worldServerInstance.isAirBlock(i + i3 + k3, j, k + j3 + l3) || !this.worldServerInstance.isAirBlock(i + i3 + k3, j + 1, k + j3 + l3);
	                boolean flag2 = !this.worldServerInstance.isAirBlock(i + i3, j, k + j3) || !this.worldServerInstance.isAirBlock(i + i3, j + 1, k + j3);

	                if (flag1 && flag2)
	                {
	                    j2 = Direction.rotateOpposite[j2];
	                    l2 = Direction.rotateOpposite[l2];
	                    i3 = Direction.offsetX[j2];
	                    j3 = Direction.offsetZ[j2];
	                    k3 = Direction.offsetX[l2];
	                    l3 = Direction.offsetZ[l2];
	                    k1 = i - k3;
	                    d8 -= (double)k3;
	                    int i4 = k - l3;
	                    d4 -= (double)l3;
	                    flag1 = !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j, i4 + j3 + l3) || !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j + 1, i4 + j3 + l3);
	                    flag2 = !this.worldServerInstance.isAirBlock(k1 + i3, j, i4 + j3) || !this.worldServerInstance.isAirBlock(k1 + i3, j + 1, i4 + j3);
	                }

	                float f1 = 0.5F;
	                float f2 = 0.5F;

	                if (!flag1 && flag2)
	                {
	                    f1 = 1.0F;
	                }
	                else if (flag1 && !flag2)
	                {
	                    f1 = 0.0F;
	                }
	                else if (flag1 && flag2)
	                {
	                    f2 = 0.0F;
	                }

	                d8 += (double)((float)k3 * f1 + f2 * (float)i3);
	                d4 += (double)((float)l3 * f1 + f2 * (float)j3);
	                float f3 = 0.0F;
	                float f4 = 0.0F;
	                float f5 = 0.0F;
	                float f6 = 0.0F;

	                if (j2 == k2)
	                {
	                    f3 = 1.0F;
	                    f4 = 1.0F;
	                }
	                else if (j2 == Direction.rotateOpposite[k2])
	                {
	                    f3 = -1.0F;
	                    f4 = -1.0F;
	                }
	                else if (j2 == Direction.rotateRight[k2])
	                {
	                    f5 = 1.0F;
	                    f6 = -1.0F;
	                }
	                else
	                {
	                    f5 = -1.0F;
	                    f6 = 1.0F;
	                }

	                double d10 = par1Entity.motionX;
	                double d11 = par1Entity.motionZ;
	                par1Entity.motionX = d10 * (double)f3 + d11 * (double)f6;
	                par1Entity.motionZ = d10 * (double)f5 + d11 * (double)f4;
	                par1Entity.rotationYaw = par8 - (float)(k2 * 90) + (float)(j2 * 90);
	            }
	            else
	            {
	                par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	            }

	            par1Entity.setLocationAndAngles(d8, d9, d4, par1Entity.rotationYaw, par1Entity.rotationPitch);
	            return true;
	        }
	        else
	        {
	            return false;
	        }
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
