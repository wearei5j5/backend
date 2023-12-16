package org.dev.otte

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@OpenAPIDefinition(
    servers = [Server(url = "/", description = "Default Server url")]
)
@SpringBootApplication
@ConfigurationPropertiesScan
class OtteApplication

fun main(args: Array<String>) {
    runApplication<OtteApplication>(*args)
}
