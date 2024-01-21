package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
    
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails userOne = User.withDefaultPasswordEncoder()
                .roles("admin")
                .username("test")
                .password("pw")
                .build();
        
        UserDetails userTwo = User.withDefaultPasswordEncoder()
                .roles("user")
                .username("test2")
                .password("pw2")
                .build();
        return new InMemoryUserDetailsManager(userOne, userTwo);
    }
}
