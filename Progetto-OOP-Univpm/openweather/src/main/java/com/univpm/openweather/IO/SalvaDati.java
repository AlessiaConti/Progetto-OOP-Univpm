package com.univpm.openweather.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.*;
import org.springframework.stereotype.Service;

import com.univpm.openweather.exception.EccezioneCoordErrate;
import com.univpm.openweather.service.*;

/**
 * Classe che sfruttando WeatherService salva in locale il JSON della città cercata con WeatherController
 * @author F.Fabiocchi
 */ 
@Service
public class SalvaDati {

	WeatherService Ws = new WeatherService();
	double lat,lon;
	
	/**
	 * Metodo di stampa che tramite PrintWriter salva in locale il file scansionato 
	 * 
	 * @param obj JSONObject
	 * @return "Stampa avvenuta"
	 * @throws IoException
	 */
	public String stampaMeteo(JSONObject obj) throws IOException, EccezioneCoordErrate {

		try {

			PrintWriter stampa = new PrintWriter(new BufferedWriter(new FileWriter("Dati meteo.txt")));
			Ws.toJSON(Ws.getMeteo(Ws.readJSON(lat,lon)));
			stampa.write(obj.toJSONString());

			stampa.flush();
			stampa.close();

		} catch (IOException e) {
			e.printStackTrace();
		}	

		return "Stampa avvenuta.";
	}	
}
