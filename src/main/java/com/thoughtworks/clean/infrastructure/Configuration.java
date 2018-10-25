package com.thoughtworks.clean.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.util.Map;

public class Configuration {

    private final String environment;
    private final int port;
    private final ObjectMapper objectMapper;
    private final ResponseTransformer jsonTransformer;

    public Configuration(Map<String, String> params) {
        this.environment = params.getOrDefault("ENV", "dev");
        this.port = Integer.parseInt(params.getOrDefault("PORT", "4567"));
        this.objectMapper = new ObjectMapper();
        this.jsonTransformer = objectMapper::writeValueAsString;
    }

    public boolean isProdEnvironment() {
        return "prod".equalsIgnoreCase(environment);
    }

    public int getPort() {
        return this.port;
    }

    public ResponseTransformer getJsonTransformer() {
        return jsonTransformer;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
