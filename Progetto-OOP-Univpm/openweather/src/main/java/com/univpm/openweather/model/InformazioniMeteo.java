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
	private long umidita; //data in %
	private String data; //espressa con sistema 'unix, UTC'
	
	/**
	 * Costruttori utili
	 */
	public InformazioniMeteo() {}
	
	/**
	 * Costruttore parametrizzato
	 * @param umidita
	 * @param tempEff
	 * @param tempPer
	 * @param data
	 */
	public InformazioniMeteo(long umidita, double tempEff, double tempPer, String data) {
		this.umidita=umidita;
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
		return umidita;
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
	public void setUmidita(long umidita) {
		this.umidita = umidita;
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
		String s= "Data: " + data + "\n"+ "Umidità: "+ umidita;
		s+= "Temperatura effettiva: "+ tempEff+ "Temperatura percepita: "+ tempPer;
		return s;
	}


}