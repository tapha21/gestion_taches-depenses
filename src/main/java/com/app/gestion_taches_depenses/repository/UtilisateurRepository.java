package com.app.gestion_taches_depenses.repository;

import com.app.gestion_taches_depenses.models.entites.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
    Utilisateur findByEmail(String email);
}
