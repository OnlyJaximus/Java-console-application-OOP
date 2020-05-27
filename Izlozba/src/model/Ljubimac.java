package model;

import java.util.ArrayList;

public class Ljubimac {
	
	private static int brojacID = 0;
	
	protected int idLjubimac;
	protected String takmicarskaOznaka;
	protected String vrstaLjubimac;
	protected String bojaLjubimac;
	protected String imeLjubimac;
	protected int godineLjubimca;
	
	
	ArrayList<Vlasnik> vlasnici = new ArrayList<Vlasnik>();
	ArrayList<Diploma> diplome = new ArrayList<Diploma>();
	
	
	/** KONSTRUKTOR */
	public Ljubimac(int idLjubimac, String takmicarskaOznaka, String vrstaLjubimac, 
					String bojaLjubimac, String imeLjubimac, int godineLjubimca) {
			if(idLjubimac == 0) {
				brojacID++;
				idLjubimac = brojacID;
			}
			this.idLjubimac = idLjubimac;
			this.takmicarskaOznaka = takmicarskaOznaka;
			this.vrstaLjubimac = vrstaLjubimac;
			this.bojaLjubimac = bojaLjubimac;
			this.imeLjubimac = imeLjubimac;
			this.godineLjubimca = godineLjubimca;
			
		}
	
	
	public Ljubimac(String tekst) {
		 String [] tokeni = tekst.split(",");
		 if(tokeni.length != 6) {
			 System.out.println("Greska prilikom ocitavanja fajla ljubimac.txt :-/ " + tekst);
		 }
		 
		 this.idLjubimac = Integer.parseInt(tokeni[0]);
		 this.takmicarskaOznaka = tokeni[1];
		 this.vrstaLjubimac = tokeni[2];
		 this.bojaLjubimac = tokeni[3];
		 this.imeLjubimac = tokeni[4];
		 this.godineLjubimca = Integer.parseInt(tokeni[5]);
		
		 
		 if(brojacID < idLjubimac) {
			 brojacID = idLjubimac;
		 }
	}
	
	
		/** METODE */
	public String toFileRepresentation() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(idLjubimac + "," + takmicarskaOznaka + "," +  vrstaLjubimac 
				   + "," + bojaLjubimac + "," +  imeLjubimac + "," +  godineLjubimca);
		return sbf.toString();
	}
	
	@Override
	public String toString() {
		String ispis = "Ljubimac sa ID-om " + idLjubimac + " ima takmicarsku oznaku " + takmicarskaOznaka 
				     + ". Vrsta ljubimca je " + vrstaLjubimac + ", koji je boje " + bojaLjubimac.toLowerCase() 
				     + ". Ime ljubimca je " + imeLjubimac + ", a godine ljbimca su " + godineLjubimca +" godine/a.";
		return ispis;
	}

	public String toStringAllVlasnik() {
		StringBuilder sb = new StringBuilder("Ljubimac sa ID-om " + idLjubimac + " ima takmicarsku oznaku " + takmicarskaOznaka 
			     + ". Vrsta ljubimca je " + vrstaLjubimac + ", koji je boje " + bojaLjubimac.toLowerCase() 
			     + ". Ime ljubimca je " + imeLjubimac + ", a godine ljbimca su " + godineLjubimca +" godine/a.");
	
		if(vlasnici != null) {
			sb.append("\n\tBrigu o njemu vode korisnici:\n");
			for(int i = 0; i < vlasnici.size(); i++) {
				sb.append("\t" + vlasnici.get(i).toString() + "\n");
			}
		}
		return sb.toString();
	}
	
	
	
	
	public String toStringAllDiploma() {
		StringBuilder sb = new StringBuilder("Ljubimac sa ID-om " + idLjubimac + " ima takmicarsku oznaku " + takmicarskaOznaka 
			     + ". Vrsta ljubimca je " + vrstaLjubimac + ", koji je boje " + bojaLjubimac.toLowerCase() 
			     + ". Ime ljubimca je " + imeLjubimac + ", a godine ljbimca su " + godineLjubimca +" godine/a.");
	
		if(diplome != null) {
			sb.append("\n\t za njega su dodeljene diplome:\n");
			for(int i = 0; i < diplome.size(); i++) {
				sb.append("\t" + diplome.get(i).toString() + "\n");
			}
		}
		return sb.toString();
	}
	
	
	
	
	
	
	

	
	
	// Get & Set metode
	

	public int getIdLjubimac() {
		return idLjubimac;
	}


	public void setIdLjubimac(int idLjubimac) {
		this.idLjubimac = idLjubimac;
	}


	public String getTakmicarskaOznaka() {
		return takmicarskaOznaka;
	}


	public void setTakmicarskaOznaka(String takmicarskaOznaka) {
		this.takmicarskaOznaka = takmicarskaOznaka;
	}


	public String getVrstaLjubimac() {
		return vrstaLjubimac;
	}


	public void setVrstaLjubimac(String vrstaLjubimac) {
		this.vrstaLjubimac = vrstaLjubimac;
	}


	public String getBojaLjubimac() {
		return bojaLjubimac;
	}


	public void setBojaLjubimac(String bojaLjubimac) {
		this.bojaLjubimac = bojaLjubimac;
	}


	public String getImeLjubimac() {
		return imeLjubimac;
	}


	public void setImeLjubimac(String imeLjubimac) {
		this.imeLjubimac = imeLjubimac;
	}


	public int getGoddineLjubimca() {
		return godineLjubimca;
	}


	public void setGodineLjubimca(int godineLjubimca) {
		this.godineLjubimca = godineLjubimca;
	}


	public ArrayList<Vlasnik> getVlasnici() {
		return vlasnici;
	}


	public void setVlasnici(ArrayList<Vlasnik> vlasnici) {
		this.vlasnici = vlasnici;
	}


	public ArrayList<Diploma> getDiplome() {
		return diplome;
	}


	public void setDiplome(ArrayList<Diploma> diplome) {
		this.diplome = diplome;
	}
	
	
	
	
	
	
	
	
	
	

}
