# Progetto-OOP-Univpm
# :cloud: Openweather :cloud:

*Applicazione Java che grazie all'inserimento di una città, riesce ad ottenere le informazioni relative a umidità, temperatura effettiva, temperatura percepita, e le statistiche riguardanti valori minimi, massimi, media e varianza, con filtraggio in base alla periodicità scelta.*

## Contenuti
* [Introduzione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#introduzione)
* [Configurazione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#configurazione)
* [Rotte](https://github.com/FabiocchiF/Progetto-OOP-Univpm#rotte)
* [Esempi di utilizzo](https://github.com/FabiocchiF/Progetto-OOP-Univpm#esempi-di-utilizzo)
* [Test](https://github.com/FabiocchiF/Progetto-OOP-Univpm#test)
* [Documentazione](https://github.com/FabiocchiF/Progetto-OOP-Univpm#documentazione)
* [Software utilizzati](https://github.com/FabiocchiF/Progetto-OOP-Univpm#software-utilizzati)
* [Autori](https://github.com/FabiocchiF/Progetto-OOP-Univpm#autori)

## Introduzione
L'applicazione permette di:
- [x] Ottenere informazioni meteo (umidità, temp effettiva e percepita) della città inserita dall'utente
- [x] Salvare dati in locale
- [ ] Gestire possibili errori dovuti a inserimento di coordinate errate
- [ ] Facilitare inserimento delle coordinate con possibilità di ricercarle tramite un dizionario statico
- [x] Ottenere statistiche riguardanti valori min max media e varianza di umidità, temp effettiva e percepita
- [x] Scegliere filtraggio delle statistiche in base alla periodicità: giornaliera, settimanale, mensile


## Configurazione
Questa applicazione è installabile dal Prompt dei Comandi digitando:
```
git clone https://github.com/FabiocchiF/Progetto-OOP-Univpm.git
```
oppure installando [*GitHub Desktop*](https://desktop.github.com/) e clonando la repository in locale.

## Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere all'indirizzo:
```
localhost:8080
```
Le rotte disponibili sono le seguenti:
N° | Rotta | Descrizione
---- | ---- | ----
1 | ```/getWeather``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temperatura effettiva e temperatura percepita 
2 | ```/getWeatherbyName``` | restituisce un JSONObject contenente le informazioni meteo relative a umidità, temperatura effettiva e temperatura percepita (*Rotta aggiuntiva*)
3 | ```/getStats``` |
4 | ```/getFilters``` |

## Esempi di utilizzo
### 1. /getWeather
Con questa rotta devono essere inserite le coordinate della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata (nome e id) e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.

![Immagine1](https://user-images.githubusercontent.com/91316014/148811173-b6f33828-9afa-4cb6-9eb2-d06af2987abc.png)

Inoltre, dopo aver effettuato la richiesta, sarà effettuato automaticamente un salvataggio in locale dei dati in un file di testo (che viene salvato nel path indicato in rosso)

![Immagine1.2](https://user-images.githubusercontent.com/91316014/148812290-d793d63a-1346-4000-9077-7de9e4c750de.png)



### 2. /getWeatherbyName
Con questa rotta invece va inserito il nome della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.

![Immagine2](https://user-images.githubusercontent.com/91316014/148811253-940f52f6-37d3-4d9f-93ad-18d82a3462fa.png)


### 3. /getStats

### 4. /getFilters

## Test

## Documentazione
Tutto il progetto è documentato in javadoc.

## Software utilizzati
* [Eclipse](https://www.eclipse.org/downloads/) - Ambiente di sviluppo
* [Maven](https://maven.apache.org/) - Software utilizzato per gestione di progetto Java e dipendenze
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework per sviluppo di applicazioni in Java
* [Postman](https://www.postman.com/) - Strumento utile per testare le API

## Autori
Progetto realizzato da
* Francesco Fabiocchi
* Alessia Conti
* Andrea Scalzi
