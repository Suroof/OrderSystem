package com.itmk.web.kimi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmk.config.KimiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class KimiService {
    @Autowired(required = false)
    private KimiConfig kimiConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public String chatWithKimi(String message) {
        try {
            log.info("Starting chat with message: {}", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + kimiConfig.getKey());

            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "moonshot-v1-8k");
            requestBody.put("messages", Arrays.asList(
                    new HashMap<String, String>() {{
                        put("role", "user");
                        put("content", message);
                    }}
            ));

            // 打印请求信息用于调试
            log.info("Request URL: {}", kimiConfig.getUrl());
            log.info("Request Headers: {}", headers);
            log.info("Request Body: {}", objectMapper.writeValueAsString(requestBody));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    kimiConfig.getUrl(),
                    entity,
                    String.class
            );

            log.info("Response Status: {}", response.getStatusCode());
            log.info("Response Body: {}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            log.error("Error in chatWithKimi: ", e);
            throw new RuntimeException("Chat service error: " + e.getMessage());
        }
    }
}
