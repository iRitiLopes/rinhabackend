package br.com.richardlopes.rinhabackend.repositories.transaction

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class TransactionResultEntityRowMapper: RowMapper<TransactionResultEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): TransactionResultEntity {
        val values = rs.getString(1)
            .replace(")", "")
            .replace("(", "")
            .split(",")
            .map { it.toLong() }
        return TransactionResultEntity(
            credit = values[0],
            balance = values[1]
        )
    }
}
