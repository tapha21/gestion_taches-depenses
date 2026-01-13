package com.app.gestion_taches_depenses.Controller;

import com.app.gestion_taches_depenses.services.TacheService;
import com.app.gestion_taches_depenses.dtos.request.CreationTachesRequestDto;
import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TacheController {
    private final TacheService service;

    @PostMapping
    public TacheResponseDto creerTache(@RequestBody CreationTachesRequestDto request, @RequestParam String utilisateurId) {
        return service.creer(request, utilisateurId);
    }

    @GetMapping
    public List<TacheResponseDto> listerTaches() {
        return service.listerTaches();
    }

    @GetMapping("/utilisateur/{id}")
    public List<TacheResponseDto> listerTachesParUtilisateur(@PathVariable String id) {
        return service.listerTachesParUtilisateur(id);
    }

    @GetMapping("/etat/{etat}")
    public List<TacheResponseDto> listerTachesParEtat(@PathVariable String etat) {
        return service.listerTachesParEtat(etat);
    }
}
