package com.devops.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CONFIGURATION CORS (Cross-Origin Resource Sharing)
 *
 * CONCEPT DEVOPS IMPORTANT :
 * Le navigateur bloque par sécurité les requêtes entre deux origines différentes.
 *
 * Notre situation :
 *   - Front HTML tourne sur : http://localhost:5500
 *   - Back Spring Boot sur  : http://localhost:8080
 *
 * → Ce sont deux "origines" différentes (ports différents) !
 * → Sans cette config, le navigateur bloque toutes les requêtes du front vers le back.
 *
 * Cette classe autorise explicitement la communication entre ces deux ports.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:5500",    // VS Code Live Server
                        "http://127.0.0.1:5500",
                        "http://localhost:3000"     // Si React/Node front
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
