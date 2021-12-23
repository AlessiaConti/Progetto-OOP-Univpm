package com.univpm.openweather.stats;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Vector;

import com.univpm.openweather.model.InformazioniMeteo;

/**
 *  Classe che esegue le statistiche sulla temperatura percepita
 * @author AlessiaConti
 *
 */

public class StatTempPercepita extends Statistiche {
	/**
	 * Metodo che trova il tipo di statistica desiderata sulla temperatura percepita
	 * 
	 * @param tipoStat     String
	 * @param vectPerStats Vector<MeteoCitta>
	 * @return mCVect
	 * 
	 */
	@Override
	public Vector<InformazioniMeteo> getStatistiche(String tipoStat, Vector<InformazioniMeteo> vectPerStats) {

		switch (tipoStat) {

		case "max":
			vect = new Vector<InformazioniMeteo>();

			max = vectPerStats.get(0);
			for (int i = 1; i < vectPerStats.size(); i++) {
				InformazioniMeteo mC = vectPerStats.get(i);
				if (mC.getTempPer() > max.getTempPer()) {
					max = mC;
				}
			}
			vect.add(max);
			return vect;

		case "min":
			vect = new Vector<InformazioniMeteo>();

			min = vectPerStats.get(0);
			for (int i = 1; i < vectPerStats.size(); i++) {
				InformazioniMeteo mC = vectPerStats.get(i);
				if (mC.getTempPer() < min.getTempPer()) {
					min = mC;
				}
			}
			vect.add(min);
			return vect;

		case "varianza":
			Vector<Double> vett = new Vector<Double>();

			Double somma = (double) 0, sommaScartiQuadrati = (double) 0, varianza, media;
			int n;
			for (int i = 0; i < vectPerStats.size(); i++) {
				vett.add(vectPerStats.elementAt(i).getTempPer());
			}
			n = vett.size();
			for (int i = 0; i < n; i++) {
				somma += vett.elementAt(i);
			}
			media = somma / n;
			for (int i = 0; i < n; i++) {
				sommaScartiQuadrati += Math.pow(vett.elementAt(i) - media, 2);
			}
			varianza = sommaScartiQuadrati / n;
			Vector<InformazioniMeteo> VMCVarianza = new Vector<InformazioniMeteo>();
			InformazioniMeteo mCVarianza = new InformazioniMeteo();
			DecimalFormat DF = new DecimalFormat("##.##", new DecimalFormatSymbols(Locale.ENGLISH));
			varianza = Double.valueOf(DF.format(varianza));
			mCVarianza.setTempPer(varianza);
//			mCVarianza.setCitta(vectPerStats.elementAt(0).getCitta());
//			mCVarianza.setData(System.currentTimeMillis()/1000);
			VMCVarianza.add(mCVarianza);
			return VMCVarianza;

		case "media":
			Vector<Double> vett1 = new Vector<Double>();

			Double somma1 = (double) 0, media1;
			int num;
			for (int i = 0; i < vectPerStats.size(); i++) {
				vett1.add(vectPerStats.elementAt(i).getTempPer());
			}
			num = vett1.size();
			for (int i = 0; i < num; i++) {
				somma1 += vett1.elementAt(i);
			}
			media1 = somma1 / num;
			Vector<InformazioniMeteo> VMCMedia = new Vector<InformazioniMeteo>();
			InformazioniMeteo mCMedia = new InformazioniMeteo();
			DecimalFormat dF = new DecimalFormat("####.##", new DecimalFormatSymbols(Locale.ENGLISH));
			media1 = Double.valueOf(dF.format(media1));
			mCMedia.setTempPer(media1);
//			mCMedia.setCitta(vectPerStats.elementAt(0).getCitta());
//			mCMedia.setData(System.currentTimeMillis()/1000);
			VMCMedia.add(mCMedia);
			return VMCMedia;

		}
		return null;
	}

}
