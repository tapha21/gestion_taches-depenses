package com.app.gestion_taches_depenses.mappers;

import org.springframework.stereotype.Component;
import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.models.entites.Depense;

@Component
public class DepenseMapper {

    // Conversion Entity → DTO
    public DepenseResponseDto toDTO(Depense depense) {
        DepenseResponseDto dto = new DepenseResponseDto();
        dto.setId(depense.getId());
        dto.setTitre(depense.getTitre());
        dto.setMontant(depense.getMontant());

        // sécuriser l'état
        dto.setEtat(depense.getEtat() != null ? depense.getEtat().name() : "INDEFINI");

        // sécuriser la catégorie
        dto.setCategorie(
            depense.getCategorieDepense() != null
                ? depense.getCategorieDepense().name()
                : "AUTRES"
        );

        dto.setDateCreation(depense.getDateCreation());
        return dto;
    }

    // Conversion Request DTO → Entity
    public Depense toEntity(CreateDepenseRequestDto request, String utilisateurId) {
        Depense depense = new Depense();
        depense.setTitre(request.getTitre());
        depense.setMontant(request.getMontant());

        // Vérification de la catégorie avant assignation
        depense.setCategorieDepense(request.getCategorie() != null ? request.getCategorie() : com.app.gestion_taches_depenses.models.enums.CategorieDepense.AUTRES);
        depense.setUtilisateurId(utilisateurId);
        return depense;
    }
}
