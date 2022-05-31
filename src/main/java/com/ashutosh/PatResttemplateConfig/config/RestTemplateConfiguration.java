package com.ashutosh.PatResttemplateConfig.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class RestTemplateConfiguration {

    @Bean(name= "jiraRestTemplate")
    public RestTemplate jiraRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000))
                .errorHandler(new JiraErrorHandler())
                .build();
    }
}
