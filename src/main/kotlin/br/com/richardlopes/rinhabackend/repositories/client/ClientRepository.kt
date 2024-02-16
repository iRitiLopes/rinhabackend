package br.com.richardlopes.rinhabackend.repositories.client

import br.com.richardlopes.rinhabackend.exceptions.ClientNotFoundException
import br.com.richardlopes.rinhabackend.models.Client
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.NoSuchElementException

@Repository
class ClientRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val rowMapper: RowMapper<ClientEntity>
) {
    fun findAll(): List<Client> {
        return jdbcTemplate.query("SELECT * FROM client", rowMapper).map { it.toModel() }.toList()
    }

    fun findById(id: Long): Client {
        try {
            return jdbcTemplate.query(
                "SELECT * FROM client WHERE id = :id", mapOf("id" to id),
                rowMapper
            ).first().toModel()
        } catch (e: NoSuchElementException) {
            throw ClientNotFoundException()

        }
    }

    fun deleteAll() {
        jdbcTemplate.update("UPDATE client SET balance = 0", emptyMap<String, String>())
    }

}