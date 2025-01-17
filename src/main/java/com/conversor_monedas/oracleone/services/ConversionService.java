package com.conversor_monedas.oracleone.services;

import com.conversor_monedas.oracleone.api.ExchangeRateAPI;
import com.conversor_monedas.oracleone.exception.CurrencyNotSupportedException;
import com.conversor_monedas.oracleone.models.ConversionResult;
import com.conversor_monedas.oracleone.models.Currency;

import java.util.List;

public class ConversionService {

    private final ExchangeRateAPI exchangeRateAPI;

    public ConversionService(ExchangeRateAPI exchangeRateAPI){
        this.exchangeRateAPI = exchangeRateAPI;
    }


    public List<Currency> getSupportedCurrencies(){
        return List.of(
                new Currency("USD", "Dolar Estadounidense"),
                new Currency("COP", "Peso Colombiano"),
                new Currency("ARS", "Peso Argentino"),
                new Currency("BRL", "Real Brasileño")
        );
    }

    public ConversionResult convert(Currency from, Currency to, double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("El monto a convertir debe ser mayor a 0");
        }

        List<Currency> supportedCurrencies = getSupportedCurrencies();
        if(!supportedCurrencies.contains(from) || !supportedCurrencies.contains(to)){
            throw new CurrencyNotSupportedException("La moneda de destino o de origen no es soportada");
        }

        double exchangeRate = exchangeRateAPI.getCurrencyInfo(from.code()).conversion_rates().get(to.code());

        return new ConversionResult(amount, from, to, amount * exchangeRate);
    }

}
