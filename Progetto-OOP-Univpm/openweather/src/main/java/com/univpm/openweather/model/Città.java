package com.univpm.openweather.model;

import java.util.Vector;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni
	
	//attributi
	private int iD;
	private String nome;
	private Coordinate coordinate; //attributo di tipo 'coordinate' (lon e lat della città)
	private Vector<InformazioniMeteo> vector = new Vector<InformazioniMeteo>(); //vettore che contiene info meteo della città
	
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
	

    //(Metodo che restituisce il vettore di informazioniMeteo della città)
	public Vector<InformazioniMeteo> getVector() {
		return vector;
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
	
	//(Metodo che setta il vettore di informazioniMeteo della città)
	public void setVector(Vector<InformazioniMeteo> vector) {
		this.vector = vector;
	}
}