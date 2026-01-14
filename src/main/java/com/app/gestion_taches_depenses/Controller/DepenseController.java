package com.app.gestion_taches_depenses.Controller;

import com.app.gestion_taches_depenses.services.DepenseService;
import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;

import java.util.List;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {

    private final DepenseService service;

    // Créer une dépense
    @PostMapping
    public DepenseResponseDto creerDepense(@RequestBody CreateDepenseRequestDto request, 
                                           @RequestParam String utilisateurId) {
        return service.creer(request, utilisateurId);
    }

    // Lister toutes les dépenses
    @GetMapping
    public List<DepenseResponseDto> listerDepenses() {
        return service.listerDepenses();
    }

    // Lister une dépense par id
    @GetMapping("/{id}")
    public DepenseResponseDto getDepense(@PathVariable String id) {
        return service.getById(id);
    }

    // Lister par utilisateur
    @GetMapping("/utilisateur/{id}")
    public List<DepenseResponseDto> listerDepensesParUtilisateur(@PathVariable String id) {
        return service.listerDepensesParUtilisateur(id);
    }

    // Lister par état
  @GetMapping("/etat/{etat}")
public List<DepenseResponseDto> listerDepensesParEtat(@PathVariable EtatDepense etat) {
    return service.listerDepensesParEtat(etat);
}


    // Modifier une dépense
    @PutMapping("/{id}")
    public DepenseResponseDto updateDepense(@PathVariable String id, 
                                            @RequestBody CreateDepenseRequestDto request) {
        return service.update(id, request);
    }

    // Supprimer une dépense
    @DeleteMapping("/{id}")
    public void deleteDepense(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/filtres")
public List<DepenseResponseDto> listerDepensesFiltres(
        @RequestParam(required = false) String utilisateurId,
        @RequestParam(required = false) EtatDepense etat,
        @RequestParam(required = false) CategorieDepense categorie
) {
    return service.listerDepensesAvecFiltres(utilisateurId, etat, categorie);
}

}
