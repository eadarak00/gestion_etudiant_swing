package sn.m1.uasz.tp3.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sn.m1.uasz.tp3.dao.JpaUtil;
import sn.m1.uasz.tp3.model.Filiere;

public class FiliereService {

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

    public Filiere rechercheFiliere(int id) {
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Filiere.class, id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la filière : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
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
