package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

import model.Vlasnik;

public class PomocnaKlasa {
	
	static Scanner sc = new Scanner(System.in);
	
	public static SimpleDateFormat sff = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	public static Date date = new Date();
	
	
	
	
	
	public static String ocitajText() {
		String text = "";
		while(text == null || text.equals("")) {
			text = sc.nextLine();
		}
		return text;
	}
	
	
	
	
	
	public static double ocitajRealanBroj() {
		double realanBroj = 0;
		boolean nijeOcitan = true;
		do {
			if(sc.hasNextDouble()) {
				realanBroj = sc.nextDouble();
				nijeOcitan = false;
			}else {
				System.out.print("Pogresno unesena vrednost. Pokusaj opet:");
			}
			sc.nextLine();
		}while(nijeOcitan);
		return realanBroj;
	}
	
	
	
	
	
	
	public static int ocitajCeoBroj() {
		int ceoBroj = 0;
		boolean nijeOcitan = true;
		do {
			if(sc.hasNextInt()) {
				ceoBroj = sc.nextInt();
				nijeOcitan = false;
			}else {
				System.out.print("Pogresno unesena vrednost. Pokusaj opet:");
			}
			sc.nextLine();
		}while(nijeOcitan);
		return ceoBroj;
	}
	
	
		
	
	
		public static char ocitajKarakter() {
			char karakter = ' ';
			boolean nijeProcitano = true;
			do {
				String text = sc.next();
				if(text.length() == 1) {
					karakter = text.charAt(0);
					nijeProcitano = false;
				}else {
					System.out.print("Pogresno unesena opcija. Pokusaj opet:");
				}
				sc.nextLine();
			}while(nijeProcitano);
			return karakter;
		}
	
	
	
		
		
	
		public static char ocitajOdlukuOPotvrdi(String text) {
			System.out.print("Da li zelite " + text + "[Y/N]:");
			char odluka = ' ';
			while(! (odluka == 'Y' || odluka == 'N')) {
				odluka = ocitajKarakter();
				if(! (odluka == 'Y' || odluka == 'N')) {
					System.out.print("Opcija je Y ili N:");
				}
			}
			return odluka;
		}
	
	
	
	
		
		
		
		public static boolean isIndexLengthValid(String index) {
			if(index.equals("")) {
				return false;
			}
			
			boolean isValiddLength = index.length() >= 3  && index.length() <= 5;
			
			return isValiddLength;
		}
		
	
	
	
	
		
	
	
	
	public static boolean isLocalDate(String datum) {
		try {
			LocalDate.parse(datum,Vlasnik.formatter);
			return true;
		}catch(Exception e){
			System.out.println("Pogresan unos datum formata.");
			return false;
		}
	}
		
	
	
	
	
	
	
	
			//	 Method Age Calculator 
		public static int ageCalculator(LocalDate datum) {
			LocalDate today = LocalDate.now();
			int years = Period.between(datum, today).getYears();
			return  years;
		}
		
		
		
			// Validacija
		public static boolean isFirstAndSecondNameValid(String firstName) {
			if(firstName.equals("")) {
				return false;
			}else if(!Character.isUpperCase(firstName.charAt(0))) {
				System.out.println("Unesite prvo slovo veliko!");
				return false;
			}
			return true;
		}
		
		
		
		
		
		
		public static boolean daLiJeDrugiDatumLegit(LocalDate date1, LocalDate date2) {
		
			if(date1.compareTo(date2) > 0) {
					System.out.println("Datum 1 je veci od datuma 2!");
					return false;
				}
			 return true;
			}
		
		
		
		
		// metoda za bodove
		public static boolean brBodovaOgranicenje(int brBodova) {
			if(brBodova < 40 || brBodova > 100) {
				System.out.println("Greska prilikom unosenja bodova.");
				return false;
			}
			return true;
			}
		
	
	

	
}
