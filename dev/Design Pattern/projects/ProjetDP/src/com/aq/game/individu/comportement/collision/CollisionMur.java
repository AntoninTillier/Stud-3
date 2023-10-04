package com.aq.game.individu.comportement.collision;

import com.aq.Frame;

public class CollisionMur implements ComportementCollision {

    @Override
    public void collision() {
        if (Frame.joueur.getPosY() < 75)
            Frame.joueur.setPosY(Frame.joueur.getPosY() + 3);
        if (Frame.joueur.getPosY() > Frame.panelGame.getHeight() - 75)
            Frame.joueur.setPosY(Frame.joueur.getPosY() - 3);
        if (Frame.joueur.getPosX() < 75)
            Frame.joueur.setPosX(Frame.joueur.getPosX() + 3);
        if (Frame.joueur.getPosX() > Frame.panelGame.getWidth() - 75)
            Frame.joueur.setPosX(Frame.joueur.getPosX() - 3);
    }
}