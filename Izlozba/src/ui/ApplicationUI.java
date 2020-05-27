package ui;

import java.io.File;
import java.io.IOException;

import utils.PomocnaKlasa;


public class ApplicationUI {
	
	
	public static void ispisiTekstOsnovneOpcije() {
		System.out.println("\n=========================================");
		System.out.println("\t" + PomocnaKlasa.sff.format(PomocnaKlasa.date));
		System.out.println("Kucni Ljubimci Izlozba - Osnovne opcije");
		System.out.println("=========================================");
		System.out.println("\tOpcija broj 1 - Rad sa takmicarima");
		System.out.println("\tOpcija broj 2 - Rad sa ljubimcima");
		System.out.println("\tOpcija broj 3 - Rad sa vlasnistvom");
		System.out.println("\tOpcija broj 4 - Rad sa izlozbom");
		System.out.println("\tOpcija broj 5 - Rad sa diplomama");
		System.out.println("\t\t.....");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}
	
	
	
	
		public static void main(String [] args) throws IOException{
			String sP = System.getProperty("file.separator");
			
			File vlasniciFajl = new File("." + sP + "data" + sP + "vlasnici.txt");
			VlasnikUI.citajIzFajlaVlasnike(vlasniciFajl);
			
			File ljubmciFajl = new File("." + sP + "data" + sP + "ljubimci.txt");
			LjubimacUI.citajIzFalaLjubimce(ljubmciFajl);
			
			File vlasnistvoFajl = new File("." + sP + "data" + sP + "vlasnistvo.txt");
			VlasnistvoUI.citajIzfajlaVlasnistvoUI(vlasnistvoFajl);
			
			File izlozbeFajl = new File("." + sP + "data" + sP + "izlozba.txt");
			IzlozbaUI.citajIzFajlaIzlobe(izlozbeFajl);
			
			File diplomeFajl = new File("." + sP + "data" + sP + "diplome.txt");
			DiplomaUI.citajIzFajlaDiploma(diplomeFajl);
			
			
			int odluka = -1;
			while(odluka != 0) {
				ispisiTekstOsnovneOpcije();
				System.out.print("opcija:");
				odluka = PomocnaKlasa.ocitajCeoBroj();
				
				switch(odluka) {
				case 0:
					System.out.println("Izlaz iz programa");
					break;
				case 1:
					VlasnikUI.meniVlasnikUI();
					break;
				case 2:
					LjubimacUI.meniLjubimacUI();
					break;
				case 3:
					VlasnistvoUI.meniVlasnistvoUI();
					break;
				case 4:
					IzlozbaUI.meniIzlozbaUI();
					break;
				case 5:
					DiplomaUI.meniDiplomaUI();
					break;
				default:
					System.out.println("Nepostojeca komanda.");
					break;
				}
			}
			
			
			VlasnikUI.pisiUFajlVlasnike(vlasniciFajl);
			LjubimacUI.pisiUFajlLjubmci(ljubmciFajl);
			VlasnistvoUI.pisiUFajlVlasnistvo(vlasnistvoFajl);
			IzlozbaUI.pisiUFajlIzlozbe(izlozbeFajl);
			DiplomaUI.pisiUFajlDipolome(diplomeFajl);
			System.out.println("Program izvrsen. Goodbye :)");
			
			
			
			
		}
	
}
