package com.univpm.openweather.stats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.*;
import java.text.ParseException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.univpm.openweather.exception.EccezionePersonalizzata;
import com.univpm.openweather.model.Citta;
import com.univpm.openweather.model.InformazioniMeteo;
import com.univpm.openweather.service.*;


/**
 * Implementazione interfaccia statistiche
 * 
 * @author F.Fabiocchi
 * 
 */

@Service
public class StatsImpl implements StatsInterface {
	/**
	 * Metodo che esegue scansione e analisi del file locale "AnconaStats.json"  secondo un indice che va da 1 a 30
	 * 
	 * @param indice int
	 * @return vettCitta
	 * @throws IOException, org.json.simple.parser.ParseException, FileNotFoundException, EccezionePersonalizzata
	 */
	
	@Override
	public Vector<Citta> getMeteoArray(int indice) throws IOException, org.json.simple.parser.ParseException, FileNotFoundException{
		
		Vector<Citta> vettCitta;
		vettCitta = new Vector<Citta>();
		weatherServiceImpl ws = new weatherServiceImpl();
		
		try {
			if(indice < 1 || indice > 30)
				throw new EccezionePersonalizzata("HAI INSERITO UN VALORE NON COMPRESO TRA 1 E 30, RIPROVA: ");					
		}catch(EccezionePersonalizzata p) {
			System.out.println("HAI INSERITO UN VALORE NON COMPRESO TRA 1 E 30, RIPROVA: ");
			indice = p.getIndice();
		}
		
		try {	
			
			JSONParser jsonpars = new JSONParser();
			BufferedReader reader = new BufferedReader(new FileReader("AnconaStats.json"));
			JSONArray array = (JSONArray)jsonpars.parse(reader);
			
			for(int i = 0; i < indice; i++) {
				JSONObject obj = (JSONObject)array.get(i);
				vettCitta.add(ws.getMeteo(obj));
			}
	
		} catch (IOException | org.json.simple.parser.ParseException e) {
			System.out.println("ECCEZIONE LANCIATA");
			e.printStackTrace();
		}
		
		return vettCitta;	
	}	

}
