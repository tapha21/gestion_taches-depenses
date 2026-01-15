package com.app.gestion_taches_depenses.services;

import com.app.gestion_taches_depenses.dtos.request.ConnexionRequestDto;
import com.app.gestion_taches_depenses.dtos.request.InscriptionRequestDto;
import com.app.gestion_taches_depenses.dtos.response.UtilisateurResponseDto;
import com.app.gestion_taches_depenses.mappers.UtilisateurMapper;
import com.app.gestion_taches_depenses.models.entites.Utilisateur;
import com.app.gestion_taches_depenses.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    /* =========================
       INSCRIPTION
    ========================= */
   public UtilisateurResponseDto inscription(InscriptionRequestDto dto) {
    if(utilisateurRepository.findByEmail(dto.getEmail()) != null) {
        throw new RuntimeException("❌ Cet email est déjà utilisé");
    }
    Utilisateur u = new Utilisateur();
    u.setNom(dto.getNom());
    u.setEmail(dto.getEmail());
    u.setMotDePasse(dto.getMotDePasse());
    utilisateurRepository.save(u);
    return utilisateurMapper.toResponseDto(u);
}

public UtilisateurResponseDto connexion(ConnexionRequestDto dto) {
    Utilisateur u = utilisateurRepository.findByEmail(dto.getEmail());
    if(u == null) throw new RuntimeException("❌ Utilisateur introuvable");
    if(!u.getMotDePasse().equals(dto.getMotDePasse()))
        throw new RuntimeException("❌ Mot de passe incorrect");
    return utilisateurMapper.toResponseDto(u);
}


    /* =========================
       LISTE DES UTILISATEURS
    ========================= */
    public List<UtilisateurResponseDto> lister() {
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateurMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public double getSolde(String userId) {
    Utilisateur u = utilisateurRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    return u.getSolde();
}

public UtilisateurResponseDto ajouterSolde(String userId, double montant) {
    if (montant <= 0) {
        throw new RuntimeException("Montant invalide");
    }

    Utilisateur u = utilisateurRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

    u.setSolde(u.getSolde() + montant);
    utilisateurRepository.save(u);

    return utilisateurMapper.toResponseDto(u);
}

}
