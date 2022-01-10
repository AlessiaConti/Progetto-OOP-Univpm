# Progetto-OOP-Univpm
# Openweather

*Applicazione Java che grazie all'inserimento di una città, riesce ad ottenere le informazioni relative a umidità, temperatura effettiva, temperatura percepita, e le statistiche riguardanti valori minimi, massimi, media e varianza, con filtraggio in base alla periodicità scelta.*

## Contenuti
* Introduzione
* Configurazione
* Rotte
* Esempi di utilizzo
* Test
* Documentazione
* Software utilizzati
* Autori

## Introduzione
Questa applicazione permette di:
- [x] Ottenere informazioni meteo (umidità, temp effettiva e percepita) della città inserita da utente tramite coordinate
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
Con questa rotta vanno inserite le coordinate della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata (nome e id) e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.
Inoltre, dopo aver effettuato la richiesta, sarà effettuato automaticamente un salvataggio in locale dei dati.
![image1](https://user-images.githubusercontent.com/91316014/148759712-8b9614f9-448e-4b07-8b0d-89421629b823.png)

### 2. /getWeatherbyName
Con questa rotta invece va inserito il nome della città di interesse. Restituisce un JSONObject contenente la data in cui è stata effettuata la ricerca, le informazioni relative alla città cercata e le informazioni sul meteo riguardanti umidità, temperatura percepita ed effettiva.
![image2](https://user-images.githubusercontent.com/91316014/148760730-9c57437b-10fc-4d2e-9003-0bbd6a86eda5.png)

### 3. /getStats
### 4. /getFilters

## Test

## Documentazione
Tutto il progetto è documentato in javadoc.

## Software utilizzati
* [Eclipse](https://www.eclipse.org/downloads/) - Ambiente di sviluppo
* [Maven](https://maven.apache.org/) - software di gestione di progetti e librerie
* [Spring Boot](https://spring.io/projects/spring-boot) - framework per sviluppo di applicazioni in Java
* [Postman](https://www.postman.com/) - ambiente di sviluppo API per effettuare richieste

## Autori
Progetto realizzato da
* Francesco Fabiocchi
* Alessia Conti
* Andrea Scalzi

