package com.ashutosh.PatResttemplateConfig.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JiraErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println(response);
        if(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining("\n"));
                String errorMessage = httpBodyResponse;
                ObjectMapper mapper = new ObjectMapper();
                JiraErrorResponse errorResponse = mapper.readValue(errorMessage, JiraErrorResponse.class);
                throw new JiraRestTemplateException(response.getStatusCode(), errorResponse.getMessage());
            }
        }
    }
}