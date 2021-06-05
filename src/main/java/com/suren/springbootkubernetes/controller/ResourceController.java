package com.suren.springbootkubernetes.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/print")
    public String printHelloKubernetes() {
        return "Hello - Kubernetes";
    }
}
