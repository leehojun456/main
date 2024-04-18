package xyz.mgsip.main

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@MapperScan("xyz.mgsip.main.dao")
@SpringBootApplication
class MgsipBackendApplication

fun main(args: Array<String>) {
    runApplication<MgsipBackendApplication>(*args)
}
