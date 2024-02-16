package br.com.richardlopes.rinhabackend.repositories.transaction

import br.com.richardlopes.rinhabackend.exceptions.ClientNotFoundException
import br.com.richardlopes.rinhabackend.exceptions.InsufficientFundsException
import br.com.richardlopes.rinhabackend.models.ClientTransactions
import br.com.richardlopes.rinhabackend.models.Transaction
import br.com.richardlopes.rinhabackend.models.TransactionResult
import br.com.richardlopes.rinhabackend.repositories.client.ClientEntity
import org.springframework.dao.DataAccessException
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.relational.core.sql.LockMode
import org.springframework.data.relational.repository.Lock
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.Transactional

@Repository
class TransactionRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val rowMapper: RowMapper<TransactionEntity>,
    private val transactionResultRowMapper: RowMapper<TransactionResultEntity>
) {

    fun lockAndSave(transaction: Transaction): TransactionResult {
        try {
            return jdbcTemplate.query(
                """
                 SELECT validate_transaction(:clientId, ABS(:value), :description, :type);
            """.trimIndent(),
                mapOf(
                    "value" to transaction.value,
                    "clientId" to transaction.clientId,
                    "description" to transaction.description,
                    "type" to transaction.type.alias
                ),
                transactionResultRowMapper
            ).first().toModel()
        } catch (e: Exception) {
            if (e.message?.contains("Insufficient funds") == true) {
                throw InsufficientFundsException()
            }

            if (e.message?.contains("Client not found") == true) {
                throw ClientNotFoundException()
            }

            throw e
        }

    }

    fun findAllByClientId(clientId: Long): List<Transaction> {
        return jdbcTemplate.query(
            """
                SELECT * FROM client_transaction WHERE client_id = :clientId ORDER BY created_at DESC;
            """.trimIndent(),
            mapOf("clientId" to clientId),
            rowMapper
        ).map { it.toModel() }.toList()
    }

    fun clean() {
        jdbcTemplate.execute("DELETE FROM client_transaction") {
            it.execute()
        }
    }
}