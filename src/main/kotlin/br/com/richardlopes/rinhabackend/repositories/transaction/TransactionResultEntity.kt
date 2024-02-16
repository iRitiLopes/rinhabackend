package br.com.richardlopes.rinhabackend.repositories.transaction

import br.com.richardlopes.rinhabackend.models.TransactionResult

data class TransactionResultEntity(
    val credit: Long,
    val balance: Long
) {
    fun toModel() = TransactionResult(
        credit = credit,
        balance = balance
    )
}