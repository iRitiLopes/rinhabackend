package br.com.richardlopes.rinhabackend.entrypoints.rest.dto

data class BalanceResponseDTO(
    val limite: Long,
    val saldo: Long
) {
}