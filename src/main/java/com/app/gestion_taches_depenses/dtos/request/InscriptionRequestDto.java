package com.app.gestion_taches_depenses.dtos.request;

public class InscriptionRequestDto {
    private String nom;
    private String email;
    private String motDePasse;
    private double solde = 0; // initialisation à 0

    // ===== Getters & Setters =====
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}
