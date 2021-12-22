package com.univpm.openweather.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.service.weatherService;


@Controller 
public class weatherController {

	@Autowired
	private weatherService service; //creo un oggetto weatherService per usare le sue funzionalità (metodi)


	/**
	 * Rotta che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate
	 * */
	@RequestMapping(value="/getWeather")                
	public ResponseEntity<Object> getWeather(@RequestParam(name="lat") double lat, @RequestParam (name="lon") double lon) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON(lat,lon))), HttpStatus.OK);
	} 


	/**
	 * Rotta che salva in un file le info meteo della città inserita dall'utente
	 * tramite coord, e restituisce il path dove viene salvato il file.
	 * IOException se si verificano errori di output su file.
	 */
	@RequestMapping(value="/saveToFile")
	public ResponseEntity<Object> save (@RequestParam(name="lat") double lat, @RequestParam (name="lon") double lon) throws IOException {
		String path = service.saveToFile(lat,lon);
		return new ResponseEntity<> (path, HttpStatus.OK);
	}






}

