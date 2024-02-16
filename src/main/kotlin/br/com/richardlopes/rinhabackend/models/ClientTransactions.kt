package br.com.richardlopes.rinhabackend.models

data class ClientTransactions(
    val client: Client,
    val transactions: List<Transaction>
) {
}