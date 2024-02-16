package br.com.richardlopes.rinhabackend.repositories.client

import br.com.richardlopes.rinhabackend.models.Client

data class ClientEntity(
    val id: Long,
    val credit: Long,
    val balance: Long
) {
    fun toModel() = Client(
        id = id,
        credit = credit,
        balance = balance
    )
}
