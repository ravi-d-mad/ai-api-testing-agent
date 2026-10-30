package com.ravi.apitesting.openapi;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenApiParserService {

    public List<ApiEndpoint> parse(String specification) {
        ParseOptions options = new ParseOptions();
        options.setResolve(true);

        SwaggerParseResult result =
                new OpenAPIParser().readContents(
                        specification, null, options);

        OpenAPI openAPI = result.getOpenAPI();

        if (openAPI == null || openAPI.getPaths() == null) {
            throw new IllegalArgumentException(
                    "Invalid OpenAPI specification: "
                            + result.getMessages());
        }

        List<ApiEndpoint> endpoints = new ArrayList<>();

        for (Map.Entry<String, PathItem> entry
                : openAPI.getPaths().entrySet()) {

            String path = entry.getKey();
            PathItem pathItem = entry.getValue();

            addOperation(endpoints, path, "GET",
                    pathItem.getGet());
            addOperation(endpoints, path, "POST",
                    pathItem.getPost());
            addOperation(endpoints, path, "PUT",
                    pathItem.getPut());
            addOperation(endpoints, path, "PATCH",
                    pathItem.getPatch());
            addOperation(endpoints, path, "DELETE",
                    pathItem.getDelete());
        }

        return endpoints;
    }

    private void addOperation(
            List<ApiEndpoint> endpoints,
            String path,
            String method,
            Operation operation) {

        if (operation == null) {
            return;
        }

        List<String> parameters = new ArrayList<>();

        if (operation.getParameters() != null) {
            for (Parameter parameter : operation.getParameters()) {
                parameters.add(
                        parameter.getName() + " (" +
                                parameter.getIn() + ")");
            }
        }

        List<String> responseCodes =
                operation.getResponses() == null
                        ? List.of()
                        : new ArrayList<>(
                        operation.getResponses().keySet());

        endpoints.add(new ApiEndpoint(
                path,
                method,
                operation.getOperationId(),
                operation.getSummary(),
                parameters,
                operation.getRequestBody() != null,
                responseCodes
        ));
    }
}