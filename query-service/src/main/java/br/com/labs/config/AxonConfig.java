package br.com.labs.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    // omitting other configuration methods...
    @Bean
    public TokenStore tokenStore(EntityManagerProvider entityManagerProvider,
                                 Serializer serializer,
                                 TransactionManager transactionManager) {
        return JpaTokenStore.builder()
                .entityManagerProvider(entityManagerProvider)
                .serializer(serializer)
                .build();
    }
}
