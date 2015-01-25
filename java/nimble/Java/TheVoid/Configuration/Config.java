package nimble.Java.TheVoid.Configuration;

import net.minecraftforge.common.config.Configuration;
import nimble.Java.TheVoid.VoidMod;

public class Config {
	private Configuration config;
	public int test = 0;
	
	public Config(Configuration c){
		config = c;
	}
	
	public void setConfig(Configuration c){
		config = c;
	}
	
	public void loadConfig() {
	    test = config.getInt("My Config Integer", EnumConfigCategories.GENERAL.getCategory(), test, 0, Integer.MAX_VALUE, "Testing stuff");

	    if(config.hasChanged()){
	    	config.save();
	    }
	}
	public Configuration getConfig(){
		return config;
	}
	
	public enum EnumConfigCategories{
		GENERAL(Configuration.CATEGORY_GENERAL);
		
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
