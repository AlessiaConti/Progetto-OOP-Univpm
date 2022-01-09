package com.univpm.openweather.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.IO.SalvaDati;
import com.univpm.openweather.service.weatherService;

/**
 * Controller che gestisce le chiamate al server che il client può fare per ricevere dati meteo di una città
 */

@Controller 
public class weatherController {

	@Autowired
	private weatherService service; //creo un oggetto weatherService per usare le sue funzionalità (metodi)
	@Autowired
	private SalvaDati stampa; //creo un oggetto stampa preposto a stampare in locale un file contenente i dati meteo
	
	/**
	 * Rotta che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite COORDINATE
	 * e stampa in locale il file con i dati meteo 
	 * 
	 * @param lat che rappresenta la latitudine della città
	 * @param lon che rappresenta la longitudine della città
	 * @return un JSONObject contenente la data e le previsioni meteo su umidità, 
	 * temperatura effettiva e temperatura percepita della città inserita.
	 * */
	@RequestMapping(value="/getWeather")                
	public ResponseEntity<Object> getWeather(@RequestParam(name="lat") double lat, @RequestParam (name="lon") double lon) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON(lat,lon))), HttpStatus.OK);
	}
	
	/**
	 * Rotta AGGIUNTIVA che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite NOME
	 * 
	 * @param city che rappresenta il nome della città di cui si richiedono le previsioni
	 * @return un JSONObject contenente la data e le previsioni meteo
	 * */
	@RequestMapping(value="/getWeatherbyName")                
	public ResponseEntity<Object> getWeather(@RequestParam(name="city") String city) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSONbyName(city))), HttpStatus.OK);
	} 
	


}

