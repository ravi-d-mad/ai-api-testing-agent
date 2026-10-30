package com.ravi.apitesting.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test_case_results")
public class TestCaseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;
    private String path;
    private String method;
    private int expectedStatus;
    private int actualStatus;
    private String result;

    @Column(length = 10000)
    private String responseBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_run_id")
    private TestRun testRun;

    public Long getId() { return id; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public int getExpectedStatus() { return expectedStatus; }
    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }
    public int getActualStatus() { return actualStatus; }
    public void setActualStatus(int actualStatus) {
        this.actualStatus = actualStatus;
    }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getResponseBody() { return responseBody; }
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    public TestRun getTestRun() { return testRun; }
    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }
}