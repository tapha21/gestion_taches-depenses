package com.app.gestion_taches_depenses.Controller;

import com.app.gestion_taches_depenses.dtos.response.TacheResponseDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.services.TacheService;
import com.app.gestion_taches_depenses.services.DepenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final TacheService tacheService;
    private final DepenseService depenseService;

    @GetMapping
    public Map<String, Object> dashboard(@RequestParam(required = false) String utilisateurId) {
        Map<String, Object> dashboard = new HashMap<>();

        // Liste des tâches
        List<TacheResponseDto> taches = (utilisateurId == null)
                ? tacheService.listerTaches()
                : tacheService.listerTachesParUtilisateur(utilisateurId);

        // Liste des dépenses
        List<DepenseResponseDto> depenses = (utilisateurId == null)
                ? depenseService.listerDepenses()
                : depenseService.listerDepensesParUtilisateur(utilisateurId);

        dashboard.put("taches", taches);
        dashboard.put("depenses", depenses);

        return dashboard;
    }
}
