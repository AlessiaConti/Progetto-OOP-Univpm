package com.univpm.openweather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.model.Coordinate;
import com.univpm.openweather.service.weatherService;


@Controller 
public class weatherController {
	
	@Autowired
	private weatherService service; //creo un oggetto weatherService per usare le sue funzionalità (metodi)
	
	/**Rotta che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente (per ora tramite nome)*/
	
	@RequestMapping(value="/getWeatherbyName")                //chiamando rotta senza parametro vale il valore di default
	public ResponseEntity<Object> getWeather(@RequestParam(name="city", defaultValue="London") String city) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON(city))), HttpStatus.OK);
	} 
	
	/**Rotta che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate*/
	
	@RequestMapping(value="/getWeatherbyCoord")    
	public ResponseEntity<Object> getWeather(@RequestParam(/**/ )  /*coord*/  ) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON( /**/ ))), HttpStatus.OK);
	} 
	
	

	

}

