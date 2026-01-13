package com.app.gestion_taches_depenses.models.entites;

import com.app.gestion_taches_depenses.models.enums.EtatTache;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "taches")
public class Tache {
    @Id
    private String id;
    private String titre;
    private String description;
    private EtatTache etat = EtatTache.EN_COURS;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private String utilisateurId;

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public EtatTache getEtat() { return etat; }
    public void setEtat(EtatTache etat) { this.etat = etat; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(String utilisateurId) { this.utilisateurId = utilisateurId; }
}
