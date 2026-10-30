package com.ravi.apitesting.controller;

import com.ravi.apitesting.dto.ApiTestScenario;
import com.ravi.apitesting.service.TestScenarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test-scenarios")
public class TestScenarioController {

    private final TestScenarioService testScenarioService;

    public TestScenarioController(TestScenarioService testScenarioService) {
        this.testScenarioService = testScenarioService;
    }

    @GetMapping
    public List<ApiTestScenario> getScenarios() {
        return testScenarioService.generateScenarios();
    }
}
