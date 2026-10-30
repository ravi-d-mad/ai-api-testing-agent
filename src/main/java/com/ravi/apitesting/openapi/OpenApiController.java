package com.ravi.apitesting.openapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
public class OpenApiController {

    private final OpenApiParserService parserService;

    public OpenApiController(OpenApiParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping(
            value = "/parse",
            consumes = MediaType.TEXT_PLAIN_VALUE
    )
    public List<ApiEndpoint> parse(
            @RequestBody String specification) {
        return parserService.parse(specification);
    }
}
