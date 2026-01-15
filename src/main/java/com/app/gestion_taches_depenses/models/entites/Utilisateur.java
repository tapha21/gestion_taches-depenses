package com.app.gestion_taches_depenses.models.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utilisateurs")
public class Utilisateur {

    @Id
    private String id;
    private String nom;
    private String email;
    private String motDePasse;

    private double solde = 0; // ✅ SOLDE PAR DÉFAUT

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}
