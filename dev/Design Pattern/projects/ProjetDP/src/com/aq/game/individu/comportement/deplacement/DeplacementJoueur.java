package com.aq.game.individu.comportement.deplacement;

import com.aq.Frame;

public class DeplacementJoueur implements ComportementDeplacement{

    public void seDeplacer() {
	Frame.joueur.collisionMur();
	Frame.joueur.collisionIndividu();
	if (Frame.kl.up)
	    Frame.joueur.setDirY(-1.0);
	if (Frame.kl.down)
	    Frame.joueur.setDirY(1.0);
	if (Frame.kl.left)
	    Frame.joueur.setDirX(-1.0);
	if (Frame.kl.right)
	    Frame.joueur.setDirX(1.0);
	if (Frame.joueur.getDirX() * Frame.joueur.getDirY() != 0) {
	    Frame.joueur.setDirX(Frame.joueur.getDirX() / Math.sqrt(2));
	    Frame.joueur.setDirY(Frame.joueur.getDirY()/ Math.sqrt(2));
	}
	Frame.joueur.setPosX((int) (Frame.joueur.getPosX() + Frame.joueur.getDirX() * 4));
	Frame.joueur.setPosY((int) (Frame.joueur.getPosY() + Frame.joueur.getDirY() * 4));
    }
}