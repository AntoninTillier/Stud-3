package com.aq.game.individu;

import java.awt.Color;
import java.awt.Graphics;

import com.aq.Frame;
import com.aq.game.individu.comportement.collision.CollisionIndividu;
import com.aq.game.individu.comportement.collision.CollisionMur;
import com.aq.game.individu.comportement.collision.ComportementCollision;
import com.aq.game.individu.comportement.deplacement.*;

public class Monstre extends Individu {
    private int pv = 50;

    public Monstre(int posX, int posY) {
        comportementDeplacement = (ComportementDeplacement) new DeplacementMonstre();
    }

    @Override
    public void collisionMur() {
        if (this.getPosY() < 75)
            this.setPosY(this.getPosY() + 3);
        ;
        if (this.getPosY() > Frame.panelGame.getHeight() - 75)
            this.setPosY(this.getPosY() - 3);
        if (this.getPosX() < 75)
            this.setPosX(this.getPosX() + 3);
        if (this.getPosX() > Frame.panelGame.getWidth() - 75)
            this.setPosX(this.getPosX() - 3);
    }

    @Override
    public void collisionIndividu() {
        for (int i = 0; i < Frame.labyrinthe.getLab().get(Frame.i).getIndividu().size(); i++) {
            Individu ind = Frame.labyrinthe.getLab().get(Frame.i).getIndividu().get(i);
            if (this.getPosX() > ind.getPosX() && this.getPosX() < ind.getPosX() + 100 && this.getPosY() > ind.getPosY()
                    && this.getPosY() < ind.getPosY() + 100) {
                if (this.getPosY() < ind.getPosY())
                    this.setPosY(this.getPosY() + 4);
                if (this.getPosY() > ind.getPosY())
                    this.setPosY(this.getPosY() - 4);
                if (this.getPosX() < ind.getPosX())
                    this.setPosX(this.getPosX() + 4);
                if (this.getPosX() > ind.getPosX())
                    this.setPosX(this.getPosX() - 4);
            }
        }
    }

    public void colisiondeMonstre(Monstre e) {
        if (Math.pow((this.getPosX() - e.getPosX()), 2) + Math.pow((-this.getPosY() + e.getPosY()), 2) <= (this.pv * e.pv * 15)) {
            if (this.getPosX() > e.getPosX()) {
                this.setPosX(this.getPosX() + 2);
                e.setPosX(e.getPosX() - 2);
            }
            if (this.getPosX() < e.getPosX()) {
                this.setPosX(this.getPosX() - 2);
                e.setPosX(e.getPosX() + 2);
            }
            if (this.getPosY() > e.getPosY()) {
                this.setPosY(this.getPosY() + 2);
                e.setPosY(e.getPosY() - 2);
            }
            if (this.getPosY() < e.getPosY()) {
                this.setPosY(this.getPosY() - 2);
                e.setPosY(e.getPosY() + 2);
            }
        }
    }

    public void attaque() {
        double a = Frame.joueur.getPosX() - this.getPosX();
        double b = Frame.joueur.getPosY() - this.getPosY();
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        if (l >= 0 && l <= 40)
            Frame.joueur.setPv(Frame.joueur.getPv() - 6);
    }

    @Override
    public void paint(Graphics g) {
        Frame.balle.animation();
        Frame.balle.paint_balle(g);
        g.setColor(new Color(1, 113, 1));
        g.fillRect(this.getPosX() + ((50 - getPv()) / 2) - 25, this.getPosY() + ((50 - getPv()) / 2) - 25, getPv(), getPv());
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
}