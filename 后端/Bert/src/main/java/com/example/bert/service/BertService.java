package com.example.bert.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class BertService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String callFlaskApi(String inputValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //构造请求体
        String requestBody = "{\"input_value\": \"" + inputValue + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        //Flask接口的URL
        String url = "http://localhost:8802/get_result";
        //发送POST请求
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();

    }
}
