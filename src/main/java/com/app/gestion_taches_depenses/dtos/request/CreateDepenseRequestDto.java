package com.app.gestion_taches_depenses.dtos.request;

import com.app.gestion_taches_depenses.models.enums.CategorieDepense;

public class CreateDepenseRequestDto {
    private String titre;
    private double montant;
    private CategorieDepense categorie; // enum directement

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public CategorieDepense getCategorie() { return categorie; }
    public void setCategorie(CategorieDepense categorie) { this.categorie = categorie; }
}
