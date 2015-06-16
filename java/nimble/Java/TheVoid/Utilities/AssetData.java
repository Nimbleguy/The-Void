package nimble.Java.TheVoid.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import net.minecraft.util.ResourceLocation;

public class AssetData {
	
	public final String omegaValley;
	
	public final String pillarO1;
	public final String pillarV1;
	
	public final String omegaGenerator;
	
	public AssetData(){
		String omegaV = "";
		String omegaG = "";
		String pO1 = "";
		String pV1 = "";
		try {
			//Worldgen
			BufferedReader omegaVRead = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/thevoid/worldgen/omegaValley.txt"), "UTF-8"));
			omegaV = omegaVRead.readLine();
			
			BufferedReader pillarO1Read = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/thevoid/worldgen/pillarO1.txt"), "UTF-8"));
			pO1 = pillarO1Read.readLine();
			
			BufferedReader pillarV1Read = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/thevoid/worldgen/pillarV1.txt"), "UTF-8"));
			pV1 = pillarV1Read.readLine();
			
			//Multiblocks
			BufferedReader omegaGRead = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/thevoid/structures/omegaGenerator.txt"), "UTF-8"));
			omegaG = omegaGRead.readLine();
		} catch (UnsupportedEncodingException e) {
			//TODO: Proper errors
			e.printStackTrace();
		} catch (IOException e) {
			//TODO: Proper errors
			e.printStackTrace();
		}
		
		omegaValley = omegaV;
		pillarO1 = pO1;
		pillarV1 = pV1;
		omegaGenerator = omegaG;
	}
}
