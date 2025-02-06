package br.com.labs.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WithdrawMoneyCommand {

    @TargetAggregateIdentifier
    private final String cpf;
    private final double amount;


    public WithdrawMoneyCommand(String cpf, double amount) {
        this.cpf = cpf;
        this.amount = amount;
    }

    public String getCpf() {
        return cpf;
    }

    public double getAmount() {
        return amount;
    }
}
