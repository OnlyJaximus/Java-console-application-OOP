package utils;

import java.util.Comparator;

import model.Ljubimac;

public class LjubimacNameKomparator {
	
	public static Comparator<Ljubimac> LjubimacNameKomparator = new Comparator<Ljubimac>() {
		
		@Override
		public int compare(Ljubimac ob1, Ljubimac ob2) {
			String Ljubimac1 = ob1.getImeLjubimac().toUpperCase();
			String Ljubimac2 = ob2.getImeLjubimac().toUpperCase();
			
			return Ljubimac1.compareTo(Ljubimac2);
		}
	};
	
	
public static Comparator<Ljubimac> LjubimacNameKomparator2 = new Comparator<Ljubimac>() {
		
		@Override
		public int compare(Ljubimac ob1, Ljubimac ob2) {
			String Ljubimac1 = ob1.getImeLjubimac().toUpperCase();
			String Ljubimac2 = ob2.getImeLjubimac().toUpperCase();
			
			return Ljubimac2.compareTo(Ljubimac1);
		}
	};
	
	
	
	
	
	
	
	

}
