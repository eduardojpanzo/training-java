package ao.eduardojpanzo.storebook.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import io.github.cdimascio.dotenv.Dotenv;

public class RequestAPI {
   Dotenv dotenv = Dotenv.load();
   final String apiKey = dotenv.get("APIKEY");

   public String getJsonData(String url) throws IOException, InterruptedException{
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
      HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        
      return response.body();
   }

   public String searchFromGoogleAPI (String query) throws IOException, InterruptedException{
      String url = "https://www.googleapis.com/books/v1/volumes?q="+query+"&key="+apiKey;
      return getJsonData(url);
   }
}