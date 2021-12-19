package com.univpm.openweather.model;

public class InformazioniMeteo {
	
	//classe che contiene i dati meteo richiesti per il progetto
	
	//attributi
	private double tempEff; //data in Kelvin
	private double tempPer;
	private double umidità; //data in %
	
	//getter 
	public double getTempEff() {
		return tempEff;
	}
	public double getTempPer() {
		return tempPer;
	}
	public double getUmidità() {
		return umidità;
	}
	
	//setter
	public void setTempEff(double tempEff) { 
		this.tempEff = tempEff;
	}
	public void setTempPer(double tempPer) {
		this.tempPer = tempPer;
	}
	public void setUmidità(double umidità) {
		this.umidità = umidità;
	}
	
	//Override del metodo toString
	// Restituisce stringa che rappresenta le info sul meteo della città
	@Override
	public String toString() {
		String s="Temperatura effettiva: " + this.tempEff+"\n";
		s+= "Temperatura percepita: "+ this.tempPer+"\n";
		s+="Umidità: "+ this.umidità+"\n";
		return s;
	}
	
}