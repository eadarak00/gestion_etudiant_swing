package sn.m1.uasz.tp3.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.m1.uasz.tp3.model.enums.Sexe;


/**
 * Classe représentant un étudiant dans le système de gestion du club étudiant.
 * 
 * Un étudiant possède un identifiant unique, des informations personnelles
 * telles que son INE, son nom, prénom, date de naissance, sexe, niveau d'étude 
 * et est associé à une filière spécifique.
 * 
 * Cette classe est une entité JPA, mappée à une base de données relationnelle.
 * 
 * @author [Ton Nom]
 * @version 1.0
 * @since [Date]
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Etudiant {

    /**
     * Identifiant unique de l'étudiant, généré automatiquement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Identifiant National de l'Étudiant (INE), unique pour chaque étudiant.
     */
    private String ine;

    /**
     * Prénom de l'étudiant.
     */
    private String prenom;

    /**
     * Nom de famille de l'étudiant.
     */
    private String nom;

    /**
     * Date de naissance de l'étudiant.
     */
    private LocalDate dateNaiss;

    /**
     * Sexe de l'étudiant (Homme ou Femme).
     * Stocké sous forme de chaîne dans la base de données.
     */
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    /**
     * Niveau d'étude de l'étudiant (ex : 1, 2, 3 pour L1, L2, L3).
     */
    private int niveau;

    /**
     * Filière à laquelle appartient l'étudiant.
     * Relation Many-to-One avec l'entité {@link Filiere}.
     */
    @ManyToOne
    private Filiere filiere;

}
