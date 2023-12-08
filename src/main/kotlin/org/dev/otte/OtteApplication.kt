package org.dev.otte

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OtteApplication

fun main(args: Array<String>) {
    runApplication<OtteApplication>(*args)
}
