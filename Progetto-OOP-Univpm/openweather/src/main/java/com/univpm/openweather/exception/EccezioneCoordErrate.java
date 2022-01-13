package com.univpm.openweather.exception;


/**
 * Eccezione per gestire possibili errori dovuti a inserimento di coordinate errate
 * 
 */

public class EccezioneCoordErrate extends Exception {

	private static final long serialVersionUID = 1L;
	String mex;

	/**
	 * Costruttore che richiama superclasse e riceve in ingresso un messaggio di errore personalizzato
	 * ( "Hai inserito coordinate errate!" )
	 * 
	 * @param mex rappresenta il messaggio di errore.
	 */
	public EccezioneCoordErrate(String mex) {
		super();
		this.mex = mex;
	}

	/**
	 * Restituisce un messaggio di errore passato dal costruttore quando le coord inserite sono sbagliate
	 * 
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}


}
