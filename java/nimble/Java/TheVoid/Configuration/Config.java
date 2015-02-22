package nimble.Java.TheVoid.Configuration;

import net.minecraftforge.common.config.Configuration;
import nimble.Java.TheVoid.VoidMod;

public class Config {
	private Configuration config;
	public int dimid = -42;
	public int biomeid = 64;
	
	public Config(Configuration c){
		config = c;
	}
	
	public void setConfig(Configuration c){
		config = c;
	}
	
	public void loadConfig() {
	    dimid = config.getInt("dimid", EnumConfigCategories.DIMENSION.getCategory(), dimid, Integer.MIN_VALUE, Integer.MAX_VALUE, "Void Dimension ID");
	    biomeid = config.getInt("biomeid", EnumConfigCategories.DIMENSION.getCategory(), biomeid, 0, Integer.MAX_VALUE, "Void Biome ID");

	    if(config.hasChanged()){
	    	config.save();
	    }
	}
	public Configuration getConfig(){
		return config;
	}
	
	public enum EnumConfigCategories{
		GENERAL(Configuration.CATEGORY_GENERAL),
		DIMENSION("dimension");
		
		private String category;
		private EnumConfigCategories(String s){
			VoidMod.config.getConfig().getCategory(s);
			category = s;
		}
		
		public String getCategory(){
			return category;
		}
	}
}
