package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Vlasnik {
	
	private static int brojacID = 0;
	
	protected int idVlasnik;
	protected String indexVlasnik;
	protected String imeVlasnik;
	protected String prezimeVlasnik;
	protected LocalDate datumRodjnja;
	protected String mestoStanovanja;
	
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	ArrayList<Ljubimac> ljubimci = new ArrayList<Ljubimac>();
	ArrayList<Diploma> diplome = new ArrayList<Diploma>();
	
	
	
	/** KONSTRUKTORI ****/
	
	public Vlasnik(int idVlasnik, String indexVlasnik, String imeVlasnik, String prezimeVlasnik, LocalDate datumRodjenja, 
			       String mestoStanovanja) {
		
		if(idVlasnik == 0) {
			brojacID++;
			idVlasnik = brojacID;
		}
		this.idVlasnik = idVlasnik;
		this.indexVlasnik = indexVlasnik;
		this.imeVlasnik = imeVlasnik;
		this.prezimeVlasnik = prezimeVlasnik;
		this.datumRodjnja = datumRodjenja;
		this.mestoStanovanja = mestoStanovanja;
		
	}
	
	
	
	public Vlasnik(String tekst) {
		String [] tokeni = tekst.split(",");
		if(tokeni.length != 6) {
			System.out.println("Greska prilikom ocitavanja fajla vlasnik.txt :-/ " + tekst);
			System.exit(0);
		}
		this.idVlasnik = Integer.parseInt(tokeni[0]);
		this.indexVlasnik = tokeni[1];
		this.imeVlasnik = tokeni[2];
		this.prezimeVlasnik = tokeni[3];
		this.datumRodjnja = LocalDate.parse(tokeni[4], formatter);
		this.mestoStanovanja = tokeni[5];
		
		
		if(brojacID < idVlasnik) {
			brojacID = idVlasnik;
		}
	}
	
	
	
		/** METODE  */
	
	public String ispis() {
		return "xe";
	}
	
	
	
	
		public String toFileRepresentation() {
			StringBuilder sb = new StringBuilder();
			sb.append(idVlasnik + "," + indexVlasnik + "," + imeVlasnik + "," 
					  + prezimeVlasnik + "," + datumRodjnja.format(formatter) + "," + mestoStanovanja);
			return sb.toString();
		}
		
		
		@Override
		public String toString() {
			String ispis = "Korisnik je osoba sa ID-om " + idVlasnik + ", ima index " + indexVlasnik 
						 + ", cije je ime i prezime " + imeVlasnik + " " + prezimeVlasnik + ", rodjen/a je dana " + datumRodjnja
						 + ", zivi u mestu " + mestoStanovanja + "."; 
				return ispis;		  
		}
		
		
		public String toStrilgAllLjubimac() {
			StringBuilder sb = new StringBuilder("Korisnik sa ID-om " + idVlasnik + ", ima index " + indexVlasnik 
					         + ", cije je ime i prezime " + imeVlasnik + " " + prezimeVlasnik + ", rodjen/a je dana " + datumRodjnja
					         + ", zivi u mestu " + mestoStanovanja +".");
			
	
		if(ljubimci.size() > 0) {	
		if(ljubimci != null) {
				sb.append("\n\tDodeljen mu je ljubimac:\n");
				for(int i = 0; i < ljubimci.size(); i++){
					sb.append("\t" + ljubimci.get(i).toString() + "\n");
				}
		   }
		 }else {
			 System.out.println(imeVlasnik + " " + prezimeVlasnik + " je bez ljubimca.");
		 }
		return sb.toString();
		}
		
		
		
		
		public String toStrilgAllDiploma() {
			StringBuilder sb = new StringBuilder("Vlasnik sa ID-om " + idVlasnik + ", ima index " + indexVlasnik 
					         + ", cije je ime i prezime " + imeVlasnik + " " + prezimeVlasnik + ", rodjen je dana " + datumRodjnja
					         + ", zivi u mestu " + mestoStanovanja + ".");
		
			
			if(diplome.size() > 0) {
			if(diplome != null) {
				sb.append("\n\tOsvojio je diplomu:\n");
				for(int i = 0; i < diplome.size(); i++) {
					sb.append("\t" + diplome.get(i).toString() + "\n");
				}
			}
			
		 }else {
			 System.out.println(imeVlasnik + " " + prezimeVlasnik + " je bez diplome.");
		 }	
			return sb.toString();
		}


		
		
		public double izracunajProsek() {
			double retVal = 0;
			int brojacOcenaVecihOdPet = 0;
			for(Diploma dip : diplome) {
				if(dip.sracunajOcenu() > 5) {
					retVal = retVal + dip.sracunajOcenu();
					brojacOcenaVecihOdPet++;
				}
			}
			return retVal / brojacOcenaVecihOdPet;
		}
		
		
		
		
		
		
		
		
		// GET & SET METODE
		
		

		public int getIdVlasnik() {
			return idVlasnik;
		}



		public void setIdVlasnik(int idVlasnik) {
			this.idVlasnik = idVlasnik;
		}



		public String getIndexVlasnik() {
			return indexVlasnik;
		}



		public void setIndexVlasnik(String indexVlasnik) {
			this.indexVlasnik = indexVlasnik;
		}



		public String getImeVlasnik() {
			return imeVlasnik;
		}



		public void setImeVlasnik(String imeVlasnik) {
			this.imeVlasnik = imeVlasnik;
		}



		public String getPrezimeVlasnik() {
			return prezimeVlasnik;
		}



		public void setPrezimeVlasnik(String prezimeVlasnik) {
			this.prezimeVlasnik = prezimeVlasnik;
		}



		public LocalDate getDatumRodjnja() {
			return datumRodjnja;
		}



		public void setDatumRodjnja(LocalDate datumRodjnja) {
			this.datumRodjnja = datumRodjnja;
		}



		public String getMestoStanovanja() {
			return mestoStanovanja;
		}



		public void setMestoStanovanja(String mestoStanovanja) {
			this.mestoStanovanja = mestoStanovanja;
		}



		public ArrayList<Ljubimac> getLjubimci() {
			return ljubimci;
		}



		public void setLjubimci(ArrayList<Ljubimac> ljubimci) {
			this.ljubimci = ljubimci;
		}



		public ArrayList<Diploma> getDiplome() {
			return diplome;
		}



		public void setDiplome(ArrayList<Diploma> diplome) {
			this.diplome = diplome;
		}
	
	
		
		
		
		
		
	
	
	
	
	
	
}
