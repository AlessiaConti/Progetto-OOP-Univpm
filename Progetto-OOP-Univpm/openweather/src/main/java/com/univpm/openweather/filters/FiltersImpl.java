package com.univpm.openweather.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.univpm.openweather.model.Citta;
import com.univpm.openweather.stats.StatsImpl;

/**
 * Classe che esegue calcoli (min,max,media,varianza) su una statistica desiderata dall'utente tra temperatura percepita, effettiva e umidita'
 * 
 * @author F.Fabiocchi
 *
 */

@Service
public class FiltersImpl implements FiltersInterface {

	/**
	 * Metodo che esegue il filtraggio della statistica desiderata
	 * @param filtro String
	 * @param stat String
	 * @param int Indice
	 * @return vett_Citta_{stat}{filtro}
	 * @throws FileNotFoundException, IOException, ParseException, IndexOutOfBoundsException
	 */

	@Override
	public Vector<Citta> meteo_filtri(String filtro, String stat, int indice) throws FileNotFoundException, IOException, ParseException {

		StatsImpl stats = new StatsImpl();		

		try {
			switch(stat) {

			case"tempEff":
				switch(filtro) {

				case"min":

					Vector<Citta> vettCitta_tempEff1 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempEffMin = new Vector<Citta>();
					vettCitta_tempEffMin.add(vettCitta_tempEff1.get(0));

					for(int i = 1; i < vettCitta_tempEff1.size(); i++){

						if(vettCitta_tempEff1.get(i).getInfoMeteo().getTempEff() < vettCitta_tempEffMin.get(0).getInfoMeteo().getTempEff())
							vettCitta_tempEffMin.get(0).getInfoMeteo().setTempEff(vettCitta_tempEff1.get(i).getInfoMeteo().getTempEff());			
					}

					return vettCitta_tempEffMin;

				case"max":

					Vector<Citta> vettCitta_tempEff2 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempEffMax = new Vector<Citta>();
					vettCitta_tempEffMax.add(vettCitta_tempEff2.get(0));

					for(int i = 1; i < vettCitta_tempEff2.size(); i++){

						if(vettCitta_tempEff2.get(i).getInfoMeteo().getTempEff() > vettCitta_tempEffMax.get(0).getInfoMeteo().getTempEff())
							vettCitta_tempEffMax.get(0).getInfoMeteo().setTempEff(vettCitta_tempEff2.get(i).getInfoMeteo().getTempEff());			
					}

					return vettCitta_tempEffMax;

				case"media":

					Vector<Citta> vettCitta_tempEff3 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempEffMedia = new Vector<Citta>();
					vettCitta_tempEffMedia.add(vettCitta_tempEff3.get(0));
					double somma = 0;

					for(Citta c : vettCitta_tempEff3) 
						somma += c.getInfoMeteo().getTempEff();			

					double media = somma/indice;
					vettCitta_tempEffMedia.get(0).getInfoMeteo().setTempEff(media);

					return vettCitta_tempEffMedia;

				case"varianza":

					Vector<Citta> vettCitta_tempEff4 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempEffVarianza = new Vector<Citta>();
					vettCitta_tempEffVarianza.add(vettCitta_tempEff4.get(0));
					double somma1 = 0, sommaScartiQuadrati1 = 0, media1, varianza1;		

					for(Citta c : vettCitta_tempEff4) 
						somma1 += c.getInfoMeteo().getTempEff();				

					media1 = somma1/indice;

					for(Citta c : vettCitta_tempEff4)
						sommaScartiQuadrati1 += Math.pow(c.getInfoMeteo().getTempEff() - media1,2);

					varianza1 = sommaScartiQuadrati1 / indice;
					vettCitta_tempEffVarianza.get(0).getInfoMeteo().setTempEff(varianza1);

					return vettCitta_tempEffVarianza;

				default:
					System.out.println("Filtro sbagliato.");			
				}

			case"tempPerc":
				switch(filtro) {

				case"min":

					Vector<Citta> vettCitta_tempPerc1 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempPercMin = new Vector<Citta>();
					vettCitta_tempPercMin.add(vettCitta_tempPerc1.get(0));

					for(int i = 1; i < vettCitta_tempPerc1.size(); i++){

						if(vettCitta_tempPerc1.get(i).getInfoMeteo().getTempPer() < vettCitta_tempPercMin.get(0).getInfoMeteo().getTempPer())
							vettCitta_tempPercMin.get(0).getInfoMeteo().setTempPer(vettCitta_tempPerc1.get(i).getInfoMeteo().getTempPer());			
					}

					return vettCitta_tempPercMin;

				case"max":

					Vector<Citta> vettCitta_tempPer2 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempPerMax = new Vector<Citta>();
					vettCitta_tempPerMax.add(vettCitta_tempPer2.get(0));

					for(int i = 1; i < vettCitta_tempPer2.size(); i++){

						if(vettCitta_tempPer2.get(i).getInfoMeteo().getTempPer() > vettCitta_tempPerMax.get(0).getInfoMeteo().getTempPer())
							vettCitta_tempPerMax.get(0).getInfoMeteo().setTempPer(vettCitta_tempPer2.get(i).getInfoMeteo().getTempPer());			
					}

					return vettCitta_tempPerMax;

				case"media":

					Vector<Citta> vettCitta_tempPer3 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempPerMedia = new Vector<Citta>();
					vettCitta_tempPerMedia.add(vettCitta_tempPer3.get(0));
					double somma = 0;

					for(Citta c : vettCitta_tempPer3) 
						somma += c.getInfoMeteo().getTempPer();			

					double media = somma/indice;
					vettCitta_tempPerMedia.get(0).getInfoMeteo().setTempPer(media);

					return vettCitta_tempPerMedia;

				case"varianza":

					Vector<Citta> vettCitta_tempPer4 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_tempPerVarianza = new Vector<Citta>();
					vettCitta_tempPerVarianza.add(vettCitta_tempPer4.get(0));
					double somma1 = 0, sommaScartiQuadrati1 = 0, media1, varianza1;		

					for(Citta c : vettCitta_tempPer4) 
						somma1 += c.getInfoMeteo().getTempPer();				

					media1 = somma1/indice;

					for(Citta c : vettCitta_tempPer4)
						sommaScartiQuadrati1 += Math.pow(c.getInfoMeteo().getTempPer() - media1,2);

					varianza1 = sommaScartiQuadrati1 / indice;
					vettCitta_tempPerVarianza.get(0).getInfoMeteo().setTempPer(varianza1);

					return vettCitta_tempPerVarianza;

				default:
					System.out.println("Filtro sbagliato.");			
				}

			case"umidità":
				switch(filtro) {

				case"min":

					Vector<Citta> vettCitta_umidita1 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_umiditaMin = new Vector<Citta>();
					vettCitta_umiditaMin.add(vettCitta_umidita1.get(0));

					for(int i = 1; i < vettCitta_umidita1.size(); i++){

						if(vettCitta_umidita1.get(i).getInfoMeteo().getUmidita() < vettCitta_umiditaMin.get(0).getInfoMeteo().getUmidita())
							vettCitta_umiditaMin.get(0).getInfoMeteo().setUmidita(vettCitta_umidita1.get(i).getInfoMeteo().getUmidita());			
					}

					return vettCitta_umiditaMin;

				case"max":

					Vector<Citta> vettCitta_umidita2 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_umiditaMax = new Vector<Citta>();
					vettCitta_umiditaMax.add(vettCitta_umidita2.get(0));

					for(int i = 1; i < vettCitta_umidita2.size(); i++){

						if(vettCitta_umidita2.get(i).getInfoMeteo().getUmidita() > vettCitta_umiditaMax.get(0).getInfoMeteo().getUmidita())
							vettCitta_umiditaMax.get(0).getInfoMeteo().setUmidita(vettCitta_umidita2.get(i).getInfoMeteo().getUmidita());			
					}

					return vettCitta_umiditaMax;

				case"media":

					Vector<Citta> vettCitta_umidita3 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_umiditaMedia = new Vector<Citta>();
					vettCitta_umiditaMedia.add(vettCitta_umidita3.get(0));
					long somma = 0;

					for(Citta c : vettCitta_umidita3) 
						somma += c.getInfoMeteo().getUmidita();			

					long media = somma/indice;
					vettCitta_umiditaMedia.get(0).getInfoMeteo().setUmidita(media);

					return vettCitta_umiditaMedia;

				case"varianza":

					Vector<Citta> vettCitta_umidita4 = new Vector<Citta>(stats.getMeteoArray(indice));
					Vector<Citta> vettCitta_umiditaVarianza = new Vector<Citta>();
					vettCitta_umiditaVarianza.add(vettCitta_umidita4.get(0));
					long somma1 = 0, sommaScartiQuadrati1 = 0, media1, varianza1;		

					for(Citta c : vettCitta_umidita4) 
						somma1 += c.getInfoMeteo().getUmidita();				

					media1 = somma1/indice;

					for(Citta c : vettCitta_umidita4)
						sommaScartiQuadrati1 += Math.pow(c.getInfoMeteo().getUmidita() - media1,2);

					varianza1 = sommaScartiQuadrati1 / indice;
					vettCitta_umiditaVarianza.get(0).getInfoMeteo().setUmidita(varianza1);

					return vettCitta_umiditaVarianza;

				default:
					System.out.println("Filtro sbagliato.");			
				}	

			default:
				System.out.println("Statistica sbagliata.");

			}

		} catch (IOException | ParseException e) {
			System.out.println("ECCEZIONE LANCIATA");
			e.printStackTrace();
		}

		return null;	
	}	

}
