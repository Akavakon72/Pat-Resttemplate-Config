package com.ashutosh.PatResttemplateConfig.config;

import org.springframework.http.HttpStatus;

public class JiraRestTemplateException extends RuntimeException{

    private HttpStatus statusCode;
    private String message;

    public JiraRestTemplateException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
