package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.mappers.DepenseMapper;
import com.app.gestion_taches_depenses.models.entites.Depense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;
import com.app.gestion_taches_depenses.repository.DepenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepenseResponseDto getById(String id) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée"));
        return mapper.toDTO(depense);
    }

    public List<DepenseResponseDto> listerDepensesParUtilisateur(String utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DepenseResponseDto> listerDepensesParEtat(String etat) {
        return repository.findByEtat(EtatDepense.valueOf(etat)).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepenseResponseDto update(String id, CreateDepenseRequestDto request) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée"));
        depense.setTitre(request.getTitre());
        depense.setMontant(request.getMontant());
        depense.setCategorieDepense(request.getCategorie());
        repository.save(depense);
        return mapper.toDTO(depense);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
