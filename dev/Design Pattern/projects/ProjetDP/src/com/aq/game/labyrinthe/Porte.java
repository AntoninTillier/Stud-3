package com.aq.game.labyrinthe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aq.Frame;

public class Porte {
    private boolean fermer;
    char direction;
    private int num;
    private int posX;
    private int posY;

    public Porte(boolean fermer, char direction) {
        this.setFermer(fermer);
        this.direction = direction;
        if (this.direction == 'n')
            setNum(1);
        if (this.direction == 's')
            setNum(2);
        if (this.direction == 'o')
            setNum(3);
        if (this.direction == 'e')
            setNum(4);
        if (this.direction == 'b')
            setNum(10);
    }

    public void paint_porte(Graphics g) {
        g.setColor(Color.lightGray);
        if (direction == 'n') {
            setPosX(Frame.panelGame.getWidth() - 675);
            setPosY(35);
            if (isFermer()) {
                g.setColor(Color.blue);
                g.fillRect(getPosX(), getPosY(), 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("img/cle1.png"));
                    g.drawImage(image, getPosX(), getPosY(), 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(getPosX(), getPosY(), 100, 25);
        }
        if (direction == 's') {
            setPosX(Frame.panelGame.getWidth() - 675);
            setPosY(Frame.panelGame.getHeight() - 60);
            if (isFermer()) {
                g.setColor(Color.blue);
                g.fillRect(getPosX(), getPosY(), 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("img/cle2.png"));
                    g.drawImage(image, getPosX(), getPosY(), 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(getPosX(), getPosY(), 100, 25);
        }
        if (direction == 'o') {
            setPosX(35);
            setPosY(Frame.panelGame.getHeight() - 400);
            if (isFermer()) {
                g.setColor(Color.blue);
                g.fillRect(getPosX(), getPosY(), 25, 100);
                try {
                    BufferedImage image = ImageIO.read(new File("img/cle3.png"));
                    g.drawImage(image, getPosX(), getPosY(), 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(getPosX(), getPosY(), 25, 100);

        }
        if (direction == 'e') {
            setPosX(Frame.panelGame.getWidth() - 65);
            setPosY(Frame.panelGame.getHeight() - 400);
            if (isFermer()) {
                g.setColor(Color.blue);
                g.fillRect(getPosX(), getPosY(), 25, 100);
                try {
                    BufferedImage image = ImageIO.read(new File("img/cle4.png"));
                    g.drawImage(image, getPosX(), getPosY(), 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(getPosX(), getPosY(), 25, 100);
        }
        if (direction == 'b') {
            setPosX((Frame.panelGame.getWidth() - 100) / 2);
            setPosY((Frame.panelGame.getHeight() - 100) / 2);
            if (isFermer()) {
                g.setColor(new Color(255, 215, 0));
                g.fillRect(getPosX(), getPosY(), 100, 25);
                try {
                    BufferedImage image = ImageIO.read(new File("img/cleB.png"));
                    g.drawImage(image, getPosX(), getPosY(), 35, 35, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                g.fillRect(getPosX(), getPosY(), 100, 25);
        }
    }

    public boolean isFermer() {
        return fermer;
    }

    public void setFermer(boolean fermer) {
        this.fermer = fermer;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}