package com.ashutosh.PatResttemplateConfig.controller;

import com.ashutosh.PatResttemplateConfig.service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class JiraController {

    @Autowired
    private JiraService jiraService;

    @GetMapping("user")
    public String getUserByUserId(
            @RequestParam(value = "username") String username) {

        Objects.requireNonNull(username, "username cannot be null");
        String response = jiraService.getUserByUserName(username);
        return response;
    }
}
