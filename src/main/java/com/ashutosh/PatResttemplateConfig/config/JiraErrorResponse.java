package com.ashutosh.PatResttemplateConfig.config;

public class JiraErrorResponse {

    private int statusCode;
    private String error;
    private String message;

    public JiraErrorResponse(int statusCode, String error, String message) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
    }

    public JiraErrorResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
