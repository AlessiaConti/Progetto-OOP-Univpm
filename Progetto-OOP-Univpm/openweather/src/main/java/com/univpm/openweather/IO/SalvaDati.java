package com.univpm.openweather.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.*;
import org.springframework.stereotype.Service;
import com.univpm.openweather.service.*;

/**
 * @author F.Fabiocchi
 */ 
@Service
public class SalvaDati {

	//attributi
	weatherServiceImpl Ws = new weatherServiceImpl();
	double lat,lon;

	public String stampaMeteo(JSONObject obj) {

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
