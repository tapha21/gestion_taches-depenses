package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.request.CreateDepenseRequestDto;
import com.app.gestion_taches_depenses.dtos.response.DepenseResponseDto;
import com.app.gestion_taches_depenses.mappers.DepenseMapper;
import com.app.gestion_taches_depenses.models.entites.Depense;
import com.app.gestion_taches_depenses.models.entites.Utilisateur;
import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;
import com.app.gestion_taches_depenses.repository.DepenseRepository;
import com.app.gestion_taches_depenses.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepenseService {

    private final DepenseRepository repository;
    private final DepenseMapper mapper;
    private final UtilisateurRepository utilisateurRepository;

    /* =========================
       CRÉATION DÉPENSE + SOLDE
    ========================= */
    public DepenseResponseDto creer(CreateDepenseRequestDto request, String utilisateurId) {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("❌ Utilisateur introuvable"));

        // ❌ Bloquer si solde insuffisant
        if (utilisateur.getSolde() < request.getMontant()) {
            throw new RuntimeException("❌ Solde insuffisant pour effectuer cette dépense");
        }

        // ➖ Diminuer le solde
        utilisateur.setSolde(utilisateur.getSolde() - request.getMontant());
        utilisateurRepository.save(utilisateur);

        // ➕ Créer la dépense
        Depense depense = mapper.toEntity(request, utilisateurId);
        repository.save(depense);

        return mapper.toDTO(depense);
    }

    /* =========================
       LISTE TOTALE
    ========================= */
    public List<DepenseResponseDto> listerDepenses() {
        List<Depense> depenses = repository.findAll();
        if (depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    /* =========================
       PAR ID
    ========================= */
    public DepenseResponseDto getById(String id) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Dépense non trouvée"));
        return mapper.toDTO(depense);
    }

    /* =========================
       PAR UTILISATEUR
    ========================= */
    public List<DepenseResponseDto> listerDepensesParUtilisateur(String utilisateurId) {
        List<Depense> depenses = repository.findByUtilisateurId(utilisateurId);
        if (depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    /* =========================
       PAR ÉTAT
    ========================= */
    public List<DepenseResponseDto> listerDepensesParEtat(EtatDepense etat) {
        List<Depense> depenses = repository.findByEtat(etat);
        if (depenses == null) return Collections.emptyList();
        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    /* =========================
       UPDATE (AVEC AJUSTEMENT SOLDE)
    ========================= */
    public DepenseResponseDto update(String id, CreateDepenseRequestDto request) {

        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Dépense non trouvée"));

        Utilisateur utilisateur = utilisateurRepository.findById(depense.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("❌ Utilisateur introuvable"));

        double ancienMontant = depense.getMontant();
        double nouveauMontant = request.getMontant();
        double difference = nouveauMontant - ancienMontant;

        // ❌ Vérifier le solde si augmentation
        if (difference > 0 && utilisateur.getSolde() < difference) {
            throw new RuntimeException("❌ Solde insuffisant pour modifier la dépense");
        }

        // Ajuster le solde
        utilisateur.setSolde(utilisateur.getSolde() - difference);
        utilisateurRepository.save(utilisateur);

        // Mettre à jour la dépense
        depense.setTitre(request.getTitre());
        depense.setMontant(nouveauMontant);
        depense.setCategorieDepense(
                request.getCategorie() != null
                        ? request.getCategorie()
                        : CategorieDepense.AUTRES
        );

        repository.save(depense);
        return mapper.toDTO(depense);
    }

    /* =========================
       SUPPRESSION + RESTITUTION SOLDE
    ========================= */
    public void delete(String id) {

        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("❌ Dépense non trouvée"));

        Utilisateur utilisateur = utilisateurRepository.findById(depense.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("❌ Utilisateur introuvable"));

        // ➕ Rendre l'argent
        utilisateur.setSolde(utilisateur.getSolde() + depense.getMontant());
        utilisateurRepository.save(utilisateur);

        repository.deleteById(id);
    }

    /* =========================
       FILTRES AVANCÉS
    ========================= */
    public List<DepenseResponseDto> listerDepensesAvecFiltres(
            String utilisateurId,
            EtatDepense etat,
            CategorieDepense categorie
    ) {

        List<Depense> depenses;

        if (utilisateurId != null && etat != null && categorie != null) {
            depenses = repository.findByUtilisateurIdAndEtatAndCategorieDepense(utilisateurId, etat, categorie);
        } else if (utilisateurId != null && etat != null) {
            depenses = repository.findByUtilisateurIdAndEtat(utilisateurId, etat);
        } else if (utilisateurId != null && categorie != null) {
            depenses = repository.findByUtilisateurIdAndCategorieDepense(utilisateurId, categorie);
        } else if (utilisateurId != null) {
            depenses = repository.findByUtilisateurId(utilisateurId);
        } else if (etat != null && categorie != null) {
            depenses = repository.findByEtatAndCategorieDepense(etat, categorie);
        } else if (etat != null) {
            depenses = repository.findByEtat(etat);
        } else if (categorie != null) {
            depenses = repository.findByCategorieDepense(categorie);
        } else {
            depenses = repository.findAll();
        }

        return depenses.stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
