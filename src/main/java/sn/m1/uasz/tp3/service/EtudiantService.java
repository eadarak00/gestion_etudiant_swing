package sn.m1.uasz.tp3.service;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import sn.m1.uasz.tp3.dao.JpaUtil;
import sn.m1.uasz.tp3.model.Etudiant;

public class EtudiantService {

    public void ajouterEtudiant(Etudiant etu){
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

    public Etudiant rechercher(int id){
        try (EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Etudiant.class, id);
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de l'etudiant : " + e.getMessage());
            e.printStackTrace();
            return null;        
        }
    }

    public void modifier(int id, Etudiant etudiant){
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

    public void supprimer(int id){
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

    public String[][] listerEtudiantsOnTable() {
        List<Etudiant> etudiants = listerEtudiants(); // ou votre méthode pour récupérer les étudiants
        String[][] data = new String[etudiants.size()][8];
    
        for (int i = 0; i < etudiants.size(); i++) {
            Etudiant etudiant = etudiants.get(i);
            data[i][0] ="" + etudiant.getId();
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
