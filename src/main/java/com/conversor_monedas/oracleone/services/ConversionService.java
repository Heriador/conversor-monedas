package com.conversor_monedas.oracleone.services;

import com.conversor_monedas.oracleone.models.Currency;

import java.util.List;

public class ConversionService {



    public List<Currency> getSupportedCurrencies(){
        return List.of(
                new Currency("USD", "Dolar Estadounidense"),
                new Currency("COP", "Peso Colombiano"),
                new Currency("ARS", "Peso Argentino"),
                new Currency("BRL", "Real Brasileño")
        );
    }

}
