package com.soprasteria.multientitymanager.config;

import com.soprasteria.multientitymanager.model.Utilisateur;
import com.soprasteria.multientitymanager.repository.input.UtilisateurInputRepository;
import com.soprasteria.multientitymanager.util.LiquibaseUtil;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = UtilisateurInputRepository.class,
        entityManagerFactoryRef = "inputEntityManagerFactory",
        transactionManagerRef = "inputTransactionManager"
)
public class InputPersistenceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.input")
    public DataSourceProperties inputDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.input.hikari")
    public DataSource inputDataSource(@Qualifier("inputDataSourceProperties") DataSourceProperties inputDataSourceProperties) {
        return inputDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean inputEntityManagerFactory(@Qualifier("inputDataSource") DataSource inputDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(inputDataSource)
                .packages(Utilisateur.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager inputTransactionManager(@Qualifier("inputEntityManagerFactory") LocalContainerEntityManagerFactoryBean inputEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(inputEntityManagerFactory.getObject()));
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.input.liquibase")
    public LiquibaseProperties inputLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase inputLiquibase(
            @Qualifier("inputDataSource") DataSource dataSource,
            @Qualifier("inputLiquibaseProperties") LiquibaseProperties properties
    ) {
        return LiquibaseUtil.springLiquibase(dataSource, properties);
    }
}
