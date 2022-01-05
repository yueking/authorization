package com.yueking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages="com.yueking.*")

// @EntityScan(basePackages = "com.yueking.security.core.entity")
// @EnableJpaRepositories(basePackages = "com.yueking.security.core.repository")
// @ComponentScan(basePackages = {"com.yueking.security.core.service"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
