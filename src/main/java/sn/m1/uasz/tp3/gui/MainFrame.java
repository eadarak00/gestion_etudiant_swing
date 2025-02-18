package sn.m1.uasz.tp3.gui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import sn.m1.uasz.tp3.model.Etudiant;
import sn.m1.uasz.tp3.model.Filiere;
import sn.m1.uasz.tp3.model.enums.Sexe;
import sn.m1.uasz.tp3.service.EtudiantService;
import sn.m1.uasz.tp3.service.FiliereService;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MainFrame extends JFrame {
    private FiliereService filiereService;
    private EtudiantService etudiantService;
    // Déclaration des variables pour chaque champ
    private JTextField ine_input;
    private JTextField nom_input;
    private JTextField prenom_input;
    private JFormattedTextField naiss_input;
    private JRadioButton homme;
    private JRadioButton femme;
    private JComboBox<String> filiereBox;
    private JComboBox<Integer> niv_box;

    public MainFrame() throws Exception {
        this.filiereService = new FiliereService();
        this.etudiantService = new EtudiantService();
        // Appliquer le look and feel Nimbus
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        // Titre de la fenêtre
        setTitle("Gestion Club Etudiant");

        // Définir la taille de la fenêtre
        setSize(700, 500);

        // Fermer l'application lorsqu'on ferme la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centrer la fenêtre à l'écran
        setLocationRelativeTo(null);

        // Panneau principal
        JPanel mainPanel = (JPanel) getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Ajouter le panneau gauche (créé par la méthode createLeftPanel())
        mainPanel.add(createLeftPanel(), BorderLayout.WEST);

        mainPanel.add(createRightPanel(), BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
        // Panneau gauche
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setPreferredSize(new Dimension(500, 0));
        panelLeft.setBackground(new Color(238, 238, 238));

        // Titre du formulaire
        JLabel title = new JLabel("Formulaire d'inscription", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(155, 0, 52));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelLeft.add(title, BorderLayout.NORTH);

        // Ajout du formulaire
        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Ajouter l'input et label INE
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel ine = new JLabel("INE");
        ine.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(ine, gbc);

        gbc.gridx = 1;
        ine_input = new JTextField(15); // Modifier cette ligne
        ine_input.setFont(new Font("Arial", Font.PLAIN, 14));
        ine_input.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        form.add(ine_input, gbc);

        // Ajouter l'input et label Nom
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nom = new JLabel("Nom");
        nom.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(nom, gbc);

        gbc.gridx = 1;
        nom_input = new JTextField(15); // Modifier cette ligne
        nom_input.setFont(new Font("Arial", Font.PLAIN, 14));
        nom_input.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        form.add(nom_input, gbc);

        // Ajouter l'input et label Prenom
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel prenom = new JLabel("Prenom");
        prenom.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(prenom, gbc);

        gbc.gridx = 1;
        prenom_input = new JTextField(15); // Modifier cette ligne
        prenom_input.setFont(new Font("Arial", Font.PLAIN, 14));
        prenom_input.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        form.add(prenom_input, gbc);

        // Ajouter l'input et label Date Naissance
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel naiss = new JLabel("Date Naissance (yyyy-MM-dd)");
        naiss.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(naiss, gbc);

        gbc.gridx = 1;
        // Créer un DateFormatter pour le format yyyy-MM-dd
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        naiss_input = new JFormattedTextField(format); // Modifier cette ligne
        naiss_input.setColumns(15);
        naiss_input.setFont(new Font("Arial", Font.PLAIN, 14));
        naiss_input.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        form.add(naiss_input, gbc);

        // Ajouter l'input et label Sexe
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel sexe = new JLabel("Sexe");
        sexe.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(sexe, gbc);

        gbc.gridx = 1;
        homme = new JRadioButton("Homme");
        femme = new JRadioButton("Femme");
        ButtonGroup bg = new ButtonGroup();
        bg.add(homme);
        bg.add(femme);
        JPanel sexPanel = new JPanel();
        sexPanel.setBackground(Color.WHITE);
        sexPanel.add(homme);
        sexPanel.add(femme);
        form.add(sexPanel, gbc);

        // Ajout de la filière
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel filiere = new JLabel("Filière");
        filiere.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(filiere, gbc);

        gbc.gridx = 1;
        java.util.List<Filiere> filieres = filiereService.listerFilieres();

        if (filieres != null && !filieres.isEmpty()) {
            filiereBox = new JComboBox<>(); // Modifier cette ligne
            for (Filiere f : filieres) {
                filiereBox.addItem(f.getLibelle());
            }
            filiereBox.setFont(new Font("Arial", Font.PLAIN, 14));
            form.add(filiereBox, gbc);
        } else {
            JLabel noFiliereLabel = new JLabel("Aucune filière disponible");
            form.add(noFiliereLabel, gbc);
        }

        // Ajout du Niveau
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel niveau = new JLabel("Niveau");
        niveau.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(niveau, gbc);

        gbc.gridx = 1;
        Integer niveaux[] = { 1, 2, 3 };
        niv_box = new JComboBox<>(niveaux); // Modifier cette ligne
        niv_box.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(niv_box, gbc);

        // Ajouter le formulaire au panneau gauche
        panelLeft.add(form, BorderLayout.CENTER);

        // Création des boutons avec un style moderne
        JButton ajouter = new JButton("Ajouter");
        ajouter.setBackground(new Color(2, 136, 85));
        JButton supprimer = new JButton("Supprimer");
        supprimer.setBackground(new Color(136, 2, 24));
        JButton modifier = new JButton("Modifier");
        modifier.setBackground(new Color(20, 4, 88));

        // Ajouter des bordures et des couleurs aux boutons
        JButton[] buttons = { ajouter, supprimer, modifier };
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setFocusPainted(false);
        }

        // Panneau des boutons
        JPanel btnGrp = new JPanel();
        btnGrp.setBackground(new Color(238, 238, 238));
        btnGrp.add(ajouter);
        btnGrp.add(supprimer);
        btnGrp.add(modifier);
        panelLeft.add(btnGrp, BorderLayout.SOUTH);

        // les actionners
        ajouter.addActionListener(e -> ajouterEtudiant());

        return panelLeft;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Créer un modèle de tableau par défaut pour gérer les données dynamiquement
        String column[] = { "INE", "Prénom", "Nom", "Date Naissance", "Sexe", "Filière", "Niveau" };
        DefaultTableModel model = new DefaultTableModel(etudiantService.listerEtudiantsOnTable(), column);
        JTable t = new JTable(model);
        t.setBackground(new Color(255, 255, 255));
        JScrollPane sp = new JScrollPane(t);

        // Ajouter le JScrollPane au panneau droit
        panel.add(sp, BorderLayout.CENTER);

        return panel;
    }

    // Méthode pour ajouter un étudiant
    // public void ajouterEtudiant() {
    //     String ine = ine_input.getText();
    //     String nom = nom_input.getText();
    //     String prenom = prenom_input.getText();
    //     Sexe sexe = homme.isSelected() ? Sexe.MASCULIN : Sexe.FEMININ;
    //     String naiss = naiss_input.getText().trim(); // Récupérer la date sous forme de String
    //     LocalDate dateNaiss = null;

    //     try {
    //         // Convertir la chaîne en LocalDate en spécifiant le format
    //         dateNaiss = LocalDate.parse(naiss);
    //     } catch (DateTimeParseException e) {
    //         // Gérer l'erreur si la date est invalide
    //         JOptionPane.showMessageDialog(this, "Date invalide. Veuillez entrer la date au format yyyy-MM-dd.");
    //     }
    //     Filiere filiere = filiereService.findByLibelle((String) filiereBox.getSelectedItem());
    //     Integer niveau = (Integer) niv_box.getSelectedItem();

    //     if (ine.isEmpty() || nom.isEmpty() || prenom.isEmpty() || naiss.isEmpty()) {
    //         JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur",
    //                 JOptionPane.ERROR_MESSAGE);
    //     } else {
    //         Etudiant etudiant = new Etudiant();
    //         etudiant.setIne(ine);
    //         etudiant.setPrenom(prenom);
    //         etudiant.setNom(nom);
    //         etudiant.setNiveau(niveau);
    //         etudiant.setSexe(sexe);
    //         etudiant.setFiliere(filiere);
    //         etudiant.setDateNaiss(dateNaiss);
    //         etudiantService.ajouterEtudiant(etudiant);

    //         JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès!", "Succès",
    //                 JOptionPane.INFORMATION_MESSAGE);
    //     }
    // }

    public void ajouterEtudiant() {
        String ine = ine_input.getText();
        String nom = nom_input.getText();
        String prenom = prenom_input.getText();
        Sexe sexe = homme.isSelected() ? Sexe.MASCULIN : Sexe.FEMININ;
        String naiss = naiss_input.getText().trim(); // Récupérer la date sous forme de String
        LocalDate dateNaiss = null;
    
        try {
            // Convertir la chaîne en LocalDate en spécifiant le format
            dateNaiss = LocalDate.parse(naiss);
        } catch (DateTimeParseException e) {
            // Gérer l'erreur si la date est invalide
            JOptionPane.showMessageDialog(this, "Date invalide. Veuillez entrer la date au format yyyy-MM-dd.");
            return;
        }
    
        Filiere filiere = filiereService.findByLibelle((String) filiereBox.getSelectedItem());
        Integer niveau = (Integer) niv_box.getSelectedItem();
    
        if (ine.isEmpty() || nom.isEmpty() || prenom.isEmpty() || naiss.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            Etudiant etudiant = new Etudiant();
            etudiant.setIne(ine);
            etudiant.setPrenom(prenom);
            etudiant.setNom(nom);
            etudiant.setNiveau(niveau);
            etudiant.setSexe(sexe);
            etudiant.setFiliere(filiere);
            etudiant.setDateNaiss(dateNaiss);
            
            // Ajouter l'étudiant via le service
            etudiantService.ajouterEtudiant(etudiant);
    
            // Mettre à jour le modèle du tableau avec les nouvelles données
            DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) ((JPanel) getContentPane().getComponent(1)).getComponent(0)).getViewport().getView()).getModel();
            model.addRow(new Object[] {
                etudiant.getIne(),
                etudiant.getPrenom(),
                etudiant.getNom(),
                etudiant.getDateNaiss().toString(),
                etudiant.getSexe().toString(),
                etudiant.getFiliere().getLibelle(),
                etudiant.getNiveau()
            });
            reinitialiserFormulaire();
            JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès!", "Succès",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void reinitialiserFormulaire() {
        ine_input.setText("");
        nom_input.setText("");
        prenom_input.setText("");
        naiss_input.setText("");
        homme.setSelected(false);
        femme.setSelected(false);
        filiereBox.setSelectedIndex(0);
        niv_box.setSelectedIndex(0);
    }
    
    
}
