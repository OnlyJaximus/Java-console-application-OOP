package ui;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


import model.Vlasnik;
import utils.PomocnaKlasa;
import utils.SortiranjeID;
import utils.VlasnikNameComparator;
import utils.VlasnikPolozeneIzlobeComparator;

public class VlasnikUI {
	
	public static ArrayList<Vlasnik> sviVlasnici = new ArrayList<Vlasnik>();
	
		/** METODE ZA ISPIS OPCIJA *****/
		public static void ispisiTekstVlasnikOsnovneOpcije() {
			System.out.println("\nRad sa vlasnicima - opcije");
			System.out.println("\tOpcija broj 1 - unos podataka novog korisnika");
			System.out.println("\tOpcija broj 2 - izmena podataka korisnika");
			System.out.println("\tOpcija broj 3 - brisanje podataka korisnika");
			System.out.println("\tOpcija broj 4 - ispis podataka svih korisnika");
			System.out.println("\tOpcija broj 5 - pretraga korisnika po pocetnim slovima imena");
			System.out.println("\tOpcija broj 6 - pretraga korisnika po datumu od - do");
			System.out.println("\tOpcija broj 7 - ispis podataka odredjenog korisnika sa njegovim ljubimcima koje ima");
			System.out.println("\tOpcija broj 8 - ispis podataka odredjenog korisnika sa njegovim dipomama");
			System.out.println("\tOpcija broj 9 - sortiranje korisnika po imenu");
			System.out.println("\tOpcija broj 10 - soritrnaje vlasnika po broju pobedjenih izlozbi");
			System.out.println("\t\t.....");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		}
	
		
		
		/** MENI OPCIJA *****/
		public static void meniVlasnikUI() {
			int odluka = -1;
			while(odluka != 0) {
				ispisiTekstVlasnikOsnovneOpcije();
				System.out.print("opcija:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				switch(odluka) {
				case 0:
					System.out.println("Izlaz");
					break;
				case 1:
					unosNovogVlasnika();
					break;
				case 2:	
					izmenaVlasnika();
					break;
				case 3:	
					brisanjeVlasnika();
					break;
				case 4:	
					soritranjeId();
					ispisiSveVlasnike();
					break;
				case 5:	
					pretragaKorisnikaPoPocetnimSlovima();
					break;
				case 6:	
					pretragaPoDatumu();
					break;
				case 7:	
					Vlasnik vlasnik = pretragaVlasnikaIndex();
					if(vlasnik != null) {
						System.out.println(vlasnik.toStrilgAllLjubimac());
					}
					break;
				case 8:	
					Vlasnik vlasnik2 = pretragaVlasnikaIndex();
					if(vlasnik2 != null) {
						System.out.println(vlasnik2.toStrilgAllDiploma());
					}
					break;
				case 9:
					sortirajVlasnikePoImenu();
					break;
				case 10:
					sortirajVlasnikePoBrojuOsvojenihIzloba();
					break;
				 default:
					 System.out.println("Nepostojeca komanda");
					 break;
				}
			}
		}
		
		/** METODE ZA ISPIS VLASNIKA *****/
		public static void ispisiSveVlasnike() {
			if(sviVlasnici.size() >  0) {
			for(int i = 0; i < sviVlasnici.size(); i++) {
				System.out.println(sviVlasnici.get(i).toString());
			}
		 }else {
			 System.out.println("Ne postoje korisnici u evidenciji. Lista je prazna.");
		 }
		}
		
		
		public static void ispisOdredjenogVlasnika(Vlasnik obj) {
			System.out.println(obj.toString());
		}
		
		
		
		
		
		/** METODE ZA UNOS, IZMENU, BRISANJE *****/
		
		// Unos
		public static void unosNovogVlasnika() {
			
			String index;
		    System.out.print("Unesite index vlasnika (3-5 index length):");
		    index = PomocnaKlasa.ocitajText().toUpperCase();
		    int duzina = index.length();
		    while(!PomocnaKlasa.isIndexLengthValid(index)) {
		    	System.out.print("Uneli ste index koji je duzine [ " + duzina + " ], i nije u skladu sa pravilom o duzini indexa.\n\tPokusaj opet:");
		    	index = PomocnaKlasa.ocitajText().toUpperCase();
		    	duzina = index.length();
		    }
			
			
			while(pretragaVlasnikaIndex(index) != null) {
				System.out.print("Korsinik sa indexom " + index + " vec postoji u evidenciji. Pokusaj opet:");
				index = PomocnaKlasa.ocitajText();
			}
			
			String imeVlasnika;
			do {
			System.out.print("Unesite ime vlasnika:");
		    imeVlasnika = PomocnaKlasa.ocitajText().trim();
			}while(!PomocnaKlasa.isFirstAndSecondNameValid(imeVlasnika));
			
			
			String prezimeVlasnika;
			do {
			System.out.print("Unesite prezime vlasnika:");
			prezimeVlasnika = PomocnaKlasa.ocitajText().trim();
			}while(!PomocnaKlasa.isFirstAndSecondNameValid(prezimeVlasnika));
			 
			LocalDate datum = null;
			String stringDatum;
			do {
				System.out.print("Unesite datum rodjenja u formatu (dd.MM.yyyy):");
				stringDatum = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isLocalDate(stringDatum));
			datum = LocalDate.parse(stringDatum, Vlasnik.formatter);
			
			int godine = PomocnaKlasa.ageCalculator(datum);
			
			if(godine > 18) {
			System.out.print("Unesite mesto stanovanja:");
			String mestoStanovanja = PomocnaKlasa.ocitajText();
			
			
			Vlasnik vlasnik = new Vlasnik(0, index, imeVlasnika, prezimeVlasnika, datum, mestoStanovanja);
			sviVlasnici.add(vlasnik);
			
			
			odlukaOIspisu();
			
			while(PomocnaKlasa.ocitajOdlukuOPotvrdi("korisniku dodeliti odredjenog ljubimca") == 'Y') {
				VlasnistvoUI.dodeliVlasnikuLjubimca(vlasnik);
			}
			
			}else {
				System.out.println("Osoba nije punoletna. Prekida se izvrsenje unosa.");
			}
			
		}
		
		
		
		
		
		
		// Izmena
			public static void izmenaVlasnika() {
			Vlasnik vlasnik = pretragaVlasnikaIndex();
			if(vlasnik != null) {
			
				String izmenaIme;
				do {	
				System.out.print("Unesite ime izmena:");
				izmenaIme = PomocnaKlasa.ocitajText();
				}while(!PomocnaKlasa.isFirstAndSecondNameValid(izmenaIme));
			
				String izmenaPrezime;
				do {
				System.out.print("Unesite prezime izmena:");	
				izmenaPrezime = PomocnaKlasa.ocitajText();
				}while(!PomocnaKlasa.isFirstAndSecondNameValid(izmenaPrezime));
				
				
				LocalDate datum = null;
				String stringDatumIzmena;
				do {
					System.out.print("Unesite datum rodjenja za izmenu u formatu (dd.MM.yyyy):");
					stringDatumIzmena = PomocnaKlasa.ocitajText();
				}while(!PomocnaKlasa.isLocalDate(stringDatumIzmena));
				
				datum = LocalDate.parse(stringDatumIzmena, Vlasnik.formatter);
				
				int godine = PomocnaKlasa.ageCalculator(datum);
				
				if(godine > 18) {
					System.out.print("Unesite mesto stanovanja izmena:");
					String mestoStanovanjaIzmena = PomocnaKlasa.ocitajText();
					
					vlasnik.setImeVlasnik(izmenaIme);
					vlasnik.setPrezimeVlasnik(izmenaPrezime);
					vlasnik.setDatumRodjnja(datum);
					vlasnik.setMestoStanovanja(mestoStanovanjaIzmena);
					
					
					
					
					odlukaOIspisu();
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("da uklonite korisnika da ne vodi brigu o ljbimcu") == 'Y') {
						VlasnistvoUI.uklonitiVlasnikaOdLjubimca(vlasnik);
					}
					
					
					odlukaOIspisu();
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("da dodelite korisniku odredjenog ljubimca") == 'Y') {
						VlasnistvoUI.dodeliVlasnikuLjubimca(vlasnik);
					}
					
					}else {
						System.out.println("Proverite godinu rodjenja korisnika. Korisnik nije punoletan");
				}
			}
		}
		
			
			
			
			// Brisanje
			public static void brisanjeVlasnika() {
			 int pozicija = pretragaVlasnikaPozicija();
			 if(pozicija > -1) {
				 	sviVlasnici.remove(pozicija);
				 	System.out.println("Podaci su uspesno obrisani iz evidencije.");
				 }
			 }
			
			
			
			/** METODA U VEZI ODLUKE ZA ISPIS */
			public static void odlukaOIspisu() {
				System.out.print("Da li zelite da vidite listu ljubimaca pre pretrage:\n\tOpcija 1 - YES\n\tOpcija 2- NO\nopcija:");
				int opcija = PomocnaKlasa.ocitajCeoBroj();
				
				switch(opcija) {
				case 1:
					LjubimacUI.ispisOdredjeniInfoLjubimac();
					break;
				case 2:
					break;
				}
			}
			
			
			
			
			/** ISPIS ODREDJENIH INFO ****/
			public static void ispisiOdredjeneInfoVlasnik() {
				for(int i = 0; i < sviVlasnici.size(); i++) {
					Vlasnik vlasnik = sviVlasnici.get(i);
					System.out.println("\t[Ime korisnika = " + vlasnik.getImeVlasnik() + "], [Index korisnika = " + vlasnik.getIndexVlasnik() + "]");
				}
			}
			
			
			
			
		
		
		/** METODE ZA PRETRAGU VLASNIKA *****/
		
		public static Vlasnik pretragaVlasnikaIndex(String index) {
			Vlasnik retVal = null;
			for(int i = 0; i < sviVlasnici.size(); i++) {
				Vlasnik vlasnik = sviVlasnici.get(i);
				if(vlasnik.getIndexVlasnik().equalsIgnoreCase(index)) {
					retVal = vlasnik;
					break;
				}
			}
			return retVal;
		}
		
		
		public static Vlasnik pretragaVlasnikaIndex() {
			System.out.print("Unesite index vlasnika za pretragu:");
			Vlasnik retVal = null;
			String indexPretraga = PomocnaKlasa.ocitajText();
			
			retVal = pretragaVlasnikaIndex(indexPretraga);
			if(retVal == null) {
				System.out.println("Korisnik sa indexom " + indexPretraga + " ne postoji u evidenciji!");
			}
			return retVal;
			
		}
		
		
		public static int pretragaVlasnikaPozicija() {
			int pozicija = -1;
			System.out.print("Unesite index korisnika za brisanje:");
			String indexKorisnika = PomocnaKlasa.ocitajText();
			for(int i = 0; i < sviVlasnici.size(); i++) {
				Vlasnik vlasnik = sviVlasnici.get(i);
				if(vlasnik.getIndexVlasnik().equalsIgnoreCase(indexKorisnika)) {
					pozicija = i;
					break;
				}
			}
			if(pozicija == -1) {
				System.out.println("Korisnik sa indeoxm " + indexKorisnika + " ne postoji u evidenciji!");
			}
			return pozicija;
			
		}
		
		
		
		public static Vlasnik pretragaKorisnikaPoPocetnimSlovima() {
			 System.out.print("Unesite pocetna slova imena za pretragu:");
			 Vlasnik retVal = null;
			 String pocetnaSlovaImena = PomocnaKlasa.ocitajText();
			 
			 for(int i = 0; i < sviVlasnici.size(); i++) {
				 Vlasnik vlasnik = sviVlasnici.get(i);
				 if(vlasnik.getImeVlasnik().toLowerCase().startsWith(pocetnaSlovaImena.toLowerCase())) {
					 retVal =  vlasnik;
					 ispisOdredjenogVlasnika(retVal);
				 }
			 }
			 if(retVal == null) {
				 System.out.println("Ne postoji korisnik sa unetim pocetnim slovima");
			 }
			 return retVal;
			 
		}
		
		
			public static Vlasnik pretragaVlasnikaPoId(int idVlasnik) {
				Vlasnik retVal = null;
				for(int i = 0; i < sviVlasnici.size(); i++) {
					Vlasnik vl = sviVlasnici.get(i);
					if(vl.getIdVlasnik() == idVlasnik) {
						retVal = vl;
						break;
					}
				}
				return retVal;
						
			}
		
		
		
		
		
		
		
		
		
	
		
		
		
		/** PRETRAGA PO DATUMU OD NEKOG DATUMA DO NEKOG DATUMA *****/
		public static void pretragaPoDatumu() {
			LocalDate datumMin;
			String stringDatumMin;
			do {
				System.out.print("Unesite minimalni datum:");
				stringDatumMin = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isLocalDate(stringDatumMin));
			datumMin = LocalDate.parse(stringDatumMin, Vlasnik.formatter);
			
			
			LocalDate datumMax;
			String stringDatumMax;
			do {
				System.out.print("Unesite maximalni datum:");
				stringDatumMax = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isLocalDate(stringDatumMax));
			datumMax = LocalDate.parse(stringDatumMax, Vlasnik.formatter);
			
			
			Vlasnik retVal = null;
			
			for(int i = 0; i < sviVlasnici.size(); i++) {
				 Vlasnik vlasnik = sviVlasnici.get(i);
				 if(vlasnik.getDatumRodjnja().compareTo(datumMin) >= 0 && vlasnik.getDatumRodjnja().compareTo(datumMax) <= 0) {
					 retVal = vlasnik;
					 ispisOdredjenogVlasnika(vlasnik);
				 }	
			}
		
			if(retVal == null) {
				System.out.println("Za unete dautme od " +  datumMin + " do " + datumMax + " nema nikakvih rezultata.");
			}
		}
		
		
		
		
		
		/** METODA ZA SORTIRANJE VLASNIKA *****/
		public static void sortirajVlasnikePoImenu() {
			System.out.println("Vlasnike je moguce sortirati\n\tOpcija broj 1 - Ime rastuce \n\tOpcija broj 2 - Ime opadajuce");
			int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
			switch(sortOpcija) {
			case 1:
				Collections.sort(sviVlasnici, new VlasnikNameComparator(1));
				System.out.println("Vlasnici nakon sortiranja:");
				ispisiSveVlasnike();
				break;
			case 2:	
				Collections.sort(sviVlasnici, new VlasnikNameComparator(-1));
				System.out.println("Vlasnici nakon sortiranja:");
				ispisiSveVlasnike();
				break;
			 default:
				 break;
			}
		}
		
		
		
		
		public static void soritranjeId() {
			Collections.sort(sviVlasnici, SortiranjeID.VlasnikIdKomparator);
		}
		
		
		
		public static void sortirajVlasnikePoBrojuOsvojenihIzloba() {
			System.out.println("Vlasnike je moguce sortirati\n\tOpcija broj 1 - broju osvojenih izlobi - rastuce \n\tOpcija broj 2 - broju osvojenih izlobi - rastuce");
			int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
			switch(sortOpcija) {
			case 1:
				Collections.sort(sviVlasnici, new VlasnikPolozeneIzlobeComparator(1));
				break;
				
			case 2:
				Collections.sort(sviVlasnici, new VlasnikPolozeneIzlobeComparator(-1));
				break;
			default:
				break;
				
			}
		
		}
		
		
		
		
		
		
		
		
		
			/** METODE ZA UCITAVANJE PODATAKA *****/ 
		 static void citajIzFajlaVlasnike(File dokument) throws IOException {
			if(dokument.exists()) {
				BufferedReader in = new BufferedReader(new FileReader(dokument));
				
				in.mark(1);
				if(in.read() != '\ufeff') {
					in.reset();
					
				}
				
				String s2;
				while((s2 = in.readLine()) != null) {
					Vlasnik vlasnik = new Vlasnik(s2);
					sviVlasnici.add(vlasnik);
				}
				in.close();
			 }else {
				System.out.println("Ne postoji fajl");
			}
		}	
		
		
		
		
		static void pisiUFajlVlasnike(File dokument) throws IOException {
			PrintWriter pw = new PrintWriter(new FileWriter(dokument));
			for(Vlasnik vlasnik : sviVlasnici) {
				pw.println(vlasnik.toFileRepresentation());
			}
			pw.flush();
			pw.close();
		}
		
		
		
		
		
		
		


}
