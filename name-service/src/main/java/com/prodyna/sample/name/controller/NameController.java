package com.prodyna.sample.name.controller;

import com.github.javafaker.Faker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
    private static final Faker faker = new Faker();

    @RequestMapping("/name")
    public String greeting() {
        return faker.name().fullName();
    }
}