package com.ravi.apitesting.repo;

import com.ravi.apitesting.entity.TestCaseResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseResultRepository
        extends JpaRepository<TestCaseResult, Long> {
}
