package com.univpm.openweather.service;

import org.json.JSONObject;
import org.json.JSONArray;

import com.univpm.openweather.model.Città;
import com.univpm.openweather.model.Coordinate;


public interface weatherService {
	
	//Questa classe è l'interfaccia che contiene i metodi richiamati dal Controller.
	
	//metodi astratti
	
	//metodi utili per la rotta /getForecast
	public abstract JSONObject getMeteoCitta(Coordinate coordinate); //metodo1
	public abstract JSONArray getPrevisioniRichieste(Coordinate coordinate);//metodo2
	
}
