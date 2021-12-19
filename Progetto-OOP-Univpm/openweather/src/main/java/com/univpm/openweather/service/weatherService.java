package com.univpm.openweather.service;

import org.json.JSONObject;
import org.json.JSONArray;

import com.univpm.openweather.model.Città;
import com.univpm.openweather.model.Coordinate;


public interface weatherService {
	
	//Questa classe è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi astratti
	public abstract JSONObject toJSON(Città città);
	public abstract JSONObject getJSONForecast(Coordinate coordinate);
	public abstract Città getForecast(JSONObject forecast);
	public abstract void saveToFile(JSONObject obj);

}
