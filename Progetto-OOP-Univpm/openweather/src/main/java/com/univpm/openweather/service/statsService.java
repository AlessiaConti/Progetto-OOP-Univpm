package com.univpm.openweather.service;

import java.util.ArrayList;
import org.json.simple.JSONArray;

public interface statsService {
	
	//metodi astratti utili per rotta /stats
	public abstract JSONArray LeggiStorico(String name, boolean flag);
	public abstract ArrayList<JSONArray> LeggiStoricoUmidita(ArrayList<String> cities, String period); 

}
