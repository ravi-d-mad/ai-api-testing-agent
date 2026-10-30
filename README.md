# AI API Testing Agent

An AI-powered API testing platform built with **Java, Spring Boot, Spring AI, and Google Gemini** that simplifies API testing through intelligent test scenario generation and automated execution.

The platform analyzes OpenAPI specifications, generates meaningful test scenarios using Generative AI, executes HTTP requests, and validates API responses using deterministic Java-based assertions.

## 🚀 Project Overview

Traditional API testing often requires developers and QA engineers to manually create test cases, prepare request payloads, execute API calls, and validate responses.

The AI API Testing Agent aims to automate this workflow using AI-assisted test generation and deterministic test execution.

The platform combines the reasoning capabilities of Large Language Models (LLMs) with reliable Java-based validation to make API testing smarter and more efficient.

## ✨ Key Features

- **OpenAPI Specification Parsing:** Analyze API definitions and extract endpoints, HTTP methods, and response information.
- **AI-Powered Test Generation:** Use Google Gemini to generate positive, negative, and boundary test scenarios.
- **Automated API Execution:** Execute generated test cases against API endpoints.
- **Deterministic Response Validation:** Validate HTTP status codes and API responses using Java-based assertions.
- **Test Execution Management:** Organize and track API test executions.
- **AI-Based Failure Analysis:** Provide meaningful explanations of test failures using Generative AI.
- **Interactive API Documentation:** Explore and test application endpoints using Swagger UI.

## 🏗️ Architecture

```text
             OpenAPI Specification
                      |
                      v
              OpenAPI Parser
                      |
                      v
             API Endpoint Details
                      |
                      v
             AI Scenario Generator
                (Google Gemini)
                      |
                      v
              Generated Test Cases
                      |
                      v
               Test Executor
                      |
                      v
               HTTP API Calls
                      |
                      v
             Java Assertion Engine
                      |
             +--------+--------+
             |                 |
             v                 v
         Test Results      Failure Analysis
                              (AI)
             |                 |
             +--------+--------+
                      |
                      v
               Test Execution
                  Reports
```

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Backend development |
| Spring Boot | Application framework |
| Spring AI | AI integration |
| Google Gemini | AI-powered test scenario generation |
| Spring Data JPA | Data persistence |
| H2 Database | In-memory database |
| Swagger Parser | OpenAPI specification parsing |
| Springdoc OpenAPI | API documentation |
| Gradle | Build and dependency management |

## ⚙️ Getting Started

### Prerequisites

- Java 17 or later
- Gradle
- Google Gemini API key
- IntelliJ IDEA or another Java IDE

### Clone the repository

```bash
git clone https://github.com/ravi-d-mad/ai-api-testing-agent.git

cd ai-api-testing-agent
```

### Configure Gemini API Key

Set your Google Gemini API key as an environment variable.

**Windows PowerShell:**

```powershell
$env:GEMINI_API_KEY="YOUR_GEMINI_API_KEY"
```

Do not commit API keys or other secrets to GitHub.

### Run the application

**Windows:**

```bash
.\gradlew.bat bootRun
```

**Linux/macOS:**

```bash
./gradlew bootRun
```

The application starts at:

```text
http://localhost:8080
```

### Access Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

### Access H2 Database Console

```text
http://localhost:8080/h2-console
```

Use the JDBC URL configured in `application.yml`:

```text
jdbc:h2:mem:apitestdb
```

Username: `sa`

Password: Leave blank.

## 🧪 Sample Testing Scenario

Consider an Employee Management API.

**Endpoint:**

```http
GET /api/employees
```

The AI scenario generator can generate test scenarios such as:

1. Successfully retrieve all employees.
2. Verify the response when no employees exist.
3. Validate the expected HTTP status code.
4. Verify the response structure.
5. Validate required response fields.

For employee creation:

```http
POST /api/employees
Content-Type: application/json
```

Example request:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "department": "Engineering"
}
```

Potential scenarios include:

- Successful employee creation.
- Missing employee name.
- Invalid email address.
- Missing department.
- Invalid or empty request payload.

## 🧠 AI + Deterministic Validation

A key design principle of this project is separating AI reasoning from test execution.

**AI responsibilities:**
- Understand API specifications.
- Generate meaningful test scenarios.
- Suggest edge cases.
- Explain test failures.

**Java responsibilities:**
- Execute HTTP requests.
- Validate HTTP status codes.
- Validate response payloads.
- Determine whether a test passes or fails.

AI-generated explanations do not determine test pass/fail status. The deterministic Java assertion engine remains the source of truth.

## 🔐 Security Considerations

- API credentials are maintained outside source control.
- API execution should be restricted to explicitly authorized targets.
- Destructive HTTP methods should be disabled by default.
- Request execution should validate target URLs to prevent SSRF vulnerabilities.

## 🗺️ Roadmap

- [x] Spring Boot project setup
- [x] Gemini integration
- [x] Employee sample REST API
- [x] OpenAPI specification parsing
- [x] AI-generated test scenarios
- [ ] Automated execution of generated scenarios
- [ ] Deterministic assertion engine enhancements
- [ ] AI-powered failure analysis
- [ ] Test execution history and reporting
- [ ] React-based frontend
- [ ] Docker support
- [ ] CI/CD integration using GitHub Actions
- [ ] MCP integration for AI-assisted testing workflows

## 🎯 Project Goal

Build an intelligent API testing platform that reduces manual test creation effort, improves API test coverage, and helps developers identify and understand API failures faster.

**AI generates the tests. Java validates the results.**
