package org.drombler.eventcenter.integration.persistence.impl;

import org.drombler.identity.management.DromblerIdentityProviderManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
//@EnableTransactionManagement
@EnableJpaAuditing
//@EnableJpaRepositories
public class EventCenterPersistenceConfig {

    @Bean
    public DromblerIdentityProviderManager dromblerIdentityProviderManager() {
        return new DromblerIdentityProviderManager();
    }
}
