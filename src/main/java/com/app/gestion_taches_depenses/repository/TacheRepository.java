package com.app.gestion_taches_depenses.repository;

import com.app.gestion_taches_depenses.models.entites.Tache;
import com.app.gestion_taches_depenses.models.enums.EtatTache;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TacheRepository extends MongoRepository<Tache, String> {
    List<Tache> findByUtilisateurId(String utilisateurId);
    List<Tache> findByEtat(EtatTache etat);
}
