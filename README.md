# Progetto Rubrica

Questo repository contiene un piccolo progetto Java per la gestione di contatti telefonici.

### Overview Readme

Questo readme contiene le indicazioni per eseguire il progetto. Il progetto è stato realizzato definendo 3 versioni:

- basic
- advanced
- advanced with Database

#### Versione Basic

La versione basic dispone di una semplice interfaccia grafica realizzata con la libreria Java SWING attraverso cui è possibile accedere ai propri contatti telefonici in forma tabellare.
La tabella contiene le seguenti informazioni:

- nome
- cognome
- indirizzo
- numero di telefono
- età

L'applicazione permette semplicemente l'agginta, rimozione e modifica dei propri contatti. Le informazioni vengono salvate in un file .txt chiamato "informazioni.txt" seguendo un formato CSV.
Ad esempio:

```
Steve;Jobs;0612344;via Cupertino 13;56
Bill;Gates;06688989;via Redmond 10;60
```

#### Versione Advanced

La versione Advanced introduce la possibilità di fare login. Prima di accedere ai propri contatti, l'utente deve inserire username e password di accesso.
In questa versione la persistenza delle informazioni è gestita attraverso file .txt:

- utenti.txt: contiene la lista degli utenti registrati, definiti come "username;password"
- informazioni: una directory che contiene, per ogni utente, un file "username_password_informazioni.txt" nel quale sono salvati i contatti dell'utente. Questi file sono create automaticamente, tuttavia la gestione degli utenti avviene manualmente intervenendo sull'omonimo file.

Esempio di utenti nel file.

```
admin;1234
mario;password
```

L'applicazione non gestisce la registrazione di nuovi utenti.

#### Versione Advanced with Database

Ultima versione dell'applicazione. In questa versione la persistenza delle informazioni è gestita attraverso comunicazione con un DBMS MySQL. Un file "database.sql" contiene le query SQL per:

- creazione delle tabelle "Utente", "Informazioni"
- inserimento di dati cammpione nelle tabelle.

L'applicazione permette il login e la gestione dei propri contatti.
Il database MySQL è gestito attraverso container Docker. Un file "docker-compose.yaml" contiene i dati per lanciare correttamente un servizio MySQL sulla porta 3306 e un servizio phpmyadmin sulla porta 8080.
Assicurarsi di avere installato "Docker" e "Docker compose" sulla propria macchina.

```
docker compose up         // Per lanciare i container
docker compose down       // Per rimuovere i container creati
docker compose down -v    // Per rimuovere i volumi allocati
```

Tra i file di progetto presente un documento "config.properties" attraverso il quale è possibile configurare l'accesso al DB.
Informazioni contenute:

```
db.url=jdbc:mysql://localhost:3306/database
db.user=user
db.password=password
```

Queste informazioni devono essere congruenti con le variabili di ambiente dichiarate nel docker compose file.
Assicurarsi di inizializzare correttamente il Database caricando con le informazioni presenti nel file "database.sql"

# Directories

Questo repository organizza i file nel modo seguente:

- Ogni versione contiene:
  - i files IntellijIdea
  - artifact .jar
  - altri file .txt
- file "database.sql"
- file "docker-compose.yaml"

# scelte progettuali:

- Pattern MVC
- Uso di classi DTO per accesso al DB
