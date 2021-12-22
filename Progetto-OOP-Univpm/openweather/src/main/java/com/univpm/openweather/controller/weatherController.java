package com.univpm.openweather.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.simple.JSONArray;
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
	@RequestMapping(value="/getWeather")                
	public ResponseEntity<Object> getWeather(@RequestParam(name="lat") double lat, @RequestParam (name="lon") double lon) { 
		return new ResponseEntity<> (service.toJSON(service.getMeteo(service.readJSON(lat,lon))), HttpStatus.OK);
	} 


	/**
	 * Rotta di tipo POST che filtra in base alla periodicità specificata da utente 
	 * (giornaliera, settimanale, mensile) le statistiche riguardanti umidità, 
	 * temp effettiva e percepita
	 * Le città ammesse sono solo Ancona,
	 * 
	 * L'utente deve inserire un JSONObject di questo tipo:
	 * {
     *     "cities": [
     *        {
     *          "name": "Ancona"
     *        },
     *      ],
     *     "period": "giornaliera"
     *  }
	 * Eccezioni:
	 * @throws EmptyStringException se una delle stringhe immesse è vuota.
	 * @throws CityNotFoundException se la città immessa non è una tra quelle indicate sopra.
	 * @throws WrongPeriodException se viene inserita una stringa errata per period, 
	 * cioè una stringa diversa da "giornaliera", "settimanale", "mensile".
	 * @throws IOException se ci sono errori di input da file.
	 */
	@PostMapping(value="/stats")
    public ResponseEntity<Object> statsHistory(@RequestBody String body) 
    		throws EmptyStringException, CityNotFoundException, WrongPeriodException, IOException {
		
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
        	return new ResponseEntity<>(service.readVisibilityHistory(cities,period).toString(),HttpStatus.OK);
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

