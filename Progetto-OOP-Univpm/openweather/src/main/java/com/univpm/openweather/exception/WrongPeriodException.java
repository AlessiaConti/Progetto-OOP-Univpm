package com.univpm.openweather.exception;
/**
 * Questa classe contiene il metodo che segnala l'eccezione riguardante 
 * stringa sbagliata del periodo
 */
public class WrongPeriodException extends Exception{
	String mex;
	 
	//costruttore
	public WrongPeriodException(String mex) {
		this.mex = mex;
	}
	
	//restituisce mex errore
	public String getMex() {
		return mex;
	}

}
