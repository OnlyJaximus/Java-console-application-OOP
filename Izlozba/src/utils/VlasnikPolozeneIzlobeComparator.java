package utils;

import java.util.Comparator;

import model.Diploma;
import model.Vlasnik;

public class VlasnikPolozeneIzlobeComparator implements Comparator<Vlasnik> {
	
	int direction = 1;
	public VlasnikPolozeneIzlobeComparator(int direction) {
		if(direction != 1 && direction != -1) {
			direction = 1;
		}
		
		this.direction = direction;
	}
	
	
	
	public int compare(Vlasnik ob1, Vlasnik ob2) {
		int retVal = 0;
		if(ob1 != null && ob2 != null) {
			int brojacPolozenihIzlozbiObj1 = 0;
			for(Diploma dip : ob1.getDiplome()) {
				if(dip.sracunajOcenu() > 5) {
					brojacPolozenihIzlozbiObj1++;
				}
			}
			
			int brojacPolozenihIzlozbiObj2 = 0;
			for(Diploma dip : ob2.getDiplome()) {
				if(dip.sracunajOcenu() > 5) {
					brojacPolozenihIzlozbiObj2++;
				}
			}
			
			retVal = brojacPolozenihIzlozbiObj1 - brojacPolozenihIzlozbiObj2;
			
		}
		return retVal * direction;
	}
	

}
