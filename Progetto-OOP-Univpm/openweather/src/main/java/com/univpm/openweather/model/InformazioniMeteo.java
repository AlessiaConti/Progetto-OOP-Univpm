package com.univpm.openweather.model;

public class InformazioniMeteo {
	
	//classe che contiene i dati meteo richiesti per il progetto
	
	//attributi
	private double tempEff; //data in Kelvin
	private double tempPer;
	private long umidità; //data in %
	
	//getter 
	public double getTempEff() {
		return tempEff;
	}
	public double getTempPer() {
		return tempPer;
	}
	public long getUmidità() {
		return umidità;
	}
	
	//setter
	public void setTempEff(double tempEff) { 
		this.tempEff = tempEff;
	}
	public void setTempPer(double tempPer) {
		this.tempPer = tempPer;
	}
	public void setUmidità(long umidità) {
		this.umidità = umidità;
	}
	
	// TODO Override del metodo toString
	
	
}