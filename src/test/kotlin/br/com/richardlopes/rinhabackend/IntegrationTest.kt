package br.com.richardlopes.rinhabackend

import br.com.richardlopes.rinhabackend.repositories.client.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntegrationTest {
    @Autowired
    lateinit var clientRepository: ClientRepository
}