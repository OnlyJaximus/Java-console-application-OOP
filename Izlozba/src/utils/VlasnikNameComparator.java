package utils;

import java.util.Comparator;

import model.Vlasnik;

public class VlasnikNameComparator implements Comparator<Vlasnik>{

	int direction = 1;
	
	public  VlasnikNameComparator(int direction) {
		if(direction != 1 && direction != -1) {
			direction = 1;
		}
		this.direction = direction;
	}
	
	
	
	@Override
	public int compare(Vlasnik ob1, Vlasnik ob2) {
		int retVal = 0;
		if(ob1 != null && ob2 != null) {
			retVal = ob1.getImeVlasnik().compareTo(ob2.getImeVlasnik());
		}
		
		return retVal * direction; 
	}
	
	

}
