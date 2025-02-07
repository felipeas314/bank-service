package br.com.labs.projection;

import br.com.labs.event.AccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountProjection {

    public AccountProjection() {}

    @EventHandler
    public void on(AccountCreatedEvent event) {
        // Cria documento no Mongo
        System.out.println("Teste");
    }
}
