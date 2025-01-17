package com.conversor_monedas.oracleone.api;

import com.conversor_monedas.oracleone.exception.ApiRequestException;
import com.conversor_monedas.oracleone.models.ExchangeRateResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {

    private final String apiKey;
    private final HttpClient client;
    private final Gson gson;


    public ExchangeRateAPI(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public ExchangeRateResponse getCurrencyInfo(String currencyCode){
       try{

           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create("https://v6.exchangerate-api.com/v6/"+this.apiKey+"/latest/" + currencyCode))
                   .GET()
                   .build();
           HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

           if(response.statusCode() != 200){
                throw new ApiRequestException("Error al consultar las tasas de cambio: "+response.statusCode());
           }

           return this.gson.fromJson(response.body(), ExchangeRateResponse.class);

       } catch (IOException | InterruptedException e) {
           throw new ApiRequestException("Error al consultar la API: "+e.getMessage());
       }

    }

}
