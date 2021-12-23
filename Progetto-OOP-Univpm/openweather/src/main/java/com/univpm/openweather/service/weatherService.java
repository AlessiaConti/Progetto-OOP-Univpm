package com.univpm.openweather.service;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.univpm.openweather.model.Città;

public interface weatherService {

	//Questa è l'interfaccia che contiene i metodi richiamati dal Controller.

	//metodi astratti

	//metodi utili per la rotta /getWeatherbyCoord
	public abstract JSONObject readJSON(double lat, double lon);
	public abstract Città getMeteo(JSONObject obj);
	public abstract JSONObject toJSON(Città city);
	
	//metodi per la rotta aggiuntiva /getWeatherbyName
	public abstract JSONObject readJSONbyName(String city); 
	
	//metodi utili per la rotta /saveToFile
	public abstract String saveToFile(String city);

	//metodi utili per rotta /stats
	public abstract JSONArray LeggiStorico(String name, boolean flag);
	public abstract ArrayList<JSONArray> LeggiStoricoUmidita(ArrayList<String> cities, String period);



}
