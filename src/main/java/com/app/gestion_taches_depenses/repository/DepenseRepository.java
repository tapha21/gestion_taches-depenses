package com.app.gestion_taches_depenses.repository;

import com.app.gestion_taches_depenses.models.entites.Depense;
import com.app.gestion_taches_depenses.models.enums.EtatDepense;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DepenseRepository extends MongoRepository<Depense, String> {
    List<Depense> findByUtilisateurId(String utilisateurId);
    List<Depense> findByEtat(EtatDepense etat);
}
