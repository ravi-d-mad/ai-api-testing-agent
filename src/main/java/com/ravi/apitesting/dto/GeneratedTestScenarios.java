package com.ravi.apitesting.dto;

import com.ravi.apitesting.dto.ApiTestScenario;
import java.util.List;

public record GeneratedTestScenarios(
        List<ApiTestScenario> scenarios
) {}
