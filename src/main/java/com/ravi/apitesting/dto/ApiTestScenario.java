package com.ravi.apitesting.dto;

public record ApiTestScenario(
        String name,
        String path,
        String method,
        int expectedStatus,
        String requestBody
) {}
