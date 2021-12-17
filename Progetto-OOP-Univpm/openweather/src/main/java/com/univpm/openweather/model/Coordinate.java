package com.univpm.openweather.model;

public class Coordinate {
	
	//Classe che rappresenta le coordinate (latitudine e longitudine) di una città
	
	//attributi
	private double lon;
	private double lat;
	
	//costruttore
	public Coordinate(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	//getter
	public double getLon() {
		return this.lon;
	}
	public double getLat() {
		return this.lat;
	}
	
	//setter
	public void setLon(int lon) {
		this.lon = lon;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	
	//Override del metodo toString 
	// restituisce stringa che rappresenta le coordinate della città
	@Override
	public String toString() {
		return "[Latitudine:" + this.lat + ", Longitudine:" + this.lon + "]";
	}

}
