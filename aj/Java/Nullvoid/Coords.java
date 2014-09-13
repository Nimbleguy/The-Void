package aj.Java.Nullvoid;

public class Coords {
	private int x;
	private int y;
	private int z;
	public Coords(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int[] getCoords(){
		return new int[] {x, y, z};
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setZ(int z){
		this.z = z;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Coords){
			Coords c = (Coords)o;
			if(c.x == x && c.y == y && c.z == z){
				return true;
			}
		}
		return false;
	}
}
