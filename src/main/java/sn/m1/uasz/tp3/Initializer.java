package sn.m1.uasz.tp3;

import sn.m1.uasz.tp3.model.Filiere;
import sn.m1.uasz.tp3.service.FiliereService;

import java.util.List;

public class Initializer {

    public static void initData() {
        FiliereService filiereService = new FiliereService();

        // Liste des filières à insérer
        List<String> filieres = List.of("MPI", "L2I", "ECO-GES", "GEO", "Sociologie");

        for (String libelle : filieres) {
            Filiere filiere = new Filiere();
            filiere.setLibelle(libelle);
            filiereService.createFiliere(filiere);
        }

        System.out.println("Filières initialisées avec succès !");
    }
}
