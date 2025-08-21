package com.lab.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloWorldWebappApplication

fun main(args: Array<String>) {
    runApplication<HelloWorldWebappApplication>(*args)
}
