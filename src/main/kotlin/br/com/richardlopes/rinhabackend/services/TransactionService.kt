package br.com.richardlopes.rinhabackend.services

import br.com.richardlopes.rinhabackend.models.ClientTransactions
import br.com.richardlopes.rinhabackend.models.Transaction
import br.com.richardlopes.rinhabackend.models.TransactionResult
import br.com.richardlopes.rinhabackend.repositories.client.ClientRepository
import br.com.richardlopes.rinhabackend.repositories.transaction.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val clientRepository: ClientRepository,
    private val transactionRepository: TransactionRepository
) {

    fun transact(transaction: Transaction): TransactionResult {
        return transactionRepository.lockAndSave(transaction)
    }

    fun allTransactionsByClientId(clientId: Long): ClientTransactions {
        val transactions = transactionRepository.findAllByClientId(clientId)
        val client = clientRepository.findById(clientId)
        return ClientTransactions(client, transactions)
    }

    fun clean() {
        transactionRepository.clean()
    }
}