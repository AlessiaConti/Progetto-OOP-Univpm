package com.univpm.openweather.service;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.univpm.openweather.model.*;


@Service
public class weatherServiceImpl implements weatherService {

	//classe che implementa l'interfaccia weatherService

	//apyKey=chiave necessaria per ottenere informazioni da OpenWeather
	private String apiKey="be1788b24b6c02e4146b4b4cd3eb9058" ;

	//ora implemento i metodi che nell'interfaccia erano astratti

	/**
	 * Il metodo getMeteoCitta prende le previsioni meteo della città da OpenWeather,
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
	
	/**
	 * Il metodo getPrevisioniRichieste utilizza getMeteoCitta per selezionare solo le info 
	 *  richieste (umidità, temperatura effettiva, temperatura percepita).
	 * riceve coord della città di cui si vogliono conoscere le previsioni ristrette
	 * e restituisce un oggetto di tipo Città che contiene tutte le info richieste su meteo e città.
	 */
	
	public Città getPrevisioniRichieste(Coordinate coordinate) {
		
		JSONObject object = getMeteoCitta(coordinate);
		
		Città città = new Città();
		
		città = getInfoCittafromApi(coordinate);
		
		
		
		JSONArray weatherArray = object.getJSONArray("list");
		JSONObject counter;
		
		Vector<InformazioniMeteo> vector = new Vector<InformazioniMeteo>(weatherArray.length());
		
		
		try {
			
			
			for (int i = 0; i<weatherArray.length(); i++) {
				
				InformazioniMeteo weather = new InformazioniMeteo();
				counter = weatherArray.getJSONObject(i);
				weather.set(counter.getInt("visibility"));
				weather.setData(counter.getString("dt_txt"));
				JSONArray arrayW = counter.getJSONArray("weather");
				JSONObject objectW = arrayW.getJSONObject(0);
				weather.setDescription(objectW.getString("description"));
				weather.setMain(objectW.getString("main"));
				JSONObject objectW2 = counter.getJSONObject("main");
				
				weather.setTempEff(objectW2.getDouble("temp")); 
				weather.setTempPer(objectW2.getDouble("feels_like"));
				weather.setUmidità(objectW2.getDouble("humidity"));
				vector.add(weather); 
		
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		città.setVector(vector);
		
		return città;
		
	}





}
