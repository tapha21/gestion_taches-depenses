package com.app.gestion_taches_depenses.mappers;

import org.springframework.stereotype.Component;
import com.app.gestion_taches_depenses.dtos.request.CreationTachesRequestDto;
import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import com.app.gestion_taches_depenses.models.entites.Tache;
import com.app.gestion_taches_depenses.models.enums.EtatTache;

@Component
public class TacheMapper {

    public TacheResponseDto toDTO(Tache tache) {
        TacheResponseDto dto = new TacheResponseDto();
        dto.setId(tache.getId());
        dto.setTitre(tache.getTitre());
        dto.setDescription(tache.getDescription());
        dto.setEtat(tache.getEtat().name());
        dto.setDateCreation(tache.getDateCreation());
        return dto;
    }

    public Tache toEntity(CreationTachesRequestDto request, String utilisateurId) {
        Tache tache = new Tache();
        tache.setTitre(request.getTitre());
        tache.setDescription(request.getDescription());
        tache.setUtilisateurId(utilisateurId);
        tache.setEtat(EtatTache.EN_COURS);
        return tache;
    }
}
