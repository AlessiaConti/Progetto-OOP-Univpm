package com.univpm.openweather.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.IO.SalvaDati;
import com.univpm.openweather.exception.EccezioneBadRequest;
import com.univpm.openweather.exception.EccezioneNoCoord;
import com.univpm.openweather.service.WeatherService;

/**
 * Classe contenente le richieste per ottenere dati meteo di una città (temp.eff.,temp.perc. e umidità)
 */

@Controller 
public class WeatherController {
	@Autowired
	private WeatherService service; //creo un oggetto weatherService per usare le sue funzionalità (metodi)
	@Autowired
	private SalvaDati stampa; //creo un oggetto stampa preposto a stampare in locale un file contenente i dati meteo

	/**
	 * Rotta che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate
	 * e prevede anche un salvataggio in locale dei dati meteo su un file chiamato 'Dati meteo.txt'
	 * 
	 * @author F.Fabiocchi, A.Conti
	 * @param double lat
	 * @param double lon
	 * @return datiMeteo
	 * @throws IOException
	 * */
	@RequestMapping(value="/getWeather")                
	public ResponseEntity<Object> getWeather( @RequestParam(name="lat") double lat, 
			                                                   @RequestParam (name="lon") double lon) 
					                                                throws IOException, EccezioneNoCoord { 

		JSONObject datiMeteo = null;
		
//	if(lat ==0 || lon==0) throw new EccezioneNoCoord("Hai dimenticato di inserire le coord...");
		try {
			datiMeteo = service.toJSON(service.getMeteo(service.readJSON(lat,lon)));
			stampa.stampaMeteo(datiMeteo); 
//			throw new EccezioneNoCoord("Hai dimenticato di inserire le coord...");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
//		catch (EccezioneNoCoord e) {
//			return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
//		}
		
		return new ResponseEntity<> (datiMeteo, HttpStatus.OK);
		
	} 	


	/**
	 * Rotta aggiuntiva che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite nome
	 * 
	 * @author A.Conti
	 * @param String city che rappresenta il nome della città di cui si richiedono le previsioni
	 * @return JSONObject contenente la data e le previsioni meteo
	 * */
	@RequestMapping(value="/getWeatherbyName")                
	public ResponseEntity<Object> getWeatherbyName(@RequestParam(name="city") String city) throws IOException { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSONbyName(city))), HttpStatus.OK);
	} 

}
