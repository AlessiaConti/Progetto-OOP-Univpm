package com.univpm.openweather.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.univpm.openweather.exception.CityNotFoundException;
import com.univpm.openweather.exception.EmptyStringException;
import com.univpm.openweather.exception.WrongPeriodException;
import com.univpm.openweather.model.*;
import com.univpm.openweather.stats.StatUmidità;

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
	 * readJSON() = metodo per LEGGERE il file JSON ottenuto 
	 *                    dalla chiamata API passando le COORDINATE
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
	 * readJSON() = metodo per LEGGERE il file JSON ottenuto 
	 *                    dalla chiamata API passando il NOME della città
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
	 * getMeteo()=metodo per OTTENERE i dati meteo che mi interessano
	 */
	@Override
	public Città getMeteo(JSONObject obj) {
		Città city=new Città();
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
	 * toJSON()= metodo per costruire la struttura del JSON da restituire all'utente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(Città city) {
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

/**************************************************************************************/
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