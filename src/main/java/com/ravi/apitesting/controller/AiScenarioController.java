package com.ravi.apitesting.controller;

import com.ravi.apitesting.dto.GeneratedTestScenarios;
import com.ravi.apitesting.service.AiScenarioGenerationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/scenarios")
public class AiScenarioController {

    private final AiScenarioGenerationService aiService;

    public AiScenarioController(AiScenarioGenerationService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public GeneratedTestScenarios generate(
            @RequestBody String openApiSpec) {

        return aiService.generateScenarios(openApiSpec);
    }
}
