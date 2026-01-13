package com.app.gestion_taches_depenses.dtos.response;

import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepenseResponseDto {
    private String id;
    private String titre;
    private double montant;
    private String etat;
    private String categorie;
    private LocalDateTime dateCreation;
}
