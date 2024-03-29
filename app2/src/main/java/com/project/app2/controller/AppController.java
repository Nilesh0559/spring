package com.project.app2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to App";
    }
}
