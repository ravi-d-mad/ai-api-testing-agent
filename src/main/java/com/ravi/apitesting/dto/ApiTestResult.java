package com.ravi.apitesting.dto;

public record ApiTestResult(
        String testName,
        String path,
        String method,
        int expectedStatus,
        int actualStatus,
        String result,
        String responseBody
) {}
