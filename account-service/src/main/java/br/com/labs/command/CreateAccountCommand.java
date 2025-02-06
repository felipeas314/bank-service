package br.com.labs.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private final String cpf;

    private final String nomeTitular;

    public CreateAccountCommand(String cpf, String nomeTitular) {
        this.cpf = cpf;
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }
}
