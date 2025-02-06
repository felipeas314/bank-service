package br.com.labs.controller;

import br.com.labs.command.CreateAccountCommand;
import br.com.labs.dto.CreateAccountRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final CommandGateway commandGateway;

    public AccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequest request) {
        // Monta o comando para criar a conta
        CreateAccountCommand command = new CreateAccountCommand(
                request.getCpf(),
                request.getNomeTitular()
        );

        // Envia comando via Axon
        return commandGateway.send(command);
    }
}
