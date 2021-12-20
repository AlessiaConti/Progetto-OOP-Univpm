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

			meteo=(JSONObject) JSONValue.parseWithException(data); //parse JSON Object
		
		//catch annidato delle eccezioni	
		} catch(IOException e) {	  
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return meteo;
	}


	//metodo per ottenere i dati meteo che mi interessano
	@Override
	public Città getMeteo(JSONObject obj) {
		Città city=new Città();
		Vector <InformazioniMeteo> infoMeteo=new Vector<InformazioniMeteo>();

		city.setNome((String) obj.get("name"));
		city.setid(String.valueOf(obj.get("id")));
		
		JSONObject mainData=(JSONObject)obj.get("main"); //per leggere oggetto JSON ''main''
		//infoMeteo.setTempEff((double) mainData.get("temp"));
		//infoMeteo.setTempPer((double) mainData.get("feels_like"));
		//infoMeteo.setUmidità((double) mainData.get("humidity"));
		
		city.setInfoMeteo(infoMeteo);
		return city;
	}

	//metodo per costruire la struttura del JSON da restituire

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(Città city) {

		//creo JSON Object con 3 parametri { }
		JSONObject output =new JSONObject();
		output.put("città", city.getNome());
		output.put("id", city.getid());
		output.put("paese", city.getPaese());

		//creo JSON Array [ ]
		JSONArray MeteoList= new JSONArray(); //inizializzo JSONArray

		for (InformazioniMeteo singleForecast : city.getInfoMeteo()) { //scorro JSONArray
			JSONObject meteo=new JSONObject();

			meteo.put("umidità", singleForecast.getUmidità());
			meteo.put("temp effettiva", singleForecast.getTempEff());
			meteo.put("temp percepita", singleForecast.getTempPer());

			MeteoList.add(meteo);
		}
		output.put("current weather", MeteoList);
		return output;
	}

}








