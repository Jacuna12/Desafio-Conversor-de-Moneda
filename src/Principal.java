import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaDatos consultaDatos = new ConsultaDatos();
        Conversor conversor = new Conversor();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Conversión estándar");
            System.out.println("2. Conversión personalizada");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 3) {
                try {
                    System.out.println("Saliendo...");
                    Thread.sleep(3000); // Espera durante 2 segundos (2000 milisegundos)
                    System.out.println("Gracias por utilizar nuestros servicios.");
                } catch (InterruptedException e) {
                    // Manejar la excepción si ocurre
                    e.printStackTrace();
                }
                break;
            }

            try {
                if (opcion == 1) {
                    System.out.println("Seleccione la conversión estándar:");
                    System.out.println("1. ARS-BOB");
                    System.out.println("2. BRL-CLP");
                    System.out.println("3. COP-USD");
                    System.out.println("4. BOB-CLP");
                    System.out.println("5. USD-ARS");
                    System.out.println("6. COP-BRL");
                    int conversionOpcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    // Definir las conversiones estándar
                    String[][] standardConversions = {
                            {"ARS", "BOB"},
                            {"BRL", "CLP"},
                            {"COP", "USD"},
                            {"BOB", "CLP"},
                            {"USD", "ARS"},
                            {"COP", "BRL"}
                    };

                    // Obtener la moneda base y destino según la opción seleccionada
                    if (conversionOpcion < 1 || conversionOpcion > standardConversions.length) {
                        System.out.println("Opción no válida.");
                        continue;
                    }

                    String monedaBase = standardConversions[conversionOpcion - 1][0];
                    String monedaDestino = standardConversions[conversionOpcion - 1][1];

                    // Obtener las tasas de cambio usando la moneda base
                    Map<String, Double> exchangeRates = consultaDatos.getExchangeRates(monedaBase);

                    if (!exchangeRates.containsKey(monedaDestino)) {
                        System.out.println("La tasa de cambio para " + monedaDestino + " no está disponible.");
                        continue;
                    }

                    System.out.print("Introduce la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    conversor.realizarConversion(monedaBase, monedaDestino, cantidad, exchangeRates);
                } else if (opcion == 2) {
                    System.out.print("Introduce la moneda de origen: ");
                    String monedaOrigen = scanner.nextLine().toUpperCase();
                    System.out.print("Introduce la moneda de destino: ");
                    String monedaDestino = scanner.nextLine().toUpperCase();
                    System.out.print("Introduce la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    // Obtener las tasas de cambio usando la moneda de origen
                    Map<String, Double> exchangeRates = consultaDatos.getExchangeRates(monedaOrigen);

                    if (!exchangeRates.containsKey(monedaDestino)) {
                        System.out.println("La tasa de cambio para " + monedaDestino + " no está disponible.");
                        continue;
                    }

                    conversor.realizarConversion(monedaOrigen, monedaDestino, cantidad, exchangeRates);
                } else {
                    System.out.println("Opción no válida.");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}