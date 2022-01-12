package com.univpm.openweather.stats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import com.univpm.openweather.exception.EccezionePersonalizzata;
import com.univpm.openweather.model.*;

/**
 * Interfaccia che contiene il metodo di scansione e analisi del file per filtraggio
 * 
 * @param indice int
 * @return vettCitta
 * @throws IOException
 * @throws ParseException
 * @throws FileNotFoundException
 * @throws IndexOutOfBoundsException
 * @throws EccezionePersonalizzata
 */

public interface StatsInterface {
	public abstract Vector<Citta> getMeteoArray(int indice) throws IOException, FileNotFoundException, ParseException, IndexOutOfBoundsException, EccezionePersonalizzata;

}
