package com.univpm.openweather.service;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.univpm.openweather.model.Coordinate;

@Service
public class weatherServiceImpl implements weatherService {
	
	//classe che implementa l'interfaccia weatherService
	
	//apyKey=chiave necessaria per ottenere informazioni da OpenWeather
	private String apiKey="be1788b24b6c02e4146b4b4cd3eb9058" ;
	
	//ora implemento i metodi che nell'interfaccia erano astratti
	
	/**
	 * Il metodo getCityWeather prende le previsioni meteo della città da OpenWeather,
	 * riceve in input le coordinate della città di cui si vuole conoscere le previsioni meteo
	 * e restituisce un JSONObject contenente le previsioni meteo complete.
	 */
	
public JSONObject getMeteoCitta(Coordinate coordinate) {
		
		JSONObject obj;
		
		//costruisco l'url della chiamata api che sarebbe:
		//api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
		String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + coordinate.getLat() + "&lon="+ coordinate.getLon()+"&appid="+apiKey;
		
		RestTemplate rt = new RestTemplate(); //RestTemplate accede a servizi http
		
		//getForObject (): metodo per trasformare l'url in stringa JSON
		obj = new JSONObject(rt.getForObject(url, String.class)); 
		
		return obj;
		
	}
	
	
	
	

}
