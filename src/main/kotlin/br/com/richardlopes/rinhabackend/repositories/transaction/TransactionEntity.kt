package br.com.richardlopes.rinhabackend.repositories.transaction

import br.com.richardlopes.rinhabackend.models.Client
import br.com.richardlopes.rinhabackend.models.Transaction
import br.com.richardlopes.rinhabackend.models.TransactionType
import java.sql.Timestamp

class TransactionEntity(
    var clientId: Long,
    var type: String,
    var value: Long,
    var description: String,
    var createdAt: Timestamp
) {
    fun toModel() = Transaction(
        clientId = clientId,
        type = TransactionType.fromChar(type.single()),
        value = value,
        description = description,
        createdAt = createdAt.toInstant()
    )
}