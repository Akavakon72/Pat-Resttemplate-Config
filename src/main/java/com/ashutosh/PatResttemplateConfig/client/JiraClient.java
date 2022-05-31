package com.ashutosh.PatResttemplateConfig.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JiraClient {

    @Autowired
    JiraRestTemplateClient jiraRestTemplateClient;

    public String getUserByUserName(String username) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("username", username);
        String response = jiraRestTemplateClient.getObject("user", null, String.class, queryParams,
                new HashMap<>());
        return response;
    }
}
