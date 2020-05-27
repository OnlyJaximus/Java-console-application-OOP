package utils;

import java.util.Comparator;

import model.Izlozba;
import model.Ljubimac;
import model.Vlasnik;

public class SortiranjeID {

	
	public static Comparator<Ljubimac> LjubimacIdKomparator = new Comparator<Ljubimac>() {
		@Override
		public int compare(Ljubimac ob1, Ljubimac ob2) {
			if(ob1.getIdLjubimac() > ob2.getIdLjubimac()) {
				return 1;  // swap
			}
			return -1;  // do not swap
		}
	};
	
	
	
	
	public static Comparator<Vlasnik> VlasnikIdKomparator = new Comparator<Vlasnik>() {
		
		@Override
		public int compare(Vlasnik ob1, Vlasnik ob2) {
			if(ob1.getIdVlasnik() > ob2.getIdVlasnik()) {
				return 1;
			}
			return -1;
		}
	};
	
	
	public static Comparator<Izlozba> IzlozbaIdKomparator = new Comparator<Izlozba>() {
		
		@Override
		public int compare(Izlozba ob1, Izlozba ob2) {
			if(ob1.getIdIzlozba() > ob2.getIdIzlozba()) {
				return 1;
			}
			return -1;
		}
	};
	

}
