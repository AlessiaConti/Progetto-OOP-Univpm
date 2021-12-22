package com.univpm.openweather.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
	//url di chiamata API tramite coordinate
	//api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
	private String url="http://api.openweathermap.org/data/2.5/weather?";

	//ora implemento i metodi che nell'interfaccia erano astratti

	/** 
	 * readJSON() = metodo per LEGGERE il file JSON ottenuto dalla chiamata API 
	 */
	@Override
	public JSONObject readJSON(double lat, double lon) {
		JSONObject meteo=null;

		try { 
			//stabilisco connessione url: costruisco l'URL da cui poi leggerò file in uscita
			URLConnection openConnection= new URL (url+"lat="+lat+"&lon="+lon+ "&appid="+apiKey).openConnection();
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
			//effettuo il parsing in JSON Object
			meteo=(JSONObject) JSONValue.parseWithException(data); 

			//catch annidato delle eccezioni	
		} catch(IOException e) {	  
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return meteo;
	}


	/**
	 * getMeteo()=metodo per OTTENERE i dati meteo che mi interessano
	 */
	@Override
	public Città getMeteo(JSONObject obj) {
		Città city=new Città();
		InformazioniMeteo infoMeteo=new InformazioniMeteo();

		city.setNome((String) obj.get("name"));
		city.setid(String.valueOf(obj.get("id")));

		JSONObject mainData=(JSONObject)obj.get("main"); //per leggere oggetto JSON ''main''

		infoMeteo.setUmidità((long) mainData.get("humidity"));
		infoMeteo.setTempEff((double) mainData.get("temp"));
		infoMeteo.setTempPer((double) mainData.get("feels_like"));

		city.setInfoMeteo(infoMeteo);
		return city;
	}

	/**
	 * toJSON()= metodo per costruire la struttura del JSON da restituire all'utente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(Città city) {
		//creo JSON Object con 2 parametri { }
		JSONObject output =new JSONObject();
		output.put("Città", city.getNome());
		output.put("id", city.getid());

		//creo JSON Array [ ]
		JSONArray meteoList= new JSONArray(); //inizializzo JSONArray
		JSONObject ob=new JSONObject();
		InformazioniMeteo infoMeteo=new InformazioniMeteo();

		ob.put( "umidità", (city.getInfoMeteo()).getUmidità() );
		ob.put( "temp effettiva", (city.getInfoMeteo()).getTempEff() );
		ob.put( "temp percepita", (city.getInfoMeteo()).getTempPer() );

		meteoList.add(ob);

		output.put("Info meteo", meteoList);
		return output;
	}

	/**
	 * Questo metodo richiama i metodi precedenti readJSON(), getMeteo() 
	 * e toJSON() e serve per salvare le info meteo richieste dall'utente 
	 * inserendo coordinate della città di interesse.
	 * Restituisce una stringa contenente il path del file salvato.
	 */
	public String saveToFile(double lat, double lon) {
		//path dove verrà creato il file
		String path = System.getProperty("user.dir") + "/[" +lat+"; "+lon+ "]DatiMeteoAttuali.txt";
		//TODO nel nome del File al posto delle coordinate andrebbe messo il nome della città

		File file = new File(path);

		//prendo i dati meteo usando i metodi precedenti
		JSONObject obj1 = new JSONObject();

		obj1 = readJSON(lat,lon);

		Città city=getMeteo(obj1);

		JSONObject obj2 = toJSON(city);

		try{
			if(!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file, true);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(obj2.toString());
			bufferedWriter.write("\n");

			bufferedWriter.close();

		} catch(IOException e) {
			System.out.println(e);
		}

		return "Il file è stato salvato in " + path;	
	}

}