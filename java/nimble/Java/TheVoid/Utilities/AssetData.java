package nimble.Java.TheVoid.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import net.minecraft.util.ResourceLocation;

public class AssetData {
	
	public final String omegaValley;
	
	public final String omegaGenerator;
	
	public AssetData(){
		String omegaV = "";
		String omegaG = "";
		try {
			BufferedReader omegaVRead = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/thevoid/worldgen/omegaValley.txt"), "UTF-8"));
			omegaV = omegaVRead.readLine();
			
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
		omegaGenerator = omegaG;
	}
}
