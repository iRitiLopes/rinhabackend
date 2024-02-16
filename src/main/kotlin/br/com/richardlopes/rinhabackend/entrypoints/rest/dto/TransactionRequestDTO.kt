package br.com.richardlopes.rinhabackend.entrypoints.rest.dto

import br.com.richardlopes.rinhabackend.exceptions.InvalidBodyException
import br.com.richardlopes.rinhabackend.exceptions.InvalidDescriptionLengthException
import br.com.richardlopes.rinhabackend.models.Transaction

data class TransactionRequestDTO(
    val valor: String?,
    val tipo: Char?,
    val descricao: String?
) {
    fun toModel(clientId: Long): Transaction {
        validate()
        return Transaction(
            clientId = clientId,
            type = tipo!!,
            value = valor?.toLong()!!,
            description = descricao!!
        )
    }

    private fun validate() {
        if (valor == null ) {
            throw InvalidBodyException()
        }

        if (!valor.all { it.isDigit() }) {
            throw InvalidBodyException()
        }

        if (tipo == null) {
            throw InvalidBodyException()
        }

        validateDescription()
    }

    private fun validateDescription() {
        if(descricao.isNullOrBlank()){
            throw InvalidDescriptionLengthException()
        }
        if (descricao.length in 1..10) {
            return
        }
        throw InvalidDescriptionLengthException()
    }
}