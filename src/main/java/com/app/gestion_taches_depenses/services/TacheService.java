package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.request.CreationTachesRequestDto;
import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import com.app.gestion_taches_depenses.mappers.TacheMapper;
import com.app.gestion_taches_depenses.models.entites.Tache;
import com.app.gestion_taches_depenses.models.enums.EtatTache;
import com.app.gestion_taches_depenses.repository.TacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository repository;
    private final TacheMapper mapper;

    // Créer une tâche
    public TacheResponseDto creer(CreationTachesRequestDto request, String utilisateurId) {
        Tache tache = mapper.toEntity(request, utilisateurId);
        repository.save(tache);
        return mapper.toDTO(tache);
    }

    // Lister toutes les tâches
    public List<TacheResponseDto> listerTaches() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lister une tâche par ID
    public TacheResponseDto getById(String id) {
        Tache tache = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        return mapper.toDTO(tache);
    }

    // Lister par utilisateur
    public List<TacheResponseDto> listerTachesParUtilisateur(String utilisateurId) {
        return repository.findByUtilisateurId(utilisateurId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lister par état
    public List<TacheResponseDto> listerTachesParEtat(String etat) {
        EtatTache etatEnum = EtatTache.valueOf(etat); // ici c'est ok car String -> Enum
        return repository.findByEtat(etatEnum).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    // Modifier une tâche
    public TacheResponseDto update(String id, CreationTachesRequestDto request) {
        Tache tache = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        tache.setTitre(request.getTitre());
        tache.setDescription(request.getDescription());

        if(request.getEtat() != null) {
            tache.setEtat(request.getEtat()); // plus besoin de valueOf
        }

        repository.save(tache);
        return mapper.toDTO(tache);
    }

    // Supprimer une tâche
    public void delete(String id) {
        repository.deleteById(id);
    }
}
