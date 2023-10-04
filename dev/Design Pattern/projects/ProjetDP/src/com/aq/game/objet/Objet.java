package com.aq.game.objet;

import java.awt.Graphics;

public abstract class Objet {
    private int posX;
    private int posY;
    int num;
    int pdv;
    int upDmg;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getNum() {
        return num;
    }

    public int getPdv() {
        return pdv;
    }

    public int getUpDmg() {
        return upDmg;
    }

    public abstract void paint(Graphics g);

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}