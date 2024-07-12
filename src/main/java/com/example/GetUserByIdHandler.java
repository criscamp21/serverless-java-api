package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

public class GetUserByIdHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    // Client DynamoDB
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    // Istanza di DynamoDB
    private final DynamoDB dynamoDB = new DynamoDB(client);
    // Riferimento alla tabella "Users"
    private final Table table = dynamoDB.getTable("Users");

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        // Ottiene l'ID utente dai parametri del percorso
        String userId = request.getPathParameters().get("id");
        // Recupera l'item dalla tabella usando l'ID
        Item item = table.getItem("id", userId);
        // Se l'item non esiste, restituisce una risposta HTTP 404 (Not Found)
        if (item == null) {
            return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("{\"message\": \"User not found\"}");
        }
        // Restituisce una risposta HTTP 200 (OK) con il JSON dell'item
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(item.toJSON());
    }
}
