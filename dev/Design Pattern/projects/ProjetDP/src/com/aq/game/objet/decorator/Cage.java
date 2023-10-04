package com.aq.game.objet.decorator;

import java.awt.Graphics;

import com.aq.game.objet.Objet;

public class Cage extends Decorateur {
    public Cage(Objet objet) {
        this.objet = objet;
    }

    public void paint(Graphics g) {
        g.drawString("Cage", this.objet.getPosX() + 20, this.getPosY() + 20);
    }
}