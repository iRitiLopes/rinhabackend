package br.com.richardlopes.rinhabackend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.TransactionManager
import javax.sql.DataSource

@Configuration
@EnableJdbcRepositories
class DatabaseConfiguration {

    @Bean
    fun namedParameterJdbcOperations(dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    fun transactionManager(dataSource: DataSource): TransactionManager {
        return DataSourceTransactionManager(dataSource)
    }
}