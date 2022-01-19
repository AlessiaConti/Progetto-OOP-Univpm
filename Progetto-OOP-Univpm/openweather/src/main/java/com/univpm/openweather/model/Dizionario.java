package com.univpm.openweather.model;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe che definisce il dizionario citta'-coordinate e ID-coordinate
 * @author A.Scalzi
 *
 */

public class Dizionario {
	
	//attributi
	private static final HashMap<String,String> elencoCittaNome = new HashMap<String,String>();
	private static final HashMap<String,String> elencoCittaId = new HashMap<String,String>();

	private static String[] eleCitta = {"Ancona", "Pescara", "Catania", "Milano", "Firenze"};
	private static String[] eleID = {"A271", "G482", "C351", "F205", "D612"};

	//static
	static {

		elencoCittaNome.put(eleCitta[0], "43.61675973 , 13.51887537");
		elencoCittaNome.put(eleCitta[1], "42.46458397 , 14.21364841");
		elencoCittaNome.put(eleCitta[2], "37.50287801 , 15.08704738");
		elencoCittaNome.put(eleCitta[3], "45.46679408 , 9.19034740");
		elencoCittaNome.put(eleCitta[4], "43.76923077 , 11.25588885");

		elencoCittaId.put(eleID[0], "43.61675973 , 13.51887537");
		elencoCittaId.put(eleID[1], "42.46458397 , 14.21364841");
		elencoCittaId.put(eleID[2], "37.50287801 , 15.08704738");
		elencoCittaId.put(eleID[3], "45.46679408 , 9.19034740");
		elencoCittaId.put(eleID[4], "43.76923077 , 11.25588885");

	}

	/**
	 * Metodo per stampare coordinate in base al nome della citta'
	 * @param citta Stringa che rappresenta nome della citta'
	 */
	public void getCoordinateNome(String citta) { 

		if (Arrays.asList(eleCitta).contains(citta)) {
			System.out.println(Dizionario.elencoCittaNome.get(citta));
		} else {
			System.out.println("Questa città non è disponibile");
		}	
	}

	/**
	 * Metodo per stampare coordinate in base all'ID della citta'
	 * @param iD Stringa che rappresenta id della citta'
	 */
	public void getCoordinateiD(String iD) {

		if (Arrays.asList(eleID).contains(iD)) {
			System.out.println(Dizionario.elencoCittaId.get(iD));
		} else {
			System.out.println("L'ID inserito non corrisponde a nessuna delle città disponibili");
		}	
	}


}