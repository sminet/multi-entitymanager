package com.soprasteria.multientitymanager.config;

import com.soprasteria.multientitymanager.model.Utilisateur;
import com.soprasteria.multientitymanager.repository.output.UtilisateurOutputRepository;
import com.soprasteria.multientitymanager.util.LiquibaseUtil;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackageClasses = UtilisateurOutputRepository.class,
        entityManagerFactoryRef = "outputEntityManagerFactory",
        transactionManagerRef = "outputTransactionManager"
)
public class OutputPersistenceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.output")
    public DataSourceProperties outputDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.output.hikari")
    public DataSource outputDataSource(@Qualifier("outputDataSourceProperties") DataSourceProperties outputDataSourceProperties) {
        return outputDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean outputEntityManagerFactory(@Qualifier("outputDataSource") DataSource outputDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(outputDataSource)
                .packages(Utilisateur.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager outputTransactionManager(@Qualifier("outputEntityManagerFactory") LocalContainerEntityManagerFactoryBean outputEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(outputEntityManagerFactory.getObject()));
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.output.liquibase")
    public LiquibaseProperties outputLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase outputLiquibase(
            @Qualifier("outputDataSource") DataSource dataSource,
            @Qualifier("outputLiquibaseProperties") LiquibaseProperties properties
    ) {
        return LiquibaseUtil.springLiquibase(dataSource, properties);
    }
}
