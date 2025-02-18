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

    /**
 * Classe MainFrame représentant l'interface principale de l'application de gestion 
 * du club étudiant.
 * 
 * Cette fenêtre inclut :
 * - Un formulaire d'inscription des étudiants sur le panneau gauche.
 * - Une table affichant la liste des étudiants sur le panneau droit.
 * 
 * @author [eadarak00]
 * @version 1.0
 * @since [18.02.2025]
 */
public class MainFrame extends JFrame {
    private FiliereService filiereService;
    private EtudiantService etudiantService;
    private JTextField id_input;
    private JTextField ine_input;
    private JTextField nom_input;
    private JTextField prenom_input;
    private JFormattedTextField naiss_input;
    private JRadioButton homme;
    private JRadioButton femme;
    private JComboBox<String> filiereBox;
    private JComboBox<Integer> niv_box;
    private JTable etudiantTable;


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

    /**
 * Crée et configure le panneau gauche de l'interface utilisateur.
 * <p>
 * Ce panneau contient un formulaire permettant l'ajout, la modification et la suppression 
 * d'étudiants. Il comprend plusieurs champs d'entrée, des boutons d'action et une 
 * interface utilisateur bien organisée.
 * </p>
 *
 * <h3>Composants du panneau :</h3>
 * <ul>
 *   <li>Un titre : "Formulaire d'inscription" en haut du panneau.</li>
 *   <li>Un formulaire structuré avec des champs pour :
 *     <ul>
 *       <li>INE</li>
 *       <li>Nom</li>
 *       <li>Prénom</li>
 *       <li>Date de naissance (au format yyyy-MM-dd)</li>
 *       <li>Sexe (Homme / Femme)</li>
 *       <li>Filière (liste déroulante alimentée dynamiquement)</li>
 *       <li>Niveau (1, 2 ou 3)</li>
 *     </ul>
 *   </li>
 *   <li>Un panneau de boutons contenant :
 *     <ul>
 *       <li>Un bouton "Ajouter" pour enregistrer un nouvel étudiant.</li>
 *       <li>Un bouton "Supprimer" pour retirer un étudiant sélectionné.</li>
 *       <li>Un bouton "Modifier" pour mettre à jour les informations d'un étudiant existant.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h3>Fonctionnalités :</h3>
 * <ul>
 *   <li>Utilisation d'un {@code GridBagLayout} pour organiser les champs du formulaire.</li>
 *   <li>Utilisation d'un {@code ButtonGroup} pour gérer la sélection du sexe.</li>
 *   <li>Affichage dynamique des filières à partir du service {@code filiereService}.</li>
 *   <li>Gestion des actions sur les boutons (ajout, suppression et modification).</li>
 * </ul>
 *
 * @return Un {@code JPanel} contenant le formulaire d'inscription des étudiants.
 */
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

        // id_input.setVisible(false);
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

        gbc.gridx = 1;
        gbc.gridy = 7;
        id_input = new JTextField(15);
        id_input.setVisible(false);
        form.add(id_input, gbc);

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
        supprimer.addActionListener(e -> supprimerEtudiant());
        modifier.addActionListener(e -> modifierEtudiant());

        return panelLeft;
    }

    /**
     * Crée et configure le panneau droit de l'interface utilisateur.
     * <p>
     * Ce panneau contient un tableau affichant la liste des étudiants avec leurs
     * informations.
     * Les étudiants sont récupérés depuis le service et affichés sous forme de
     * tableau.
     * Un {@code JScrollPane} est utilisé pour permettre le défilement lorsque les
     * données sont nombreuses.
     * </p>
     *
     * <h3>Composants du panneau :</h3>
     * <ul>
     * <li>Un {@code JTable} affichant la liste des étudiants avec les colonnes
     * suivantes :
     * <ul>
     * <li># (Identifiant de l'étudiant)</li>
     * <li>INE (Numéro d'identification de l'étudiant)</li>
     * <li>Prénom</li>
     * <li>Nom</li>
     * <li>Date de naissance</li>
     * <li>Sexe</li>
     * <li>Filière</li>
     * <li>Niveau</li>
     * </ul>
     * </li>
     * <li>Un {@code JScrollPane} permettant de gérer le défilement des données du
     * tableau.</li>
     * </ul>
     *
     * <h3>Fonctionnalités :</h3>
     * <ul>
     * <li>Affichage dynamique des étudiants en utilisant un
     * {@code DefaultTableModel}.</li>
     * <li>Déclenchement automatique du remplissage du formulaire lorsque
     * l'utilisateur clique sur une ligne du tableau.</li>
     * </ul>
     *
     * @return Un {@code JPanel} contenant le tableau des étudiants.
     */
    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Créer un modèle de tableau par défaut pour gérer les données dynamiquement
        String column[] = { "#", "INE", "Prénom", "Nom", "Date Naissance", "Sexe", "Filière", "Niveau" };
        DefaultTableModel model = new DefaultTableModel(etudiantService.listerEtudiantsOnTable(), column);
        etudiantTable = new JTable(model);
        etudiantTable.setBackground(new Color(255, 255, 255));
        JScrollPane sp = new JScrollPane(etudiantTable);

        // Ajouter le JScrollPane au panneau droit
        panel.add(sp, BorderLayout.CENTER);

        etudiantTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remplirFormulaireDepuisTableau();
            }
        });

        return panel;
    }

    /**
     * Ajoute un nouvel étudiant à la base de données ou à la liste d'étudiants.
     * <p>
     * Cette méthode récupère les informations saisies dans le formulaire,
     * les valide, puis crée un nouvel objet {@code Etudiant} avant de l'ajouter via
     * le service correspondant. Une fois l'ajout effectué, elle met à jour
     * l'affichage
     * et réinitialise le formulaire.
     * </p>
     *
     * <h3>Étapes du processus :</h3>
     * <ol>
     * <li>Récupération et validation des champs du formulaire.</li>
     * <li>Conversion de la date de naissance en {@code LocalDate}.</li>
     * <li>Vérification des champs obligatoires.</li>
     * <li>Création et initialisation d'un objet {@code Etudiant}.</li>
     * <li>Ajout de l'étudiant via
     * {@code etudiantService.ajouterEtudiant(etudiant)}.</li>
     * <li>Rafraîchissement du tableau des étudiants.</li>
     * <li>Réinitialisation du formulaire.</li>
     * <li>Affichage d'un message de confirmation.</li>
     * </ol>
     *
     * <h3>Gestion des erreurs :</h3>
     * <ul>
     * <li>Si la date de naissance est invalide, une boîte de dialogue affiche un
     * message d'erreur.</li>
     * <li>Si un champ obligatoire est vide, un message d'erreur est affiché.</li>
     * </ul>
     */
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

            rafraichirTableau();
            reinitialiserFormulaire();

            JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès!", "Succès",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Réinitialise le formulaire de saisie des étudiants.
     * <p>
     * Cette méthode vide les champs de saisie du formulaire et réinitialise
     * les sélections aux valeurs par défaut. Elle effectue les actions suivantes :
     * </p>
     * <ul>
     * <li>Efface le contenu des champs de texte (INE, nom, prénom, date de
     * naissance).</li>
     * <li>Déselectionne les boutons radio pour le sexe.</li>
     * <li>Réinitialise la sélection des comboBox (filière et niveau) à leur premier
     * élément.</li>
     * </ul>
     */
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

    /**
     * Remplit le formulaire avec les données de l'étudiant sélectionné dans le
     * tableau.
     * <p>
     * Cette méthode récupère les informations de l'étudiant sélectionné dans le
     * tableau
     * et les affecte aux champs correspondants du formulaire. Elle met à jour :
     * </p>
     * <ul>
     * <li>L'ID de l'étudiant</li>
     * <li>L'INE</li>
     * <li>Le prénom</li>
     * <li>Le nom</li>
     * <li>La date de naissance</li>
     * <li>Le sexe (en sélectionnant le bouton radio correspondant)</li>
     * <li>La filière (en mettant à jour la comboBox)</li>
     * <li>Le niveau (en mettant à jour la comboBox du niveau)</li>
     * </ul>
     * <p>
     * Si aucune ligne n'est sélectionnée, la méthode ne fait rien.
     * </p>
     */
    private void remplirFormulaireDepuisTableau() {
        int selectedRow = etudiantTable.getSelectedRow();
        if (selectedRow >= 0) {
            id_input.setText(etudiantTable.getValueAt(selectedRow, 0).toString());
            ine_input.setText(etudiantTable.getValueAt(selectedRow, 1).toString());
            prenom_input.setText(etudiantTable.getValueAt(selectedRow, 2).toString());
            nom_input.setText(etudiantTable.getValueAt(selectedRow, 3).toString());
            naiss_input.setText(etudiantTable.getValueAt(selectedRow, 4).toString());

            // Gestion du sexe
            String sexe = etudiantTable.getValueAt(selectedRow, 5).toString();
            if (sexe.equalsIgnoreCase("MASCULIN")) {
                homme.setSelected(true);
            } else {
                femme.setSelected(true);
            }

            // Sélection de la filière
            String filiere = etudiantTable.getValueAt(selectedRow, 6).toString();
            filiereBox.setSelectedItem(filiere);

            // Sélection du niveau
            int niveau = Integer.parseInt(etudiantTable.getValueAt(selectedRow, 7).toString());
            niv_box.setSelectedItem(niveau);
        }
    }

    /**
     * Supprime l'étudiant sélectionné dans le tableau après confirmation.
     * <p>
     * Cette méthode vérifie si une ligne d'étudiant a été sélectionnée dans le
     * tableau. Si c'est le cas,
     * elle récupère les informations de l'étudiant (ID, INE, prénom et nom), puis
     * affiche une boîte de
     * dialogue pour demander à l'utilisateur de confirmer la suppression de
     * l'étudiant.
     * Si l'utilisateur confirme, l'étudiant est supprimé de la base de données via
     * la méthode
     * `etudiantService.supprimer()`. Le tableau est ensuite mis à jour et le
     * formulaire réinitialisé.
     * </p>
     * <p>
     * Si aucune ligne n'est sélectionnée, un message d'erreur est affiché pour
     * inviter l'utilisateur à
     * sélectionner un étudiant avant de procéder à la suppression.
     * </p>
     */
    private void supprimerEtudiant() {
        int selectedRow = etudiantTable.getSelectedRow();

        if (selectedRow >= 0) {
            // Récupération de l'INE de l'étudiant sélectionné
            String idString = etudiantTable.getValueAt(selectedRow, 0).toString();
            int id = Integer.parseInt(idString);
            String ine = etudiantTable.getValueAt(selectedRow, 1).toString();
            String prenom = etudiantTable.getValueAt(selectedRow, 2).toString();
            String nom = etudiantTable.getValueAt(selectedRow, 3).toString();

            String message = String.format("Voulez-vous vraiment supprimer cet étudiant %d [ (%s) %s - %s ]?", id, ine,
                    prenom, nom);
            // Confirmation de suppression
            int confirm = JOptionPane.showConfirmDialog(this, message,
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Suppression dans la base de données ou la liste des étudiants
                etudiantService.supprimer(id);

                // Mise à jour du tableau
                rafraichirTableau();

                // Réinitialisation du formulaire
                reinitialiserFormulaire();

                JOptionPane.showMessageDialog(this, "Étudiant supprimé avec succès.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à supprimer.");
        }
    }

    /**
     * Rafraîchit le tableau des étudiants avec les données actuelles.
     * <p>
     * Cette méthode récupère les informations les plus récentes des étudiants en
     * appelant la méthode
     * `listerEtudiantsOnTable()` du service `etudiantService`. Ensuite, elle met à
     * jour le modèle du
     * tableau pour refléter ces données.
     * </p>
     * <p>
     * Le tableau est composé de colonnes pour afficher l'ID, l'INE, le prénom, le
     * nom, la date de naissance,
     * le sexe, la filière, et le niveau de chaque étudiant.
     * </p>
     */
    private void rafraichirTableau() {
        String data[][] = etudiantService.listerEtudiantsOnTable();
        String column[] = { "#", "INE", "Prénom", "Nom", "Date Naissance", "Sexe", "Filière", "Niveau" };

        DefaultTableModel model = new DefaultTableModel(data, column);
        etudiantTable.setModel(model);
    }

    /**
     * Permet de modifier les informations d'un étudiant sélectionné dans le
     * tableau.
     * Cette méthode récupère les données du formulaire, effectue une validation, et
     * si tout est valide,
     * elle modifie les informations de l'étudiant dans la base de données.
     * <p>
     * Si un étudiant est sélectionné dans le tableau, un message de confirmation
     * est affiché pour
     * valider l'action de modification. Si l'utilisateur confirme, l'étudiant est
     * modifié dans la base
     * de données et le tableau est mis à jour.
     * </p>
     * <p>
     * Si aucun étudiant n'est sélectionné, un message d'erreur est affiché.
     * </p>
     */
    public void modifierEtudiant() {
        int selectedRow = etudiantTable.getSelectedRow();
        if (selectedRow >= 0) {
            String idString = etudiantTable.getValueAt(selectedRow, 0).toString();
            int id = Integer.parseInt(idString);

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

                // Message de confirmation pour la modification
                String message = String.format("Voulez-vous vraiment modifier cet étudiant %d ?", id);
                // Confirmation de modification
                int confirm = JOptionPane.showConfirmDialog(this, message,
                        "Confirmation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Modification dans la base de données ou la liste des étudiants
                    etudiantService.modifier(id, etudiant);

                    // Mise à jour du tableau
                    rafraichirTableau();

                    // Réinitialisation du formulaire
                    reinitialiserFormulaire();

                    JOptionPane.showMessageDialog(this, "Étudiant modifié avec succès.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à modifier.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
