package xyz.mgsip.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MgsipBackendApplication

fun main(args: Array<String>) {
    runApplication<MgsipBackendApplication>(*args)
}
