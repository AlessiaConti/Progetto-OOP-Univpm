package com.univpm.openweather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.univpm.openweather.model.*;


@Service
public class weatherServiceImpl implements weatherService {

	//classe che implementa l'interfaccia weatherService

	//apyKey=chiave necessaria per ottenere informazioni da OpenWeather
	private String apiKey= "be1788b24b6c02e4146b4b4cd3eb9058" ;
	private String url="http://api.openweathermap.org/data/2.5/weather?q=";

	//ora implemento i metodi che nell'interfaccia erano astratti

	//metodo per LEGGERE il file JSON ottenuto dalla chiamata API 
	@Override
	public JSONObject readJSON(String city) {
		JSONObject meteo=null;

		try { 
			//apro connessione url: costruisco l'URL da cui poi leggerò file in uscita
			URLConnection openConnection= new URL (url+city+ "&appid="+apiKey).openConnection();
			//metto il file generato da url su un input stream
			InputStream in= openConnection.getInputStream();

			String data= " ";
			String line= " ";

			try {
				InputStreamReader inR= new InputStreamReader(in);
				BufferedReader buf= new BufferedReader(inR);

				while((line= buf.readLine() ) != null) {
					data+=line;
				} 
			} finally {
				in.close();
			}

			//parse JSON Object
			meteo=(JSONObject) JSONValue.parseWithException(data); 
			//ho forzato a JSONObject il parseWithException del JSONValue

			//catch annidato delle eccezioni
		} catch(IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return meteo;
	}
	
	
}








