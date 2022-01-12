package com.univpm.openweather.exception;

/**
 * 
 * Eccezione nel caso di errata richiesta all'API di OpenWeather
 * @author a
 */

public class EccezioneBadRequest extends Exception {
	/**
	 * Costruttore per BarRequestException
	 */
	public EccezioneBadRequest() {
		super("La richiesta all'API di OpenWheather non è andata a buon fine");
	}
	

}
