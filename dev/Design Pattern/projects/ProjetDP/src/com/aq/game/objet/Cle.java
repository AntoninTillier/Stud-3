package com.aq.game.objet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aq.Frame;

public class Cle extends Objet {
    BufferedImage image;

    public Cle(int num, int posX, int posY) {
        this.num = num;
        try {
            if (num == 1)
                image = ImageIO.read(new File("img/cle1.png"));
            if (num == 2)
                image = ImageIO.read(new File("img/cle2.png"));
            if (num == 3)
                image = ImageIO.read(new File("img/cle3.png"));
            if (num == 4)
                image = ImageIO.read(new File("img/cle4.png"));
            if (num == 10)
                image = ImageIO.read(new File("img/cleB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPosX(posX);
        this.setPosY(posY);

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