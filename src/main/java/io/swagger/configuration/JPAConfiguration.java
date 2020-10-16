package io.swagger.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"io.swagger.model.db"})
@EnableJpaRepositories({"io.swagger.api.repositories"})
@EnableTransactionManagement
@EnableJpaAuditing
public class JPAConfiguration {
}