package com.aq.game.objet.decorator;

import java.awt.Graphics;

import com.aq.game.objet.Objet;

public class Paillette extends Decorateur {
    public Paillette(Objet objet) {
        this.objet = objet;
    }

    public void paint(Graphics g) {
        g.drawString("Paillette", this.objet.getPosX() + 20, this.getPosY() + 20);
    }
}