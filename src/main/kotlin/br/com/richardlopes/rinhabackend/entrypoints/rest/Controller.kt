package br.com.richardlopes.rinhabackend.entrypoints.rest

import br.com.richardlopes.rinhabackend.entrypoints.rest.dto.BalanceResponseDTO
import br.com.richardlopes.rinhabackend.entrypoints.rest.dto.ClientDTO
import br.com.richardlopes.rinhabackend.entrypoints.rest.dto.ClientExtractDTO
import br.com.richardlopes.rinhabackend.entrypoints.rest.dto.TransactionRequestDTO
import br.com.richardlopes.rinhabackend.repositories.client.ClientRepository
import br.com.richardlopes.rinhabackend.services.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
    private val clientRepository: ClientRepository,
    private val transactionService: TransactionService
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, World!"
    }

    @GetMapping("/all")
    fun all(): ResponseEntity<List<ClientDTO>> {
        val clients = clientRepository.findAll().map { ClientDTO(it) }
        return ResponseEntity.ok(clients)
    }

    @GetMapping("/client/{id}")
    fun findClientById(@PathVariable id: Long): ResponseEntity<ClientDTO> {
        return ResponseEntity.ok(ClientDTO(clientRepository.findById(id)))
    }

    @PostMapping("/clientes/{clientId}/transacoes")
    fun createTransaction(@PathVariable clientId: Long, @RequestBody request: TransactionRequestDTO): ResponseEntity<BalanceResponseDTO> {
        val transactionResult = transactionService.transact(request.toModel(clientId))
        return ResponseEntity.ok(BalanceResponseDTO(transactionResult.credit, transactionResult.balance))
    }

    @GetMapping("/clientes/{clientId}/extrato")
    fun extrato(@PathVariable clientId: Long): ResponseEntity<ClientExtractDTO> {
        val clientTransactions = transactionService.allTransactionsByClientId(clientId)
        return ResponseEntity.ok(ClientExtractDTO(clientTransactions))
    }

    @GetMapping("/limpar")
    fun limpar(): ResponseEntity<String> {
        clientRepository.deleteAll()
        transactionService.clean()
        return ResponseEntity.ok("Banco de dados limpo")
    }
}