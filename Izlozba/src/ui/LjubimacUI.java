package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


import model.Ljubimac;
import utils.LjubimacNameKomparator;
import utils.PomocnaKlasa;
import utils.SortiranjeID;

public class LjubimacUI {

	
	static ArrayList<Ljubimac> sviLjubimci = new ArrayList<Ljubimac>();
	
	
		public static void ispisTekstLjubimacOsnovneOpcije() {
			System.out.println("\nRad sa ljubimcima - opcije");
			System.out.println("\tOpcija broj 1 - unos podataka novog ljubimca");
			System.out.println("\tOpcija broj 2 - izmena podataka ljubimca");
			System.out.println("\tOpcija broj 3 - brisanje podataka ljubimca");
			System.out.println("\tOpcija broj 4 - ispis podataka svih ljubimaca");
			System.out.println("\tOpcija broj 5 - ispis ljubimaca po vrsti");
			System.out.println("\tOpcija broj 8 - sortiranje ljubimaca po imenu");
			System.out.println("\tOpcija broj 9 - ispis podataka o odredjenom ljubimcu i svih korisnika koji vode racuna o njemu");
			System.out.println("\tOpcija broj 10 - ispis podataka o odredjenom ljubimcu i svih diploma koje ime");
			System.out.println("\t.....");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		}
		
		
		public static void meniLjubimacUI() {
			int odluka = -1;
			while(odluka != 0) {
				ispisTekstLjubimacOsnovneOpcije();
				System.out.print("opcija:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				switch(odluka) {
				case 0:
					System.out.println("Izlaz");
					break;
				case 1:
					unosNovogLjubimca();
					break;
				case 2:
					izmenaLjubimca();
					break;
				case 3:
					brisanjePodatakaLjubimca();
					break;
				case 4:
					soritranjeId();
					ispisiSveLjubimce();
					break;
				case 5:
					ispisLjubimacaPoVrsti();
					break;
				case 8:	
					sortirajLjubimce();
					break;
				case 9:	
					Ljubimac ljubimac = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
							if(ljubimac != null) {
								System.out.println(ljubimac.toStringAllVlasnik());
							}
					break;
				case 10:	
					Ljubimac ljubimac2 = LjubimacUI.pretragaLjubimcaPoId();
							if(ljubimac2 != null) {
								System.out.println(ljubimac2.toStringAllDiploma());
							}
					
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
				}
				
			}
		}
		
		/** METODE ZA ISPIS LJUBIMACA*/
		public static void ispisiLjubimceObj(Ljubimac obj) {
			System.out.println(obj.toString());
		}
		
		
		
		public  static void ispisOdredjeniInfoLjubimac() {
			for(int i = 0; i < sviLjubimci.size(); i++) {
				Ljubimac lj = sviLjubimci.get(i);
				System.out.println("\t[idLjubimca= "+ lj.getIdLjubimac() + "],[vrsta ljubimca = " + lj.getVrstaLjubimac() + "], [ime ljubimca = " + lj.getImeLjubimac() + "], [takmicarska oznaka = " + lj.getTakmicarskaOznaka() + "]");
			}
		}
		
		
		
		
		
		
		
		public static void ispisiSveLjubimce() {
		if(sviLjubimci.size() > 0) {	
			for(Ljubimac lj : sviLjubimci) {
				System.out.println(lj.toString());
			}
		}else {
			System.out.println("Lista ljubimaca u evidenciji je prazna.");
		 }	
		}
		

		
		public static void ispisLjubimacaPoVrsti() {
			System.out.print("Unesite vrstu ljubimca za ispis:\n\tOpcija broj 1 - ispis svih pasa\n\tOpcija broj 2 - ispis svih macaka\n\tOpcija broj 3 - ispis svih papagaja\nopcija:");
			
			int odluka = PomocnaKlasa.ocitajCeoBroj();
			switch(odluka) {
			case 1:
				Ljubimac retVal;
				String vrstaLjubimac = "pas";
				
				for(int i = 0; i < sviLjubimci.size(); i++) {
					Ljubimac ljubimac = sviLjubimci.get(i);
					if(ljubimac.getVrstaLjubimac().equals(vrstaLjubimac)) {
						retVal = ljubimac;
						ispisiLjubimceObj(retVal);
					}
				}
				break;	
				
			case 2:
				Ljubimac retVal2;
				String vrstaLjubimac2 = "macka";
				
				for(int i = 0; i < sviLjubimci.size(); i++) {
					Ljubimac ljubimac2 = sviLjubimci.get(i);
					if(ljubimac2.getVrstaLjubimac().equals(vrstaLjubimac2)) {
						retVal2 = ljubimac2;
						ispisiLjubimceObj(retVal2);
					}
				}
				break;	
				
			case 3:
				Ljubimac retVal3;
				String vrstaLjubimac3 = "papagaj";
				
				for(int i = 0; i < sviLjubimci.size(); i++) {
					Ljubimac ljubimac3 = sviLjubimci.get(i);
					if(ljubimac3.getVrstaLjubimac().equals(vrstaLjubimac3)) {
						retVal3 = ljubimac3;
						ispisiLjubimceObj(retVal3);
					}
				}
				break;	
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		/** METODE ZA UNOS, IZMENU I BRISANJE *****/
		
		// Unos
		public static void unosNovogLjubimca() {
			
			System.out.print("Unesite takmicarsku oznaku ljubimca (3-5 duzina oznake):");
			String takmicarskaOznaka = PomocnaKlasa.ocitajText().toUpperCase();
			int duzinaTakmOznake = takmicarskaOznaka.length();
			
			while(!PomocnaKlasa.isIndexLengthValid(takmicarskaOznaka)) {
				System.out.print("Uneli ste takmicarksu oznaku koji je duzine [ " + duzinaTakmOznake + " ], i nije u skladu sa pravilom o duzini indexa.\n\tPokusaj opet:");
				takmicarskaOznaka =  PomocnaKlasa.ocitajText().toUpperCase();
				duzinaTakmOznake = takmicarskaOznaka.length();
			}
			
			while(pretragaLjubimcaTakmicarskaOznaka(takmicarskaOznaka) != null) {
				System.out.print("Ljubimac sa takmicarskom oznakom " + takmicarskaOznaka + " postoji u evidenciji. Unesite novi index:");
				takmicarskaOznaka = PomocnaKlasa.ocitajText();
			}
			
			String vrstaLjubimac = setLjubimacVrsta();
		
			
			
			System.out.print("Unesite boju ljubimca:");
			String bojaLjubimca = PomocnaKlasa.ocitajText();
			
			
			String imeLjubimca;
			do {
			System.out.print("Unesite ime ljubimca:");
			imeLjubimca  = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isFirstAndSecondNameValid(imeLjubimca));
			
			System.out.print("Unesite godine vaseg ljubimca:");
			int godineLjubimca = PomocnaKlasa.ocitajCeoBroj();
			
			Ljubimac ljubimac = new Ljubimac(0, takmicarskaOznaka, vrstaLjubimac, bojaLjubimca, imeLjubimca, godineLjubimca);
			sviLjubimci.add(ljubimac);
			
			
			odlukaOIspisu();
			
			while(PomocnaKlasa.ocitajOdlukuOPotvrdi("dodeliti ljubimca vlasniku") == 'Y') {
				VlasnistvoUI.dodeliLjubimcuVlasnika(ljubimac);
			}
			
		}
		
		
		
			// izmena
			public static void izmenaLjubimca() {
				Ljubimac ljubimac = pretragaLjubimcaPoTakmicarskojOznaci();
				if(ljubimac != null) {
					System.out.println("Unesite vrstu ljubimca izmena:");
					String vrstaIzmena = setLjubimacVrsta();
					
					System.out.print("Unesite boju ljubimca izmena:");
					String bojaIzmena = PomocnaKlasa.ocitajText();
					
					String izmenaIme;
					do {
						System.out.print("Unesite ime ljubimca izmena:");
						izmenaIme = PomocnaKlasa.ocitajText();
					}while(!PomocnaKlasa.isFirstAndSecondNameValid(izmenaIme));
					
					System.out.print("Unesite godine ljubimca:");
					int godIzmena = PomocnaKlasa.ocitajCeoBroj();
					
					ljubimac.setVrstaLjubimac(vrstaIzmena);
					ljubimac.setBojaLjubimac(bojaIzmena);
					ljubimac.setImeLjubimac(izmenaIme);
					ljubimac.setGodineLjubimca(godIzmena);
					
					
					odlukaOIspisu();
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("ukloniti vlasnike da vise ne vode racuna o ljbimcu") == 'Y') {
						VlasnistvoUI.ukloniVlasnikaOdLjubimca(ljubimac);
					}
					
					odlukaOIspisu();
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("da dodelite nekog korisnika da vodi brigu o ljubimcu") == 'Y') {
						VlasnistvoUI.dodeliLjubimcuVlasnika(ljubimac);
					}
					
				}
			}
		
		
		
			// brisanje
			public static void brisanjePodatakaLjubimca() {
				int pozicijaBrisanje  = pronadjiPozicijuLjubimca();
				if(pozicijaBrisanje > -1) {
					sviLjubimci.remove(pozicijaBrisanje);
					System.out.println("Uspesno obrisano.");
				}
			}
			
		
		
		
			/** METODA U VEZI ODLUKE ZA ISPIS */
			public static void odlukaOIspisu() {
				System.out.print("Da li zelite da vidite listu korisnika pre pretrage:\n\tOpcija 1 - YES\n\tOpcija 2- NO\nopcija:");
				int opcija = PomocnaKlasa.ocitajCeoBroj();
				
				switch(opcija) {
				case 1:
					VlasnikUI.ispisiOdredjeneInfoVlasnik();
					break;
				case 2:
					break;
				}
			}
			
			
			
			
			
			
			
			
			
			public static String setLjubimacVrsta() {
			System.out.println("Izaberite opciju: \n\tOpcija broj 1 - pas\n\tOpcija broj 2 - macka\n\tOpcija broj 3 - papagaj");
			
			int odluka = 0;
		
			
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
		
			if(odluka == 1) {
				return "pas";
			}else if (odluka == 2) {
				 return "macka";
			}else if(odluka == 3) {
				 return "papagaj";
			 }else {
				 System.out.println("Izabrali ste opciju " + odluka + " koja je pogresna. Pokusaj ponovo.");
				 return setLjubimacVrsta();
			 }
				 
			 }
		
		
			
		
		
		
		
		
		
		
		
		
		
		/** METODE ZA PRETRAGU LJUBIMACA*/ 
		
		public static Ljubimac pretragaLjubimcaTakmicarskaOznaka(String takmicarskaOznaka) {
			Ljubimac retVal = null;
			for(int i = 0; i < sviLjubimci.size(); i++) {
				Ljubimac ljubimac = sviLjubimci.get(i);
				if(ljubimac.getTakmicarskaOznaka().equals(takmicarskaOznaka)) {
					retVal = ljubimac;
					break;
				}
			}
			return retVal;
		}
		
		
		public static Ljubimac pretragaLjubimcaPoTakmicarskojOznaci() {
			System.out.print("Unesite takmicarsku oznaku ljubimca:");
			String takmOznaka = PomocnaKlasa.ocitajText().trim();
			Ljubimac retVal = pretragaLjubimcaTakmicarskaOznaka(takmOznaka);
			if(retVal == null) {
				System.out.println("Ljubimac sa takmicarskom oznakom " + takmOznaka + " ne postoji u evidenciji.");
			}
			return retVal;
		}
		
		
		public static int pronadjiPozicijuLjubimca() {
			System.out.print("Unesite takmicarsku oznaku ljubimca za pretragu:");
			int pozicija = -1;
			String oznakaLjubimca = PomocnaKlasa.ocitajText().trim();
			for(int i = 0; i < sviLjubimci.size(); i++) {
				Ljubimac ljubimac = sviLjubimci.get(i);
				if(ljubimac.getTakmicarskaOznaka().equalsIgnoreCase(oznakaLjubimca)) {
					pozicija = i;
					break;
				}
			}if(pozicija == -1) {
				System.out.println("Uneta takmicarska oznaka ljubimca " + oznakaLjubimca + " nije pronadjena u evidenciji.");
			}
			return pozicija;
		}
		
		
		
		public static Ljubimac pretragaLjubimcaPoId() {
			Ljubimac retVal = null;
			System.out.print("Unesite ID ljubimca za pretragu:");
			int idLjubimac = PomocnaKlasa.ocitajCeoBroj();
			retVal = pretragaLjubimcaPoId(idLjubimac);
			if(retVal == null) {
				System.out.println("Uneti ID za ljubimca " + idLjubimac + " ne postoji u evidenciji.");
			}
			return  retVal;
		}
		
		
		
		
		
		public static Ljubimac pretragaLjubimcaPoId(int idLjubimac) {
			Ljubimac retVal = null;
			for(int i = 0; i < sviLjubimci.size(); i++) {
				Ljubimac lj = sviLjubimci.get(i);
				if(lj.getIdLjubimac() == idLjubimac) {
					retVal = lj;
					break;
				}
			}
			return retVal;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/** METODA ZA SORTIRANJE LJUBIMACA *****/
		public static void sortirajLjubimce() {
			System.out.println("Ljubmice je moguce sortirati \n\tOpcija broj 1 - ime rastuce\n\tOpcija broj 2 - ime opadajuce\nopcija:");
			int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
			switch(sortOpcija) {
			
			case 1:
				Collections.sort(sviLjubimci, LjubimacNameKomparator.LjubimacNameKomparator);
				System.out.println("Ljubimci nakon sortiranja:");
				ispisiSveLjubimce();
				break;
			case 2:
				Collections.sort(sviLjubimci, LjubimacNameKomparator.LjubimacNameKomparator2);
				System.out.println("Ljubimci nakon sortiranja:");
				ispisiSveLjubimce();
				break;
			}
		}
		
		
		public static void soritranjeId() {
			Collections.sort(sviLjubimci, SortiranjeID.LjubimacIdKomparator);
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			/** METODA ZA UCITAVANJE PODATAKA *****/ 
			static void citajIzFalaLjubimce(File dokument) throws IOException {
				if(dokument.exists()) {
					BufferedReader in = new BufferedReader(new FileReader(dokument));
					
					in.mark(1);
					if(in.read() != '\ufeff') {
						in.reset();
					 }
					
					String s2;
					while((s2 = in.readLine()) != null) {
						Ljubimac ljubimac = new Ljubimac(s2);
						sviLjubimci.add(ljubimac);
					}
					in.close();
					}else {
						System.out.println("Ne postji fajl ljubimac.txt");
					}
			    }
		
		
			
			public static void pisiUFajlLjubmci(File dokument) throws IOException {
				PrintWriter pw = new PrintWriter(new FileWriter(dokument));
				for(Ljubimac lj : sviLjubimci) {
					pw.println(lj.toFileRepresentation());
					
				}
				pw.flush();
				pw.close();
			}
		
		
		
			
			
		
		
	

}
