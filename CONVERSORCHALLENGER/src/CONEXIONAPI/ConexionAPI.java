package CONEXIONAPI;

import MODELOS.Dinero;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConexionAPI {
    public static String response(String dinerosOrigen, String dineros, double valor) {

        try {

            //Modificar la API KEY por la dada en https://www.exchangerate-api.com
            final String API_KEY = "6016f1110e52ee9fabfd6ffc";

            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + dinerosOrigen + "/" + dineros + "/" + valor);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            double result = new Gson().fromJson(response.body(), Dinero.class).getConversion_result();

            return "$" + valor + " " + dinerosOrigen + " = $" + String.format("%.2f",result) + " " + dineros;

        } catch (Exception e) {

            return "Error: " + e.getMessage();
        }
    }
}
