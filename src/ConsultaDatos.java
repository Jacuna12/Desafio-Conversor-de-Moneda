import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Map;

public class ConsultaDatos {

    private static final String API_KEY = "8329f3179fe017fb9d74e37d";
    private HttpClient httpClient;
    private Gson gson;

    public ConsultaDatos() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Map<String, Double> getExchangeRates(String monedaBase) throws IOException, InterruptedException {
        String apiUrl = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaBase;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

        Type type = new TypeToken<Map<String, Double>>() {}.getType();
        return gson.fromJson(conversionRates, type);
    }
}
