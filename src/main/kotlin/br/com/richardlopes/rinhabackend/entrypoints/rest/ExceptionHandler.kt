package br.com.richardlopes.rinhabackend.entrypoints.rest

import br.com.richardlopes.rinhabackend.exceptions.*
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    companion object {
        val logger = KotlinLogging.logger {}
    }

    @ExceptionHandler(*[
        InvalidTransactionTypeException::class,
        InvalidDescriptionLengthException::class,
        InsufficientFundsException::class,
        InvalidBodyException::class
    ])
    fun invalidTransactionType(e: Exception): ResponseEntity<Unit> {
        logger.info { "Invalid transaction type ${e.message}" }
        return ResponseEntity.status(422).build()
    }

    @ExceptionHandler(ClientNotFoundException::class)
    fun clientNotFound(): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }
}