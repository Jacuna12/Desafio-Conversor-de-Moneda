import java.util.Map;

public class Conversor {
    public void realizarConversion(String monedaBase, String monedaDestino, double cantidad, Map<String, Double> exchangeRates) {
        double tasaCambio = exchangeRates.get(monedaDestino);
        double cantidadConvertida = cantidad * tasaCambio;

        // Mostrar con 6 dígitos después de la coma si el valor es menor que 0.01
        if (cantidadConvertida < 0.01) {
            System.out.println(String.format("%.6f %s", cantidadConvertida, monedaDestino));
        } else {
            System.out.println(String.format("%.2f %s", cantidadConvertida, monedaDestino));
        }
    }
}