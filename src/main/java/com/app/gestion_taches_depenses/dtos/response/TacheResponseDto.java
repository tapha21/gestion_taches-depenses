package com.app.gestion_taches_depenses.dtos.response;

import java.time.LocalDateTime;

public class TacheResponseDto {
    private String id;
    private String titre;
    private String description;
    private String etat;
    private LocalDateTime dateCreation;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}
