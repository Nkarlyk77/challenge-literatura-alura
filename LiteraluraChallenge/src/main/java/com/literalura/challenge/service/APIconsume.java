package com.literalura.challenge.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIconsume {
    public static String getData(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error al enviar la solicitud: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("La solicitud fue interrumpida: " + e.getMessage(), e);
        }
        String json = response.body();
        return json;

    }
}
