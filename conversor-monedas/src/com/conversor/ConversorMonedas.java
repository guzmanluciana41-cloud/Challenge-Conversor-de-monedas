package com.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMonedas {

    // ⚠️ Reemplaza con tu clave de API de https://www.exchangerate-api.com/
    private static final String API_KEY = "TU_API_KEY_AQUI";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("*************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("*************************************");

        while (opcion != 7) {
            mostrarMenu();

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n⚠ Por favor, ingresa un número válido.\n");
                continue;
            }

            if (opcion == 7) {
                System.out.println("\n¡Hasta luego! Gracias por usar el Conversor de Monedas. =]");
                break;
            }

            String[] pares = obtenerPar(opcion);
            if (pares == null) {
                System.out.println("\n⚠ Opción no válida. Por favor, elige una opción del 1 al 7.\n");
                continue;
            }

            System.out.print("\nIngresa el valor que deseas convertir: ");
            double monto;
            try {
                monto = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n⚠ Valor inválido. Ingresa un número (ej: 100 o 100.50).\n");
                continue;
            }

            realizarConversion(pares[0], pares[1], monto);
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=============================================");
        System.out.println("          MENÚ DE CONVERSIÓN DE MONEDAS");
        System.out.println("=============================================");
        System.out.println("1) Dólar (USD)         =>> Peso Argentino (ARS)");
        System.out.println("2) Peso Argentino (ARS) =>> Dólar (USD)");
        System.out.println("3) Dólar (USD)         =>> Real Brasileño (BRL)");
        System.out.println("4) Real Brasileño (BRL) =>> Dólar (USD)");
        System.out.println("5) Dólar (USD)         =>> Peso Colombiano (COP)");
        System.out.println("6) Peso Colombiano (COP) =>> Dólar (USD)");
        System.out.println("7) Salir");
        System.out.println("=============================================");
        System.out.print("Elige una opción válida: ");
    }

    private static String[] obtenerPar(int opcion) {
        return switch (opcion) {
            case 1 -> new String[]{"USD", "ARS"};
            case 2 -> new String[]{"ARS", "USD"};
            case 3 -> new String[]{"USD", "BRL"};
            case 4 -> new String[]{"BRL", "USD"};
            case 5 -> new String[]{"USD", "COP"};
            case 6 -> new String[]{"COP", "USD"};
            default -> null;
        };
    }

    private static void realizarConversion(String monedaOrigen, String monedaDestino, double monto) {
        try {
            String url = BASE_URL + monedaOrigen + "/" + monedaDestino;

            // Crear cliente HTTP
            HttpClient cliente = HttpClient.newHttpClient();

            // Crear solicitud HTTP
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Enviar solicitud y recibir respuesta
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Parsear JSON con Gson
            JsonObject json = JsonParser.parseString(respuesta.body()).getAsJsonObject();

            String resultado = json.get("result").getAsString();

            if (!resultado.equals("success")) {
                System.out.println("\n❌ Error al consultar la API: " + resultado);
                return;
            }

            double tasaConversion = json.get("conversion_rate").getAsDouble();
            double valorConvertido = monto * tasaConversion;

            System.out.printf("\n✅ El valor de %.2f [%s] corresponde al valor final de %.2f [%s]%n",
                    monto, monedaOrigen, valorConvertido, monedaDestino);

        } catch (Exception e) {
            System.out.println("\n❌ Error al realizar la conversión: " + e.getMessage());
            System.out.println("   Verifica tu API Key y tu conexión a internet.");
        }
    }
}
