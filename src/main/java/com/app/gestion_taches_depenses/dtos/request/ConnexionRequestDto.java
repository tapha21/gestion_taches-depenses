package com.app.gestion_taches_depenses.dtos.request;

import lombok.Data;

@Data
public class ConnexionRequestDto {
    private String email;
    private String motDePasse;
}
