package br.com.richardlopes.rinhabackend

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestRinhaBackendApplication {


}

fun main(args: Array<String>) {
    fromApplication<RinhaBackendApplication>().with(TestRinhaBackendApplication::class).run(*args)
}
