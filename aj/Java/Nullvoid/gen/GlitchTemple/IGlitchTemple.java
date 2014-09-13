package aj.Java.Nullvoid.gen.GlitchTemple;

import aj.Java.Nullvoid.Coords;

public interface IGlitchTemple {
	public Coords[] getExits();
	public Coords[] getEntrances();
	public Coords getCenter();
	
	public boolean canExitTo(IGlitchTemple part, Coords exit);
	public boolean canEnterFrom(IGlitchTemple part, Coords entrance);
	
	public boolean connectsToTower();
}
