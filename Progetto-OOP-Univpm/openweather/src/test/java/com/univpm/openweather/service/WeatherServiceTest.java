package com.univpm.openweather.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.univpm.openweather.exception.EccezioneCoordErrate;
import com.univpm.openweather.model.Citta;
import com.univpm.openweather.model.Coordinate;
import com.univpm.openweather.model.InformazioniMeteo;

/**
 * Classe che testa il metodo toJSON di WeatherService
 * e l'eccezione personalizzata EccezioneCoordErrate
 * @author A.Conti
 *
 */
class WeatherServiceTest {

	/**
	 * Costruisco gli oggetti static che serviranno per testare i metodi
	 */
	private static WeatherService ws;
	private static JSONObject jo;
	private static Coordinate coord; 
	private static Citta city; 
	private static InformazioniMeteo meteo;


	/**
	 * Inizializzo gli oggetti
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		ws=new WeatherService();
		jo=new JSONObject();
		coord= new Coordinate(43.55, 13.1667); //lat e lon di Ancona
		city= new Citta(coord);
		meteo= new InformazioniMeteo();

		jo=ws.readJSON(43.55, 13.1667); //non testato
		city= ws.getMeteo(jo); //non testato
		jo= ws.toJSON(city); //unico metodo testato

	}

	/**
	 * Metodo che testa toJSON() , cioè verifica se l'oggetto Citta (appositamente creato) 
	 * viene convertito correttamente in JSONObject.
	 */
	@SuppressWarnings("unchecked")
	@Test
	void toJSONtest() throws IOException {

		//costruisco città che mi servirà per testare metodo
		
		city.setNome("Ancona");
		city.setid("804");

		meteo.setUmidita(65);
		meteo.setTempEff(277.25);
		meteo.setTempPer(276.02);
		meteo.setData("12/01/2022");

		city.setInfoMeteo(meteo);

		//costruisco JSON che dovrei avere (expected)

		//creo JSON Object { }
		JSONObject output =new JSONObject();
		output.put("Città", city.getNome());
		output.put("id", city.getid());
		output.put( "Data", (city.getInfoMeteo()).getData() );

		//creo JSON Array [ ]
		JSONArray meteoList= new JSONArray(); //inizializzo JSONArray
		JSONObject ob=new JSONObject();

		ob.put( "umidità", (city.getInfoMeteo()).getUmidita() );
		ob.put( "temp effettiva", (city.getInfoMeteo()).getTempEff() );
		ob.put( "temp percepita", (city.getInfoMeteo()).getTempPer() );

		meteoList.add(ob);
		output.put("Info meteo", meteoList);

		// assertEquals ( expected, actual )
		assertEquals(output.toString(), ws.toJSON(city).toString());
	}
	
	/**
	 * Metodo che testa l'eccezione personalizzata EccezioneCoordErrate
	 */
	@Test
	void EccCoordErrateTest() {
		// assertThrows(expected type, executable)
		assertThrows (EccezioneCoordErrate.class, ()-> ws.readJSON(181, -181));
	}

}
