package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import com.app.gestion_taches_depenses.dtos.request.CreationTachesRequestDto;
import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import com.app.gestion_taches_depenses.mappers.TacheMapper;
import com.app.gestion_taches_depenses.models.entites.Tache;
import com.app.gestion_taches_depenses.repository.TacheRepository;
import com.app.gestion_taches_depenses.models.enums.EtatTache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository repository;
    private final TacheMapper mapper;

 public TacheResponseDto creer(CreationTachesRequestDto request, String utilisateurId) {
    Tache tache = mapper.toEntity(request, utilisateurId);
    repository.save(tache);
    return mapper.toDTO(tache);
}


    public List<TacheResponseDto> listerTaches() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TacheResponseDto> listerTachesParUtilisateur(String utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TacheResponseDto> listerTachesParEtat(String etat) {
        return repository.findByEtat(EtatTache.valueOf(etat)).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
