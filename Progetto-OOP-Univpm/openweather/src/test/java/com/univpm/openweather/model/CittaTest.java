package com.univpm.openweather.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CittaTest {
	
	private static Citta city;
	private static Coordinate coord; 
	private static InformazioniMeteo meteo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		city=new Citta();
		coord=new Coordinate(43.55, 13.1667); //lat e lon di Ancona
		meteo= new InformazioniMeteo();
		
		meteo.setUmidita(60);
		
		city.setCoordinate(coord);
		city.setNome("Ancona");
		city.setInfoMeteo(meteo);

	}

	@Test
	void test() {
		// assertEquals ( expected, actual )
		assertEquals ("Ancona", city.getNome()); 
		assertEquals (coord, city.getCoordinate()); 
		assertEquals (meteo, city.getInfoMeteo());
	}

}
