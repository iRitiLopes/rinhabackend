package br.com.richardlopes.rinhabackend.repositories.client

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class ClientEntityRowMapper: RowMapper<ClientEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): ClientEntity {
        return ClientEntity(
            id = rs.getLong("id"),
            credit = rs.getLong("credit"),
            balance = rs.getLong("balance")
        )
    }
}