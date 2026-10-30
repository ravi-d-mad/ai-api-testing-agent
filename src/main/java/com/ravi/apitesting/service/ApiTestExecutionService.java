package com.ravi.apitesting.service;

import com.ravi.apitesting.dto.ApiTestResult;
import com.ravi.apitesting.dto.ApiTestScenario;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ApiTestExecutionService {

    private static final String BASE_URL = "http://localhost:8080";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public ApiTestResult execute(ApiTestScenario scenario) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + scenario.path()))
                    .timeout(Duration.ofSeconds(10))
                    .header("Accept", "application/json");

            String body = scenario.requestBody();

            switch (scenario.method().toUpperCase()) {
                case "GET" -> requestBuilder.GET();
                case "POST" -> requestBuilder
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(
                                body == null ? "" : body));
                default -> throw new IllegalArgumentException(
                        "Unsupported HTTP method: " + scenario.method());
            }

            HttpResponse<String> response = httpClient.send(
                    requestBuilder.build(),
                    HttpResponse.BodyHandlers.ofString());

            int actualStatus = response.statusCode();

            return new ApiTestResult(
                    scenario.name(),
                    scenario.path(),
                    scenario.method(),
                    scenario.expectedStatus(),
                    actualStatus,
                    actualStatus == scenario.expectedStatus() ? "PASS" : "FAIL",
                    response.body()
            );

        } catch (Exception exception) {
            return new ApiTestResult(
                    scenario.name(),
                    scenario.path(),
                    scenario.method(),
                    scenario.expectedStatus(),
                    0,
                    "ERROR",
                    exception.getMessage()
            );
        }
    }
}