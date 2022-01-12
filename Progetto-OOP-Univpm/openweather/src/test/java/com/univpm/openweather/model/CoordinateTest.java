package com.univpm.openweather.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CoordinateTest {

	private static Coordinate coord; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		coord=new Coordinate(43.55, 13.1667); //lat e lon di Ancona
	}

	@Test
	void test() {
		// assertEquals ( expected, actual )
		assertEquals (43.55, coord.getLat()); 
		assertEquals (13.1667, coord.getLon()); 
		assertEquals ("[Latitudine:43.55, Longitudine:13.1667]", coord.toString());
	}

	
}
