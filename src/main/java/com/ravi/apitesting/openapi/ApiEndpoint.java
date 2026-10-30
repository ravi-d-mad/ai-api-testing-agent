package com.ravi.apitesting.openapi;

import java.util.List;

public record ApiEndpoint(
        String path,
        String method,
        String operationId,
        String summary,
        List<String> parameters,
        boolean hasRequestBody,
        List<String> responseCodes
) {
}
