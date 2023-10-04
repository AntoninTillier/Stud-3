package com.aq.game.individu;

import java.awt.Graphics;
import java.util.ArrayList;

import com.aq.game.individu.comportement.collision.ComportementCollision;
import com.aq.game.individu.comportement.deplacement.*;
import com.aq.game.objet.Objet;

public abstract class Individu implements IndividuInterface {
    private int posX;
    private int posY;
    ArrayList<Objet> objet = new ArrayList<Objet>();
    ComportementDeplacement comportementDeplacement;

    public Individu() { }

    public void paint(Graphics g) { }

    public void seDeplacer() {
        comportementDeplacement.seDeplacer();
    }

    public void setComportementDeplacement(ComportementDeplacement cp) {
        comportementDeplacement = cp;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}