package com.devops.demo.controller;

import com.devops.demo.model.Produit;
import com.devops.demo.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER (Couche HTTP)
 *
 * Ce composant écoute les requêtes HTTP sur le PORT 8080.
 * Il définit les routes (endpoints) de notre API REST.
 *
 * BASE URL : http://localhost:8080/api/produits
 *
 * ┌─────────────────────────────────────────────────────────┐
 * │  Méthode │ URL                    │ Action               │
 * ├──────────┼────────────────────────┼──────────────────────┤
 * │  GET     │ /api/produits          │ Lister tous          │
 * │  GET     │ /api/produits/{id}     │ Trouver par ID       │
 * │  GET     │ /api/produits/search   │ Rechercher           │
 * │  POST    │ /api/produits          │ Créer un nouveau     │
 * │  PUT     │ /api/produits/{id}     │ Modifier             │
 * │  DELETE  │ /api/produits/{id}     │ Supprimer            │
 * └─────────────────────────────────────────────────────────┘
 */
@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    // ================================================================
    //  GET /api/produits
    //  Retourne la liste complète des produits (HTTP 200)
    // ================================================================
    @GetMapping
    public ResponseEntity<List<Produit>> getAll() {
        List<Produit> produits = service.findAll();
        return ResponseEntity.ok(produits);   // HTTP 200 OK
    }

    // ================================================================
    //  GET /api/produits/{id}
    //  Retourne un produit par ID (200) ou erreur (404)
    // ================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)                                     // HTTP 200
                .orElse(ResponseEntity.notFound().build());                  // HTTP 404
    }

    // ================================================================
    //  GET /api/produits/search?q=laptop
    //  Recherche par nom (insensible à la casse)
    // ================================================================
    @GetMapping("/search")
    public ResponseEntity<List<Produit>> search(@RequestParam String q) {
        List<Produit> resultats = service.search(q);
        return ResponseEntity.ok(resultats);  // HTTP 200
    }

    // ================================================================
    //  POST /api/produits
    //  Crée un nouveau produit — body JSON requis
    //  Exemple body : { "nom": "Laptop", "prix": 999.99, "quantite": 5 }
    // ================================================================
    @PostMapping
    public ResponseEntity<Produit> create(@Valid @RequestBody Produit produit) {
        Produit nouveau = service.create(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);  // HTTP 201
    }

    // ================================================================
    //  PUT /api/produits/{id}
    //  Met à jour un produit existant (200) ou erreur (404)
    // ================================================================
    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(
            @PathVariable Long id,
            @Valid @RequestBody Produit produit) {
        return service.update(id, produit)
                .map(ResponseEntity::ok)                                     // HTTP 200
                .orElse(ResponseEntity.notFound().build());                  // HTTP 404
    }

    // ================================================================
    //  DELETE /api/produits/{id}
    //  Supprime un produit (204) ou erreur (404)
    // ================================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();  // HTTP 204 No Content
        }
        return ResponseEntity.notFound().build();       // HTTP 404
    }
}
