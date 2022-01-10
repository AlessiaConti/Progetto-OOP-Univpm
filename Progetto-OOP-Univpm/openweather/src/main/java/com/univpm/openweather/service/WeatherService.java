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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.univpm.openweather.model.*;

/**
 * Classe che implementa l'interfaccia WeatherServiceInterface
 * @author A.Conti
 * 
 */

@Service
public class WeatherService implements WeatherServiceInterface {
	//apyKey=chiave necessaria per ottenere informazioni da OpenWeather
	private String apiKey= "be1788b24b6c02e4146b4b4cd3eb9058" ;
	//url di chiamata API tramite coordinate
	//api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
	private String url="http://api.openweathermap.org/data/2.5/weather?";

	//ora implemento i metodi che nell'interfaccia erano astratti

	/** 
	 * Questo metodo serve per leggere il file JSON ottenuto dalla chiamata API 
	 * passando le COORDINATE della città
	 * @param double lat
	 * @param double lon
	 * @return JSONObject contenente i dati meteo
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
	 * Questo metodo serve per leggere il file JSON ottenuto dalla chiamata API 
	 * passando il NOME della città
	 * @param String city (nome della città)
	 * @return JSONObject contenente i dati meteo
	 */
	@Override
	public JSONObject readJSONbyName(String city) {
		JSONObject meteo=null;

		try { 
			//stabilisco connessione url: costruisco l'URL da cui poi leggerò file in uscita
			URLConnection openConnection= new URL (url+"q="+city+ "&appid="+apiKey).openConnection();
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
	 * Questo metodo serve per ottenere i dati meteo richiesti
	 * @param JSONObject contenente i dati meteo
	 * @return oggetto di tipo Citta con dati meteo aggiornati
	 */
	@Override
	public Citta getMeteo(JSONObject obj) {
		Citta city=new Citta();
		InformazioniMeteo infoMeteo=new InformazioniMeteo();

		city.setNome((String) obj.get("name"));
		city.setid(String.valueOf(obj.get("id")));

		infoMeteo.setData(String.valueOf (obj.get("dt"))); //per prendere la data
		//ora converto la data dal formato Unix a UTC
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
		infoMeteo.setData(today);

		JSONObject mainData=(JSONObject)obj.get("main"); //per leggere oggetto JSON ''main''

		infoMeteo.setUmidita((long) mainData.get("humidity"));
		infoMeteo.setTempEff((double) mainData.get("temp"));
		infoMeteo.setTempPer((double) mainData.get("feels_like"));

		city.setInfoMeteo(infoMeteo);
		return city;
	}

	/**
	 * Questo metodo serve per costruire la struttura del JSON da restituire all'utente
	 * @param oggetto di tipo Citta 
	 * @return JSONObject contenente i dati meteo della città richiesta dall'utente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(Citta city) {
		//creo JSON Object con 2 parametri { }
		JSONObject output =new JSONObject();
		output.put("Città", city.getNome());
		output.put("id", city.getid());

		output.put( "Data", (city.getInfoMeteo()).getData() ); //per restituire la data

		//creo JSON Array [ ]
		JSONArray meteoList= new JSONArray(); //inizializzo JSONArray
		JSONObject ob=new JSONObject();
		InformazioniMeteo infoMeteo=new InformazioniMeteo();

		ob.put( "umidità", (city.getInfoMeteo()).getUmidita() );
		ob.put( "temp effettiva", (city.getInfoMeteo()).getTempEff() );
		ob.put( "temp percepita", (city.getInfoMeteo()).getTempPer() );

		meteoList.add(ob);

		output.put("Info meteo", meteoList);
		return output;
	}
	/******************************************************************************************/	

	/**
	 * Questo metodo richiama i metodi precedenti readJSON(), getMeteo() 
	 * e toJSON() e serve per salvare le info meteo richieste dall'utente 
	 * inserendo coordinate della città di interesse.
	 * Restituisce una stringa contenente il path del file salvato.
	 */
	public String saveToFile(String nome) {
		//path dove verrà creato il file
		String path = System.getProperty("user.dir") + "/[" +nome+ "]DatiMeteoAttuali.txt";

		File file = new File(path);

		//prendo i dati meteo usando i metodi precedenti
		JSONObject obj1 = new JSONObject();

		obj1 = readJSONbyName(nome);

		Citta city=getMeteo(obj1);

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