// package com.app.gestion_taches_depenses.models.fixtures;

// import com.app.gestion_taches_depenses.models.entites.Depense;
// import com.app.gestion_taches_depenses.models.entites.Tache;
// import com.app.gestion_taches_depenses.models.entites.Utilisateur;
// import com.app.gestion_taches_depenses.models.enums.CategorieDepense;
// import com.app.gestion_taches_depenses.models.enums.EtatDepense;
// import com.app.gestion_taches_depenses.models.enums.EtatTache;
// import com.app.gestion_taches_depenses.repository.DepenseRepository;
// import com.app.gestion_taches_depenses.repository.TacheRepository;
// import com.app.gestion_taches_depenses.repository.UtilisateurRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// @Component
// @RequiredArgsConstructor
// public class DataFixtures implements CommandLineRunner {

//     private final UtilisateurRepository utilisateurRepository;
//     private final TacheRepository tacheRepository;
//     private final DepenseRepository depenseRepository;

//     @Override
//     public void run(String... args) {

//         // 🔐 Sécurité : éviter les doublons
//         if (utilisateurRepository.count() > 0) {
//             System.out.println("⚠️ Données déjà présentes, fixtures ignorées.");
//             return;
//         }

//         /* =======================
//            UTILISATEURS
//         ======================= */

//         Utilisateur tapha = new Utilisateur();
//         tapha.setNom("Tapha");
//         tapha.setEmail("tapha@tapha.sn");
//         tapha.setMotDePasse("1234");
//         utilisateurRepository.save(tapha);

//         Utilisateur talla = new Utilisateur();
//         talla.setNom("Talla");
//         talla.setEmail("talla@tapha.sn");
//         talla.setMotDePasse("1234");
//         utilisateurRepository.save(talla);

//         /* =======================
//            TÂCHES – TAPHA
//         ======================= */
//         for (int i = 1; i <= 4; i++) {
//             Tache tache = new Tache();
//             tache.setTitre("Tâche Tapha #" + i);
//             tache.setDescription("Description de la tâche Tapha #" + i);
//             tache.setEtat(EtatTache.EN_COURS);
//             tache.setUtilisateurId(tapha.getId());
//             tacheRepository.save(tache);
//         }

//         /* =======================
//            TÂCHES – TALLA
//         ======================= */
//         for (int i = 1; i <= 4; i++) {
//             Tache tache = new Tache();
//             tache.setTitre("Tâche Talla #" + i);
//             tache.setDescription("Description de la tâche Talla #" + i);
//             tache.setEtat(EtatTache.EN_COURS);
//             tache.setUtilisateurId(talla.getId());
//             tacheRepository.save(tache);
//         }

//         /* =======================
//            DÉPENSES – TAPHA (6)
//         ======================= */
//         CategorieDepense[] categories = CategorieDepense.values();

//         for (int i = 0; i < 6; i++) {
//             Depense depense = new Depense();
//             depense.setTitre("Dépense " + categories[i % categories.length] + " Tapha");
//             depense.setMontant(2000 + (i * 1000)); // CFA
//             depense.setEtat(EtatDepense.EFFECTUEE);
//             depense.setCategorieDepense(categories[i % categories.length]);
//             depense.setUtilisateurId(tapha.getId());
//             depenseRepository.save(depense);
//         }

//         /* =======================
//            DÉPENSES – TALLA (6)
//         ======================= */
//         for (int i = 0; i < 6; i++) {
//             Depense depense = new Depense();
//             depense.setTitre("Dépense " + categories[i % categories.length] + " Talla");
//             depense.setMontant(3000 + (i * 1500)); // CFA
//             depense.setEtat(EtatDepense.PREVUE);
//             depense.setCategorieDepense(categories[i % categories.length]);
//             depense.setUtilisateurId(talla.getId());
//             depenseRepository.save(depense);
//         }

//         System.out.println("✅ Fixtures chargées avec succès !");
//     }
// }
