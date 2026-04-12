package com.devops.demo.repository;

import com.devops.demo.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * REPOSITORY (Couche d'accès aux données)
 *
 * Cette interface fait le pont entre Java et PostgreSQL.
 * Spring Data JPA génère automatiquement le SQL pour :
 *   - findAll()     → SELECT * FROM produit
 *   - findById(id)  → SELECT * FROM produit WHERE id = ?
 *   - save(produit) → INSERT INTO / UPDATE produit ...
 *   - deleteById()  → DELETE FROM produit WHERE id = ?
 *
 * On peut aussi écrire des requêtes personnalisées.
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    // Spring génère automatiquement : SELECT * FROM produit WHERE nom LIKE %keyword%
    List<Produit> findByNomContainingIgnoreCase(String keyword);

    // Requête JPQL personnalisée
    @Query("SELECT p FROM Produit p WHERE p.prix <= :prixMax ORDER BY p.prix ASC")
    List<Produit> findByPrixMaximum(Double prixMax);
}
