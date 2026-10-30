package com.ravi.apitesting.controller;

import com.ravi.apitesting.dto.ApiTestResult;
import com.ravi.apitesting.dto.ApiTestScenario;
import com.ravi.apitesting.entity.TestRun;
import com.ravi.apitesting.service.ApiTestExecutionService;
import com.ravi.apitesting.service.TestRunService;
import com.ravi.apitesting.service.TestScenarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-executions")
public class TestExecutionController {

    private final TestRunService testRunService;

    public TestExecutionController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    @PostMapping("/run")
    public TestRun runTests() {
        return testRunService.executeAndSave();
    }
}