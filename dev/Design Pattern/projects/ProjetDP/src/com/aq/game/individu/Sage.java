package com.aq.game.individu;

import java.awt.Graphics;

import com.aq.game.individu.comportement.deplacement.*;

public class Sage extends Individu {
    public Sage(int posX, int posY) {
        comportementDeplacement = (ComportementDeplacement) new DeplacementSage();
    }

    @Override
    public void paint(Graphics g) { }

    @Override
    public void collisionMur() {
        // TODO Auto-generated method stub
    }

    @Override
    public void collisionIndividu() {
        // TODO Auto-generated method stub
    }
}