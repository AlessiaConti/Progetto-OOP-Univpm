package com.univpm.openweather.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.univpm.openweather.exception.CityNotFoundException;
import com.univpm.openweather.exception.EmptyStringException;
import com.univpm.openweather.exception.WrongPeriodException;

import com.univpm.openweather.service.weatherService;

@Controller 
public class weatherController {

	@Autowired
	private weatherService service; //creo un oggetto weatherService per usare le sue funzionalità (metodi)
	


	/**
	 * Rotta che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate
	 * */
	@RequestMapping(value="/getWeatherbyCoord")                
	public ResponseEntity<Object> getWeatherC(@RequestParam(name="lat") double lat, @RequestParam (name="lon") double lon) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON(lat,lon))), HttpStatus.OK);
	} 
	
	/**
	 * Rotta AGGIUNTIVA che mostra le previsioni relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite nome
	 * */
	@RequestMapping(value="/getWeatherbyName")                
	public ResponseEntity<Object> getWeatherN(@RequestParam(name="city") String city) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSONbyName(city))), HttpStatus.OK);
	} 
	
	/**
	 * Rotta che salva in un file le info meteo della città inserita dall'utente
	 * tramite coord, e restituisce il path dove viene salvato il file.
	 * IOException se si verificano errori di output su file.
	 */
	@RequestMapping(value="/saveToFile")
	public ResponseEntity<Object> save (@RequestParam(name="city") String city) throws IOException {
		return new ResponseEntity<> (service.saveToFile(city), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo POST che filtra in base alla periodicità specificata da utente 
	 * (giornaliera, settimanale, mensile) le statistiche riguardanti solo
	 * temp percepita (per ora)
	 * Le città ammesse sono solo Ancona e Roma per ora
	 * 
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * {
     *     "città": [
     *        {
     *          "nome": "Ancona"
     *        },
     *         {
     *          "nome": "Roma"
     *        }
     *      ],
     *     "periodicità": "giornaliera"
     *  }
	 * Eccezioni:
	 * @throws EmptyStringException se una delle stringhe immesse è vuota.
	 * @throws CityNotFoundException se la città immessa non è una tra quelle indicate sopra.
	 * @throws WrongPeriodException se viene inserita una stringa errata per period, 
	 * cioè una stringa diversa da "giornaliera", "settimanale", "mensile".
	 * @throws IOException se ci sono errori di input da file.
	 */
	@PostMapping(value="/statUmidita")
    public ResponseEntity<Object> statsHistory(@RequestBody String body) 
    		throws EmptyStringException, CityNotFoundException, WrongPeriodException, IOException {
		
		//costruisco il BODY della richiesta che poi dovrà inserire l'utente
		JSONObject object = new JSONObject(body);
        JSONArray array = new JSONArray();

        array = object.getJSONArray("città");
        
        ArrayList<String> cities = new ArrayList<String>(array.length());
        
        for(int i=0; i<array.length();i++) {
            JSONObject obj = new JSONObject();
            obj = array.getJSONObject(i);
            cities.add(obj.getString("nome"));
        }
		
        String period = object.getString("periodicità");
        
        try {
        	return new ResponseEntity<>(service.LeggiStoricoUmidita(cities,period).toString(),HttpStatus.OK);
        }
        catch (EmptyStringException e) {
			return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
		}
        catch (CityNotFoundException e) {
			return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
		}
        catch (WrongPeriodException e) {
        	return new ResponseEntity<>(e.getMex(),HttpStatus.BAD_REQUEST);
        }
        
	}






}

