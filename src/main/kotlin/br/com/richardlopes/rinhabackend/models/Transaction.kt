package br.com.richardlopes.rinhabackend.models

import br.com.richardlopes.rinhabackend.exceptions.InvalidDescriptionLengthException
import br.com.richardlopes.rinhabackend.exceptions.InvalidTransactionTypeException
import java.time.Instant

enum class TransactionType(
    var alias: Char
) {
    DEPOSIT('d'),
    CREDIT('c');

    companion object {
        fun fromChar(alias: Char): TransactionType {
            return entries.find { it.alias == alias } ?: throw InvalidTransactionTypeException()
        }
    }
}

class Transaction(
    var clientId: Long,
    var type: TransactionType,
    var value: Long,
    var description: String,
    val createdAt: Instant = Instant.now()
) {
    companion object {
        private fun valueBasedOnType(type: TransactionType, value: Long): Long {
            return when (type) {
                TransactionType.DEPOSIT -> -value
                TransactionType.CREDIT -> value
            }
        }
    }
    constructor(
        clientId: Long,
        type: Char,
        value: Long,
        description: String
    ) : this(
        clientId,
        TransactionType.fromChar(type),
        valueBasedOnType(TransactionType.fromChar(type), value),
        description
    )
}

data class TransactionResult(
    val credit: Long,
    val balance: Long
)