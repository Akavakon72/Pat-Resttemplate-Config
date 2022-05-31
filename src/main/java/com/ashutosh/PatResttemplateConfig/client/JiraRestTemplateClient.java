package com.ashutosh.PatResttemplateConfig.client;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@Service
public class JiraRestTemplateClient {

    @Autowired
    @Qualifier("jiraRestTemplate")
    private RestTemplate jiraRestTemplate;

    @Value("${jira.access.token}")
    private String token;

    @Value("${jira.base.url}")
    private String baseUrl;


    public HttpHeaders createHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Content-Type", "application/json");
        return httpHeaders;
    }

    public String createUrl(String url, Map<String, ?> queryParams) {
        UriComponentsBuilder urlTemplate = UriComponentsBuilder.fromHttpUrl(baseUrl + url);
        if(queryParams != null && queryParams.size()>0){
            for (Map.Entry<String,?> entry: queryParams.entrySet()) {
                urlTemplate.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return urlTemplate.encode().toUriString();
    }

    public <REQ, T> List<T> getObjects(String url, REQ req, Class<T> response, Map<String, ?> queryParams,
                                       Map<String, String> params) {
        List<T> responseObj = null;
        String requestUrl = createUrl(url, queryParams);
        HttpHeaders requestHeader = createHttpHeader();
        HttpEntity<REQ> reqHttpEntity = new HttpEntity<>(requestHeader);
        ResponseEntity<List<T>> responseEntity = jiraRestTemplate.exchange(requestUrl, HttpMethod.GET, reqHttpEntity,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, response)), params);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            responseObj = responseEntity.getBody();
        }
        return responseObj;
    }

    public <REQ, T> T getObject(String url, REQ req, Class<T> response, Map<String, ?> queryParams,
                                       Map<String, String> params) {
        T responseObj = null;
        String requestUrl = createUrl(url, queryParams);
        HttpHeaders requestHeader = createHttpHeader();
        HttpEntity<REQ> reqHttpEntity = new HttpEntity<>(requestHeader);
        ResponseEntity<T> responseEntity = jiraRestTemplate.exchange(requestUrl, HttpMethod.GET, reqHttpEntity,
                response, params);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            responseObj = responseEntity.getBody();
        }
        return responseObj;
    }
}
