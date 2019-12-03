package com.prodyna.sample.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @Value("${name-service.url}")
    private String nameServiceUrl;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String greeting() {
        ResponseEntity<String> response = restTemplate.getForEntity(nameServiceUrl + "/name", String.class);
        return String.format(template, response.getBody());
    }
}