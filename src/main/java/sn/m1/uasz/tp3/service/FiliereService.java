package sn.m1.uasz.tp3.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sn.m1.uasz.tp3.dao.JpaUtil;
import sn.m1.uasz.tp3.model.Filiere;

/**
 * Service de gestion des filières.
 * 
 * Cette classe permet de gérer les opérations CRUD (Créer, Lire, Modifier,
 * Supprimer) sur les objets `Filiere`.
 * Elle interagit avec la base de données à travers JPA (Java Persistence API).
 * 
 * @author [eadarak]
 * @version 1.0
 * @since 18.02.2025
 */
public class FiliereService {

    /**
     * Crée une nouvelle filière dans la base de données.
     * 
     * Cette méthode prend un objet `Filiere` en paramètre et l'ajoute à la base de
     * données.
     * Si une erreur survient, elle est capturée et un message d'erreur est affiché.
     * 
     * @param f L'objet `Filiere` à ajouter dans la base de données.
     */
    public void createFiliere(Filiere f) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(f);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la création de la filière : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Recherche une filière dans la base de données par son identifiant.
     * 
     * Cette méthode permet de récupérer une filière à partir de son identifiant
     * unique. Si la filière n'est pas trouvée
     * ou en cas d'erreur, un message d'erreur est affiché et la méthode retourne
     * `null`.
     * 
     * @param id L'identifiant unique de la filière à rechercher.
     * @return L'objet `Filiere` correspondant à l'ID, ou `null` si la filière n'est
     *         pas trouvée.
     */
    public Filiere rechercheFiliere(int id) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Filiere.class, id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la filière : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Recherche une filière par son libellé.
     * 
     * Cette méthode permet de rechercher une filière dans la base de données par
     * son libellé. Si aucune filière n'est
     * trouvée ou en cas d'erreur, la méthode retourne `null`.
     * 
     * @param libelle Le libellé de la filière à rechercher.
     * @return L'objet `Filiere` correspondant au libellé, ou `null` si la filière
     *         n'est pas trouvée.
     */
    public Filiere findByLibelle(String libelle) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            String jpql = "SELECT f FROM Filiere f WHERE f.libelle = :libelle";
            TypedQuery<Filiere> query = em.createQuery(jpql, Filiere.class);
            query.setParameter("libelle", libelle);

            // Exécuter la requête et récupérer la filière
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la filière : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Liste toutes les filières présentes dans la base de données.
     * 
     * Cette méthode récupère toutes les filières dans la base de données et
     * retourne une liste d'objets `Filiere`.
     * En cas d'erreur, un message d'erreur est affiché et la méthode retourne
     * `null`.
     * 
     * @return Une liste d'objets `Filiere` représentant toutes les filières dans la
     *         base de données, ou `null` en cas d'erreur.
     */
    public List<Filiere> listerFilieres() {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            TypedQuery<Filiere> query = em.createQuery("SELECT f FROM Filiere f", Filiere.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des filières : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Modifie une filière existante dans la base de données.
     * 
     * Cette méthode permet de modifier une filière en utilisant son identifiant. Si
     * la filière est trouvée, ses informations
     * (par exemple le libellé) sont mises à jour. En cas d'erreur, la transaction
     * est annulée et un message d'erreur est affiché.
     * 
     * @param id      L'identifiant unique de la filière à modifier.
     * @param filiere L'objet `Filiere` contenant les nouvelles informations.
     */
    public void modifierFiliere(int id, Filiere filiere) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Filiere f = em.find(Filiere.class, id);
                if (f != null) {
                    f.setLibelle(filiere.getLibelle());
                    em.merge(f);
                    tx.commit();
                } else {
                    System.err.println("Filière introuvable avec l'ID : " + id);
                }
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la modification de la filière : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime une filière de la base de données.
     * 
     * Cette méthode permet de supprimer une filière en fonction de son identifiant.
     * Si la filière est trouvée, elle est supprimée
     * de la base de données. En cas d'erreur, la transaction est annulée et un
     * message d'erreur est affiché.
     * 
     * @param id L'identifiant unique de la filière à supprimer.
     */
    public void supprimerFiliere(int id) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Filiere f = em.find(Filiere.class, id);
                if (f != null) {
                    em.remove(f);
                    tx.commit();
                } else {
                    System.err.println("Filière introuvable avec l'ID : " + id);
                }
            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erreur lors de la suppression de la filière : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
