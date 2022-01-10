package com.univpm.openweather.model;

/** 
 * Questa classe descrive le proprietà di ogni città e le relative informazioni meteo
 */

public class Citta {

	/**
	 * attributi che caratterizzano ogni città
	 */
	private String id;
	private String nome;
	private String paese;

	private Coordinate coordinate; //attributo di tipo 'coordinate' (lon e lat della città)
	private InformazioniMeteo infoMeteo; //attributo che contiene info meteo della città

	/**
	 * Metodi Getters che restituiscono tutti gli attributi della città
	 */
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
	public InformazioniMeteo getInfoMeteo() {
		return infoMeteo;
	}


	/**
	 * Metodi Setters che settano tutti gli attributi della città
	 */
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
	public void setInfoMeteo(InformazioniMeteo infoMeteo) {
		this.infoMeteo = infoMeteo;
	}

	/**
	 * Override del metodo toString.
	 * @return String (stringa che rappresenta la città)
	 */
	@Override
	public String toString() {
		String s= "Nome della città: " + nome +"\n"+ "id:" +id;
		s+= "\n"+ "Coordinate: " + coordinate.toString();
		s+= "\n"+ "Informazioni meteo: " + infoMeteo.toString();
		return  s;
	}


}
