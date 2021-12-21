package com.univpm.openweather.service;

import org.json.simple.JSONObject;
import com.univpm.openweather.model.Città;

public interface weatherService {
	
	//Questa è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi (astratti) utili per la rotta /getWeather
	public abstract JSONObject readJSON(double lat, double lon);
	public abstract Città getMeteo(JSONObject obj);
	public abstract JSONObject toJSON(Città city);
	

	
}
