package com.univpm.openweather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.univpm.openweather.exception.EccezioneCoordErrate;
import com.univpm.openweather.exception.EccezionePersonalizzata;
import com.univpm.openweather.model.*;

/**
 * Classe che implementa l'interfaccia WeatherServiceInterface
 * @author A.Conti
 * 
 */

@Service
public class WeatherService implements WeatherServiceInterface {
	/**
	 * Definisco gli attributi necessari ai metodi implementati in seguito: 
	 * l'apyKey, cioè la chiave necessaria per ottenere informazioni da OpenWeather,
	 * e l'url di chiamata API con coordinate della città:
	 * api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
	 */
	private String apiKey= "be1788b24b6c02e4146b4b4cd3eb9058" ;
	private String url="http://api.openweathermap.org/data/2.5/weather?";


	/** 
	 * Questo metodo serve per leggere il file JSON ottenuto dalla chiamata API 
	 * passando come parametri le coordinate della città
	 * @param double lat
	 * @param double lon
	 * @return JSONObject contenente i dati meteo
	 * @throws EccezioneCoordErrate se le coordinate inserite sono <-180 o >180
	 */
	@Override
	public JSONObject readJSON(double lat, double lon) throws IOException, EccezioneCoordErrate {

		Dizionario diz=new Dizionario();

		do {
			try {
				if((lat<-180 || lat>180) && (lon<-180 || lon>180)) throw new EccezioneCoordErrate("Le coordinate inserite sono errate!");
				else if (lat<-180 || lat>180) throw new EccezioneCoordErrate("La latitudine inserita è errata!");
				else if (lon<-180 || lon>180) throw new EccezioneCoordErrate("La longitudine inserita è errata!");	

			} catch(EccezioneCoordErrate e) { 
				e.menuDizionario(); /** permette utilizzo del dizionario*/
			} 
		} while ((lat<-180 || lat>180) || (lon<-180 || lon>180));


		JSONObject meteo=null;

		try { 
			/**stabilisco connessione url: costruisco l'URL da cui poi leggerò file in uscita*/
			URLConnection openConnection= new URL (url+"lat="+lat+"&lon="+lon+ "&appid="+apiKey).openConnection();
			/**metto il file generato da url su un input stream*/
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
			/**effettuo il parsing in JSON Object*/
			meteo=(JSONObject) JSONValue.parseWithException(data); 

			/**catch annidato delle eccezioni*/	
		} catch(IOException e) {	  
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return meteo;

	}


	/** 
	 * Questo metodo serve per leggere il file JSON ottenuto dalla chiamata API 
	 * passando come parametro il nome della città (utilizzato per la rotta /getWeatherbyName)
	 * @param String city (nome della città)
	 * @return JSONObject contenente i dati meteo
	 */
	@Override
	public JSONObject readJSONbyName(String city) {
		JSONObject meteo=null;

		try { 
			URLConnection openConnection= new URL (url+"q="+city+ "&appid="+apiKey).openConnection();
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

			meteo=(JSONObject) JSONValue.parseWithException(data); 

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

		infoMeteo.setData(String.valueOf (obj.get("dt"))); /**per prendere la data*/
		/**ora converto la data dal formato Unix a UTC*/
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
		infoMeteo.setData(today);

		JSONObject mainData=(JSONObject)obj.get("main"); /**per leggere oggetto JSON ''main''*/

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

		/** creo JSON Object { } */
		JSONObject output =new JSONObject();
		output.put("Città", city.getNome());
		output.put("id", city.getid());

		output.put( "Data", (city.getInfoMeteo()).getData() ); /**per restituire la data*/

		/** creo JSON Array [ ] */
		JSONArray meteoList= new JSONArray(); /**inizializzo JSONArray*/
		JSONObject ob=new JSONObject();

		ob.put( "umidità", (city.getInfoMeteo()).getUmidita() );
		ob.put( "temp effettiva", (city.getInfoMeteo()).getTempEff() );
		ob.put( "temp percepita", (city.getInfoMeteo()).getTempPer() );

		meteoList.add(ob);

		output.put("Info meteo", meteoList);
		return output;
	}


}
