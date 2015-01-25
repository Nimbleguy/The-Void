package nimble.Java.TheVoid.Utilities;

import java.io.Serializable;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private final Logger log = LogManager.getLogger("TheVoid");
	
	public <T extends Comparable<T>> void log(T message, Level l){
		log.log(l, message);
	}
}
