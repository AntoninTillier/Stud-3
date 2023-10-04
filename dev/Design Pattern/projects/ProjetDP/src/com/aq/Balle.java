package com.aq;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aq.game.individu.Monstre;

public class Balle {
    int pNum = Frame.i;
    public double posBX;
    public double posBY;
    public double dirX;
    public double dirY;
    public double vitesse;
    protected int cadence = 40;
    Monstre e;
    int c;
    ArrayList<Balle> balle = new ArrayList<Balle>();

    public Balle() { }

    public Balle(Monstre e, double posX, double posY, double posJX, double posJY) {
        double a = posJX - posX;
        double b = posJY - posY;
        double l = Math.sqrt(a * a + b * b);
        a = a / l;
        b = b / l;
        posBX = posX + a * 25;
        posBY = posY + b * 25;
        dirX = a;
        dirY = b;
        vitesse = 4;
        this.e = e;
    }

    public void deplacement() {
        posBX += dirX * vitesse;
        posBY += dirY * vitesse;
    }

    public void animation() {
        for (int i = 0; i < balle.size(); i++) {
            Balle b = balle.get(i);
            String s = b.e.getClass().toString();
            if (b.posBX < 50 || b.posBX > Frame.panelGame.getWidth() - 50)
                balle.remove(b);
            if (b.posBY < 50 || b.posBY > Frame.panelGame.getHeight() - 50)
                balle.remove(b);
            if (pNum != Frame.i) {
                balle.removeAll(balle);
                pNum = Frame.i;
            }
            if (b.posBX < Frame.joueur.getPosX() + 25 && b.posBX > Frame.joueur.getPosX() - 25 && b.posBY < Frame.joueur.getPosY() + 25
                    && b.posBY > Frame.joueur.getPosY() - 25) {
                balle.remove(b);
                if (s.equals("class Boss"))
                    Frame.joueur.setPv(Frame.joueur.getPv() - 4);
                else
                    Frame.joueur.setPv(Frame.joueur.getPv() - 2);
            }
            b.deplacement();
        }
        if (c == cadence) {
            c = 0;
            if (Frame.joueur.getDirX() != 0 || Frame.joueur.getDirY() != 0) {
                double angle1 = Math.acos(Frame.joueur.getDirX());
                if (Frame.joueur.getDirY() < 0)
                    angle1 = -angle1;
                for (Monstre e : Frame.labyrinthe.getLab().get(Frame.i).getM()) {
                    String s = e.getClass().toString();

                    double angle2 = getAngle(Frame.joueur.getPosX(), Frame.joueur.getPosY(), e.getPosX(), e.getPosY());
                    double angle_predi = 2 * angle2 - angle1 + Math.PI;
                    double a = e.getPosX() + Math.cos(angle_predi) * 10;
                    double b = e.getPosY() + Math.sin(angle_predi) * 10;
                    if (angle_predi - Math.PI < Math.PI / 2 && angle_predi - Math.PI > -Math.PI / 2)
                        balle.add(new Balle(e, e.getPosX(), e.getPosY(), a, b));
                    else
                        balle.add(new Balle(e, e.getPosX(), e.getPosY(), Frame.joueur.getPosX(), Frame.joueur.getPosY()));
                }
            } else {
                for (int j = 0; j < Frame.labyrinthe.getLab().get(Frame.i).getM().size(); j++) {
                    Monstre e = Frame.labyrinthe.getLab().get(Frame.i).getM().get(j);
                    String s = e.getClass().toString();
                    if (s.equals("class MiniBoss"))
                        cadence = 0;
                    if (s.equals("class Boss"))
                        cadence = 10;
                    balle.add(new Balle(e, e.getPosX(), e.getPosY(), Frame.joueur.getPosX(), Frame.joueur.getPosY()));
                }
            }
        } else
            c++;
    }

    public double getAngle(double eX, double eY, double jX, double jY) {
        double a = jX - eX;
        double b = jY - eY;
        double l = Math.sqrt(a * a + b * b);
        double rad = Math.acos(a / l);
        if (b < 0)
            rad = -rad;
        return rad;
    }

    public void paint_balle(Graphics g) {
        g.setColor(Color.green);
        for (Balle b : balle)
            g.fillOval((int) (b.posBX), (int) (b.posBY), 20, 20);
    }
}