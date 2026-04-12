package com.devops.demo.config;

import com.devops.demo.model.Produit;
import com.devops.demo.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DATA LOADER
 *
 * Ce composant s'exécute automatiquement au démarrage de l'application.
 * Il insère quelques produits de démonstration si la base est vide.
 *
 * Utile pour les démos et les tests !
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final ProduitRepository repository;

    public DataLoader(ProduitRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        // Insérer des données seulement si la table est vide
        if (repository.count() == 0) {
            repository.save(new Produit("Laptop Dell XPS",    1299.99, 10));
            repository.save(new Produit("Souris Logitech",      29.99, 50));
            repository.save(new Produit("Clavier Mécanique",   89.99, 25));
            repository.save(new Produit("Écran 27\" 4K",      399.99,  8));
            repository.save(new Produit("Câble USB-C",          9.99, 100));

            System.out.println("✅ Base de données initialisée avec 5 produits de démo.");
        } else {
            System.out.println("ℹ️  Base de données déjà initialisée (" + repository.count() + " produits).");
        }
    }
}
