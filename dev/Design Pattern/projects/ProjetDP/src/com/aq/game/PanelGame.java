package com.aq.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.aq.Frame;

public class PanelGame extends JPanel {

    public void paintComponent(Graphics g) {
        if (Frame.labyrinthe.getLab().size() > 0) {
            Frame.labyrinthe.getLab().get(Frame.i).paint(g);
        }
        Frame.joueur.paint(g);
    }
}