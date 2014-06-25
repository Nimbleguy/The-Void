package aj.Java.Nullvoid.Effects;

public enum Effect {
	LIGHT("Light"),
	BLANK("");
	private String identity = "";
	private Effect(String identifier){
		identity = identifier;
	}
	public String getIdentifier(){
		return identity;
	}
}
