package com.encora.todoback.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {

    @Autowired
    private TestRestTemplate template;


    @Test
    void getTodos() {
        setup();
        String response = template.getForObject("/todos", String.class);
    }

    void setup() {

    }
}
