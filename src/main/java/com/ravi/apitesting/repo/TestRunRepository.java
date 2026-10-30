package com.ravi.apitesting.repo;

import com.ravi.apitesting.entity.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TestRunRepository
        extends JpaRepository<com.ravi.apitesting.entity.TestRun, Long> {
    @Query("""
        SELECT DISTINCT t FROM TestRun t
        LEFT JOIN FETCH t.results
        ORDER BY t.startedAt DESC
    """)
    List<TestRun> findAllWithResults();

    @Query("""
        SELECT DISTINCT t FROM TestRun t
        LEFT JOIN FETCH t.results
        WHERE t.id = :id
    """)
    Optional<TestRun> findByIdWithResults(@Param("id") Long id);
}
