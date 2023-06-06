package com.soprasteria.multientitymanager.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {
    @Bean
    @Primary
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier("inputTransactionManager") PlatformTransactionManager input,
            @Qualifier("outputTransactionManager") PlatformTransactionManager output) {
        return new ChainedTransactionManager(input, output);
    }
}
