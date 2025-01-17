package com.conversor_monedas.oracleone.models;

public record ConversionResult(
        Double initialAmount,
        Currency fromCurrency,
        Currency toCurrency,
        Double convertedAmount
) {
}
