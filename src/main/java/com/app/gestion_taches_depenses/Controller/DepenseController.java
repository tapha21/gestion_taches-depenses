package com.app.gestion_taches_depenses.Controller;

import com.app.gestion_taches_depenses.services.TacheService;
import com.app.gestion_taches_depenses.dtos.request.CreationTachesRequestDto;
import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TacheController {

    private final TacheService service;

    // Créer une tache
    @PostMapping
    public TacheResponseDto creerTache(@RequestBody CreationTachesRequestDto request, 
                                       @RequestParam String utilisateurId) {
        return service.creer(request, utilisateurId);
    }

    // Lister toutes les tâches
    @GetMapping
    public List<TacheResponseDto> listerTaches() {
        return service.listerTaches();
    }

    // Lister une tâche par id
    @GetMapping("/{id}")
    public TacheResponseDto getTache(@PathVariable String id) {
        return service.getById(id);
    }

    // Lister par utilisateur
    @GetMapping("/utilisateur/{id}")
    public List<TacheResponseDto> listerTachesParUtilisateur(@PathVariable String id) {
        return service.listerTachesParUtilisateur(id);
    }

    // Lister par état
    @GetMapping("/etat/{etat}")
    public List<TacheResponseDto> listerTachesParEtat(@PathVariable String etat) {
        return service.listerTachesParEtat(etat);
    }

    // Modifier une tache
    @PutMapping("/{id}")
    public TacheResponseDto updateTache(@PathVariable String id, 
                                        @RequestBody CreationTachesRequestDto request) {
        return service.update(id, request);
    }

    // Supprimer une tache
    @DeleteMapping("/{id}")
    public void deleteTache(@PathVariable String id) {
        service.delete(id);
    }
}
