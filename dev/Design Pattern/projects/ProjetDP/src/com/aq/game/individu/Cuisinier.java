package com.aq.game.individu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aq.game.objet.Nourriture;

public class Cuisinier extends Individu {
    BufferedImage image;

    public Cuisinier(int posX, int posY) {
        this.setPosX(posX);
        this.setPosY(posY);
        try {
            image = ImageIO.read(new File("img/cuisinier.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objet.add(new Nourriture(2));
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