package com.univpm.openweather.exception;

import java.util.Scanner;

import com.univpm.openweather.model.Dizionario;

/**
 * Eccezione per gestire possibili errori dovuti a inserimento di coordinate errate
 * 
 * @author A.Conti
 */

public class EccezioneCoordErrate extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore che richiama superclasse e stampa messaggio di errore
	 */
	public EccezioneCoordErrate() {
		super();
		System.out.println("Hai inserito coordinate errate!");
	}

	/** 
	 * Costruttore che riceve in ingresso un messaggio di errore personalizzato
	 * 
	 * @param mex String
	 */
	public EccezioneCoordErrate (String mex) {
		super(mex);
		System.out.println("Hai inserito coordinate errate!"+mex);
	};

	/** 
	 * Metodo che restituisce il dizionario
	 * 
	 */
//	public void getDizionario(String nomeCitta, String id) {
//		Dizionario diz = new Dizionario();
//		diz.getCoordinateNome(nomeCitta);
//		diz.getCoordinateiD(id);
//	}

}
