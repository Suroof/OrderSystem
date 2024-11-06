package com.itmk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@Data
@Configuration
@ConfigurationProperties(prefix = "kimi.api")
public class KimiConfig {
    private String key;
    private String url;

    @PostConstruct
    public void validate() {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalStateException("Kimi API key is not configured");
        }
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalStateException("Kimi API URL is not configured");
        }

        // 验证 URL 格式
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                throw new IllegalStateException("Kimi API URL must start with http:// or https://");
            }
            new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Invalid Kimi API URL: " + e.getMessage());
        }
    }
}