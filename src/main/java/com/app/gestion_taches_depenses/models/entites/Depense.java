package com.app.gestion_taches_depenses.models.entites;

import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "depenses")
public class Depense {
    @Id
    private String id;
    private String titre;
    private double montant;
    private EtatDepense etat = EtatDepense.PREVUE;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private String utilisateurId;
    private CategorieDepense categorieDepense;

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public EtatDepense getEtat() { return etat; }
    public void setEtat(EtatDepense etat) { this.etat = etat; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(String utilisateurId) { this.utilisateurId = utilisateurId; }

    public CategorieDepense getCategorieDepense() { return categorieDepense; }
    public void setCategorieDepense(CategorieDepense categorieDepense) { this.categorieDepense = categorieDepense; }
}
