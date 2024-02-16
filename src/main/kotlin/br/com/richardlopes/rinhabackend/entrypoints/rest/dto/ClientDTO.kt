package br.com.richardlopes.rinhabackend.entrypoints.rest.dto

import br.com.richardlopes.rinhabackend.models.Client

data class ClientDTO(
    val id: Long,
    val credit: Long,
    val balance: Long
) {
    constructor(
        model: Client
    ) : this(
        id = model.id,
        credit = model.credit,
        balance = model.balance
    )
}