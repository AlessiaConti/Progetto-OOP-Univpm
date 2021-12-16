package com.univpm.openweather.model;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni
	
	//attributi
	private Coordinate coordinate; 
	private int iD;
	private String nome;
	
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
	
}