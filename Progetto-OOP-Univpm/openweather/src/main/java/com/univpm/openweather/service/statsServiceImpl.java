package com.univpm.openweather.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;

import com.univpm.openweather.exception.CityNotFoundException;
import com.univpm.openweather.exception.EmptyStringException;
import com.univpm.openweather.exception.WrongPeriodException;
import com.univpm.openweather.stats.StatUmidità;

public class statsServiceImpl implements statsService{
	/**
	 * Questo metodo viene richiamato da LeggiStoricoUmidita().
	 * Si occupa della lettura dello storico della città passata in ingresso. 
	 * A seconda che il flag sia true o false, il metodo andrà a leggere lo storico 
	 * per le statistiche sull'umidità.
	 * 
	 * @param name è il nome della città di cui si vuole leggere lo storico.
	 * @param flag indica quale storico andare a leggere.
	 * @return il JSONArray che contiene tutte le informazioni sulla visibilità.
	 * @throws IOException se si verificano errori di input da file.
	 */
	
	public JSONArray LeggiStorico(String name, boolean flag) {
		
		String path =  System.getProperty("user.dir") + "/visibility/" + name +".txt";
		
		String everything;
			
		BufferedReader br = new BufferedReader(new FileReader(path));
		
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    everything = sb.toString();
			} finally {
			    br.close();
			}	
		
			JSONArray array = new JSONArray(everything);
	
			return array;
			
	}
	
	
	/**
	 * Questo metodo va a richiamare LeggiStorico per leggere i file su cui sono salvate le info
	 * relative alla visibilità per 3 settimane. Dopo aver salvato in un ArrayList di JSONArray 
	 * le info di ogni città, lo passa al metodo che serve per calcolare le statistiche sulla visibilità.
	 * 
	 * @param cities rappresenta i nomi delle città su cui si vogliono fare statistiche. Le città ammesse sono
	 *        Ancona, Campobasso, Macerata, Roma, San Martino in Pensilis e Tolentino.
	 * @param period rappresenta il periodo su cui si vuole fare la statistica.
	 */
	public ArrayList<JSONArray> LeggiStoricoUmidita(ArrayList<String> cities, String period){
		
		Iterator<String> it1 = cities.iterator();
		Iterator<String> it2 = cities.iterator();
		ArrayList<JSONArray> visibilityInfo = new ArrayList<JSONArray>();
		ArrayList<JSONArray> info = new ArrayList<JSONArray>();
		
//		for(int i=0; i<cities.size(); i++) {
//			if(cities.get(i).isEmpty())
//				throw new EmptyStringException ("Hai dimenticato di inserire la città...");
//			else if( !(cities.get(i).equals("Ancona") || cities.get(i).equals("Roma")) )
//				throw new CityNotFoundException(cities.get(i) + " non è presente nello storico. Puoi scegliere tra: \"Ancona\" o \"Roma\". ");
//		}
		
		while(it1.hasNext()) {
			
			JSONArray array = new JSONArray();
			array = LeggiStorico(it1.next(),false);
			
			visibilityInfo.add(array);
			
		}
		
		int i=0;
		while(it2.hasNext()) {
			
			StatUmidità stats = new StatUmidità(); 
			JSONArray array = new JSONArray();
			
			if(period.equals("giornaliera"))
				array = stats.StatUmiditaGiornaliera(it2.next(),visibilityInfo.get(i));
			//else if(period.equals("settimanale"))
				//array = stats.oneWeekVisibilityStats(it2.next(),visibilityInfo.get(i));
			//else if(period.equals("mensile"))
				//array = stats.threeWeekVisibilityStats(it2.next(),visibilityInfo.get(i));
//			else throw new WrongPeriodException(period+" non è permessa. Devi inserire una stringa tra \"giornaliera\","
//					+ "\"settimanale\" e \"mensile\". ");
				
			info.add(array);
			i++;
		}
		
		return info;
	
	}

}
