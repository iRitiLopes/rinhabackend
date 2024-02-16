package br.com.richardlopes.rinhabackend.entrypoints.rest.dto

import br.com.richardlopes.rinhabackend.models.ClientTransactions
import br.com.richardlopes.rinhabackend.models.Transaction
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class ClientExtractDTO(
    val saldo: SaldoDTO,

    @JsonProperty("ultimas_transacoes")
    val ultimasTransacoes: List<TransactionDTO>
) {
    constructor(clientTransactions: ClientTransactions): this(
        saldo = SaldoDTO(
            total = clientTransactions.client.balance,
            limite = clientTransactions.client.credit
        ),
        ultimasTransacoes = clientTransactions.transactions.map { TransactionDTO(it) }
    )
}


data class SaldoDTO(
    val total: Long,
    val limite: Long,

    @JsonProperty("data_extrato")
    val dataExtrato: Instant = Instant.now()
)

data class TransactionDTO(
    val tipo: Char,
    val valor: Long,
    val descricao: String,

    @JsonProperty("realizada_em")
    val realizadaEm: Instant
) {
    constructor(transaction: Transaction) : this(
        tipo = transaction.type.alias,
        valor = transaction.value,
        descricao = transaction.description,
        realizadaEm = transaction.createdAt
    )
}