package com.devops.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Spring Boot.
 *
 * Au démarrage, Spring Boot :
 *  1. Lance un serveur Tomcat embarqué sur le PORT 8080
 *  2. Se connecte à PostgreSQL sur le PORT 5432
 *  3. Expose les routes REST définies dans les Controllers
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
