package com.conversor_monedas.oracleone;

import com.conversor_monedas.oracleone.api.ExchangeRateAPI;


public class main {
    public static void main(String[] args){

        String apiKey = "81660eb78f61feea4fda8285";

        ExchangeRateAPI api = new ExchangeRateAPI(apiKey);



        System.out.println(api.getCurrency("COP").toString());

    }
}
