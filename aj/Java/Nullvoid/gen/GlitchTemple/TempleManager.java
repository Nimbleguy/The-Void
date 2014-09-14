package aj.Java.Nullvoid.gen.GlitchTemple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import aj.Java.Nullvoid.Coords;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TempleManager {
	public static List<TempleManager> temples = new ArrayList<TempleManager>(100);
	public static HashMap<EnumRoom, List<Class<? extends IGlitchTemple>>> rooms = new HashMap<EnumRoom, List<Class<? extends IGlitchTemple>>>(20);
	public IGlitchTemple[][][] map = new IGlitchTemple[15][15][15];
	private Coords entCenter = null;
	
	public TempleManager(Coords enterenceCenter){
		entCenter = enterenceCenter;
		Random r = new Random();
		int roomsGenned = 15^3;
		int[] last = new int[] {7, 14, 7};
		int dir = 0;
		boolean[][][] roomPut = new boolean[15][15][15];
		roomPut[7][14][7] = true;
		roomsGenned--;
		Class<? extends IGlitchTemple> clazz = rooms.get(EnumRoom.ENTERANCE).get(r.nextInt(rooms.get(EnumRoom.ENTERANCE).size()));
		if(clazz == null){
			map[7][14][7] = null;
		}
		else{
			try {
				map[7][14][7] = clazz.getConstructor(Coords.class).newInstance(enterenceCenter);
			} catch (Exception e) {
				System.err.println("There has been an error when loding temples. Please report this to Nimbleguy:");
				e.printStackTrace(System.err);
			}
		}
		Coords current = new Coords(enterenceCenter.getX(), enterenceCenter.getY(), enterenceCenter.getZ());
		while(roomsGenned > 0){
			dir = r.nextInt(3);
			int mod = r.nextInt(2) == 0 ? -1 : 1;
			last[dir] = last[dir] + mod;
			if(last[dir] > 14 || last[dir] < 0){
				last[dir] = last[dir] - mod;
				continue;
			}
			if(roomPut[last[0]][last[1]][last[2]] == true){
				last[dir] = last[dir] - mod;
				continue;
			}
			switch(dir){
			case 0:
				current.setX(current.getX() + (mod * 15));
				break;
			case 1:
				current.setY(current.getY() + (mod * 15));
				break;
			case 2:
				current.setZ(current.getZ() + (mod * 15));
				break;
			}
			EnumRoom room = EnumRoom.values()[r.nextInt(EnumRoom.values().length)];
			//System.out.println(rooms.get(room).size());
			while(room.equals(EnumRoom.ENTERANCE)){
				room = EnumRoom.values()[r.nextInt(EnumRoom.values().length)];
			}
			clazz = rooms.get(room).get(r.nextInt(rooms.get(room).size()));
			try {
				map[last[0]][last[1]][last[2]] = clazz.getConstructor(Coords.class).newInstance(current);
			} catch (Exception e) {
				System.err.println("There has been an error when loding temples. Please report this to Nimbleguy:");
				e.printStackTrace(System.err);
			}
			roomPut[last[0]][last[1]][last[2]] = true;
			roomsGenned--;
		}
		temples.add(this);
		System.out.println("INITED");
		for(int x = 0; x < 15; x++){
			for(int y = 0; y < 15; y++){
				for(int z = 0; z < 15; z++){
					System.out.println(x + "," + y + "," + z + "," + map[x][y][z]);
				}
			}
		}
	}
	private TempleManager(){
		
	}
	
	public static void initilize(){
		//Category Init
		for(EnumRoom e : EnumRoom.values()){
			rooms.put(e, new ArrayList<Class<? extends IGlitchTemple>>(20));
		}
		
		//Entrance Rooms
		//rooms.get(EnumRoom.ENTERANCE).add(null);
		rooms.get(EnumRoom.ENTERANCE).add(GlitchEnterence.class);
		
		//Passageway Rooms
		//rooms.get(EnumRoom.PASSAGEWAY).add(null);
		rooms.get(EnumRoom.PASSAGEWAY).add(GlitchCrossway.class);
	}
	
	public static NBTTagCompound saveToNBT(NBTTagCompound t){
		NBTTagList l = new NBTTagList();
		for(TempleManager tem : temples){
			l.appendTag(tem.save(new NBTTagList()));
		}
		t.setTag("temples", l);
		return t;
	}
	public static void readFromNBT(NBTTagCompound t){
		temples.clear();
		NBTTagList l = t.getTagList("temples", Constants.NBT.TAG_COMPOUND);
		for(int count = 0; count < l.tagCount(); count++){
			TempleManager tem = new TempleManager();
			tem.read(l.getCompoundTagAt(count));
			temples.add(tem);
		}
	}
	
	public NBTTagCompound save(NBTTagList t){
		for(int x = 0; x < 15; x++){
			for(int y = 0; y < 15; y++){
				for(int z = 0; z < 15; z++){
					if(map[x][y][z] != null){
						IGlitchTemple tem = map[x][y][z];
						NBTTagCompound tag = new NBTTagCompound();
						tag.setString("kind", tem.getClass().getName());
						tag.setIntArray("coords", new int[] {x, y, z});
						t.appendTag(tag);
					}
				}
			}
		}
		NBTTagCompound finalTag = new NBTTagCompound();
		finalTag.setTag("map", t);
		finalTag.setIntArray("entCoords", entCenter.getCoords());
		return finalTag;
	}
	public void read(NBTTagCompound t){
		int[] coordEnt = t.getIntArray("entCoords");
		entCenter = new Coords(coordEnt[0], coordEnt[1], coordEnt[2]);
		NBTTagList tagMap = t.getTagList("map", Constants.NBT.TAG_COMPOUND);
		for(int c = 0; c < tagMap.tagCount(); c++){
			try{
				NBTTagCompound room = tagMap.getCompoundTagAt(c);
				int[] coord = room.getIntArray("coords");
				Coords coords = new Coords(coord[0], coord[1], coord[2]);
				IGlitchTemple temple = (IGlitchTemple) Class.forName(room.getString("kind")).getConstructor(Coords.class).newInstance(coords);
				map[coord[0]][coord[1]][coord[2]] = temple;
			}
			catch(Exception e){
				System.err.println("There has been an error when loding temples. Please report this to Nimbleguy:");
				e.printStackTrace(System.err);
			}
		}
	}
	public void populate(World w){
		for(int x = 0; x < 15; x++){
			for(int y = 0; y < 15; y++){
				for(int z = 0; z < 15; z++){
					if(map[x][y][z] != null){
						map[x][y][z].gen(w);
						System.out.println("GENNED");
					}
				}
			}
		}
	}
}
