package com.finance.dailystockdatamicroservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/v1")
public class TestController {
    @GetMapping("/healthok")
    public String apiTest() {
        System.out.println("Test OK!");
        return "Test OK!";
    }
}
