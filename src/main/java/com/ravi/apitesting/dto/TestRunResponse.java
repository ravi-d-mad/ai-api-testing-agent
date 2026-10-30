package com.ravi.apitesting.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TestRunResponse(
        Long id,
        LocalDateTime startedAt,
        int totalTests,
        int passedTests,
        int failedTests,
        List<TestCaseResultResponse> results
) {}
