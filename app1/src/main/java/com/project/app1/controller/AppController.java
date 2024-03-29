package com.project.app1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/api1")
    public String api1() {
        return "Hello from API 1";
    }

    @GetMapping("/api2")
    public String api2() {
        return "Hello from API 2";
    }

    @GetMapping("/api3")
    public String api3() {
        return "Hello from API 3";
    }
}
