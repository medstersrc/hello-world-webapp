package com.lab.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/")
    fun hello(): String {
        return "Hello World From Spring Boot!"
    }
}