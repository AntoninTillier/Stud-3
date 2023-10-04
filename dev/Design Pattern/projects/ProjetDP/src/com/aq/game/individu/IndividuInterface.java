package com.aq.game.individu;

import java.awt.Graphics;

import com.aq.game.individu.comportement.deplacement.*;
import com.aq.game.individu.comportement.collision.*;

public interface IndividuInterface {
    public void paint(Graphics g);

    public void seDeplacer();

    public void setComportementDeplacement(ComportementDeplacement cp);

    public void collisionMur();

    public void collisionIndividu();
}