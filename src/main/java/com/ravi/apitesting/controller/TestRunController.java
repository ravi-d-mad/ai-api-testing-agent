package com.ravi.apitesting.controller;

import com.ravi.apitesting.dto.TestRunResponse;
import com.ravi.apitesting.service.TestRunQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test-executions")
public class TestRunController {

    private final TestRunQueryService queryService;

    public TestRunController(TestRunQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public List<TestRunResponse> getAllRuns() {
        return queryService.getAllRuns();
    }

    @GetMapping("/{id}")
    public TestRunResponse getRunById(@PathVariable Long id) {
        return queryService.getRunById(id);
    }
}
