package com.univpm.openweather.service;

import org.json.JSONObject;
import org.json.JSONArray;

import com.univpm.openweather.model.Città;
import com.univpm.openweather.model.Coordinate;


public interface weatherService {
	
	//Questa classe è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi astratti
	public abstract JSONObject getMeteoCitta(Coordinate coordinate);
	public abstract Città getCityInfofromApi(Coordinate coordinate);
	public abstract JSONArray getVisibilityfromApi(Coordinate coordinate);
	public abstract Città getCityWeatherRistrictfromApi(Coordinate coordinate); 

}
