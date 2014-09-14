package aj.Java.Nullvoid.gen.GlitchTemple;

import java.util.List;

import scala.actors.threadpool.Arrays;
import net.minecraft.world.World;
import aj.Java.Nullvoid.Coords;
import aj.Java.Nullvoid.VoidMod;

public class GlitchCrossway implements IGlitchTemple {

	private Coords center;
	public GlitchCrossway(Coords center){
		this.center = center;
	}

	@Override
	public Coords[] getExits() {
		return new Coords[] {new Coords(center.getX() + 6, center.getY(), center.getZ()), new Coords(center.getX() - 6, center.getY(), center.getZ()), new Coords(center.getX(), center.getY(), center.getZ() + 6), new Coords(center.getX(), center.getY(), center.getZ() - 6)};
	}

	@Override
	public Coords[] getEntrances() {
		return new Coords[] {new Coords(center.getX() + 6, center.getY(), center.getZ()), new Coords(center.getX() - 6, center.getY(), center.getZ()), new Coords(center.getX(), center.getY(), center.getZ() + 6), new Coords(center.getX(), center.getY(), center.getZ() - 6)};
	}

	@Override
	public Coords getCenter() {
		return center;
	}

	@Override
	public void gen(World w) {
		for(int x = center.getX() - 7; x < center.getX() + 8; x++){
			for(int y = center.getY() - 7; y < center.getY() + 8; y++){
				for(int z = center.getZ() - 7; z < center.getZ() + 8; z++){
					if((x == center.getX() - 7 || x == center.getX() + 7) || (y == center.getY() - 7 || y == center.getY() + 7) || (z == center.getZ() - 7 || z == center.getZ() + 7)){
						w.setBlock(x, y, z, VoidMod.chamberWall);
						w.setBlockMetadataWithNotify(x, y, z, 4, 3);
					}
					else{
						w.setBlockToAir(x, y, z);
					}
				}
			}
		}
		System.out.println("CROSS");
		//TODO: Finish Generation
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnumRoom> getCanExitTo(Coords c) {
		return Arrays.asList(EnumRoom.values());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnumRoom> getCanEnterFrom(Coords c) {
		return Arrays.asList(EnumRoom.values());
	}

}
