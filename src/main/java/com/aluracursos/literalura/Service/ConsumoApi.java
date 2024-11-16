package com.aluracursos.literalura.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class ConsumoApi {
    public String obtenerDatos(String url) {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Error al consumir la API", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Error al consumir la API", e);
        }

        String json = respuesta.body();
        return json;
        
    }
}
