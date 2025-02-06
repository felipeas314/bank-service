package br.com.labs.event;

public class MoneyDepositedEvent {

    private final String cpf;
    private final double amount;

    public MoneyDepositedEvent(String cpf, double amount) {
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
