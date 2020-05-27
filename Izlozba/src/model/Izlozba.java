package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class Izlozba {
	
	private static int brojacID = 0;
	
	protected int idIzlozba;
	protected String nazivIzlozba;
	protected LocalDate pocetakIzloba;
	protected LocalDate krajIzlozba;
	
	ArrayList<Diploma> diplome = new ArrayList<Diploma>();
	
	public Izlozba(int idIzlozba, String nazivIzlozba, LocalDate pocetakIzlozba, LocalDate krajIzlozba) {
		if(idIzlozba == 0) {
			brojacID++;
			idIzlozba = brojacID;
		}
		this.idIzlozba = idIzlozba;
		this.nazivIzlozba = nazivIzlozba;
		this.pocetakIzloba =pocetakIzlozba;
		this.krajIzlozba = krajIzlozba;
	}
	
	
	public Izlozba(String tekst) {
		String [] tokeni = tekst.split(",");
		if(tokeni.length != 4) {
			System.out.println("Greska prilikom ocitavanja fajla vlasnik.txt :-/ " + tekst);
			System.exit(0);
		}
		this.idIzlozba = Integer.parseInt(tokeni[0]);
		this.nazivIzlozba = tokeni[1];
		this.pocetakIzloba = LocalDate.parse(tokeni[2], Vlasnik.formatter);
		this.krajIzlozba = LocalDate.parse(tokeni[3], Vlasnik.formatter);
		
		if(brojacID < idIzlozba) {
			brojacID = idIzlozba;
		}
	}
	
	
	
	
	public String toFileRepresentation() {
		StringBuilder sb = new StringBuilder();
		sb.append(idIzlozba + "," + nazivIzlozba + "," + pocetakIzloba.format(Vlasnik.formatter) + "," + krajIzlozba.format(Vlasnik.formatter));
		return sb.toString();
	}
	
	
	@Override
	public String toString() {
		String ispis = "Izlozba sa ID-om " + idIzlozba + ", ciji je naziv "
					 + nazivIzlozba.toUpperCase() + ", odrzava se datuma " + pocetakIzloba + " i traje do " + krajIzlozba + ".";
		return ispis;
	}
	
	
	public String toStringAllDiplome() {
		StringBuilder sb = new StringBuilder("Izlozba sa ID-om " + idIzlozba + ", ciji je naziv "
						 + nazivIzlozba + ", odrzava se datuma " + pocetakIzloba + " i traje do " + krajIzlozba);
		
		if(diplome != null) {
			sb.append(" Dodeljuje se diploma:\n");
			for(int i = 0; i < diplome.size(); i++) {
				sb.append("\t" + diplome.get(i).toString() + "\n");
			}
		}
		return sb.toString();
	}

	
	
	
	// GET & SET METODE
	
	
	public int getIdIzlozba() {
		return idIzlozba;
	}


	public void setIdIzlozba(int idIzlozba) {
		this.idIzlozba = idIzlozba;
	}


	public String getNazivIzlozba() {
		return nazivIzlozba;
	}


	public void setNazivIzlozba(String nazivIzlozba) {
		this.nazivIzlozba = nazivIzlozba;
	}


	public LocalDate getPocetakIzlozbe() {
		return pocetakIzloba;
	}


	public void setPocetakIzlozbe(LocalDate pocetakIzloba) {
		this.pocetakIzloba = pocetakIzloba;
	}


	public LocalDate getKrajIzlozba() {
		return krajIzlozba;
	}


	public void setKrajIzlozba(LocalDate krajIzlozba) {
		this.krajIzlozba = krajIzlozba;
	}


	public ArrayList<Diploma> getDiplome() {
		return diplome;
	}


	public void setDiplome(ArrayList<Diploma> diplome) {
		this.diplome = diplome;
	}
	
	
	
	
	
	
	
	
	
}
