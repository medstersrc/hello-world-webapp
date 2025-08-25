package com.lab.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    val logger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(HelloWorldController::class.java)

    @GetMapping("/hello")
    fun hello(@RequestParam name: String): String {
        logger.info("Received request for name={}", name)
       // logger.info("Received hello request with payload: {}", request)
        return "Hello $name!"
    }

    @GetMapping("/bad/logging")
    fun badLogging(@RequestParam name: String): String {
        val requestPayload = mapOf("name" to name, "email" to "$name@mail.com")
        logger.info("Payload: {}", requestPayload) // bad: logs sensitive data
        return "bad"
    }

    @GetMapping("/good/redacted")
    fun goodLogging(@RequestParam name: String): String {
        val payload = mapOf("name" to name, "email" to "$name@mail.com")
        val safePayload = payload - "email"
        logger.info("Payload received: {}", safePayload)
        return "good"
    }

}