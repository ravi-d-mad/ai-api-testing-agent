package com.ravi.apitesting.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_runs")
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startedAt;

    private int totalTests;
    private int passedTests;
    private int failedTests;

    @OneToMany(mappedBy = "testRun",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TestCaseResult> results = new ArrayList<>();

    public Long getId() { return id; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public int getTotalTests() { return totalTests; }
    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getPassedTests() { return passedTests; }
    public void setPassedTests(int passedTests) {
        this.passedTests = passedTests;
    }

    public int getFailedTests() { return failedTests; }
    public void setFailedTests(int failedTests) {
        this.failedTests = failedTests;
    }

    public List<TestCaseResult> getResults() { return results; }

    public void addResult(TestCaseResult result) {
        results.add(result);
        result.setTestRun(this);
    }
}