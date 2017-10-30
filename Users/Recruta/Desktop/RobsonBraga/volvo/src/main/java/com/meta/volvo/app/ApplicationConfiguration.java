package com.meta.volvo.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@ComponentScan(basePackages = { "com.meta.volvo.services" })
@EnableJpaRepositories(basePackages = "com.meta.volvo.repositories")
@EntityScan(basePackages = { "com.meta.volvo.entities" })
@EnableSpringDataWebSupport
public class ApplicationConfiguration {

}
