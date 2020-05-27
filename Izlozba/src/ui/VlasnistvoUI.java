package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Ljubimac;
import model.Vlasnik;
import utils.PomocnaKlasa;


public class VlasnistvoUI {
	
	
		/** METODE ZA ISPIS OPCIJA *****/
		public static void ispisiMenu() {
			System.out.println("\nRad sa ljubimcima vlasnika - opcije:");
			System.out.println("\tOpcija broj 1 - ispis svih ljubimaca koje ima korsinik");
			System.out.println("\tOpcija broj 2 - ispis svih korisnika koji vode racuna o ljubimcu");
			System.out.println("\tOpcija broj 3 - dodavanje korisnika o vodjenju racuna za ljubimca");
			System.out.println("\tOpcija broj 4 - uklanjanje korisnika o vodjenju racuna za ljubimca");
			System.out.println("\t\t....");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		}
	
	
	
		/** MENI OPCIJA *****/
		public static void meniVlasnistvoUI() {
			int odluka = -1;
			while(odluka != 0) {
				ispisiMenu();
				System.out.print("odluka:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				switch(odluka) {
				case 0:
					System.out.println("Izlaz");
					break;
				case 1:
					ispisiLjubimceZaVlasnika();
					break;
				case 2:
					ispisiSveVlasnikeZaLjubimca();
					break;
				case 3:
					dodajVlasnikaZaLjubimca();
					break;
				case 4:
					ukloniVlasnikeOdLjubimaca();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
				}
			}
		}
		
		
		
		public static void ispisiLjubimceZaVlasnika() {
			Vlasnik vlasnik = VlasnikUI.pretragaVlasnikaIndex();
			if(vlasnik != null) {
				List<Ljubimac> listaLjubimci = vlasnik.getLjubimci();
				if(listaLjubimci.size() > 0) {
				for(Ljubimac lj : listaLjubimci) {
					System.out.println(vlasnik.toString() + "\n\tBrine o ljubimcima:");
					System.out.println(lj.toString());
				}
				}else {
					System.out.println(vlasnik.toString() + "\n On nema ljubimce o koima se brine");
				}
			}
		}
		
		
		
		public static void ispisiSveVlasnikeZaLjubimca() {
			Ljubimac lj = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
			if(lj != null) {
				List<Vlasnik> listaVlasnici = lj.getVlasnici();
				if(listaVlasnici.size() > 0) {
				System.out.println(lj.toString() + "\n\tBrine osoba:");	
				for(Vlasnik vl : listaVlasnici) {
					System.out.println(vl);
				}
			  }else {
				  System.out.println(lj.toString() + "\n Nema zaduzenih korisnika za ovog ljubimca");
			  }
			}
		}
		
		
		
		
		
		
		
	
	
	
				// Ukloniti
	public static void ukloniVlasnikeOdLjubimaca() {
		Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
		Ljubimac lj = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
		
		if(vl != null && lj != null) {
		uklonitiVlasnikaOdLjubimca(vl, lj);
		System.out.println("Uspesno izbrisano.");
	   }
	}
		
		
		
		
		
	public static void uklonitiVlasnikaOdLjubimca(Vlasnik vlasnik) {
		Ljubimac ljubimac = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
		
		if(ljubimac != null) {
		uklonitiVlasnikaOdLjubimca(vlasnik, ljubimac);
		System.out.println("Uspesno je izbrisano");
		}
	}
	
	
	public static void ukloniVlasnikaOdLjubimca(Ljubimac ljubimac) {
		Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
		uklonitiVlasnikaOdLjubimca(vl, ljubimac);
	}
	
	
	
	public static void uklonitiVlasnikaOdLjubimca(Vlasnik vlasnik, Ljubimac ljubimac) {
			if(vlasnik != null && ljubimac != null) {
				
				List<Ljubimac> ljubimciLista = vlasnik.getLjubimci();
				boolean found = false;
				for(int i = 0; i < ljubimciLista.size(); i++) {
					if(ljubimac.equals(ljubimciLista.get(i))) {
						found = true;
						ljubimciLista.remove(i);
						break;
					}
				}
				
				if(found) {
					List<Vlasnik> vlasnikLista = ljubimac.getVlasnici();
					for(int i = 0; i < vlasnikLista.size(); i++) {
						if(vlasnik.equals(vlasnikLista.get(i))) {
							 vlasnikLista.remove(i);
							 break;
						}
					}
				}
				
			}
		}	
	
	
	
	
	
	
	
	
	
	
		// Dodavanje
	public static void dodajVlasnikaZaLjubimca() {
		Vlasnik vl = VlasnikUI.pretragaVlasnikaIndex();
		Ljubimac lj = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
		if(vl != null && lj != null) {
		dodajVlasnikaKodLjubimca(vl, lj);
		System.out.println("Uspesno je uneseno!");
		}
	}
	
	
	
	public static void dodeliVlasnikuLjubimca(Vlasnik vlasnik) {
		Ljubimac ljubimac = LjubimacUI.pretragaLjubimcaPoTakmicarskojOznaci();
		dodajVlasnikaKodLjubimca(vlasnik, ljubimac);
	}
	
	public static void dodeliLjubimcuVlasnika(Ljubimac ljubimac) {
		Vlasnik vlasnik = VlasnikUI.pretragaVlasnikaIndex();
		dodajVlasnikaKodLjubimca(vlasnik, ljubimac);
	}
	
	
	
	
	
	public static void dodajVlasnikaKodLjubimca(Vlasnik vlasnik, Ljubimac ljubimac) {
		if(vlasnik != null && ljubimac != null) {
			List<Ljubimac> ljubimciLista = vlasnik.getLjubimci();
			boolean found = false;
			for(int i = 0; i < ljubimciLista.size(); i++) {
				if(ljubimac.equals(ljubimciLista.get(i))) {
					found = true;
					break;
				}
			}
			if(!found) {
				vlasnik.getLjubimci().add(ljubimac);
				ljubimac.getVlasnici().add(vlasnik);
			}
		}else {
			System.out.println("Ne postoje podaci o vlasniku/ljubimcu");
		}
	}
	
	
	
	
	
			/** METODA ZA UCITAVANJE PODATAKA ***/
		static void citajIzfajlaVlasnistvoUI(File dokument) throws IOException {
			if(dokument.exists()) {
				BufferedReader in = new BufferedReader(new FileReader(dokument));
				
				
				in.mark(1);
				if(in.read() != '\ufeff') {
					in.reset();
				}
				
				String s2;
				while((s2 = in.readLine()) != null) {
					String [] vlasnistvoPodaciTekst = s2.split(",");
					int idKorisnik = Integer.parseInt(vlasnistvoPodaciTekst[0]);
					int idLjubimac = Integer.parseInt(vlasnistvoPodaciTekst[1]);
					
					Vlasnik vl = VlasnikUI.pretragaVlasnikaPoId(idKorisnik);
					Ljubimac lj = LjubimacUI.pretragaLjubimcaPoId(idLjubimac);
					
					
					if(vl != null && lj != null) {
						vl.getLjubimci().add(lj);
						lj.getVlasnici().add(vl);
					}
				}
				in.close();
			}else {
				System.out.println("Ne postoji fajl!");
			}
		}
		
		
		
		public static void pisiUFajlVlasnistvo(File dokument) throws IOException {
			PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
			for(Vlasnik vl : VlasnikUI.sviVlasnici) {
				for(Ljubimac lj : vl.getLjubimci()) {
					out2.println(vl.getIdVlasnik() + "," + lj.getIdLjubimac());
				}
			}
			out2.flush();
			out2.close();
		}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
