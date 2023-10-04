package com.aq.game.objet.decorator;

import java.awt.Graphics;

import com.aq.game.objet.Objet;

public abstract class Decorateur extends Objet {
    Objet objet;

    @Override
    public void paint(Graphics g) {
        objet.paint(g);
    }
}