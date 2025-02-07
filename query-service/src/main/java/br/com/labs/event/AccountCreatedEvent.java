package br.com.labs.event;

public class AccountCreatedEvent {
    private final String cpf;
    private final String nomeTitular;
    private final double initialBalance;

    public AccountCreatedEvent(String cpf, String nomeTitular, double initialBalance) {
        this.cpf = cpf;
        this.nomeTitular = nomeTitular;
        this.initialBalance = initialBalance;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public double getInitialBalance() {
        return initialBalance;
    }
}