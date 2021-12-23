package com.univpm.openweather.filters;

import java.util.ArrayList;

import org.json.simple.JSONArray;

/**
 * Questa è l'interfaccia che contiene i metodi per il filtraggio di un valore 
 * (il valore può essere umidità, temp effettiva o temp percepita)  
 * @author AlessiaConti 
 */
public interface FiltroGenerale {
	//filtro giornaliero
		public abstract JSONArray unGiorno(ArrayList<String> cities, String value);

}
