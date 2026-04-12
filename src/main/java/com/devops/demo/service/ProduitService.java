package com.devops.demo.service;

import com.devops.demo.model.Produit;
import com.devops.demo.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE (Logique métier)
 *
 * Le service fait le lien entre le Controller (HTTP) et le Repository (BD).
 * C'est ici qu'on met les règles métier (calculs, validations, transformations).
 *
 * Flux de données :
 *   Requête HTTP → Controller → Service → Repository → PostgreSQL
 *   PostgreSQL   → Repository → Service → Controller → Réponse HTTP
 */
@Service
public class ProduitService {

    private final ProduitRepository repository;

    // Injection de dépendance par constructeur (bonne pratique)
    public ProduitService(ProduitRepository repository) {
        this.repository = repository;
    }

    // ---- READ ----

    public List<Produit> findAll() {
        return repository.findAll();
    }

    public Optional<Produit> findById(Long id) {
        return repository.findById(id);
    }

    public List<Produit> search(String keyword) {
        return repository.findByNomContainingIgnoreCase(keyword);
    }

    // ---- CREATE ----

    public Produit create(Produit produit) {
        return repository.save(produit);
    }

    // ---- UPDATE ----

    public Optional<Produit> update(Long id, Produit nouvelleDonnee) {
        return repository.findById(id).map(existant -> {
            existant.setNom(nouvelleDonnee.getNom());
            existant.setPrix(nouvelleDonnee.getPrix());
            existant.setQuantite(nouvelleDonnee.getQuantite());
            return repository.save(existant);
        });
    }

    // ---- DELETE ----

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
