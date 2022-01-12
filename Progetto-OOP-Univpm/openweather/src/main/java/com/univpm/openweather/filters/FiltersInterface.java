package com.univpm.openweather.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import com.univpm.openweather.model.Citta;

/**
 * Interfaccia che contiene i metodi per il filtraggio dei dati meteo
 * 
 * @author F.Fabiocchi
 * @param filtro String
 * @param stat String
 * @param int Indice
 * @return vett_Citta_{stat}{filtro}
 * @throws FileNotFoundException
 * @throws IOException
 * @throws ParseException
 * @throws IndexOutOfBoundsException
 */


public interface FiltersInterface {
	public Vector<Citta> meteo_filtri(String filtro, String stat, int indice) throws FileNotFoundException, IOException, ParseException; //IndexOutOfBoundsException;

}
