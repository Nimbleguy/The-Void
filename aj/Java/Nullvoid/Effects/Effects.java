package aj.Java.Nullvoid.Effects;

import java.util.ArrayList;
import java.util.List;

public class Effects {
	private List<Effect> l = new ArrayList<Effect>(20);
	public void addEffect(Effect e){
		l.add(e);
	}
	public void removeEffect(Effect e){
		l.remove(e);
	}
	public List<Effect> getEffects(){
		boolean b = false;
		List<Effect> li = new ArrayList<Effect>(1);
		for(Effect e : l){
			if(e != null){
				li.add(e);
				b = true;
			}
		}
		if(!b){
			li.add(Effect.BLANK);
		}
		return li;
	}
}
