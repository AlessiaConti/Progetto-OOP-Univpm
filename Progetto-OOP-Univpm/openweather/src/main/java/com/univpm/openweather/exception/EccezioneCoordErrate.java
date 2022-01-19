package com.univpm.openweather.exception;

import java.util.Scanner;

import com.univpm.openweather.model.Dizionario;

/**
 * Eccezione per gestire possibili errori dovuti a inserimento di coordinate errate
 * @author A.Conti
 * 
 */

public class EccezioneCoordErrate extends Exception {

	private static final long serialVersionUID = 1L;
	String mex;

	/**
	 * Costruttore che richiama superclasse e riceve in ingresso un messaggio di errore personalizzato
	 * 
	 * @param mex rappresenta il messaggio di errore.
	 */
	public EccezioneCoordErrate(String mex) {
		super();
		this.mex = mex;
	}

	/**
	 * Metodo che restituisce un messaggio di errore passato dal costruttore quando le coord inserite sono sbagliate
	 * 
	 * @return String che contiene il messaggio d'errore che viene stampato.
	 */
	public String getMex() {
		return mex;
	}

	/** Metodi per utilizzare il dizionario */
	Dizionario diz=new Dizionario();

	/** 
	 * Metodo che chiede all'utente di cercare coord inserendo il nome
	 * 
	 */
	public void CoordNome() {
		Scanner input = new Scanner(System.in);
		System.out.println("Le città presenti nel dizionario sono: Ancona, Pescara, Catania, Milano, Firenze");
		System.out.println("Inserisci nome della città: ");
		String cityName = input.nextLine();
		diz.getCoordinateNome(cityName);
	}

	/** 
	 * Metodo che chiede all'utente di cercare coord inserendo id
	 * 
	 */
	public void Coordid() {
		Scanner input = new Scanner(System.in);
		System.out.println("Gli id delle città disponibili sono: A271, G482, C351, F205, D612");
		System.out.println("Inserisci id della città: ");
		String id = input.nextLine();
		diz.getCoordinateiD(id) ;

	}

	/** 
	 * Metodo che dà all'utente la possibilità di scegliere se cercare coordinate
	 * desiderate tramite nome o id della città
	 * 
	 */
	public void menuDizionario() {
		Scanner input = new Scanner(System.in);
		System.out.println();
		System.out.println("---- DIZIONARIO ----"); 
		System.out.println("Cerca le coordinate corrette della città tramite nome o id"); 
		System.out.println(" Cosa vuoi inserire? ");
		System.out.println(" 1. Nome della città ");
		System.out.println(" 2. Id della città ");
		int x = input.nextInt();
		switch(x) {
		case 1: CoordNome(); break;
		case 2: Coordid(); break;
		default: System.out.println("errore");
		}
	}


}
