package com.univpm.openweather.model;

public class Città {

	//classe che definisce la citta' dalla quale si ottengono le previsioni

	//attributi
	private String id;
	private String nome;
	private String paese;

	private Coordinate coordinate; //attributo di tipo 'coordinate' (lon e lat della città)
	private InformazioniMeteo infoMeteo; //attributo che contiene info meteo della città*/
	
	//costruttori utili
	public Città() {
		super();
	}
	
	public Città(String nome) {
		this.id=null;
		this.nome=nome;
		this.coordinate=null;
		//this.weatherArray = null;
	}

	//getter
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public String getid() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getPaese() {
		return paese;
	}
	public InformazioniMeteo getInfoMeteo() {
		return infoMeteo;
	}


	//setter
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	public void setid(String string) {
		this.id = string;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPaese(String paese) {
		this.paese = paese;
	}
	public void setInfoMeteo(InformazioniMeteo infoMeteo) {
		this.infoMeteo = infoMeteo;
	}

	// TODO Override del metodo toString 


}