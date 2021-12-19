package com.univpm.openweather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.model.Coordinate;


@Controller 
public class weatherController {
	
	@Autowired
	weatherService service;
	
	/**Rotta di tipo GET che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate*/
	
	@GetMapping(value = "/Forecast")
	public ResponseEntity<Object> getForecast(@RequestParam Coordinate coordinate) {
		return new ResponseEntity<> (service.getForecastfromApi(coordinate).toString(), HttpStatus.OK);
    }
	

}

