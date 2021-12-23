package com.univpm.openweather.stats;

import org.json.simple.JSONObject;

import com.univpm.openweather.model.Città;
import com.univpm.openweather.service.weatherServiceImpl;

public class Statistiche {
	weatherServiceImpl service = new weatherServiceImpl();

	/**
	 * Questo metodo serve per calcolare le medie giornaliere.
	 * @param name è il nome della città su cui si vogliono fare statistiche.
	 * @return JSONObject contenente il nome della città e le relative medie
	 */

	public JSONObject medieGiornaliere(String name) {

		Città city = new Città(name);
		JSONObject obj=new JSONObject();
		city = service.getMeteo(obj);

		//double temp_max_ave = 0;
		//double temp_min_ave = 0;
		double tPercepita_media = 0;
		//double visibility_ave = 0;
		//double variance = 0;

		//int i=0;

		String date = "";
		date += (city.getInfoMeteo().getData()).charAt(8);
		date += (city.getInfoMeteo().getData()).charAt(9);

		String effectiveDate = date;

		//double max_visibility = 0;
		//double min_visibility = city.getInfoMeteo().get(i).getVisibility();

		while(date.equals(effectiveDate)) {
			//temp_max_ave += city.getInfoMeteo().getTemp_max();
			//temp_min_ave += city.getVector().get(i).getTemp_min();
			tPercepita_media += city.getInfoMeteo().getTempPer();
			//visibility_ave += city.getVector().get(i).getVisibility();
//			if(city.getVector().get(i).getVisibility()>max_visibility)
//				max_visibility = city.getVector().get(i).getVisibility();
//			if (city.getVector().get(i).getVisibility()<min_visibility)
//				min_visibility = city.getVector().get(i).getVisibility();
//			i++;
			effectiveDate = "";
			effectiveDate += (city.getInfoMeteo().getData()).charAt(8);
			effectiveDate += (city.getInfoMeteo().getData()).charAt(9);
		}


//		temp_max_ave = temp_max_ave/i;
//		temp_min_ave = temp_min_ave/i;
		tPercepita_media = tPercepita_media/i;
//		visibility_ave = visibility_ave/i;

		effectiveDate = date;
		i=0;

		//calcolo della varianza di visibilità
		while(date.equals(effectiveDate)) {
			variance += ((int)((city.getVector().get(i).getVisibility())-visibility_ave))^2;
			i++;
			effectiveDate = "";
			effectiveDate += (city.getVector().get(i).getData()).charAt(8);
			effectiveDate += (city.getVector().get(i).getData()).charAt(9);

		}


		variance /= i;

		JSONObject object = new JSONObject();
		JSONObject visibility_data = new JSONObject();

		visibility_data.put("Visibility average",visibility_ave);
		visibility_data.put("Max visibility",max_visibility);
		visibility_data.put("Min Visibility", min_visibility);
		visibility_data.put("Visibility variance", variance);

		object.put("CityName", name);
		object.put("Temp_Max Average", temp_max_ave);
		object.put("Temp_Min Average", temp_min_ave);
		object.put("Feels_like Average", feels_like_ave);
		object.put("Visibility Data", visibility_data);

		return object;

	}

}
