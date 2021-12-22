package com.univpm.openweather.exception;
/**
 * Questa classe contiene il metodo che segnala l'eccezione riguardante 
 * l'errato inserimento del nome di una città. 
 */
public class CityNotFoundException extends Exception {
	String mex;

	//costruttore
	public CityNotFoundException(String mex) {
		super();
		this.mex = mex;
	}

	//restituisce il mex di errore
	public String getMex() {
		return mex;
	}

}
