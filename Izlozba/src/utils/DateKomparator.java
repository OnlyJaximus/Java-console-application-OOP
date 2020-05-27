package utils;

import java.time.LocalDate;
import java.util.Comparator;

import model.Izlozba;

public class DateKomparator {
	
	
	public static Comparator<Izlozba> datumKomparator1 = new Comparator<Izlozba>() {
		
		@Override
		public int compare(Izlozba ob1, Izlozba ob2) {
			LocalDate datum1 = ob1.getPocetakIzlozbe();
			LocalDate datum2 = ob2.getPocetakIzlozbe();
			
			return datum1.compareTo(datum2);
		}
	};
	
	
	
	
public static Comparator<Izlozba> datumKomparator2 = new Comparator<Izlozba>() {
		
		@Override
		public int compare(Izlozba ob1, Izlozba ob2) {
			LocalDate datum1 = ob1.getPocetakIzlozbe();
			LocalDate datum2 = ob2.getPocetakIzlozbe();
			
			return datum2.compareTo(datum1);
		}
	};

}
