package com.app.gestion_taches_depenses.dtos.request;

import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import lombok.Data;

@Data
public class CreateDepenseRequestDto {
    private String titre;
    private double montant;
    private String categorie; // ALIMENTATION, TRANSPORT...
}
