package com.aq.game.objet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aq.Frame;

public class Medicament extends Objet {
    BufferedImage image;

    public Medicament(int pdv, int posX, int posY) {
        this.pdv = pdv;
        try {
            image = ImageIO.read(new File("img/medicament.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPosX(posX);
        this.setPosY(posY);
    }

    public Medicament(int pdv) {
        this.pdv = pdv;
        try {
            image = ImageIO.read(new File("img/medicament.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.getPosX(), this.getPosY(), 35, 35, null);
        if (Frame.ml.x > this.getPosX() && Frame.ml.x < this.getPosX() + 35 && Frame.ml.y > this.getPosY()
                && Frame.ml.y < this.getPosY() + 35) {
            g.setColor(Color.lightGray);
            g.drawRect(getPosX(), getPosY(), 35, 35);
        }
    }
}