package org.example.resourcesservice;

import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableMethodSecurity
@SpringBootApplication
public class ResourcesServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ResourcesServiceApplication.class, args);
    }
    
}


@Service
class GreetingsService{
    
    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public Map<String, String> greet(){
        Jwt principal = (Jwt)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return Map.of("message", "hello " + principal.getSubject());
    }
}

@RestController
class GreetingsController {
    
    private final GreetingsService service;
    
    GreetingsController(GreetingsService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    Map<String, String> hello(@AuthenticationPrincipal Jwt jwt) {
        // return Map.of("message", "hello " + jwt.getSubject());
        return service.greet();
    }
}