package com.univpm.openweather.service;

import org.json.simple.JSONObject;

import com.univpm.openweather.exception.EccezioneCoordErrate;
import com.univpm.openweather.model.Citta;

/**
 * Questa è l'interfaccia che contiene i metodi richiamati dal Controller.
 * @author A.Conti
 * 
 */

public interface WeatherServiceInterface {

	//metodi astratti

	//metodi utili per la rotta /getWeather(byCoord)
	public abstract JSONObject readJSON(double lat, double lon);
	public abstract Citta getMeteo(JSONObject obj);
	public abstract JSONObject toJSON(Citta city);

	//metodi per la rotta aggiuntiva /getWeatherbyName
	public abstract JSONObject readJSONbyName(String city); 

}
