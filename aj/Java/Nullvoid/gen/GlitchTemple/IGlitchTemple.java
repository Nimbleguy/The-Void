package aj.Java.Nullvoid.gen.GlitchTemple;

import java.util.List;

import net.minecraft.world.World;
import aj.Java.Nullvoid.Coords;

public interface IGlitchTemple {
	public Coords[] getExits();
	public Coords[] getEntrances();
	public Coords getCenter();
	
	public List<EnumRoom> getCanExitTo(Coords c);
	public List<EnumRoom> getCanEnterFrom(Coords c);
	
	public void gen(World w);
}
