package br.com.richardlopes.rinhabackend.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@Configuration
class PostgresDatasourceConfiguration {
    @Bean
    fun postgresDataSource() = HikariDataSource(postgresDatasourceProperties())

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun postgresDatasourceProperties() = HikariConfig()
}

