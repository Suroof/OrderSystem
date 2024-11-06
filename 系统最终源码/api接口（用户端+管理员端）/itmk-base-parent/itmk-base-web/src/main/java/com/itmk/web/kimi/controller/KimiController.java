package com.itmk.web.kimi.controller;

import com.itmk.web.kimi.service.KimiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/kimi")
public class KimiController {

    @Autowired
    private KimiService kimiService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String, String> request) {
        try {
            log.info("Received chat request: {}", request);

            // 验证请求
            if (!request.containsKey("message")) {
                return new ResponseEntity<>("Missing 'message' field in request", HttpStatus.BAD_REQUEST);
            }

            String message = request.get("message");
            if (message == null || message.trim().isEmpty()) {
                return new ResponseEntity<>("Message cannot be empty", HttpStatus.BAD_REQUEST);
            }

            // 调用服务
            String response = kimiService.chatWithKimi(message);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error processing chat request: ", e);
            String errorMessage = String.format("Error: %s - %s", e.getClass().getSimpleName(), e.getMessage());
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}