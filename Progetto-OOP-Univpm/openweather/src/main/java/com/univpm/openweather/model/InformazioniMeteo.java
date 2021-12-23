package com.univpm.openweather.model;

public class InformazioniMeteo {
	
	//classe che contiene i dati meteo richiesti per il progetto
	
	//attributi
	private double tempEff; //data in Kelvin
	private double tempPer;
	private long umidità; //data in %
	private String data; //espressa con sistema 'unix, UTC'
	
	//getter 
	public double getTempEff() {
		return tempEff;
	}
	public double getTempPer() {
		return tempPer;
	}
	public long getUmidita() {
		return umidità;
	}
	public String getData() {
		return data;
	}
	
	//setter
	public void setTempEff(double tempEff) { 
		this.tempEff = tempEff;
	}
	public void setTempPer(double tempPer) {
		this.tempPer = tempPer;
	}
	public void setUmidita(long umidità) {
		this.umidità = umidità;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	// TODO Override del metodo toString
	
	
}