package com.conversor_monedas.oracleone;

import com.conversor_monedas.oracleone.api.ExchangeRateAPI;
import com.conversor_monedas.oracleone.services.ConversionService;
import com.conversor_monedas.oracleone.view.CurrencyConvertirCLI;


public class Main {
    public static void main(String[] args){

        String apiKey = "81660eb78f61feea4fda8285";

        ExchangeRateAPI api = new ExchangeRateAPI(apiKey);

        ConversionService conversionService = new ConversionService(api);

        CurrencyConvertirCLI cli = new CurrencyConvertirCLI(conversionService);
        cli.start();


    }
}
