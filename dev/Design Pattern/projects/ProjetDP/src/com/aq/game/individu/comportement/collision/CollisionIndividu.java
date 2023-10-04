package com.aq.game.individu.comportement.collision;

import com.aq.Frame;
import com.aq.game.individu.Individu;

public class CollisionIndividu implements ComportementCollision {

    public void collision() {
        for (int i = 0; i < Frame.labyrinthe.getLab().get(Frame.i).getIndividu().size(); i++) {
            Individu ind = Frame.labyrinthe.getLab().get(Frame.i).getIndividu().get(i);
            if (Frame.joueur.getPosX() > ind.getPosX() && Frame.joueur.getPosX() < ind.getPosX() + 100 && Frame.joueur.getPosY() > ind.getPosY()
                    && Frame.joueur.getPosY() < ind.getPosY() + 100) {
                if (Frame.joueur.getPosY() < ind.getPosY())
                    Frame.joueur.setPosY(Frame.joueur.getPosY() + 4);
                if (Frame.joueur.getPosY() > ind.getPosY())
                    Frame.joueur.setPosY(Frame.joueur.getPosY() - 4);
                if (Frame.joueur.getPosX() < ind.getPosX())
                    Frame.joueur.setPosX(Frame.joueur.getPosX() + 4);
                if (Frame.joueur.getPosX() > ind.getPosX())
                    Frame.joueur.setPosX(Frame.joueur.getPosX() - 4);
            }
        }
    }
}