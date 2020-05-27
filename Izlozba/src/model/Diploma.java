package model;

import ui.IzlozbaUI;
import ui.LjubimacUI;
import ui.VlasnikUI;

public class Diploma {
	
	protected Vlasnik vlasnik;
	protected Ljubimac ljubimac;
	protected Izlozba izlozba;
	protected int izgledLjubimac;
	protected int poslusnostLjubimac;
	
	
	public Diploma(Vlasnik vlasnik, Ljubimac ljubimac, Izlozba izlozba, int izgledLjubimac, int poslusnostLjubimac) {
		this.vlasnik = vlasnik;
		this.ljubimac = ljubimac;
		this.izlozba = izlozba;
		this.izgledLjubimac = izgledLjubimac;
		this.poslusnostLjubimac = poslusnostLjubimac;
	}
	
	
	public Diploma(String tekst) {
		String [] tokeni = tekst.split(",");
		if(tokeni.length != 5) {
			System.out.println("Greska prilikom ocitavanja fajla diploma.txt :-/ " + tekst);
			System.exit(0);
		}
		
		int idVlasnik = Integer.parseInt(tokeni [0]);
		int idLjubimac = Integer.parseInt(tokeni[1]);
		int idIzlozba = Integer.parseInt(tokeni[2]);
		
		vlasnik = VlasnikUI.pretragaVlasnikaPoId(idVlasnik);
		if(vlasnik == null) {
			System.out.println("Podaci o vlasniku za diplomu ne mogu da se ocitaju :-/ " + tekst);
		}
		
		ljubimac = LjubimacUI.pretragaLjubimcaPoId(idLjubimac);
		if(ljubimac == null) {
			System.out.println("Podaci o ljubimcu za diplomu ne mogu da se ocitaju :-/ " +  tekst);
		}
		
		
		izlozba = IzlozbaUI.pretragaIzlozbePoId(idIzlozba);
		if(izlozba == null) {
			System.out.println("Podaci o izlozbi za diplomu ne mogu da se ocitaju :-/ " +  tekst);
		}
		
		izgledLjubimac = Integer.parseInt(tokeni[3]);
		poslusnostLjubimac = Integer.parseInt(tokeni[4]);
		
		
		 vlasnik.getDiplome().add(this);
		 ljubimac.getDiplome().add(this);
		 izlozba.getDiplome().add(this);
		
		
	}
	
	
	
		/** METODE */
		public String toFileRepresentation() {
			StringBuffer sbf = new StringBuffer();
			sbf.append(vlasnik.getIdVlasnik() + "," + ljubimac.getIdLjubimac() + "," + izlozba.getIdIzlozba() + "," + izgledLjubimac + "," + poslusnostLjubimac);
			return sbf.toString();
		}
		
		
		
		
		@Override
		public String toString() {
			String ispis = "Korisnik " + vlasnik.getImeVlasnik() + " " + vlasnik.getPrezimeVlasnik() + " iz mesta " + vlasnik.getMestoStanovanja()
					     + ", koji ima ljubimca vrste " + ljubimac.getVrstaLjubimac() + ".\n\t" + ljubimac.getVrstaLjubimac() + " se zove " + ljubimac.getImeLjubimac() + ".\n" 
					     + "\tUcestvovao je na izlozbi pod nazivom " + izlozba.getNazivIzlozba().toUpperCase() + "\n\t"
					     +  izlozba.getNazivIzlozba().toUpperCase() + " izlozba se odrzala dana " + izlozba.getPocetakIzlozbe() + " i trajala je do " + izlozba.getKrajIzlozba() + ".\n\t"
					     + ljubimac.getVrstaLjubimac().toUpperCase() + " je osvojio iz izgleda " + izgledLjubimac + " bodova"
					     + ", a iz dresiranosti " + poslusnostLjubimac + " bodova.";
			return ispis;
 		}
		
		
	
		
		public int sracunajOcenu() {
			double bodovi = ocenaRacunanje();
			int ocena;
			if(bodovi >= 95) {
				ocena = 10;
			}if(bodovi >= 85) {
				ocena = 9;
			}if(bodovi >= 75) {
				ocena = 8;
			}if(bodovi >= 65) {
				ocena = 7;
			}if(bodovi >= 55) {
				ocena = 6;
			}else {
				ocena = 5;
			  }
			return ocena;
			}
			

		
		
		
		public double ocenaRacunanje() {
			int bodovi = izgledLjubimac + poslusnostLjubimac;
			return bodovi / 2;
		}
		
		
		
		
		
		


		public Vlasnik getVlasnik() {
			return vlasnik;
		}


		public void setVlasnik(Vlasnik vlasnik) {
			this.vlasnik = vlasnik;
		}


		public Ljubimac getLjubimac() {
			return ljubimac;
		}


		public void setLjubimac(Ljubimac ljubimac) {
			this.ljubimac = ljubimac;
		}


		public Izlozba getIzlozba() {
			return izlozba;
		}


		public void setIzlozba(Izlozba izlozba) {
			this.izlozba = izlozba;
		}


		public int getIzgledLjubimac() {
			return izgledLjubimac;
		}


		public void setIzgledLjubimac(int izgledLjubimac) {
			this.izgledLjubimac = izgledLjubimac;
		}


		public int getPoslusnostLjubimac() {
			return poslusnostLjubimac;
		}


		public void setPoslusnostLjubimac(int poslusnostLjubimac) {
			this.poslusnostLjubimac = poslusnostLjubimac;
		}
	
		
		
		
		
	
	
	
	
	
	

}
