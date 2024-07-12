package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.example.model.User;
import com.google.gson.Gson;

public class CreateUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    // Client DynamoDB
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    // Istanza di DynamoDB
    private final DynamoDB dynamoDB = new DynamoDB(client);
    // Riferimento alla tabella "Users"
    private final Table table = dynamoDB.getTable("Users");

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        // Istanzia un oggetto Gson per la deserializzazione
        Gson gson = new Gson();
        // Converte il body della richiesta in un oggetto User
        User user = gson.fromJson(request.getBody(), User.class);
        // Crea un nuovo item con i dettagli dell'utente
        Item item = new Item()
                .withPrimaryKey("id", user.getId())
                .withString("name", user.getName())
                .withString("email", user.getEmail());
        // Inserisce l'item nella tabella DynamoDB
        table.putItem(item);

        // Restituisce una risposta HTTP 201 (Created)
        return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody("{\"message\": \"User created successfully\"}");
    }
}
