package br.com.labs.aggregate;

import br.com.labs.command.CreateAccountCommand;
import br.com.labs.command.DepositMoneyCommand;
import br.com.labs.command.WithdrawMoneyCommand;
import br.com.labs.event.AccountCreatedEvent;
import br.com.labs.event.MoneyDepositedEvent;
import br.com.labs.event.MoneyWithdrawnEvent;
import br.com.labs.exception.InsufficientBalanceException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String cpf;          // ID do agregado
    private String nomeTitular;
    private double balance;

    // Construtor vazio exigido pelo Axon
    public AccountAggregate() {}

    // Command Handlers
    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd) {
        System.out.println("1");
        // Aqui poderíamos validar se o CPF já existe
        // mas isso normalmente exige uma checagem no Query side ou
        // alguma consistência externa.
        // Para simplificar, assumimos que esse ID será único.

        apply(new AccountCreatedEvent(
                cmd.getCpf(),
                cmd.getNomeTitular(),
                0.0 // saldo inicial zero (por exemplo)
        ));
    }

    @CommandHandler
    public void handle(DepositMoneyCommand cmd) {
        System.out.println("4");
        if (cmd.getAmount() <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        apply(new MoneyDepositedEvent(cmd.getCpf(), cmd.getAmount()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand cmd) {
        System.out.println("5");
        if (cmd.getAmount() <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (cmd.getAmount() > balance) {
            throw new InsufficientBalanceException("Saldo insuficiente para saque.");
        }
        apply(new MoneyWithdrawnEvent(cmd.getCpf(), cmd.getAmount()));
    }

    // Event Sourcing Handlersß
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        System.out.println("6");
        this.cpf = event.getCpf();
        this.nomeTitular = event.getNomeTitular();
        this.balance = event.getInitialBalance();
    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) {
        System.out.println("7");
        this.balance += event.getAmount();
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event) {
        System.out.println("8");
        this.balance -= event.getAmount();
    }
}
