package com.univpm.openweather.model;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe che definisce il dizionario citta'-coordinate e ID-coordinate
 * @author A.Scalzi
 *
 */

public class Dizionario {
	
	/** 
	 * Attributi che definiscono il dizionario
	 */
	private static final HashMap<String,String> elencoCittaNome = new HashMap<String,String>();
	private static final HashMap<String,String> elencoCittaId = new HashMap<String,String>();

	private static String[] eleCitta = {"Ancona", "Pescara", "Catania", "Milano", "Firenze"};
	private static String[] eleID = {"A271", "G482", "C351", "F205", "D612"};

	/** 
	 * Blocco static che riempie il dizionario associando ad ogni città e ID le rispettive coordinate
	 */
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
	 * Metodo che restituisce in output le coordinate relative al nome della città inserita
	 * @param citta indica il nome della città richiesta
	 */
	public void getCoordinateNome(String citta) { 

		if (Arrays.asList(eleCitta).contains(citta)) {
			System.out.println(Dizionario.elencoCittaNome.get(citta));
		} else {
			System.out.println("Questa città non è disponibile");
		}	
	}

	/** 
	 * Metodo che restituisce in output le coordinate relative all'ID della città inserita
	 * @param iD indica il codice identificativo della città richiesta
	 */
	public void getCoordinateiD(String iD) {

		if (Arrays.asList(eleID).contains(iD)) {
			System.out.println(Dizionario.elencoCittaId.get(iD));
		} else {
			System.out.println("L'ID inserito non corrisponde a nessuna delle città disponibili");
		}	
	}


}