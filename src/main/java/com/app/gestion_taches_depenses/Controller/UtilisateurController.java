package com.app.gestion_taches_depenses.Controller;

import com.app.gestion_taches_depenses.dtos.request.InscriptionRequestDto;
import com.app.gestion_taches_depenses.dtos.request.ConnexionRequestDto;
import com.app.gestion_taches_depenses.dtos.response.UtilisateurResponseDto;
import com.app.gestion_taches_depenses.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService service;

    @PostMapping("/inscription")
    public UtilisateurResponseDto inscription(@RequestBody InscriptionRequestDto request) {
        return service.inscription(request);
    }

    @PostMapping("/connexion")
    public UtilisateurResponseDto connexion(@RequestBody ConnexionRequestDto request) {
        return service.connexion(request);
    }

    @GetMapping
    public List<UtilisateurResponseDto> lister() {
        return service.lister();
    }
}
