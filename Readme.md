Documentazione del Progetto Serverless Java API

Introduzione

Questo progetto rappresenta un'API RESTful serverless, sviluppata in Java, che utilizza AWS Lambda e DynamoDB. L'obiettivo è fornire funzionalità per la gestione degli utenti, inclusa la creazione e il recupero di utenti tramite ID. Il progetto è stato sviluppato utilizzando il framework Serverless per facilitare il deployment e la gestione delle risorse AWS.

Obiettivo del Progetto

L'obiettivo principale è creare un'API serverless che permetta:

1. Creazione di un nuovo utente: tramite un endpoint HTTP POST.
2. Recupero dei dettagli di un utente: tramite un endpoint HTTP GET basato sull'ID dell'utente.


Servizi Utilizzati

- AWS Lambda: Utilizzato per eseguire funzioni serverless in risposta agli eventi HTTP.
- Amazon API Gateway: Gestisce le richieste HTTP e le inoltra alle funzioni Lambda.
- Amazon DynamoDB: Utilizzato come database NoSQL per memorizzare i dati degli utenti.


Struttura del Progetto

Il progetto è organizzato come segue:

src/main/java/com/example/: Contiene le classi Java che implementano le funzioni Lambda.
- CreateUserHandler.java: Classe per gestire la creazione di un nuovo utente.
- GetUserByIdHandler.java: Classe per gestire il recupero di un utente tramite ID.
- serverless.yml: File di configurazione del framework Serverless.
- pom.xml: File di configurazione di Maven per la gestione delle dipendenze e la compilazione del progetto.


Testing delle API

Per testare le API, ho utilizzato Postman. Di seguito gli endpoint delle API.

1. Creazione di un Nuovo Utente

Endpoint: POST https://kig1pjd8mi.execute-api.us-east-1.amazonaws.com/dev/users


2. Recupero di un Utente tramite ID

Endpoint: GET https://kig1pjd8mi.execute-api.us-east-1.amazonaws.com/dev/users/{id}



Repository GitHub

Il progetto è disponibile su GitHub al seguente link: [GitHub Repository](https://github.com/criscamp21/serverless-java-api)
