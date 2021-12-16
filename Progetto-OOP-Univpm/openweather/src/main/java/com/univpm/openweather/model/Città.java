package com.univpm.openweather.model;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni
	
	//attributi
	private int lon;
	private int lat;
	private int iD;
	private String nome;
	
	//getter
	public int getLon() {
		return lon;
	}
	
	public int getLat() {
		return lat;
	}
	
	public int getiD() {
		return iD;
	}
	
	public String getNome() {
		return nome;
	}
	
	//setter
	public void setLon(int lon) {
		this.lon = lon;
	
	public void setLat(int lat) {
		this.lat = lat;
	}
	
	public void setiD(int iD) {
		this.iD = iD;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}