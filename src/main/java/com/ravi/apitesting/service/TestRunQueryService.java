package com.ravi.apitesting.service;

import com.ravi.apitesting.dto.TestCaseResultResponse;
import com.ravi.apitesting.dto.TestRunResponse;
import com.ravi.apitesting.entity.TestRun;
import com.ravi.apitesting.exception.ResourceNotFoundException;
import com.ravi.apitesting.repo.TestRunRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestRunQueryService {

    private final TestRunRepository repository;

    public TestRunQueryService(TestRunRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TestRunResponse> getAllRuns() {
        return repository.findAllWithResults()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TestRunResponse getRunById(Long id) {
        TestRun run = repository.findByIdWithResults(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Test run not found with id: " + id));

        return toResponse(run);
    }

    private TestRunResponse toResponse(TestRun run) {

        List<TestCaseResultResponse> results =
                run.getResults().stream()
                        .map(result -> new TestCaseResultResponse(
                                result.getId(),
                                result.getTestName(),
                                result.getPath(),
                                result.getMethod(),
                                result.getExpectedStatus(),
                                result.getActualStatus(),
                                result.getResult(),
                                result.getResponseBody()
                        ))
                        .toList();

        return new TestRunResponse(
                run.getId(),
                run.getStartedAt(),
                run.getTotalTests(),
                run.getPassedTests(),
                run.getFailedTests(),
                results
        );
    }
}
