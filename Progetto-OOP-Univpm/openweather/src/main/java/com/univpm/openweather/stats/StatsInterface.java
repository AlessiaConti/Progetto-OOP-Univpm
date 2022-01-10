package com.univpm.openweather.stats;

import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

import org.json.simple.*;

import com.univpm.openweather.exception.EccezionePersonalizzata;
import com.univpm.openweather.model.*;

/**
 * Interfaccia che contiene il metodo di scansione e analisi del file per filtraggio
 * 
 * @param indice int
 * @return vettCitta
 * @throws IOException, org.json.simple.parser.ParseException, FileNotFoundException, EccezionePersonalizzata
 */

public interface StatsInterface {
	public abstract Vector<Citta> getMeteoArray(int indice) throws IOException,FileNotFoundException, org.json.simple.parser.ParseException, EccezionePersonalizzata;

}
