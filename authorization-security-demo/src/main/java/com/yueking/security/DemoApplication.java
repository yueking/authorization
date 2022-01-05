package com.yueking.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
// @SpringBootApplication(scanBasePackages="com.yueking.*")
// @EntityScan(basePackages = "com.yueking.security.core.entity")
// @EnableJpaRepositories(basePackages = "com.yueking.security.core.repository")
// @ComponentScan(basePackages = {"com.yueking.security.core.service","com.yueking.security.core.base"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
