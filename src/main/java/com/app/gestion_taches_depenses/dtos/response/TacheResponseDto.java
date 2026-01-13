package com.app.gestion_taches_depenses.dtos.response;

import com.app.gestion_taches_depenses.models.enums.EtatTache;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TacheResponseDto {
    private String id;
    private String titre;
    private String description;
    private String etat;
    private LocalDateTime dateCreation;
}
