package com.app.gestion_taches_depenses.Controller;


import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;

import lombok.RequiredArgsConstructor;
import com.app.gestion_taches_depenses.services.DepenseService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {
    private final DepenseService service;

    @PostMapping
    public DepenseResponseDto creerDepense(@RequestBody CreateDepenseRequestDto request, @RequestParam String utilisateurId) {
        return service.creer(request, utilisateurId);
    }

    @GetMapping
    public List<DepenseResponseDto> listerDepenses() {
        return service.listerDepenses();
    }

    @GetMapping("/utilisateur/{id}")
    public List<DepenseResponseDto> listerDepensesParUtilisateur(@PathVariable String id) {
        return service.listerDepensesParUtilisateur(id);
    }

    @GetMapping("/etat/{etat}")
    public List<DepenseResponseDto> listerDepensesParEtat(@PathVariable String etat) {
        return service.listerDepensesParEtat(etat);
    }
}
