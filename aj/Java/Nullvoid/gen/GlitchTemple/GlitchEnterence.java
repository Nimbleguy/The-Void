package aj.Java.Nullvoid.gen.GlitchTemple;

import aj.Java.Nullvoid.Coords;

public class GlitchEnterence implements IGlitchTemple {
	private Coords center;
	public GlitchEnterence(Coords center){
		this.center = center;
	}

	@Override
	public Coords[] getExits() {
		return null;
	}

	@Override
	public Coords[] getEntrances() {
		return new Coords[] {center};
	}

	@Override
	public boolean canExitTo(IGlitchTemple part, Coords exit) {
		return false;
	}

	@Override
	public boolean canEnterFrom(IGlitchTemple part, Coords entrance) {
		return false;
	}

	@Override
	public boolean connectsToTower() {
		return true;
	}

	@Override
	public Coords getCenter() {
		return center;
	}

}
