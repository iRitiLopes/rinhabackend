package br.com.richardlopes.rinhabackend.repositories.transaction

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class TransactionEntityRowMapper: RowMapper<TransactionEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): TransactionEntity {
        return TransactionEntity(
            value = rs.getLong("amount"),
            type = rs.getString("transaction_type"),
            description = rs.getString("description"),
            clientId = rs.getLong("client_id"),
            createdAt = rs.getTimestamp("created_at")
        )
    }
}
