package com.univpm.openweather.model;

import java.util.Vector;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni

	//attributi
	private String id;
	private String nome;
	private String paese;

	private Coordinate coordinate; //attributo di tipo 'coordinate' (lon e lat della città)
	private Vector<InformazioniMeteo> infoMeteo = new Vector<InformazioniMeteo>(); //vettore che contiene info meteo della città

	//getter
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public String getid() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPaese() {
		return paese;
	}

	public Vector<InformazioniMeteo> getInfoMeteo() {
		return infoMeteo;
	}

	//setter
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setid(String string) {
		this.id = string;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPaese(String paese) {
		this.paese = paese;
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
	@Override
	public String toString() {
		String s= "id: "+this.id+"\n";
		s+= "Nome: "+this.nome+"\n";
		s+="Coordinate: "+ this.coordinate+"\n";
		s+="Informazioni sul meteo: "+toStringInfoMeteo()+"\n";
		return s;
	}
}