package com.aq.game.individu.comportement.deplacement;

import com.aq.Frame;
import com.aq.game.individu.Monstre;

public class DeplacementMonstre implements ComportementDeplacement {

    public void seDeplacer() {
        for (Monstre e : Frame.labyrinthe.getLab().get(Frame.i).getM()) {
            double a = Frame.joueur.getPosX() - e.getPosX();
            double b = Frame.joueur.getPosY() - e.getPosY();
            double l = Math.sqrt(a * a + b * b);
            a = a / l;
            b = b / l;
            if (l > 100) {
                e.collisionMur();
                e.collisionIndividu();
                e.setPosX((int) (e.getPosX() + a * 1.5));
                e.setPosY((int) (e.getPosY() + b * 1.5));
            } else {
                e.collisionMur();
                e.collisionIndividu();
                e.setPosX((int) (e.getPosX() - a * 1.5));
                e.setPosY((int) (e.getPosY() - b * 1.5));
            }
        }
    }
}