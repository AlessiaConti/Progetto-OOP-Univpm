package com.univpm.openweather.exception;
/**
 * Questa classe contiene il metodo che segnala l'eccezione riguardante 
 * stringa vuota. 
 */
public class EmptyStringException extends Exception{
String mex;
	
	//costruttore
	public EmptyStringException(String mex) {
		this.mex=mex;
	}
	
	//restituisce mex errore
	public String getMex() {
		return mex;
	}

}
