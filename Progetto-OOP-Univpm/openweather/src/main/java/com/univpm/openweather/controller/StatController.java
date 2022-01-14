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

import com.univpm.openweather.filters.FiltersImpl;
import com.univpm.openweather.model.*;

/**
 * Classe contenente la richiesta GET per filtrare un dato meteo a scelta tra temp.eff.,temp.perc. e umidità
 * 
 * @author F.Fabiocchi
 *
 */

@Controller
public class StatController {
	@Autowired
	private FiltersImpl filtersImpl;

	/**
	 * Richiesta GET che tramite scelta di statistica e filtro esegue tale filtro in un intervallo da 1 a 30 giorni
	 * 
	 * @param frequenza int
	 * @param stat String
	 * @param filtro String
	 * @return  ResponseEntity<Vector<Citta>>
	 * @throws FileNotFoundException, IOException, ParseException
	 */

	@RequestMapping(value = "/getFilters")
	public ResponseEntity<Vector<Citta>> getFilters(@RequestParam(name = "frequenza")int frequenza,@RequestParam(name = "stat")String stat,@RequestParam(name = "filtro")String filtro) throws FileNotFoundException, IOException, ParseException { //mettere param tipo filtro
		return new ResponseEntity<Vector<Citta>> (filtersImpl.meteo_filtri(filtro, stat, frequenza),HttpStatus.OK);
	}

}
