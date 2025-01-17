package com.conversor_monedas.oracleone.view;

import com.conversor_monedas.oracleone.models.ConversionResult;
import com.conversor_monedas.oracleone.models.Currency;
import com.conversor_monedas.oracleone.services.ConversionService;

import java.util.Scanner;

public class CurrencyConvertirCLI {

    private final ConversionService conversionService;

    public CurrencyConvertirCLI(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.println("\n*** Conversor de Monedas ***");
            System.out.println("1) Convertir Moneda");
            System.out.println("2) Salir");
            System.out.print("Seleccione una opcion: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    convertCurrency(scanner);
                    break;

                case 2:
                    exit = true;
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void convertCurrency(Scanner scanner){
        try {
            System.out.println("\nmodenas disponibles: ");

            Currency[] currencies = conversionService.getSupportedCurrencies().toArray(new Currency[0]);

            for (int i=0; i<currencies.length; i++){
                System.out.println((i+1)+") "+currencies[i].code()+" - "+currencies[i].name());
            }


            System.out.println("\nSelecciona la moneda de origen: ");
            int monedaOrigen = scanner.nextInt();

            System.out.println("\nSelecciona la monedade destino: ");
            int monedaDestino = scanner.nextInt();

            System.out.println("\nIngrese la cantidad de " + currencies[monedaOrigen-1].code() + " a convertir: ");
            double cantidad = scanner.nextDouble();


            ConversionResult result = conversionService.convert(currencies[monedaOrigen-1], currencies[monedaDestino-1], cantidad);

            System.out.println("\nResultado de la conversion: ");
            System.out.println("Monto inicial: "+result.initialAmount() +" "+result.fromCurrency().code());
            System.out.println("Monto convertido: "+result.convertedAmount()+" " +result.toCurrency().code());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Moneda no válida");
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
