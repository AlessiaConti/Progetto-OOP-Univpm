package com.univpm.openweather.service;

import org.json.JSONArray;

import com.univpm.openweather.model.Coordinate;

public interface weatherService {
	
	//Questa classe è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi astratti
	public abstract JSONArray getForecastfromApi(Coordinate coordinate);

}
