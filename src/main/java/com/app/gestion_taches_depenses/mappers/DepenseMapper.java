package com.app.gestion_taches_depenses.mappers;

import org.springframework.stereotype.Component;
import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.models.entites.Depense;

@Component
public class DepenseMapper {

    public DepenseResponseDto toDTO(Depense depense) {
        DepenseResponseDto dto = new DepenseResponseDto();
        dto.setId(depense.getId());
        dto.setTitre(depense.getTitre());
        dto.setMontant(depense.getMontant());
        dto.setEtat(depense.getEtat().name());
        dto.setCategorie(depense.getCategorieDepense().name());
        dto.setDateCreation(depense.getDateCreation());
        return dto;
    }

    public Depense toEntity(CreateDepenseRequestDto request, String utilisateurId) {
        Depense depense = new Depense();
        depense.setTitre(request.getTitre());
        depense.setMontant(request.getMontant());
        depense.setCategorieDepense(request.getCategorie());
        depense.setUtilisateurId(utilisateurId);
        return depense;
    }
}
