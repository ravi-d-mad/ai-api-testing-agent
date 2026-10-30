package com.ravi.apitesting.dto;

public record TestCaseResultResponse(
        Long id,
        String testName,
        String path,
        String method,
        int expectedStatus,
        int actualStatus,
        String result,
        String responseBody
) {}