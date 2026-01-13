package com.app.gestion_taches_depenses.dtos.request;

import com.app.gestion_taches_depenses.models.enums.EtatTache;

public class CreationTachesRequestDto {
    private String titre;
    private String description;
    private EtatTache etat; // ⚡ ajouté

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public EtatTache getEtat() { return etat; } // ⚡ ajouté
    public void setEtat(EtatTache etat) { this.etat = etat; } // ⚡ ajouté
}
