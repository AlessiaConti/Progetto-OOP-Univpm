package com.univpm.openweather.model;

/**
 * Classe che contiene i dati meteo richiesti per il progetto
 */

public class InformazioniMeteo {

	/**
	 * Attributi
	 */
	private double tempEff; //data in Kelvin
	private double tempPer;
	private long umidità; //data in %
	private String data; //espressa con sistema 'unix, UTC'
	
	/**
	 * Costruttori utili
	 */
	public InformazioniMeteo() {
		
	}
	public InformazioniMeteo(long umidità, double tempEff, double tempPer, String data) {
		this.umidità=umidità;
		this.tempEff=tempEff;
		this.tempPer=tempPer;
		this.data=data;
	}

	/**
	 * Metodi Getters che restituiscono le informazioni meteo
	 */
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

	/**
	 * Metodi Setters che settano le informazioni meteo
	 */
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

	/**
	 * Override del metodo toString.
	 * @return String che rappresenta il meteo.
	 */
	@Override
	public String toString() {
		String s= "Data: " + data + "\n"+ "Umidità: "+ umidità;
		s+= "Temperatura effettiva: "+ tempEff+ "Temperatura percepita: "+ tempPer;
		return s;
	}


}