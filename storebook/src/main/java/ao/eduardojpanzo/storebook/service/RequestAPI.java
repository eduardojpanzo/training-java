package ao.eduardojpanzo.storebook.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestAPI {
   final String APIKEY = "AIzaSyAWuJEe8cDnj1GeYFx93Ct6BFAU9t00Jz0";
   
   public String getJsonData(String url) throws IOException, InterruptedException{
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
      HttpResponse<String> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
        
      return response.body();
   }

   public String searchFromGoogleAPI (String query) throws IOException, InterruptedException{
      String url = "https://www.googleapis.com/books/v1/volumes?q="+query+"&key="+APIKEY;
      return getJsonData(url);
   }
}