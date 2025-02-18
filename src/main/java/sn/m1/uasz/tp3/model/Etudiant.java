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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ine;
    private String prenom, nom;
    private LocalDate dateNaiss;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    private int niveau;
    @ManyToOne
    private Filiere filiere;

}
