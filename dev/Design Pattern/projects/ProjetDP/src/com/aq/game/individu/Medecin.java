package com.aq.game.individu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aq.game.objet.Medicament;

public class Medecin extends Individu {
    BufferedImage image;

    public Medecin(int posX, int posY) {
        this.setPosX(posX);
        this.setPosY(posY);
        try {
            image = ImageIO.read(new File("img/medecin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objet.add(new Medicament(25));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.getPosX(), this.getPosY(), 100, 100, null);
    }

    @Override
    public void collisionMur() {
        // TODO Auto-generated method stub
    }

    @Override
    public void collisionIndividu() {
        // TODO Auto-generated method stub
    }
}