package com.ravi.apitesting.service;

import com.ravi.apitesting.dto.GeneratedTestScenarios;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiScenarioGenerationService {

    private final ChatClient chatClient;

    public AiScenarioGenerationService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public GeneratedTestScenarios generateScenarios(String openApiSpec) {

        return chatClient.prompt()
                .system("""
                    You are an expert API test automation engineer.

                    Analyze the supplied OpenAPI specification.

                    Generate positive, negative and boundary-value
                    test scenarios for each supported endpoint.

                    Rules:
                    1. Generate valid HTTP methods and paths.
                    2. Use the documented request schema.
                    3. Include expected HTTP status codes.
                    4. Generate realistic request bodies where required.
                    5. Do not invent undocumented endpoints.
                    6. Do not generate DELETE or PUT requests for now.
                    7. Return only structured scenario data.
                    """)
                .user(openApiSpec)
                .call()
                .entity(GeneratedTestScenarios.class,
                        spec -> spec.validateSchema());
    }
}
