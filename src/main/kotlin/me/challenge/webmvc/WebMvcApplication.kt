package me.challenge.webmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebMvcApplication

fun main(args: Array<String>) {
	runApplication<WebMvcApplication>(*args)
}