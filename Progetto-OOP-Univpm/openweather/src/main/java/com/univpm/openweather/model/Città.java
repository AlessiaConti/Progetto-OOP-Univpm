package com.univpm.openweather.model;

import java.util.Vector;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni
	
	//attributi
	private int iD;
	private String nome;
	private Coordinate coordinate; //attributo di tipo 'coordinate' (lon e lat della città)
	private Vector<InformazioniMeteo> infoMeteo = new Vector<InformazioniMeteo>(); //vettore che contiene info meteo della città
	
	//getter
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public int getiD() {
		return iD;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Vector<InformazioniMeteo> getInfoMeteo() {
		return infoMeteo;
	}
	
	
	//setter
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public void setiD(int iD) {
		this.iD = iD;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setInfoMeteo(Vector<InformazioniMeteo> infoMeteo) {
		this.infoMeteo = infoMeteo;
	}
	
	//metodi toString
	
	// metodo che scrive il vettore infoMeteo come una stringa
	public String toStringInfoMeteo() {
		String s= ""; //inizializzo stringa
		for (int i=0; i<infoMeteo.size(); i++)
			s += infoMeteo.get(i).toString(); //richiama il metodo toString della classe 'InformazioniMeteo'
		return s;
	}

	// Override del metodo toString 
	// restituisce la stringa che rappresenta la città
	public String toString() {
		String s= "iD: "+this.iD;
		s+= "Nome: "+this.nome+"\n";
		s+="Coordinate: "+ this.coordinate+"\n";
		s+="Informazioni sul meteo: "+toStringInfoMeteo();
		return s;
	}
}