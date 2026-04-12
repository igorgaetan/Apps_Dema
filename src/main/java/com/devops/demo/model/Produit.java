package com.devops.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * MODÈLE (Entity)
 *
 * Cette classe est mappée directement sur une table PostgreSQL.
 * JPA/Hibernate se charge de créer la table automatiquement.
 *
 * Table générée : produit (id, nom, prix, quantite)
 */
@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO-INCREMENT en PostgreSQL
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    @Column(nullable = false)
    private Double prix;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    @Column(nullable = false)
    private Integer quantite;

    // ---- Constructeurs ----

    public Produit() {}

    public Produit(String nom, Double prix, Integer quantite) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    // ---- Getters & Setters ----

    public Long getId()                  { return id; }
    public void setId(Long id)           { this.id = id; }

    public String getNom()               { return nom; }
    public void setNom(String nom)       { this.nom = nom; }

    public Double getPrix()              { return prix; }
    public void setPrix(Double prix)     { this.prix = prix; }

    public Integer getQuantite()         { return quantite; }
    public void setQuantite(Integer q)   { this.quantite = q; }

    @Override
    public String toString() {
        return "Produit{id=" + id + ", nom='" + nom + "', prix=" + prix + ", quantite=" + quantite + "}";
    }
}
