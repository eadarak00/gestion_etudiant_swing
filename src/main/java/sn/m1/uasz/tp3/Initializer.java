package sn.m1.uasz.tp3;

import sn.m1.uasz.tp3.model.Filiere;
import sn.m1.uasz.tp3.service.FiliereService;

import java.util.List;

/**
 * Classe d'initialisation des données.
 * 
 * Cette classe permet d'initialiser des données par défaut pour les filières
 * dans la base de données.
 * Elle crée plusieurs filières et les insère dans la base de données en
 * utilisant le service `FiliereService`.
 * 
 * @author [eadarak]
 * @version 1.0
 * @since 18.02.2025
 */
public class Initializer {

    /**
     * Initialise les données de base, notamment les filières, dans la base de
     * données.
     * 
     * Cette méthode crée une liste prédéfinie de filières, puis utilise le service
     * `FiliereService`
     * pour les insérer dans la base de données. Une fois les filières créées, un
     * message de succès
     * est affiché.
     * 
     * La méthode ne prend aucun paramètre et ne retourne rien.
     */
    public static void initData() {
        FiliereService filiereService = new FiliereService();

        // Liste des filières à insérer
        List<String> filieres = List.of("MPI", "L2I", "ECO-GES", "GEO", "Sociologie");

        // Insérer chaque filière dans la base de données
        for (String libelle : filieres) {
            Filiere filiere = new Filiere();
            filiere.setLibelle(libelle);
            filiereService.createFiliere(filiere);
        }

        // Afficher un message une fois les filières insérées
        System.out.println("Filières initialisées avec succès !");
    }
}
