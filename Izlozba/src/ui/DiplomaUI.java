package ui;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Diploma;
import model.Izlozba;
import model.Ljubimac;
import model.Vlasnik;
import utils.PomocnaKlasa;

public class DiplomaUI {
	
	
	
		/** METODE ZA ISPIS OPCIJA *****/
	
		public static void ispisiTekstDiplomaOsnovneOpcije() {
			System.out.println("\nRad sa diplomama - opcije:");
			System.out.println("\tOpcija broj 1 - diplome za izlozbu");
			System.out.println("\tOpcija broj 2 - diplome za vlasnika");
			System.out.println("\tOpcija broj 3 - diplome za ljubimca");
			System.out.println("\tOpcija broj 4 - dodavanje diplome");
			System.out.println("\tOpcija broj 5 - uklanjanje diplome");
			System.out.println("\t\t....");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		}
		
		
		public static void meniDiplomaUI() {
			int odluka = -1;
			while(odluka != 0) {
				ispisiTekstDiplomaOsnovneOpcije();
				System.out.print("opcija:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				switch(odluka) {
				case 0:
					System.out.println("Izlaz");
					break;
				case 1:
					ispisiDiplomeZaIzlozbu();
					break;
				case 2:	
					ispisiDiplomeZaVlasnikaLjubimca();
					break;
				case 3:	
					ispisiDiplomeZaLjubimce();
					break;
				case 4:	
					dodajDiplomu();
					break;
				case 5:
					ukloniDiplomu();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
				}
			}
		}
		
		
		
		
		// Opcija 1 - diplome za izlozbu
		public static void ispisiDiplomeZaIzlozbu() {
			Izlozba izlozba = IzlozbaUI.pretragaIzlozbePoId();
			if(izlozba != null) {
				List<Diploma> diplomeLista = izlozba.getDiplome();
				if(diplomeLista.size() > 0) {
				for(Diploma dip : diplomeLista) {
					System.out.println(dip.toString());
				}
			 }else {
				 System.out.println("Nema diploma za izlozbu :)");
			 }
			}
		}
		
		
		
		
		// Opcija 2 - diplome za kandidata
		public static void ispisiDiplomeZaVlasnikaLjubimca() {
			Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
			if(vl != null) {
				List<Diploma> listaDiploma = vl.getDiplome();
				if(listaDiploma.size() > 0) {
				for(Diploma dip : listaDiploma) {
					System.out.println(dip);
				}
			  }else {
				  System.out.println("Nema diploma za vlasnika :)");
			  }
			}
		}
		
		
		
		// Opcija 3 - diplome za ljubimce
		public static void ispisiDiplomeZaLjubimce() {
			Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
			if(lj != null) {
			List<Diploma> listaDiploma = lj.getDiplome();
			if(listaDiploma.size() > 0) {
			for(Diploma dip : listaDiploma) {
				System.out.println(dip);
			 }
			}else {
				System.out.println("Nema diploma za ljubimca :)");
			}
		   }
		}
		
		
		
		
		
		
		
			/** DODAVANJE DIPLOME *****/
		public static void dodajDiplomu() {
			LjubimacUI.odlukaOIspisu();
			Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
			
			VlasnikUI.odlukaOIspisu();
			Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
			
			IzlozbaUI.odlukaOIspisu();
			Izlozba izlozba = IzlozbaUI.pretragaIzlozbePoId();
			
			dodajDiplomu(izlozba, vl, lj);
		}
		
		/******************************************/
		public static void dodajDiplomuZaIzlobu(Izlozba izlozba) {
			Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
			Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
			dodajDiplomu(izlozba, vl, lj);
		}
		
		
		
		
			public static void dodajDiplomu(Izlozba izlozba, Vlasnik vlasnik, Ljubimac ljubimac) {
			if(izlozba != null && vlasnik != null && ljubimac != null) {
				// uspostavljamo vezu izmedju entiteta
				List<Diploma> listaDiplome = izlozba.getDiplome();
				boolean found = false;
				for(int i = 0; i < listaDiplome.size(); i++) {
					if(izlozba.equals(listaDiplome.get(i).getIzlozba())
					&& vlasnik.equals(listaDiplome.get(i).getVlasnik())
					&& ljubimac.equals(listaDiplome.get(i).getLjubimac())) {
						found = true;
						break;
					}
				}
				
				if(!found) {
					int brBodovaIzgled;
					System.out.print("Unesite ceo broj bodova za izgled ljubimca (min 40 - max 100):");
					brBodovaIzgled = PomocnaKlasa.ocitajCeoBroj();
					while(!PomocnaKlasa.brBodovaOgranicenje(brBodovaIzgled)) {
						System.out.print("Uneli ste pogresnu vrednost za bodove. Pokusaj opet:");
						brBodovaIzgled = PomocnaKlasa.ocitajCeoBroj();
					}
					
					
					
					
					System.out.print("Unesite ceo broj bodova za dresiranost ljubimca (min 40 - max 100):");
					int brBodovaPoslusnost= PomocnaKlasa.ocitajCeoBroj();
					while(!PomocnaKlasa.brBodovaOgranicenje(brBodovaPoslusnost)) {
						System.out.print("Uneli ste pogresnu vrednost za bodove. Pokusaj opet:");
						brBodovaPoslusnost = PomocnaKlasa.ocitajCeoBroj();
					}
					
					
					
					Diploma dip = new Diploma(vlasnik, ljubimac, izlozba, brBodovaIzgled, brBodovaPoslusnost);
					
					izlozba.getDiplome().add(dip);
					vlasnik.getDiplome().add(dip);
					ljubimac.getDiplome().add(dip);
				}
								
			}else {
				System.out.println("Ne postoje podaci o izlozbi/vlasniku/ljubimcu");
			}
		}
		
		
		
		
		
		
		
		
		
			/** UKLONITI DIPLOMU *****/
			public static void ukloniDiplomu() {
				Izlozba izl = IzlozbaUI.pretragaIzlozbePoId();
				Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
				Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
				
				ukloniDiplomu(izl, vl, lj);
			}
			
			
			
			public static void ukloniDiplomu(Izlozba izlozba) {
				Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
				Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
				ukloniDiplomu(izlozba, vl, lj);
			}
			
			
			
			public static void ukloniDiplomu(Diploma diploma) {
				Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
				Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId();
				Izlozba izl = IzlozbaUI.pretragaIzlozbePoId();
				
				ukloniDiplomu(izl, vl, lj);
			}
			
			
			
			
			
			
		public static void ukloniDiplomu(Izlozba izlozba, Vlasnik vlasnik, Ljubimac ljubimac) {
			if(izlozba != null && vlasnik != null && ljubimac != null) {
				
				// brisemo vezu izmedju diplome i ostalih entiteta
				List<Diploma> diplomeOdIzlozbe = izlozba.getDiplome();
				for(int i = 0; i < diplomeOdIzlozbe.size(); i++) {
					if(izlozba.equals(diplomeOdIzlozbe.get(i).getIzlozba())
					&& vlasnik.equals(diplomeOdIzlozbe.get(i).getVlasnik())
					&& ljubimac.equals(diplomeOdIzlozbe.get(i).getLjubimac())) {
						diplomeOdIzlozbe.remove(i);
						break;
					}
				}
				
				
				List<Diploma> diplomeOdVlasnika = vlasnik.getDiplome();
				for(int i = 0; i < diplomeOdIzlozbe.size(); i++) {
					if(izlozba.equals(diplomeOdVlasnika.get(i).getIzlozba())
					&& vlasnik.equals(diplomeOdVlasnika.get(i).getVlasnik())
					&& ljubimac.equals(diplomeOdVlasnika.get(i).getLjubimac())) {
						diplomeOdIzlozbe.remove(i);
						break;
					}
				}
				
				List<Diploma> diplomeOdLjubimaca = ljubimac.getDiplome();
				for(int i = 0; i < diplomeOdLjubimaca.size(); i++) {
					if(izlozba.equals(diplomeOdLjubimaca.get(i).getIzlozba())
					&& vlasnik.equals(diplomeOdLjubimaca.get(i).getVlasnik())	
					&& ljubimac.equals(diplomeOdLjubimaca.get(i).getLjubimac())) {
						diplomeOdLjubimaca.remove(i);
						break;
					}
				}
			}
		}
			
		
		
		
		
		
		
		
		
		
		/** METODA ZA UCITAVANJE PODATAKA *****/
		public static void citajIzFajlaDiploma(File dokument) throws IOException {
			if(dokument.exists()) {
				BufferedReader in = new BufferedReader(new FileReader(dokument));
				
				in.mark(1);
				if(in.read() != '\ufeff') {
					in.reset();
				}
				String s2;
				if((s2 = in.readLine()) != null) {
					new Diploma(s2);
				}
				in.close();
				
			}else {
				System.out.println("Ne postoji fajl.");
			}
		}
		
		
		public static void pisiUFajlDipolome(File dokument) throws IOException {
			PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
			for(Izlozba izlozba : IzlozbaUI.sveIzlozbe) {
				for(Diploma diploma : izlozba.getDiplome()) {
					out2.println(diploma.toFileRepresentation());
				}
			}
			out2.flush();
			out2.close();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		

}
