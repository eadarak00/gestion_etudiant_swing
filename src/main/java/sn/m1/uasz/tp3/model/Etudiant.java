package sn.m1.uasz.tp3.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    private String prenom, nom;
    private LocalDate dateNaiss;
    private Sexe sexe;
    private int niveau;
    @OneToOne
    private Filiere filiere;

}
