package com.univpm.openweather.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

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
import com.univpm.openweather.model.Città;
import com.univpm.openweather.model.InformazioniMeteo;
import com.univpm.openweather.service.CittaJSON;
import com.univpm.openweather.service.EccezionePersonalizzata;
import com.univpm.openweather.service.MeteoCitta;
import com.univpm.openweather.service.weatherService;
import com.univpm.openweather.stats.StatTempPercepita;

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
/**************************************************************************************/
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
	@PostMapping(value="/stats")
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
	/**************************************************************************************/
	/**
	 * Rotte per statistiche e filtri
	 */
		private StatTempPercepita sTP = new StatTempPercepita();//Oggetto StatsTempPercepita utile per ricevere stats sulla temp percepita
		
		@RequestMapping("/stats")
		public Vector<Città> stats(@RequestParam(value = "city") String city,
				@RequestParam(value = "dataInit") long dataInit,
				@RequestParam(value = "dataFin") long dataFin, 
				@RequestParam(value = "variabile") String variabile,
				@RequestParam(value = "tipoStat") String tipoStat) {

			cJVect = new Vector<CittaJSON>();
			String[] citta = new String[] { citta1, citta2 };

			for (int i = 0; i < citta.length; i++) {
				/* Qui filtra per città */
				vettCitta = fC.getFromCityFilter(citta[i]);
				/*
				 * Qui cambia il formato delle date da giorno-mese-anno a secondi passati dal
				 * 01/01/1970
				 */
				/* Qui filtra il vettore precedentemente filtrato per citta, per data */
				vettData = fD.getFromDataFilter(vettCitta, dataInit, dataFin);
				/*
				 * Qui crea un vettore e ci mette il massimo/minimo della statistica data in
				 * input
				 */
				try {
					if (vettData.isEmpty())
						throw new EccezionePersonalizzata("La ricerca non ha prodotto alcun risultato!");
				} catch (EccezionePersonalizzata eP) {
					return EccezionePersonalizzata.getVCJError();
				}
				Vector<MeteoCitta> mCVect1 = this.variabile(variabile, tipoStat, vettData);
				/*
				 * Qui cambia il formato della data da secondi passati dal 01/01/1970 a
				 * giorno-mese-anno e aggiunge tutto ad un vettore di CittaJSON (con data
				 * formato giorno-mese-anno)
				 */
				cJVect.addAll(cCJ.getCittaJSON(mCVect1));
				/*
				 * Torna indietro e rifà tutto se nel parametro in entrata c'è più di una città
				 */
			}

			/*
			 * Ritorna il vettore finale filtrato per città, data e con la statistica
			 * desiderata
			 */
			return cJVect;
		}

		
		/**
		 * Metodo che restituisce le stats desiderate, restituendo un oggetto MeteoCitta
		 * (formato data String). A seconda del var dato in input, parte una funzione
		 * che genera le stats di tale var
		 * 
		 * @param var          String
		 * @param tipoStat     String
		 * @param vectPerStats Vector<MeteoCitta>
		 * @return
		 */
		public Vector<InformazioniMeteo> variabile(String var, String tipoStat, Vector<InformazioniMeteo> vectPerStats) {

			switch (var) {

//			case "pressione":
//				return sP.getStats(tipoStat, vectPerStats);

//			case "temp":
//				return sT.getStats(tipoStat, vectPerStats);

//			case "tempMax":
//				return sTM.getStats(tipoStat, vectPerStats);

//			case "tempMin":
//				return sTm.getStats(tipoStat, vectPerStats);

			case "tempPercepita":
				return sTP.getStatistiche(tipoStat, vectPerStats);

//			case "umidita":
//				return sU.getStats(tipoStat, vectPerStats);

			}
			return null;

		}




}

