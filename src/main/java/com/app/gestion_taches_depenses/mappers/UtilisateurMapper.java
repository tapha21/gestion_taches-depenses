package com.app.gestion_taches_depenses.mappers;

import org.springframework.stereotype.Component;
import com.app.gestion_taches_depenses.dtos.response.UtilisateurResponseDto;
import com.app.gestion_taches_depenses.models.entites.Utilisateur;

@Component
public class UtilisateurMapper {

    public UtilisateurResponseDto toResponseDto(Utilisateur utilisateur) {
        UtilisateurResponseDto dto = new UtilisateurResponseDto();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        return dto;
    }
}
