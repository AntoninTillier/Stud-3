package com.aq.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.aq.Frame;

public class PanelMenu extends JPanel {
    BufferedImage image;

    public void paintComponent(Graphics g) {
        if (Frame.classement) {
            g.setColor(Color.lightGray);
            g.fillRect(550, 20, 190, 60);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("    Retour", 560, 60);
            g.drawString("Nom", 55, 40);
            g.drawString("Niveau", 300, 40);
            Graphics2D g2d = (Graphics2D) g;
            Stroke s = g2d.getStroke();
            g2d.setStroke(new BasicStroke(5));
            g.drawLine(55, 48, 415, 48);
            g2d.setStroke(s);
            for (int i = 0; i < Frame.nameClassement.length; i++) {
                if (Frame.nameClassement[i] != null) {
                    if (i % 2 == 0) {
                        g.setColor(new Color(3, 15, 66));
                        g.drawString(Frame.nameClassement[i], 55, 80 + 40 * i);
                    } else {
                        g.setColor(new Color(181, 23, 0));
                        g.drawString(Frame.nameClassement[i], 55, 80 + 40 * i);
                    }
                }
            }
            for (int i = 0; i < Frame.niveauxClassement.length; i++) {
                if (Frame.niveauxClassement[i] != null) {
                    if (i % 2 == 0) {
                        g.setColor(new Color(3, 15, 66));
                        g.drawString(Frame.niveauxClassement[i], 300, 80 + 40 * i);
                    } else {
                        g.setColor(new Color(181, 23, 0));
                        g.drawString(Frame.niveauxClassement[i], 300, 80 + 40 * i);
                    }
                }
            }
        } else if (Frame.propos) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 420, 150, 60);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("  Retour", 10, 460);
            g.setColor(new Color(181, 23, 0));
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Vous allez mourir", 160, 60);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(new Color(3, 15, 66));
            g.drawString("Les déplacements : haut : z  bas : s gauche : q  droite : d.", 130, 120);
            g.setColor(new Color(181, 23, 0));
            g.drawString("L'attaque : maintenez enfoncé le clic souris et déplacer la souris pour viser.", 20, 170);
            g.setColor(new Color(3, 15, 66));
            g.drawString("Les portes : pour traverser une porte il faut s'en approcher.", 130, 210);
            g.setFont(new Font("Arial", Font.BOLD, 19));
            g.setColor(new Color(181, 23, 0));
            g.drawString("Les objets : pour prendre un objet il faut être a côté et mettre la souris dessus,", 20, 250);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("il faut un drag&drop sur l'objet pour enlever un objet.", 130, 280);
            g.setColor(new Color(3, 15, 66));
            g.drawString("Pour trouver le passage secret appuyer sur R.", 130, 320);
            Graphics2D g2d = (Graphics2D) g;
            Stroke s = g2d.getStroke();
            g2d.setStroke(new BasicStroke(5));
            g.drawLine(55, 390, 715, 390);
            g2d.setStroke(s);
        } else {
            try {
                image = ImageIO.read(new File("img/menu.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            g.setColor(Color.lightGray);
            g.fillRect(0, 420, 150, 60);
            g.fillRect(550, 20, 190, 60);
            g.fillRect(300, 285, 150, 40);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("A propos", 10, 460);
            g.drawString("Classement", 560, 60);
            g.drawString("Jouer", 335, 315);
        }
    }
}