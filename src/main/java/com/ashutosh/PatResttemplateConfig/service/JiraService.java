package com.ashutosh.PatResttemplateConfig.service;

import com.ashutosh.PatResttemplateConfig.client.JiraClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiraService {
    @Autowired
    private JiraClient jiraClient;

    public String getUserByUserName(String userName) {
        return jiraClient.getUserByUserName(userName);
    }
}
