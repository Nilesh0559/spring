package com.project.app3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from App";
    }

    @GetMapping("bye")
    public String bye() {
        return "Get lost from App";
    }
}
