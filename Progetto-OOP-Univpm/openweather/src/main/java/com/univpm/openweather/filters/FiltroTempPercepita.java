package com.univpm.openweather.filters;

import java.util.Vector;

import com.univpm.openweather.model.InformazioniMeteo;

/**
 * Classe che esegue il filtraggio sulla temperatura percepita
 */
public class FiltroTempPercepita {

	private Vector<InformazioniMeteo> vectFiltrato;//Vector contenente gli oggetti di tipo MeteoCitta filtarti per temp percepita

	/**
	 * Metodo che esegue il filtraggio per temperatura percepita su un Vector
	 * ricevuto in input
	 * 
	 * @param vectDaFiltr Vector<MeteoCitta>
	 * 
	 * @param TPInit Double
	 * 
	 * @param TPFin Double
	 * 
	 * @return Vector<MeteoCitta>
	 * 
	 */
	public Vector<InformazioniMeteo> getFromPTemperatureFilter(Vector<InformazioniMeteo> vectDaFiltr, Double TPInit, Double TPFin) {

		vectFiltrato = new Vector<InformazioniMeteo>();

		for (int i = 0; i < vectDaFiltr.size(); i++) {
			InformazioniMeteo mC = vectDaFiltr.get(i);
			if (mC.getTempPer() >= TPInit && mC.getTempPer() <= TPFin) {
				vectFiltrato.add(mC);
			}
		}
		return vectFiltrato;
	}

}
