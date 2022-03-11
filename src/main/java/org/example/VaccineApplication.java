package org.example;

import org.example.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class VaccineApplication {
    public static void main(String[] args) {
        SpringApplication.run(VaccineApplication.class, args);
    }
}

