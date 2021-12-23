package com.univpm.openweather.stats;

import java.util.Vector;

import org.json.simple.JSONObject;

import com.univpm.openweather.model.Città;
import com.univpm.openweather.model.InformazioniMeteo;
import com.univpm.openweather.service.weatherServiceImpl;

/**
 * Classe da cui ereditano le sottoclassi utili per eseguire le varie
 * statistiche
 */

public class Statistiche {
	
	protected InformazioniMeteo max; //Oggetto MeteoCitta che contiene il massimo
	protected InformazioniMeteo min; //Oggetto MeteoCitta che contiene il minimo
	protected Vector<InformazioniMeteo> vect; //Vector di MeteoCitta che contiene gli oggetti desiderati dall'utente

	/**
	 * Metodo che trova il tipo di statistica desiderata, data in input dall'utente
	 * 
	 * @param tipoStat     String
	 * @param vectPerStats Vector<MeteoCitta>
	 * @return mCVect
	 * 
	 */
	public Vector<InformazioniMeteo> getStatistiche(String tipoStat, Vector<InformazioniMeteo> vectPerStat) {
		return vect;
	};


}
