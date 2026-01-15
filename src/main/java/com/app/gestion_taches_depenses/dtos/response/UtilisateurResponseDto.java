package com.app.gestion_taches_depenses.dtos.response;

public class UtilisateurResponseDto {
    private String id;
    private String nom;
    private String email;
    private double solde;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}
