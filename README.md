# Progetto-OOP-Univpm
# :cloud: Openweather :cloud:

*Applicazione Java tramite la quale l'utente, grazie all'inserimento di una città, riesce ad ottenere le informazioni relative a umidità, temperatura effettiva, temperatura percepita, e le statistiche riguardanti valori minimi, massimi, media e varianza, con filtraggio in base alla periodicità scelta.*


## Contenuti
* [Introduzione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#introduzione)
* [Configurazione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#configurazione)
* [Rotte](https://github.com/FabiocchiF/Progetto-OOP-Univpm#rotte)
* [Esempi di utilizzo](https://github.com/FabiocchiF/Progetto-OOP-Univpm#esempi-di-utilizzo)
  * [**/getWeather**](https://github.com/FabiocchiF/Progetto-OOP-Univpm#1-getweather)
  * [**/getWeatherbyName**](https://github.com/FabiocchiF/Progetto-OOP-Univpm#2-getweatherbyname)
  * [**/getFilters**](https://github.com/FabiocchiF/Progetto-OOP-Univpm#4-getfilters)
  * [Dizionario](https://github.com/FabiocchiF/Progetto-OOP-Univpm/blob/main/README.md#dizionario)
  * [Eccezioni](https://github.com/FabiocchiF/Progetto-OOP-Univpm#eccezioni)
* [Test](https://github.com/FabiocchiF/Progetto-OOP-Univpm#test)
* [Documentazione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#documentazione)
* [Struttura del progetto](https://github.com/FabiocchiF/Progetto-OOP-Univpm#struttura-del-progetto)
* [Software utilizzati](https://github.com/FabiocchiF/Progetto-OOP-Univpm#software-utilizzati)
* [Autori](https://github.com/FabiocchiF/Progetto-OOP-Univpm#autori)


## Introduzione
L'applicazione utilizza l'API di OpenWeather riguardante il meteo corrente, disponibile al seguente link: 
```
https://openweathermap.org/current#geo
```
Tramite questa API il programma riceve, salva ed elabora i dati meteo riguardanti la città cercata dall'utente.
L'applicazione permette quindi di:
- [x] Ottenere informazioni meteo (umidità, temp effettiva e percepita) della città inserita dall'utente
- [x] Salvare dati in locale
- [ ] Gestire possibili errori dovuti a inserimento di coordinate errate
- [ ] Facilitare inserimento delle coordinate con possibilità di ricercarle tramite un dizionario statico
- [x] Ottenere statistiche riguardanti valori min max media e varianza di umidità, temp effettiva e percepita
- [x] Scegliere filtraggio delle statistiche in base alla periodicità: range personalizzabile



## Configurazione
Questa applicazione è installabile dal Prompt dei Comandi digitando:
```
git clone https://github.com/FabiocchiF/Progetto-OOP-Univpm.git
```
oppure installando [*GitHub Desktop*](https://desktop.github.com/) e clonando la repository in locale.


## Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo:
```
http://localhost:8080
```
Le rotte disponibili sono le seguenti:
N° | Rotta | Descrizione
---- | ---- | ----
1 | ```/getWeather``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temperatura effettiva e percepita, con salvataggio automatico dei dati in locale
2 | ```/getWeatherbyName``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temp effettiva e percepita (*Rotta aggiuntiva*)
3 | ```/getFilters``` | restituisce un Vettore Città contenente la statistica desiderata filtrata 


## Esempi di utilizzo


### 1. /getWeather
Con questa rotta devono essere inserite le coordinate della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata (nome e id) e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.

![Immagine1](https://user-images.githubusercontent.com/91316014/148811173-b6f33828-9afa-4cb6-9eb2-d06af2987abc.png)

Inoltre, dopo aver effettuato la richiesta, sarà effettuato automaticamente un salvataggio in locale dei dati in un file di testo (che viene salvato nel path indicato in rosso)

![im](https://user-images.githubusercontent.com/91316014/149120049-0da903d5-7984-45fc-84b3-dbb3045a2f5f.png)


***

### 2. /getWeatherbyName
Con questa rotta invece va inserito il nome della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.

![Immagine2](https://user-images.githubusercontent.com/91316014/148811253-940f52f6-37d3-4d9f-93ad-18d82a3462fa.png)

***

### 3. /getFilters
Questa rotta permette il filtraggio di una statistica a scelta tra temperatura effettiva, temperatura percepita e umidità a scelta dell'utente. I filtri disponibili sono massimo, minimo, media e varianza. Il metodo implementatato dalla rotta prevede un'eccezione personalizzata lanciata in caso di frequenza di filtraggio non compresa nell'intervallo (1,30). 
Per questioni di tempo il filtraggio viene eseguito sul file locale "AnconaStats.json".

**UMIDITA' MINIMA**

![EsempioStatsUmiditàMin](https://user-images.githubusercontent.com/91212564/148920743-8f38e591-dcea-4951-820a-adf113b92aa9.png)

**UMIDITA' MASSIMA**

 ![EsempioStatsUmiditàMax](https://user-images.githubusercontent.com/91212564/148920755-e862fd48-0782-4be2-8196-0dc54f43d31b.png) 

***

### Dizionario

In caso siano state inserite coordinate errate da parte dell'utente, quest ultimo ha la possibilità di consultare il dizionario statico tramite il quale potrà ottenere in output 
le coordinate corrette inserendo il nome o l'identificativo della città richiesta.
Il dizionario contiene il nome, l'identifiativo e le coordinate di cinque città italiane: Ancona, Pescara, Catania, Milano e Firenze.
La classe **dizionario** comprende due metodi:

*getCoordinateNome(citta)* restituisce in output le coordinate associate al nome della città inserita.

![NomeCoord4](https://user-images.githubusercontent.com/95362575/149492787-a59387bc-d809-4083-b7bb-c927d4879bb5.png)

*getCoordinateiD(iD)* restituisce in output le coordinate associate all'ID della città inserita.

![IdCoord4](https://user-images.githubusercontent.com/95362575/149492567-1acc0e8e-f99a-42c7-8891-d470cbc397cb.png)

***

### Eccezioni

**Eccezione Personalizzata** prevede, tramite il metodo *getIndice()*, l'inserimento di un nuovo indice in caso quello immesso nella rotta */getFilters* 
non sia compreso tra 1 e 30. L'inserimento avviene nella console di comandi del compilatore.

![55ea4486d7657c99e4baa9eb216ea2e7](https://user-images.githubusercontent.com/91212564/148922591-525ef1f3-13bf-4b05-b880-ba93062e8185.png)


**Eccezione CoordErrate** (da finire)


## Test

Per verificare il corretto funzionamento di alcuni metodi e alcune eccezioni, sono stati implementati i seguenti test:
- [x] **Test model ->** Implementate classi per testare gli oggetti *Citta* e *Coordinate*
- [x] **Test service ->** WeatherServiceTest: classe per testare in particolare il metodo *toJSON*

![image](https://user-images.githubusercontent.com/91316014/149234191-a4b73aa8-f818-47cc-a3a0-ab2efeb290ca.png)



## Documentazione
Tutto il progetto è documentato in [Javadoc]().


## Struttura del progetto

* MAIN

  * **IO**
    * SalvaDati
  * **controller**
    * StatController
    * WeatherController
  * **exception**
    * EccezionePersonalizzata
    * EccezioneCoordErrate
  * **filters**
    * FiltersImpl
    * FiltersInterface
  * **model**
    * Citta
    * Coordinate
    * Dizionario
    * InformazioniMeteo
  * **service**
    * WeatherService
    * WeatherServiceInterface
  * **stats**
    * StatsImpl
    * StatsInterface
 
 
* TEST
  
  * **model**
    * CittaTest
    * CoordinateTest
  * **service**
    * WeatherServiceTest    




## Software utilizzati
* [Eclipse](https://www.eclipse.org/downloads/) - Ambiente di sviluppo
* [Postman](https://www.postman.com/) - Strumento utile per richiamare e testare le API
* [Git](https://git-scm.com/) e [GitHub]() - Sistemi per il versioning del codice
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework per sviluppo di applicazioni in Java
* [Maven](https://maven.apache.org/) - Software per gestione del progetto Java e dipendenze
* [Libreria JSON.simple](https://www.tutorialspoint.com/json_simple/index.htm) - Libreria per parsing, lettura e scrittura di oggetti e array in formato JSON
* [Javadoc](https://www.oracle.com/java/technologies/javase/javadoc-tool.html) - Strumento per la generazione automatica della documentazione del codice sorgente
* [JUnit 5](https://junit.org/junit5/) -Framework per lo unit testing


## Autori
Progetto realizzato da
* *Alessia Conti*
* *Andrea Scalzi* 
* *Francesco Fabiocchi*
