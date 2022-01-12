package com.univpm.openweather.exception;

/**
 * Questa classe contiene il metodo che segnala l'eccezione dovuta al non inserimento di coordinate. 
 * @author A.Conti
 *
 */

public class EccezioneNoCoord extends Exception {

	private static final long serialVersionUID = 1L;

	String mex;
	
	/**
	 * Questo è il costruttore.
	 * @param mex rappresenta il messaggio di errore.
	 */
	public EccezioneNoCoord(String mex) {
		this.mex=mex;
	}
	
	/**
	 * Restituisce un messaggio di errore passato al costruttore quando viene inserita una stringa vuota.
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}


}
