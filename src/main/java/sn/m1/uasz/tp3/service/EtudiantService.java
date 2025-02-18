package sn.m1.uasz.tp3.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sn.m1.uasz.tp3.dao.JpaUtil;
import sn.m1.uasz.tp3.model.Etudiant;

/**
 * Service de gestion des étudiants.
 * 
 * Cette classe fournit des méthodes pour interagir avec les données des étudiants dans la base de données.
 * Elle permet d'ajouter, de modifier, de supprimer, de rechercher et de lister les étudiants.
 * Les opérations sont effectuées via JPA (Java Persistence API).
 * 
 * Les principales fonctionnalités incluent la gestion des informations des étudiants, comme leur nom, prénom, date
 * de naissance, sexe, niveau, et la filière à laquelle ils appartiennent.
 * 
 * @author [eadarak]
 * @version 1.0
 * @since 18.02.2025
 */
public class EtudiantService {

    /**
     * Ajoute un étudiant dans la base de données.
     *
     * Cette méthode utilise un `EntityManager` pour persister un étudiant dans la
     * base de données.
     * Une transaction est initiée avant l'opération et validée après l'insertion.
     * En cas d'erreur, la transaction est annulée et une exception est affichée.
     *
     * @param etu L'étudiant à ajouter.
     */
    public void ajouterEtudiant(Etudiant etu) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                em.persist(etu);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la création de l'etudiant : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Recherche un étudiant dans la base de données en utilisant son identifiant.
     *
     * Cette méthode utilise un `EntityManager` pour rechercher un étudiant par son
     * `id` dans la base de données.
     * Si l'étudiant est trouvé, il est retourné, sinon `null` est renvoyé.
     * En cas d'erreur, un message d'erreur est affiché.
     *
     * @param id L'identifiant de l'étudiant à rechercher.
     * @return L'étudiant trouvé ou `null` si aucun étudiant n'est trouvé ou en cas
     *         d'erreur.
     */
    public Etudiant rechercher(int id) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Etudiant.class, id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de l'etudiant : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Modifie un étudiant existant dans la base de données en utilisant son
     * identifiant.
     * 
     * Cette méthode permet de modifier les informations d'un étudiant. Si un
     * étudiant avec l'ID spécifié existe,
     * ses informations sont mises à jour avec celles passées en paramètre. Si
     * l'étudiant n'est pas trouvé,
     * un message d'erreur est affiché. En cas d'erreur pendant la transaction, un
     * rollback est effectué.
     *
     * @param id       L'identifiant de l'étudiant à modifier.
     * @param etudiant L'objet `Etudiant` contenant les nouvelles informations à
     *                 mettre à jour.
     */
    public void modifier(int id, Etudiant etudiant) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Etudiant etu = em.find(Etudiant.class, id);
                if (etu != null) {
                    etu.setNom(etudiant.getNom());
                    etu.setPrenom(etudiant.getPrenom());
                    etu.setDateNaiss(etu.getDateNaiss());
                    etu.setNiveau(etudiant.getNiveau());
                    etu.setSexe(etudiant.getSexe());
                    etu.setFiliere(etudiant.getFiliere());
                    em.merge(etu);
                    tx.commit();
                } else {
                    System.err.println("Etudiant introuvable avec l'ID : " + id);
                }
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la modification de l'etudiant : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime un étudiant de la base de données en utilisant son identifiant.
     * 
     * Cette méthode permet de supprimer un étudiant à partir de son identifiant. Si
     * un étudiant avec l'ID spécifié
     * existe, il sera supprimé de la base de données. Si l'étudiant n'est pas
     * trouvé, un message d'erreur sera affiché.
     * En cas d'erreur pendant la transaction, un rollback sera effectué pour
     * annuler toute modification.
     *
     * @param id L'identifiant de l'étudiant à supprimer.
     */
    public void supprimer(int id) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Etudiant etu = em.find(Etudiant.class, id);
                if (etu != null) {
                    em.remove(etu);
                    tx.commit();
                } else {
                    System.err.println("Etudiant introuvable avec l'ID : " + id);
                }
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la suppression de l'etudiant : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Récupère la liste de tous les étudiants dans la base de données.
     * 
     * Cette méthode effectue une requête JPQL pour récupérer tous les étudiants
     * enregistrés dans la base de données
     * et retourne une liste d'objets `Etudiant`.
     *
     * @return Une liste d'objets `Etudiant` représentant tous les étudiants dans la
     *         base de données.
     *         Retourne `null` en cas d'erreur de récupération.
     */
    public List<Etudiant> listerEtudiants() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            TypedQuery<Etudiant> query = em.createQuery("SELECT e FROM Etudiant e", Etudiant.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des etudiants : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère la liste des étudiants sous forme de tableau 2D de chaînes de
     * caractères.
     * 
     * Cette méthode permet de récupérer tous les étudiants dans la base de données
     * et de les formater dans un tableau
     * 2D pour une utilisation dans un composant comme un tableau JTable. Chaque
     * ligne du tableau représente un étudiant
     * avec ses informations (ID, INE, prénom, nom, date de naissance, sexe,
     * filière, niveau).
     *
     * @return Un tableau 2D de chaînes de caractères contenant les données des
     *         étudiants, avec chaque ligne correspondant
     *         à un étudiant et chaque colonne représentant une propriété de
     *         l'étudiant.
     */
    public String[][] listerEtudiantsOnTable() {
        List<Etudiant> etudiants = listerEtudiants();
        String[][] data = new String[etudiants.size()][8];

        for (int i = 0; i < etudiants.size(); i++) {
            Etudiant etudiant = etudiants.get(i);
            data[i][0] = "" + etudiant.getId();
            data[i][1] = etudiant.getIne();
            data[i][2] = etudiant.getPrenom();
            data[i][3] = etudiant.getNom();
            data[i][4] = etudiant.getDateNaiss() != null ? etudiant.getDateNaiss().toString() : "";
            data[i][5] = etudiant.getSexe().toString();
            data[i][6] = etudiant.getFiliere().getLibelle();
            data[i][7] = String.valueOf(etudiant.getNiveau());
        }

        return data;
    }

}
