package com.ravi.apitesting.service;

import com.ravi.apitesting.entity.TestCaseResult;
import com.ravi.apitesting.entity.TestRun;
import com.ravi.apitesting.dto.ApiTestResult;
import com.ravi.apitesting.repo.TestRunRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestRunService {

    private final TestRunRepository testRunRepository;
    private final TestScenarioService scenarioService;
    private final ApiTestExecutionService executionService;

    public TestRunService(
            TestRunRepository testRunRepository,
            TestScenarioService scenarioService,
            ApiTestExecutionService executionService) {
        this.testRunRepository = testRunRepository;
        this.scenarioService = scenarioService;
        this.executionService = executionService;
    }

    @Transactional
    public TestRun executeAndSave() {
        TestRun run = new TestRun();
        run.setStartedAt(LocalDateTime.now());

        List<ApiTestResult> results = scenarioService.generateScenarios()
                .stream()
                .map(executionService::execute)
                .toList();

        run.setTotalTests(results.size());
        run.setPassedTests((int) results.stream()
                .filter(r -> "PASS".equals(r.result())).count());
        run.setFailedTests((int) results.stream()
                .filter(r -> !"PASS".equals(r.result())).count());

        for (ApiTestResult result : results) {
            TestCaseResult entity = new TestCaseResult();
            entity.setTestName(result.testName());
            entity.setPath(result.path());
            entity.setMethod(result.method());
            entity.setExpectedStatus(result.expectedStatus());
            entity.setActualStatus(result.actualStatus());
            entity.setResult(result.result());
            entity.setResponseBody(result.responseBody());
            run.addResult(entity);
        }

        return testRunRepository.save(run);
    }
}
