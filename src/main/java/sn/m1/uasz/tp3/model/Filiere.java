package sn.m1.uasz.tp3.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe représentant une filière dans le système de gestion du club étudiant.
 * 
 * Une filière est un domaine d'étude auquel plusieurs étudiants peuvent être associés.
 * Chaque filière possède un identifiant unique et un libellé unique.
 * 
 * Cette classe est une entité JPA, mappée à une base de données relationnelle.
 * 
 * Utilisation de Lombok pour générer automatiquement les getters, setters, 
 * un constructeur sans argument et un constructeur avec tous les champs.
 * 
 * @author [eadarak]
 * @version 1.0
 * @since [18.02.2025]
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {

    /**
     * Identifiant unique de la filière, généré automatiquement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Libellé unique de la filière (ex: Informatique, Génie Civil, etc.).
     */
    @Column(unique = true)
    private String libelle;

    /**
     * Liste des étudiants inscrits dans cette filière.
     * Relation One-to-Many avec l'entité {@link Etudiant}.
     */
    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;
}