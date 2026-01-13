package com.app.gestion_taches_depenses.dtos.request;

import com.app.gestion_taches_depenses.models.enums.EtatTache;
import lombok.Data;

@Data
public class CreationTachesRequestDto {
    private String titre;
    private String description;
}
