package com.sigsauer.zyxit.configuration;

import com.sigsauer.zyxit.domain.User;
import com.sigsauer.zyxit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> userRepository.save(new User("admin", "admin"));
        }
    }
