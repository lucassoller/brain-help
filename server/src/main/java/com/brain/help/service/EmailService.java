package com.brain.help.service;

import com.brain.help.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Value("${mail.token}")
    private String apiKey;

    @Value("${mail.from:Brain Help <onboarding@resend.dev>}")
    private String remetente;

    public void enviarEmail(String destinatario, String link) throws IOException {
        String html = carregarTemplate(link);

        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("from", remetente);
        payload.putArray("to").add(destinatario);
        payload.put("subject", "Redefinição de senha - Brain Help");
        payload.put("html", html);

        String body = objectMapper.writeValueAsString(payload);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                log.error("Falha ao enviar e-mail via Resend. Status: {}, Resposta: {}", response.statusCode(), response.body());
                throw new BusinessException("Não foi possível enviar o e-mail de recuperação");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("Envio de e-mail interrompido");
        }
    }

    private String carregarTemplate(String link) throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/redefinir-senha.html");

        if (!resource.exists()) {
            throw new IllegalStateException("Template de e-mail não encontrado em resources/templates/redefinir-senha.html");
        }

        try (InputStream inputStream = resource.getInputStream()) {
            String html = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return html.replace("{{LINK_REDEFINICAO}}", link);
        }
    }
}