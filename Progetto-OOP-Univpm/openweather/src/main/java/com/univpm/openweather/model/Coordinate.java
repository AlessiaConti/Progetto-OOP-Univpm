package com.univpm.openweather.model;

/**
 * Classe che rappresenta le coordinate (latitudine e longitudine) di una città
 */

public class Coordinate {

	/**
	 * Attributi
	 */
	private double lon;
	private double lat;

	/** Costruttore dell'oggetto Coordinate
	 * @param lat      rappresenta la latitudine
	 * @param lon     rappresenta la longitudine
	 */
	public Coordinate(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * Metodi Getters che restituiscono latitudine e longitudine
	 */
	public double getLon() {
		return this.lon;
	}
	public double getLat() {
		return this.lat;
	}

	/**
	 * Metodi Setters che settano latitudine e longitudine
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Override del metodo toString. 
	 * @return String (stringa che rappresenta le coordinate della città)
	 */
	@Override
	public String toString() {
		return "[Latitudine:" + this.lat + ", Longitudine:" + this.lon + "]";
	}

}
