package com.ravi.apitesting.service;

import com.ravi.apitesting.dto.ApiTestScenario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestScenarioService {

    public List<ApiTestScenario> generateScenarios() {
        return List.of(
                new ApiTestScenario(
                        "Get all employees",
                        "/api/employees",
                        "GET",
                        200,
                        null
                ),
                new ApiTestScenario(
                        "Create employee successfully",
                        "/api/employees",
                        "POST",
                        201,
                        """
                        {
                            "name": "John Doe",
                            "email": "john.doe@example.com",
                            "department": "Engineering"
                        }
                        """
                ),
                new ApiTestScenario(
                        "Create employee with invalid email",
                        "/api/employees",
                        "POST",
                        400,
                        """
                        {
                            "name": "John Doe",
                            "email": "invalid-email",
                            "department": "Engineering"
                        }
                        """
                ),
                new ApiTestScenario(
                        "Create employee without name",
                        "/api/employees",
                        "POST",
                        400,
                        """
                        {
                            "email": "john.doe@example.com",
                            "department": "Engineering"
                        }
                        """
                )
        );
    }
}
