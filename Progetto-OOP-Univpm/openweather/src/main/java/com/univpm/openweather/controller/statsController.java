package com.univpm.openweather.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univpm.openweather.filters.FiltersImpl;
import com.univpm.openweather.model.*;
import com.univpm.openweather.stats.StatsImpl;

/**
 * Controller che contiene rotte per ottenere statistiche su dati meteo richiesti
 */


@RestController
  public class statsController {
	
	@Autowired
	private StatsImpl statImpl;
	@Autowired
	private FiltersImpl filtersImpl;
	
	@RequestMapping(value = "/getStats")
	public ResponseEntity<Vector<Citta>> getStats(@RequestParam(name = "frequenza") int frequenza) 
			throws FileNotFoundException, IOException, ParseException { //mettere param tipo filtro
		return new ResponseEntity<Vector<Citta>> (statImpl.getMeteoArray(frequenza),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getFilters")
	public ResponseEntity<Vector<Citta>> getFilters(
			@RequestParam(name = "frequenza")int frequenza,
			@RequestParam(name = "stat")String stat,
			@RequestParam(name = "filtro")String filtro) 
					 throws FileNotFoundException, IOException, ParseException { //mettere param tipo filtro
		return new ResponseEntity<Vector<Citta>> (filtersImpl.meteo_filtri(filtro, stat, frequenza),HttpStatus.OK);
	}

}
