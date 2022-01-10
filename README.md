# Progetto-OOP-Univpm
# Openweather

#### Applicazione Java che grazie all'inserimento delle coordinate di una città, riesce ad ottenere le informazioni relative a umidità, temperatura effettiva e temperatura percepita, e le statistiche riguardanti valori minimi, massimi, media e varianza, con filtraggio in base alla periodicità scelta.

## Contenuti
* Introduzione
* Configurazione
* Rotte
* Esempi di utilizzo
* Test
* Documentazione
* Autori

## Introduzione

## Configurazione
Questa applicazione è installabile dal Prompt dei Comandi digitando:
```
git clone https://github.com/FabiocchiF/Progetto-OOP-Univpm.git
```
oppure installando https://desktop.github.com/ e clonando la repository in locale.

## Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo:
```
localhost:8080
```
Le rotte definite sono le seguenti:
N° | Rotta | Descrizione
---- | ---- | ----
1 | ```/getWeather``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temperatura effettiva e temperatura percepita 
2 | ```/getWeatherbyName``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temperatura effettiva e temperatura percepita (*Rotta aggiuntiva*)
3 | ```/getStats``` |
4 | ```/getFilters``` |

## Esempi di utilizzo
### 1. /getWeather
Con questa rotta vanno inserite le coordinate della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.
Inoltre, dopo aver effettuato la richiesta, sarà effettuato automaticamente un salvataggio in locale dei dati.
### 2. /getWeatherbyName
Con questa rotta invece va inserito il nome della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.
![Immagine 2022-01-10 112936](https://user-images.githubusercontent.com/91316014/148751522-cc53419e-9c20-4a63-bfe2-f53913631f43.png)
### 3. /getStats
### 4. /getFilters

## Test

## Documentazione
Tutto il progetto è documentato in javadoc.

## Autori
Progetto realizzato da
* Francesco Fabiocchi
* Alessia Conti
* Andrea Scalzi

