package sn.m1.uasz.tp3;

import sn.m1.uasz.tp3.gui.MainFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Lancer l'interface graphique
           MainFrame fen = new MainFrame();
           fen.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}