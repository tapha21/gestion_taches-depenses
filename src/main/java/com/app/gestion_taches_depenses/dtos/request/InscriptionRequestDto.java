package com.app.gestion_taches_depenses.dtos.request;

import lombok.Data;

@Data
public class InscriptionRequestDto {
    private String nom;
    private String email;
    private String motDePasse;
}

