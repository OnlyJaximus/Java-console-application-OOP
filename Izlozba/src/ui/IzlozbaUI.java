package ui;


import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import model.Diploma;
import model.Izlozba;
import model.Vlasnik;
import utils.DateKomparator;
import utils.PomocnaKlasa;
import utils.SortiranjeID;

public class IzlozbaUI {
	
	static ArrayList<Izlozba> sveIzlozbe = new ArrayList<Izlozba>();
	
	
		/** METODE ZA ISPIS OPCIJA *****/
		public static void ispisiTekstOsnovneOpicje() {
			System.out.println("\nRad sa Izlozbom - opcije");
			System.out.println("\tOpcija broj 1 - unos podataka o izlozbi");
			System.out.println("\tOpcija broj 2 - izmena podataka o izlozbi");
			System.out.println("\tOpcija broj 3 - brisanje podataka o izlozbi");
			System.out.println("\tOpcija broj 4 - ispis svih izlozbi");
			System.out.println("\tOpcija broj 5 - ispis podataka o odredjenoj izlozni sa njenim diplomama");
			System.out.println("\tOpcija broj 6 - soritrnje izlozbi po datumu");
			System.out.println("\t\t.....");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		}
		
		
		
		/** MENI OPCIJA *****/
		public static void meniIzlozbaUI() {
			int odluka = -1;
			while(odluka != 0) {
				ispisiTekstOsnovneOpicje();
				System.out.print("opcija:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				switch(odluka) {
				case 0:
					System.out.println("Izlaz.");
					break;
				case 1:
					unesiNovuIzlozbu();
					break;
				case 2:	
					izmenaPodatakaOIzlozbi();
					break;
				case 3:	
					brisanjeIzlozbe();
					break;
				case 4:	
					sortiranjeId();
					ispisiSveIzlozbe();
					break;
				case 5:
					Izlozba izl = IzlozbaUI.pretragaIzlozbeNaziv();
							if(izl != null) {
								System.out.println(izl.toStringAllDiplome());
							}
					break;
				case 6:	
					sortirajDatume();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
				}
			}
		}
		
		
		
		/** METODE ZA ISPIS */
		public static void ispisiSveIzlozbe() {
			if(sveIzlozbe.size() > 0) {
				for(int i = 0; i < sveIzlozbe.size(); i++) {
					System.out.println(sveIzlozbe.get(i).toString());
				}
			}else {
				System.out.println("Lista sa izlozbama je prazna!");
			}
		}
		
		
	
		
		
		
		
		
			/** METODE ZA UNOS, IZMENU I BRISANJE IZLOZBE *****/
		
		// Unos
		public static void unesiNovuIzlozbu() {
			System.out.print("Unesite naizv izlozbe:");
			String nazivIzlozbe = PomocnaKlasa.ocitajText().trim();
			
			LocalDate datumPocetak = null;
			String stringDatumPocetak;
			do {
				System.out.print("Unesite datum za pocetak izlozbe u formatu (dd.MM.yyyy):");
				stringDatumPocetak = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isLocalDate(stringDatumPocetak));
			
			datumPocetak = LocalDate.parse(stringDatumPocetak, Vlasnik.formatter);
			
			
			LocalDate datumKraj = null;
			String stringDatumKraj;
			
			do {
				System.out.print("Unesite datum za kraj izlozbe u formatu (dd.MM.yyyy):");
				stringDatumKraj = PomocnaKlasa.ocitajText();
			}while(!PomocnaKlasa.isLocalDate(stringDatumKraj));
			
			datumKraj = LocalDate.parse(stringDatumKraj, Vlasnik.formatter);
			
		
			while(!PomocnaKlasa.daLiJeDrugiDatumLegit(datumPocetak, datumKraj)) {
				System.out.print("Pokusaj opet sa unosom drugog datuma:");
				stringDatumKraj = PomocnaKlasa.ocitajText();
				datumKraj = LocalDate.parse(stringDatumKraj, Vlasnik.formatter);
				
			}
				
				
			Izlozba izlozba = new Izlozba(0, nazivIzlozbe, datumPocetak, datumKraj);
			sveIzlozbe.add(izlozba);
			
			while(PomocnaKlasa.ocitajOdlukuOPotvrdi("dodati diplome za izlozbu") == 'Y') {
				DiplomaUI.dodajDiplomuZaIzlobu(izlozba);
			}
			
			System.out.println("Uspesno je upisano!");
			
		}
		
		
		
		
					// Izmena
			public static void izmenaPodatakaOIzlozbi() {
				Izlozba izlozba = pretragaIzlozbeNaziv();
				
				if(izlozba != null) {
					System.out.print("Unesite naziv izlozbe izmena:");
					String nazIzlozbeIzmena = PomocnaKlasa.ocitajText().trim();
					
					LocalDate datumIzmenaPocetak = null;
					String stringDatumIzmenaPocetak;
					do {
						System.out.print("Unesite datum u formatu (dd.MM.yyyy) za POCETAK izlozbe - izmena:");
						stringDatumIzmenaPocetak = PomocnaKlasa.ocitajText();
					}while(!PomocnaKlasa.isLocalDate(stringDatumIzmenaPocetak));
					
					 datumIzmenaPocetak = LocalDate.parse(stringDatumIzmenaPocetak, Vlasnik.formatter);
					
					
					LocalDate datumIzmenaKraj = null;
					String stringDatumIzmenaKraj;
					
					do {
						System.out.print("Unesite datum u formatu (dd.MM.yyyy) za KRAJ izlozbe - izmena:");
						stringDatumIzmenaKraj = PomocnaKlasa.ocitajText();
					}while(!PomocnaKlasa.isLocalDate(stringDatumIzmenaKraj));
					
					datumIzmenaKraj = LocalDate.parse(stringDatumIzmenaKraj, Vlasnik.formatter);
					
					

					while(!PomocnaKlasa.daLiJeDrugiDatumLegit(datumIzmenaPocetak, datumIzmenaKraj)) {
						System.out.print("Pokusaj opet sa unosom drugog datuma:");;
						stringDatumIzmenaKraj = PomocnaKlasa.ocitajText();
						datumIzmenaKraj = LocalDate.parse(stringDatumIzmenaKraj, Vlasnik.formatter);
					}
					
					izlozba.setNazivIzlozba(nazIzlozbeIzmena);
					izlozba.setPocetakIzlozbe(datumIzmenaPocetak);
					izlozba.setKrajIzlozba(datumIzmenaKraj);
					
					
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("ukloniti diplome za izlozbu") == 'Y') {
						DiplomaUI.ukloniDiplomu(izlozba);
					}
					
					
					while(PomocnaKlasa.ocitajOdlukuOPotvrdi("dodati diplome za izlozbu") == 'Y') {
						DiplomaUI.dodajDiplomuZaIzlobu(izlozba);
					}
					
					
					
					System.out.println("Uspesno izmenjeno!");
				}
			}
		
		
		
				// Brisanje
			public static void brisanjeIzlozbe() {
				int pozicija = pretragaPozicijeIzlozbeZaBrisanje();
				if(pozicija > -1 ) {
					Izlozba izlozba =  sveIzlozbe.remove(pozicija);
				
					ArrayList<Diploma> lista = new ArrayList<Diploma>(izlozba.getDiplome());
					
					// sada moramo da uklonimo sve za dipolmu
					for(Diploma dip : lista) {
						DiplomaUI.ukloniDiplomu(dip);
					}
					System.out.println("Podaci su obrisani iz evidencij!");
				}
			}
				
			
			
			
			
			
			/** SORTIRANJE DATUMA */
			public static void sortirajDatume() {
				System.out.print("Datume je moguce sortirati \n\tOpcioja broj 1 - datum rastuci\n\tOpcija broj 2 - datum opadajuci\nopcija:");
				int sortOpcija = PomocnaKlasa.ocitajCeoBroj();
				
				switch(sortOpcija) {
				case 1:
					Collections.sort(sveIzlozbe, DateKomparator.datumKomparator1);
					System.out.println("\tDatumi nakon sortiranja:");
					ispisiSveIzlozbe();
					break;
				
				case 2:
					Collections.sort(sveIzlozbe, DateKomparator.datumKomparator2);
					System.out.println("\tDatumi nakon sortiranja:");
					ispisiSveIzlozbe();
					break;
				}	
					
			}
			
			
			
			public static void sortiranjeId() {
				Collections.sort(sveIzlozbe, SortiranjeID.IzlozbaIdKomparator);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/** METODE ZA PRETRAGU IZLOZBI *****/
			
			public static Izlozba pretragaIzlozbeNaziv() {
				System.out.print("Unesite naziv izlozbe:");
				String nazIzlozbe = PomocnaKlasa.ocitajText();
				Izlozba retVal = null;
				retVal = pretragaIzlozbePoNazivu(nazIzlozbe);
				
				if(retVal == null) {
					System.out.println("Naziv " + nazIzlozbe + " za izlozbu ne postoji u evidenciji.");
				}
				return retVal;	
			}	
		
			
			
			public static Izlozba pretragaIzlozbePoNazivu(String nazIzlobe) {
				Izlozba retVal = null;
				for(int i = 0; i < sveIzlozbe.size(); i++) {
					Izlozba izl = sveIzlozbe.get(i);
					if(izl.getNazivIzlozba().equalsIgnoreCase(nazIzlobe)) {
						retVal = izl;
						break;
					}
				}
				return retVal;
			}
		
		
		
			public static int pretragaPozicijeIzlozbeZaBrisanje() {
				System.out.print("Unesite ID izlozbe za brisanje:");
				int idIzlozbe = PomocnaKlasa.ocitajCeoBroj();
				
				int pozicija = -1;
				for(int i = 0; i < sveIzlozbe.size(); i++) {
					Izlozba izlozba = sveIzlozbe.get(i);
					if(izlozba.getIdIzlozba() == idIzlozbe) {
						pozicija = i;
						break;
					}
				}
				if(pozicija == -1) {
					System.out.println("Izloba sa ID-om " + idIzlozbe + " ne postoji u evidenciji.");
				}
				return pozicija;
			}
		
		
			
			public static Izlozba pretragaIzlozbePoId() {
				Izlozba retVal = null;
				System.out.print("Unesite ID izlozbe za pretragu:");
				int idIzlobe = PomocnaKlasa.ocitajCeoBroj();
				retVal = pretragaIzlozbePoId(idIzlobe);
				
				if(retVal == null) {
					System.out.println("Izlozba sa ID--om " + idIzlobe + " ne postoji u evidenciji.");
				}
				return retVal;
			}
			
			
			
			
			public static Izlozba pretragaIzlozbePoId(int idIzlozba) {
				Izlozba retVal = null;
				for(int i = 0; i < sveIzlozbe.size(); i++) {
					Izlozba izlozba = sveIzlozbe.get(i);
					if(izlozba.getIdIzlozba() == idIzlozba) {
						retVal = izlozba;
						break;
					}
				}
				return retVal;
			}
			
			
			
			/** METODA U VEZI ODLUKE ZA ISPIS */
			public static void odlukaOIspisu() {
				System.out.print("Da li zelite da vidite listu izlozbi:\n\tOpcija 1 - YES\n\tOpcija 2- NO\nopcija:");
				int opcija = PomocnaKlasa.ocitajCeoBroj();
				
				switch(opcija) {
				case 1:
					ispisiSveIzlozbe();
					break;
				case 2:
					break;
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/** METODE ZA UCITAVANJE PODATAKA ****/
		 static void citajIzFajlaIzlobe(File dokument) throws IOException {
			if(dokument.exists()) {
			BufferedReader in = new BufferedReader(new FileReader(dokument));
			
			in.mark(1);
			if(in.read() != '\ufeff') {
				in.reset();
			 }
			
			String s2;
			while((s2 = in.readLine()) != null) {
				  Izlozba izloba = new Izlozba(s2);
				  sveIzlozbe.add(izloba);
			}
			in.close();
			}else {
				System.out.println("Ne postoji fajl!");
			}
		}
		
		
		 static void pisiUFajlIzlozbe(File dokument) throws IOException {
			 PrintWriter pw = new PrintWriter(new FileWriter(dokument));
			 
			 for(Izlozba izl : sveIzlozbe) {
				 pw.println(izl.toFileRepresentation());
			 }
			 
			 pw.flush();
			 pw.close();
		 }
		
		
		
	
}
