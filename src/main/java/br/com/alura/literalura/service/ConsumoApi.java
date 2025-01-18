package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


public class ConsumoApi {
    public String obterDados(String endereco) {
        // Configurando o HttpClient para seguir redirecionamentos
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // Habilita o acompanhamento de redirecionamentos
                .build();

        // Criando a solicitação HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            // Enviando a solicitação e capturando a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Exibindo o status e corpo da resposta para debug
            System.out.println("Status da resposta: " + response.statusCode());
            System.out.println("Corpo da resposta: " + response.body());

            // Retornando o corpo da resposta
            return response.body();
        } catch (IOException | InterruptedException e) {
            // Lançando exceção em caso de erro
            throw new RuntimeException("Erro ao consumir API: " + e.getMessage(), e);
        }
    }
}

