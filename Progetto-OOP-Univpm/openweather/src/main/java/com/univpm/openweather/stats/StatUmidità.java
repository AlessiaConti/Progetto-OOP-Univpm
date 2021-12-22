package com.univpm.openweather.stats;

import org.json.JSONObject;
import org.json.simple.JSONArray;

public class StatUmidità {
	/**
	 * Questo metodo restituisce il JSONArray con le statistiche sull'umidità di tutti i giorni presenti nello storico.
	 * @param city rappresenta il nome della città di cui si vuole fare statistica.
	 * @param umidità contiene tutte le informazioni sull' umidità presenti nello storico.
	 * @return un JSONArray con le statistiche giorno per giorno.
	 */
	public JSONArray StatUmiditaGiornaliera(String city, JSONArray visibility) {
		
		JSONArray stats = new JSONArray();
		int i=0;
		String date = "";
		
		stats.put(city);
		
		while(i<visibility.length()) {
			
			JSONArray oneDayVisibility = new JSONArray();
			oneDayVisibility = visibility.getJSONArray(i);
			
			int average=0;
			int j=0;
			int max=0;
			int min=10000;
			int variance=0;
			
			while(j<oneDayVisibility.length()) {
				JSONObject hour = new JSONObject();
				hour = oneDayVisibility.getJSONObject(j);
				average += hour.getInt("visibility");
				date = hour.getString("data").substring(0,10);
				if(hour.getInt("visibility")>max)
					max = hour.getInt("visibility");
				if(hour.getInt("visibility")<min)
					min = hour.getInt("visibility");
				
				j++;
			}
			
			average = average/j;
			
			JSONObject info = new JSONObject();
			info.put("date", date);
			info.put("max", max);
			info.put("min", min);
			info.put("average", average);
			
			j=0;
			while(j<oneDayVisibility.length()) {
				JSONObject hour = new JSONObject();
				hour = oneDayVisibility.getJSONObject(j);
				hour.getInt("visibility");
		        variance += ((hour.getInt("visibility")-average))^2;
		       	j++;
		     }
			
			variance /= j;
			info.put("variance", variance);
			
			stats.put(info);
			
			i++;
		}
		
		return stats;
	}
	

}
