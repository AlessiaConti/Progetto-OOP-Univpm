package com.univpm.openweather.exception;

import java.util.Scanner;


/**
 * Eccezione per gestire un numero non incluso tra 1 e 30 nell'indice di filtraggio
 * 
 * @author F.Fabiocchi
 */

public class EccezionePersonalizzata extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EccezionePersonalizzata() {
		super();
	}
	
	/** 
	 * Costruttore che chiede all'utente di inserire un numero tra 1 e 30 per l'indice di filtraggio in caso di errore
	 * 
	 * @param errore String
	 */
	
	public EccezionePersonalizzata(String errore) {};
	
	public int getIndice() {
		Scanner input = new Scanner(System.in);
		int indice = input.nextInt();
		return indice;
	}

}
