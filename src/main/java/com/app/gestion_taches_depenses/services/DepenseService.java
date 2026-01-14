package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.mappers.DepenseMapper;
import com.app.gestion_taches_depenses.models.entites.Depense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;
import com.app.gestion_taches_depenses.repository.DepenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepenseService {

    private final DepenseRepository repository;
    private final DepenseMapper mapper;

    public DepenseResponseDto creer(CreateDepenseRequestDto request, String utilisateurId) {
        Depense depense = mapper.toEntity(request, utilisateurId);
        repository.save(depense);
        return mapper.toDTO(depense);
    }

    public List<DepenseResponseDto> listerDepenses() {
        List<Depense> depenses = repository.findAll();
        if(depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public DepenseResponseDto getById(String id) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée"));
        return mapper.toDTO(depense);
    }

    public List<DepenseResponseDto> listerDepensesParUtilisateur(String utilisateurId) {
        List<Depense> depenses = repository.findByUtilisateurId(utilisateurId);
        if(depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<DepenseResponseDto> listerDepensesParEtat(EtatDepense etat) {
        List<Depense> depenses = repository.findByEtat(etat);
        if(depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public DepenseResponseDto update(String id, CreateDepenseRequestDto request) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée"));

        depense.setTitre(request.getTitre());
        depense.setMontant(request.getMontant());
        depense.setCategorieDepense(request.getCategorie() != null ? request.getCategorie() : com.app.gestion_taches_depenses.models.enums.CategorieDepense.AUTRES);

        repository.save(depense);
        return mapper.toDTO(depense);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
    public List<DepenseResponseDto> listerDepensesAvecFiltres(String utilisateurId, EtatDepense etat, CategorieDepense categorie) {
    List<Depense> depenses;

    if(utilisateurId != null && etat != null && categorie != null) {
        depenses = repository.findByUtilisateurIdAndEtatAndCategorieDepense(utilisateurId, etat, categorie);
    } else if(utilisateurId != null && etat != null) {
        depenses = repository.findByUtilisateurIdAndEtat(utilisateurId, etat);
    } else if(utilisateurId != null && categorie != null) {
        depenses = repository.findByUtilisateurIdAndCategorieDepense(utilisateurId, categorie);
    } else if(utilisateurId != null) {
        depenses = repository.findByUtilisateurId(utilisateurId);
    } else if(etat != null && categorie != null) {
        depenses = repository.findByEtatAndCategorieDepense(etat, categorie);
    } else if(etat != null) {
        depenses = repository.findByEtat(etat);
    } else if(categorie != null) {
        depenses = repository.findByCategorieDepense(categorie);
    } else {
        depenses = repository.findAll();
    }

    return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
}

}
