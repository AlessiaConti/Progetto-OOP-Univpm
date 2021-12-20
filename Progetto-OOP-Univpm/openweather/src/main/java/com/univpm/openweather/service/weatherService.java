package com.univpm.openweather.service;

import org.json.JSONObject;

import com.univpm.openweather.model.Città;


public interface weatherService {
	
	//Questa classe è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi (astratti) utili per la rotta /getWeather
	public abstract Città getMeteo(JSONObject meteo);
	public abstract JSONObject readJSON(String city);
	public abstract JSONObject toJSON(Città city);
	

	
}
