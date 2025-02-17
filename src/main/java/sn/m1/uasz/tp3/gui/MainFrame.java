package sn.m1.uasz.tp3.gui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws Exception {
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
    }

    private JPanel createLeftPanel() {
        // Panneau gauche
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setPreferredSize(new Dimension(600, 0));
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
        JTextField ine_input = new JTextField(15);
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
        JTextField nom_input = new JTextField(15);
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
        JTextField prenom_input = new JTextField(15);
        prenom_input.setFont(new Font("Arial", Font.PLAIN, 14));
        prenom_input.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        form.add(prenom_input, gbc);

        // Ajouter l'input et label Date Naissance
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel naiss = new JLabel("Date Naissance");
        naiss.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(naiss, gbc);

        gbc.gridx = 1;
        JTextField naiss_input = new JTextField(15);
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
        JRadioButton homme = new JRadioButton("Homme");
        JRadioButton femme = new JRadioButton("Femme");
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
        String filieres[] = {"MPI", "L2I", "ECO-GES", "GEO"};
        JComboBox<String> filiere_box = new JComboBox<>(filieres);
        filiere_box.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(filiere_box, gbc);

        // Ajout du Niveau
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel niveau = new JLabel("Niveau");
        niveau.setFont(new Font("Arial", Font.PLAIN, 14));
        form.add(niveau, gbc);

        gbc.gridx = 1;
        Integer niveaux[] = {1, 2, 3};
        JComboBox<Integer> niv_box = new JComboBox<>(niveaux);
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
        JButton[] buttons = {ajouter, supprimer, modifier};
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

        return panelLeft;
    }
}
