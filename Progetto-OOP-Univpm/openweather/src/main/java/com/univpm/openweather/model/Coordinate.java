package com.univpm.openweather.model;

public class Coordinate {
	
	//Classe che rappresenta le coordinate (latitudine e longitudine) di una città
	
	private int lon;
	private int lat;
	
	//getter
	public int getLon() {
		return lon;
	}
	public int getLat() {
		return lat;
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
